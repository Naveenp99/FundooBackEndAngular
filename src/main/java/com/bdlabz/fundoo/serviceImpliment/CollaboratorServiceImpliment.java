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
import com.bdlabz.fundoo.repository.UserRepository;
import com.bdlabz.fundoo.service.CollaboratorService;
import com.bdlabz.fundoo.util.Jwt;
import com.bdlabz.fundoo.util.Mail;

@Service
public class CollaboratorServiceImpliment implements CollaboratorService{

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
	public Notes createCollaborator(String token, long noteid, CollaboratorDto dto) {
		
		try {
			long mail = jwt.idDetails(token);
			User user = userrep.findOneById(mail);
			Notes note = noterep.findByid(noteid);
			if(user != null && note != null) {
				Collaborator collab = new Collaborator();
				collab.setCollaborate_email_to(dto.getCollaborate_to_email());
				collab.setNote(note);
		ma.sendNotesColl(dto.getCollaborate_to_email(), note);
				colrep.save(collab);
				
				return note;
			}
			
		} catch (JWTVerificationException | IllegalArgumentException  e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean deleteCollaborator(String token, long id, long cid) {
		try {
			User user = userrep.findOneById(jwt.idDetails(token));
			Notes note = noterep.findByid(id);
			if(user != null && note != null) {
			Collaborator coll =	colrep.findById(cid);
			colrep.delete(coll);
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
				List<Collaborator> list =colrep.findAll();
				return list;
			}
		} catch (JWTVerificationException | IllegalArgumentException  e) {
			e.printStackTrace();
		}
		return null;
	}

}
