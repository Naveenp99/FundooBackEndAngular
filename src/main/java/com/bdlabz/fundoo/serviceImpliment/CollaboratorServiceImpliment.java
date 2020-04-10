package com.bdlabz.fundoo.serviceImpliment;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bdlabz.fundoo.Dto.CollaboratorDto;
import com.bdlabz.fundoo.entitymodel.Collaborator;
import com.bdlabz.fundoo.entitymodel.Notes;
import com.bdlabz.fundoo.entitymodel.User;
import com.bdlabz.fundoo.repository.CollaboratorRepository;
import com.bdlabz.fundoo.repository.NotesRepository;
import com.bdlabz.fundoo.repository.TempNotesRepository;
import com.bdlabz.fundoo.repository.UserRepository;
import com.bdlabz.fundoo.service.CollaboratorService;
import com.bdlabz.fundoo.util.Jwt;
import com.bdlabz.fundoo.util.Mail;

@Service
public class CollaboratorServiceImpliment implements CollaboratorService{

	@Autowired
	TempNotesRepository temprep;
	
	@Autowired
	UserRepository userrep;
	
	@Autowired
	NotesRepository noterep;
	
	@Autowired
	CollaboratorRepository colrep;
	
	@Autowired
	Jwt jwt;

	@Autowired
	Mail ma;
	
	@Override
	public List<Notes> createCollaborator(String token, long noteid, CollaboratorDto dto) {
		
		try {
			long mail = jwt.idDetails(token);
			User user = userrep.findOneById(mail);
			List<Notes> note = noterep.getallNotes(noteid, mail);
			if(user != null && note != null) {
		User users = userrep.findOneByuserEmail(dto.getCollaborate_to_email());	
		users.setNotes(note);
		userrep.save(users);
		noterep.save(users.getNotes());
		ma.sendNotesColl(dto.getCollaborate_to_email());
				return users.getNotes();
			}
			
		} catch (JWTVerificationException | IllegalArgumentException  e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean deleteCollaborator(String token, String email, long cid, long noteId) {
		try {
			User user = userrep.findOneById(jwt.idDetails(token));
			if(user != null) {
			User users = getUserByEmail(email);
			System.out.println("Finding User by Email:"+ users);
			Notes notes = noterep.findOnebyID(noteId); 
			Notes note = noterep.deleteNotesByUser(notes.getTitle(), notes.getTake_a_note(), users.getId());
			noterep.save(note);
			Collaborator collab = colrep.findById(cid);
			colrep.delete(collab);
			return true; 
		}
		} catch (JWTVerificationException | IllegalArgumentException  e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Collaborator> getAllCollaborator(String token, long id) {
		try {
			long mail = jwt.idDetails(token);
			User user = userrep.findOneById(mail);
			Notes notes = noterep.findByid(id);
			if(user != null && notes != null) {
				List<Collaborator> list =colrep.getallCollaborator(id);
				return list;
			}
		} catch (JWTVerificationException | IllegalArgumentException  e) {
			e.printStackTrace();
		}
		return null;
	}

  public User getUserByEmail(String email) {
	User user = userrep.findOneByuserEmail(email);
	return user;
	}
}
