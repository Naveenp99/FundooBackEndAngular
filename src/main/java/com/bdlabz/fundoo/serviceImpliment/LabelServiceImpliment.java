package com.bdlabz.fundoo.serviceImpliment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bdlabz.fundoo.Dto.LabelDto;
import com.bdlabz.fundoo.entitymodel.Label;
import com.bdlabz.fundoo.entitymodel.Notes;
import com.bdlabz.fundoo.entitymodel.User;
import com.bdlabz.fundoo.repository.LabelRepository;
import com.bdlabz.fundoo.repository.NotesRepository;
import com.bdlabz.fundoo.repository.UserRepository;
import com.bdlabz.fundoo.service.LabelService;
import com.bdlabz.fundoo.util.Jwt;

@Service
public class LabelServiceImpliment implements LabelService {

	@Autowired
	NotesRepository noterep;

	@Autowired
	UserRepository userrep;

	@Autowired
	Jwt jwt;

	@Autowired
	LabelRepository repos;

	@Override
	public boolean createLabel(LabelDto dto, String token, long nId) {

		try {
			long mail = jwt.idDetails(token);
			User user = userrep.findOneById(mail);
			if (user != null) {
				Label label = new Label();
				label.setTitle(dto.getTitlename());
				label.setNoteId(nId); 
				label.setUser(user);
				repos.save(label);
				return true;
			}

		} catch (JWTVerificationException | IllegalArgumentException  e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteLabel(String token, long id) {
		try {
			long mail = jwt.idDetails(token);
			User user = userrep.findOneById(mail);
			if (user != null) {
				Label label = repos.findByid(id);
				repos.delete(label);
				return true;
			}

		} catch (JWTVerificationException | IllegalArgumentException  e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateLabel(String token, long id, LabelDto dto) {

		try {
			long mail = jwt.idDetails(token);
			User user = userrep.findOneById(mail);
			Label label = repos.findByid(id);
			if (user != null && label != null) {
				label.setTitle(dto.getTitlename());
				repos.save(label);
				return true;
			}
		} catch (JWTVerificationException | IllegalArgumentException  e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Label> getAllLabel(String token) {
		try {
			long mail = jwt.idDetails(token);
			User user = userrep.findOneById(mail);
			if (user != null) {
				List<Label> label = repos.getAllLabels();
				return label;
			}
		} catch (JWTVerificationException | IllegalArgumentException  e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Label getsingleLabel(String token, long labelId) {
		long userId = jwt.idDetails(token);
		User user = userrep.findOneById(userId);
		Label label = repos.getAllLabelById(labelId);
		if(user != null && label != null) {
		return label;
		}
;		return null;
	}

	@Override
	public boolean updatenoteIdinLabel(String token, long noteId, long labelId) {
		long userId = jwt.idDetails(token);
		User user = userrep.findOneById(userId);
		if(user != null) {
			repos.updateNoteId(labelId, noteId); 
			return true;
		}
		return false;
	}

	@Override
	public List<Notes> getLabelBytitle(String token, String title) {
		long userId = jwt.idDetails(token);
		User user = userrep.findOneById(userId);
	Label labels = repos.getLabelsByTitle(title);
		if(user != null && labels != null) {
			long noteId = labels.getNoteId();
			List<Notes> notes = noterep.getAllNotesinLabel(userId, noteId);
			return notes;
		}
		return null;
	}

//	@Override
//	public List<Notes> getAllNotes(String token, long labelid) {
//		try {
//			long mail = jwt.idDetails(token);
//			User user = userrep.findOneById(mail);
//			Label label = repos.findByid(labelid);
//			
//			if (user != null && label != null) {
//          return label.getNote();
//          
//			}
//		} catch (JWTVerificationException | IllegalArgumentException  e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
