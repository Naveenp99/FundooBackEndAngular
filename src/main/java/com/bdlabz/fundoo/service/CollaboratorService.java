package com.bdlabz.fundoo.service;

import java.util.List;

import com.bdlabz.fundoo.Dto.CollaboratorDto;
import com.bdlabz.fundoo.entitymodel.Collaborator;

public interface CollaboratorService {

	boolean createCollaborator(String token, long noteid, CollaboratorDto dto);
	
	boolean deleteCollaborator(String token, long id, long cid);
	
	List<Collaborator> getAllCollaborator(String token, long id);
	
}
