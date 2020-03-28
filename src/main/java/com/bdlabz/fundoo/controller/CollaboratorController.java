package com.bdlabz.fundoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdlabz.fundoo.Dto.CollaboratorDto;
import com.bdlabz.fundoo.entitymodel.Collaborator;
import com.bdlabz.fundoo.service.CollaboratorService;
import com.bdlabz.fundoo.util.Response;

@RestController
@RequestMapping(value = "/collaborator")
public class CollaboratorController {

	@Autowired
	CollaboratorService service;
	
	
	@PostMapping(value = "/create/{noteid}")
	public ResponseEntity<Response> collaborator(@RequestBody CollaboratorDto dto, 
			                                           @PathVariable(value = "noteid") long noteid, 
			                                           @RequestHeader(value = "token") String token) {
		boolean is_collaborator = service.createCollaborator(token, noteid, dto);
		if(is_collaborator == true)
			return ResponseEntity.ok().body(new Response("Created Successfully", 200));
		else
			return ResponseEntity.ok().body(new Response(" UnSuccessfull", 400));
	}
	
	@DeleteMapping(value = "/delete/{nid}/{cid}")
	public ResponseEntity<Response> deleteCollaborator(@PathVariable(value = "nid") long nid,
			@PathVariable(value = "cid") long cid,
			@RequestHeader(value = "token") String token) {
		boolean is_delete = service.deleteCollaborator(token, nid, cid);
		if(is_delete == true)
			return ResponseEntity.ok().body(new Response("Deleted Successfully", 200, is_delete));
		else
			return ResponseEntity.ok().body(new Response(" UnSuccessfull", 400, is_delete));
	}
	
	@GetMapping(value = "/getall/{id}")
	public ResponseEntity<Response> getAllCollaborator(@PathVariable(value = "id") long id, 
			@RequestHeader(value = "token") String token) {
		List<Collaborator> getall = service.getAllCollaborator(token, id);
		if(getall != null)
			return ResponseEntity.ok().body(new Response(" Successfull", 200, getall));
		else
			return ResponseEntity.ok().body(new Response(" UnSuccessfull", 400, null));
	}
	
}
