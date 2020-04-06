package com.bdlabz.fundoo.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bdlabz.fundoo.Dto.LabelDto;
import com.bdlabz.fundoo.entitymodel.Label;
import com.bdlabz.fundoo.entitymodel.Notes;
import com.bdlabz.fundoo.service.LabelService;
import com.bdlabz.fundoo.util.Response;

@RestController
@RequestMapping(value = "/label")
public class LabelController {
	
	@Autowired
	LabelService service;
	
	@PostMapping(value = "/create")
	public ResponseEntity<Response> createLabel(@RequestBody LabelDto dto,
			                                    @RequestHeader(value = "token") String token) {
		     boolean is_created = service.createLabel(dto, token); 
		if(is_created == true) 
			return ResponseEntity.ok().body(new Response("Created Successfully", 200, dto));
		else
			return ResponseEntity.ok().body(new Response("Created UnSuccessfully", 400, is_created));
		
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Response> deleteLabel(@PathVariable(value = "id") long id,
			@RequestHeader(value = "token") String token) {
		     boolean is_deleted = service.deleteLabel(token, id); 
		if(is_deleted == true) 
			return ResponseEntity.ok().body(new Response("Deleted Successfully", 200));
		else
			return ResponseEntity.ok().body(new Response("Deleted UnSuccessfully", 400));
		
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Response> updateORchangeLabel(@PathVariable(value = "id") long id,
			                                            @RequestHeader(value = "token") String token,
			                                    @RequestBody LabelDto dto) {
		     boolean is_updated = service.updateLabel(token, id, dto); 
		if(is_updated == true) 
			return ResponseEntity.ok().body(new Response("Updated Successfully", 200));
		else
			return ResponseEntity.ok().body(new Response("Updated UnSuccessfully", 400));
		
	}
	
	@GetMapping(value = "/getalllabel")
	public ResponseEntity<Response> getAllLabel(@Valid @RequestHeader(value = "token") String token) {	                                    
		     List<Label> getAll = service.getAllLabel(token); 
		if(getAll != null) 
			return ResponseEntity.ok().body(new Response(" Successfully", 200, getAll));
		else
			return ResponseEntity.ok().body(new Response(" UnSuccessfully", 400, null));
		
	}
	@GetMapping(value = "/getsinglelabel/{lid}")
	public ResponseEntity<Response> getSingleLabel(@Valid @RequestHeader(value = "token") String token,
			                                       @PathVariable(value = "lid") long lid  ) {	                                    
		     Label label = service.getsingleLabel(token, lid); 
		if(label != null) 
			return ResponseEntity.ok().body(new Response(" Successfully", 200, label));
		else
			return ResponseEntity.ok().body(new Response(" UnSuccessfully", 400, null));
		
	}
	
	@PutMapping( value = "/updatenoteId/{noteId}/{labelId}")
	public ResponseEntity<Response> updateNoteId( @RequestHeader(value = "token") String token,
			@PathVariable(value = "noteId") long noteId,
			@PathVariable(value = "labelId") long labelId) {
	boolean is_updated = service.updatenoteIdinLabel(token, noteId, labelId);
		if(is_updated == true)
			return ResponseEntity.ok().body(new Response(" Successfully", 200, is_updated));
		else
			return ResponseEntity.ok().body(new Response(" UnSuccessfully", 400, null));
	}
	
	@GetMapping( value = "/getNotesinLabel/{title}")
	public ResponseEntity<Response> getNotesinLabel( @RequestHeader(value = "token") String token,
			                                         @PathVariable(value = "title") String title) {
		List<Notes> notes = service.getLabelBytitle(token, title);
		if( notes != null)
			return ResponseEntity.ok().body(new Response(" Successfully", 200, notes));
		else
			return ResponseEntity.ok().body(new Response(" UnSuccessfully", 400, null));
	}
	
	
	
	
	
	
//	@GetMapping(value = "/getallnote/{labelid}")
//	public ResponseEntity<Response> getAllNote(@Valid @RequestHeader(value = "token") String token,
//			@PathVariable(value = "labelid") long labelid) {	                                    
//		     List<Notes> getAll = service.getAllNotes(token, labelid); 
//		if(getAll != null) 
//			return ResponseEntity.ok().body(new Response(" Successfully", 200, getAll));
//		else
//			return ResponseEntity.ok().body(new Response(" UnSuccessfully", 400, null));
//		
//	}
	
}
