package com.bdlabz.fundoo.serviceImpliment;


import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bdlabz.fundoo.Dto.NoteDto;
import com.bdlabz.fundoo.entitymodel.Collaborator;
import com.bdlabz.fundoo.entitymodel.Label;
import com.bdlabz.fundoo.entitymodel.Notes;
import com.bdlabz.fundoo.entitymodel.TempNotes;
import com.bdlabz.fundoo.entitymodel.User;
import com.bdlabz.fundoo.repository.CollaboratorRepository;
import com.bdlabz.fundoo.repository.NotesRepository;
import com.bdlabz.fundoo.repository.TempNotesRepository;
import com.bdlabz.fundoo.repository.UserRepository;
import com.bdlabz.fundoo.service.NoteService;
import com.bdlabz.fundoo.util.Jwt;

@Service
public class NoteServiceImpliment implements NoteService{

	@Autowired
	TempNotesRepository temprep; 
	
	@Autowired
	CollaboratorRepository colrep;
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	Jwt jwt;
	
	Date date = new Date();
	
	@Autowired
	NotesRepository repos;
	
	@Override
	public boolean createNote(NoteDto dto, String token) {
		try {
			long mail = jwt.idDetails(token);
			User user = userrepo.findOneById(mail);
			if(user != null) {
				Notes note = new Notes();
				note.setTitle(dto.getTitle());
				note.setTake_a_note(dto.getTake_a_note());
				note.setColor(dto.getColor());
				note.setArchive(false);
				note.setPin(false);
				note.setTrash(false);
				note.setUser(user);
				note.setCreate_date(date);
				note.setUpdate_date(date);
  				repos.save(note);
				return true;
			}
			
		} catch (IllegalArgumentException | JWTCreationException  e) {	
			e.printStackTrace();
		} 
		
		return false;
	}

	@Override
	public int updatePin(@Valid String token, long id) {
		try {
			long mail = jwt.idDetails(token);
			User user = userrepo.findOneById(mail);
			Notes note = repos.findByid(id);
			if(user != null && note != null) {
				if(note.isPin()) {
					note.setPin(false);
					repos.save(note);
				return 1;
				}
				else if( !note.isPin()) {
				note.setArchive(false);
				note.setTrash(false);
				note.setUpdate_date(date);
				note.setPin(true); 
				repos.save(note);
				return 0;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int updateArchive(String token, long id) {
		try {
			long mail = jwt.idDetails(token);
			User user = userrepo.findOneById(mail);
			Notes note = repos.findByid(id);
			if(user != null && note != null) {
				if(note.isArchive()) {
					note.setArchive(false);
					repos.save(note);
				return 1;
				}
				else if( !note.isArchive()) {
				note.setArchive(true);
				note.setTrash(false);
				note.setUpdate_date(date);
				note.setPin(false); 
				repos.save(note);
				return 0;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int updateTrash(String token, long id) {
		try {
			long mail = jwt.idDetails(token);
			User user = userrepo.findOneById(mail);
			Notes note = repos.findByid(id);
			if(user != null && note != null) {
				if(note.isTrash()) {
					note.setTrash(false);
					repos.save(note);
				return 1;
				}
				else if( !note.isTrash()) {
				note.setArchive(false);
				note.setTrash(true);
				note.setUpdate_date(date);
				note.setPin(false); 
				repos.save(note);
				return 0;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public boolean deleteNote(String token, long id) {
		try {
			long mail = jwt.idDetails(token);
			User user = userrepo.findOneById(mail);
			if(user != null) {
				Notes node = repos.findByid(id);
				repos.delete(node);
				return true;
			}
		} catch (JWTVerificationException | IllegalArgumentException  e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Notes getSingleNote(String token, long id) {
		
		try {
			long mail = jwt.idDetails(token);
			User user = userrepo.findOneById(mail);
			if(user != null) {
			Notes note = repos.findByid(id);
			return note;
			}
		} catch (JWTVerificationException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Notes> getAllNotes(String token) {
		try {
			long mail = jwt.idDetails(token);
			User user = userrepo.findOneById(mail);
			if(user != null) {
				List<Notes> note = repos.getAllNotes(user.getId());
				return note;
			}
		} catch (JWTVerificationException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Label> getAllLabel(String token, long noteid) {
		try {
			long mail = jwt.idDetails(token);
			User user = userrepo.findOneById(mail);
			Notes note = repos.findByid(noteid);
			if(user != null && note != null) {
				return note.getLabel();
			}
			
		} catch (JWTVerificationException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateNotes(NoteDto dto, String token, long noteid) {
		long id = jwt.idDetails(token);
		User user = userrepo.findOneById(id);
		if(user != null) {
			Notes note = repos.findByid(noteid);
			note.setUpdate_date(date);
			note.setTitle(dto.getTitle());
			note.setTake_a_note(dto.getTake_a_note());
			repos.save(note);
			return true; 
		}
		return false;
	}

	@Override
	public boolean updateColor(String token, long noteid, String color) {
		long id = jwt.idDetails(token);
		User user = userrepo.findOneById(id);
		Notes note = repos.findByid(noteid);
		if(user != null && note != null) {
			note.setColor(color);
        repos.save(note);
         return true;
		}
		return false;
	}

	@Override
	public boolean reminder(String time, String token, long noteid) {
		long id = jwt.idDetails(token);
		User user = userrepo.findOneById(id);
			Notes note= repos.findByid(noteid);
			if(user != null && note != null) {
				note.setReminder(time);
				repos.save(note);
				return true;
			}
		return false;
	}

	@Override
	public boolean emptyBin(String token) {
		long id = jwt.idDetails(token);
		User user = userrepo.findOneById(id);
		if(user != null) {
			repos.empty(id);
			return true;
		}
		return false;
	}

	@Override
	public List<Notes> getAllPinedNotes(String token) {
		long id = jwt.idDetails(token);
         User user = userrepo.findOneById(id);
		if(user != null) {
			List<Notes> notes = repos.getallpinned(id);
			return notes; 
		}
		
		return null;
	}

	@Override
	public List<Notes> getAllunPinnedNotes(String token) {
		long id = jwt.idDetails(token);
		User user = userrepo.findOneById(id);
		if(user != null) {
			List<Notes> notes = repos.getAllunPinnedNotes(id);
			return notes;
		}
		return null;
	}

	@Override
	public List<Notes> getAllTrashedNotes(String token) {
		long id = jwt.idDetails(token);
		User user = userrepo.findOneById(id);
		if(user != null) {
		List<Notes> notes =	repos.getAllTrashedNotes(id);
		return notes;
		}
		return null;
	}

	@Override
	public List<Notes> getAllArchiveNotes(String token) {
          long id = jwt.idDetails(token);
		User user = userrepo.findOneById(id);
		if(user != null) {
			List<Notes> notes = repos.getallArchivedNotes(id);
			return notes;
		}
		return null;
	}

	@Override
	public List<Notes> getAllunArchiveNotes(String token) {
           long id = jwt.idDetails(token);
		User user = userrepo.findOneById(id);
		if(user != null) {
			List<Notes> notes = repos.getallunArchivedNotes(id);
			return notes;
		}
		return null;
	}

	@Override
	public List<Notes> getNotesByTitle( String token, String title) {
	    long userId = jwt.idDetails(token);
		User user = userrepo.findOneById(userId);
		List<Notes> notes = repos.getNotesByTitle(title);
		if(user != null && notes != null) {
			 return notes;
		}
		return null;
	}

	@Override
	public List<Notes> getAllReminders(String token) {
		long uId = jwt.idDetails(token);
		User user = userrepo.findOneById(uId);
		List<Notes> reminders =	repos.getAllRemiders(uId);
		if( user != null && reminders != null) {
             return reminders;
		
		}
		return null;
	}

	@Override
	public boolean deleteReminder(String token, long noteId, String reminder) {
		long userId = jwt.idDetails(token);
		User user = userrepo.findOneById(userId);
		Notes notes = repos.findByid(noteId);
		if(user != null && notes != null) {
			   repos.deleteReminder(userId, noteId, reminder); 
			   return true;
		}
		return false;
	}

	@Override
	public boolean getNotes(String email, String token, long noteId) {
		long userId = jwt.idDetails(token);
		User user = userrepo.findOneById(userId);
		if(user != null) {
		  Notes notes = repos.findOnebyID(noteId);
			if( notes != null) {
				User users = userrepo.findOneByuserEmail(email);
				if (!(user.getEmail().equals(users.getEmail()))) {	
				Collaborator collab = new Collaborator();
				collab.setCollaborate_email_to(email);
				collab.setNoteId(noteId); 
				colrep.save(collab);
				if(users != null) { 
					TempNotes temp = new TempNotes();
					temp.setTitle(notes.getTitle());
					temp.setTake_a_note(notes.getTake_a_note());
					temp.setColor(notes.getColor());
					temp.setArchive(notes.isArchive());
					temp.setPin(notes.isPin());
					temp.setTrash(notes.isTrash());
					temp.setCreate_date(notes.getCreate_date());
					temp.setUpdate_date(notes.getUpdate_date()); 
					temp.setReminder(notes.getReminder());
					temp.setUser(users); 
					temprep.save(temp);
					return true;
				}
			}
			}
		}
		return false;
	}
	
	
}
