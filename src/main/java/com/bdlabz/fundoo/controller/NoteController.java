package com.bdlabz.fundoo.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bdlabz.fundoo.Dto.NoteDto;
import com.bdlabz.fundoo.entitymodel.Label;
import com.bdlabz.fundoo.entitymodel.Notes;
import com.bdlabz.fundoo.service.NoteService;
import com.bdlabz.fundoo.util.Response;

@RestController
@RequestMapping(value = "/note")

public class NoteController {

	@Autowired
	NoteService service;
	
	@PostMapping(value = "/create")
	public ResponseEntity<Response> createNote( @RequestHeader(value = "token") String token,@RequestBody NoteDto dto) {
		boolean create = service.createNote(dto, token);
		if(create == true)
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Note is Created", 200));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Note is NOT Created", 400));
	}
	
	@PutMapping(value = "/updatepin/{id}")
	public ResponseEntity<Response> updatePin(@Valid @RequestHeader(value = "token") String token,
			                                  @PathVariable(value = "id") long id) {
			int is_pin = service.updatePin(token, id);
		if(is_pin == 1)
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated Successfully", 200));
		else if(is_pin == 0)
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated Successfully", 200));
		else
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Updated UnSuccessfull", 400));
		
	}
	
	@PutMapping(value = "/updatearchive/{id}")
	public ResponseEntity<Response> updateArchive(@Valid @RequestHeader(value = "token") String token,
			                                   @PathVariable(value = "id") long id) {
			int is_archive = service.updateArchive(token, id);
		if(is_archive == 1)
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated Successfully", 200));
		else if(is_archive == 0)
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated Successfully", 200));
		else
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Updated UnSuccessfull", 400));
		
	}
	
	@PutMapping(value = "/updatetrash/{id}")
	public ResponseEntity<Response> updateTrash(@Valid @RequestHeader(value = "token") String token,
			                                   @PathVariable(value = "id") long id) {
			int is_trash = service.updateTrash(token, id);
			if(is_trash == 1)
				return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated Successfully", 200));
			else if(is_trash == 0)
				return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated Successfully", 200));
			else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Updated UnSuccessfull", 400));
	}
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Response> DeleteNote(@Valid @RequestHeader(value = "token") String token,
            @PathVariable(value = "id") long id) {
		boolean is_deleted = service.deleteNote(token, id);
		if(is_deleted == true)
			return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Successfully", 200));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Deleted UnSuccessfull", 400));
}
	@GetMapping(value = "/singlenote/{id}")
	public ResponseEntity<Response> getSingleNote(@Valid @RequestHeader(value = "token") String token,
            @PathVariable(value = "id") long id) {
		Notes note = service.getSingleNote(token, id);
		if(note != null) 
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Success", 200));
		else 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccess", 400));
	}
	@GetMapping(value = "/allnote")
	public ResponseEntity<Response> getAllNotes(@Valid @RequestHeader(value = "token") String token) {
		List<Notes> note = service.getAllNotes(token);
		if(note != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Success", 200, note));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccess", 400));
	}
	
	@GetMapping(value = "/alllabel/{noteid}")
	public ResponseEntity<Response> getAllLabel(@Valid @RequestHeader(value = "token") String token,
			@PathVariable(value = "noteid") long noteid) {
		List<Label> label = service.getAllLabel(token, noteid);
		if(label != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Success", 200, label));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccess", 400));
	}
	
	@PutMapping(value = "/updatenote/{noteid}")
	public ResponseEntity<Response> updateNotes(@RequestBody NoteDto dto, 
			@RequestHeader(value = "token") String token,
			@PathVariable(value = "noteid") long noteid) {
		boolean is_updated = service.updateNotes(dto, token, noteid);
		if(is_updated == true)
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully Updated", 200));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccessfull", 400));
	}
	
	@PutMapping(value = "/addcolor/{noteid}/{color}")
	public ResponseEntity<Response> updateColor(@PathVariable(value = "color") String color,
			@RequestHeader(value = "token") String token,
			@PathVariable(value = "noteid") long noteid) {
		System.out.println("color name :"+color);
		boolean is_updated = service.updateColor(token, noteid, color);
		if(is_updated == true) 
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated Successfully", 200));
			else 
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccessfull", 400));
		}
		
	@PostMapping(value = "/reminder/{noteid}/{time}")
	public ResponseEntity<Response> reminder(@RequestHeader(value = "token") String token,
			@PathVariable(value = "noteid") long noteid,
			@PathVariable(value = "time") String time) {
		boolean reminder = service.reminder(time, token, noteid);
		if(reminder == true)
			return  ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated Successfully", 200));
		else
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccessfull", 400));
	}
	
	@PostMapping(value = "/emptybin")
	public ResponseEntity<Response> emptyBin(@RequestHeader(value = "token") String token) {
		boolean is_empty = service.emptyBin(token);
		if(is_empty == true)
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Empty Successfully", 200));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccessfull", 400));
	}
	
	@GetMapping(value = "/allpinnednotes")
	public ResponseEntity<Response> getAllPinnedNotes(@RequestHeader(value = "token") String token) {
		List<Notes> notes = service.getAllPinedNotes(token);
		if(notes != null)
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfull", 200, notes));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccessfull", 400));
	}
	
	@GetMapping(value = "/allunpinnednotes")
	public ResponseEntity<Response> getAllunPinnedNotes(@RequestHeader(value = "token") String token) {
		List<Notes> notes = service.getAllunPinnedNotes(token);
		if(notes != null)
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfull", 200, notes));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccessfull", 400));
	}

	@GetMapping(value = "/getAllTrashedNotes")
	public ResponseEntity<Response> getAllTrashedNotes(@RequestHeader(value = "token") String token) {
		List<Notes> notes = service.getAllTrashedNotes(token);
		if(notes != null)
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfull", 200, notes));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccessfull", 400));
	}

	@GetMapping(value = "/getallarchived")
	public ResponseEntity<Response> getallArchivedNotes(@RequestHeader(value = "token") String token) {
		List<Notes> notes = service.getAllArchiveNotes(token);
		if(notes != null) 
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfull", 200, notes));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccessfull", 400));
	}
	
	@GetMapping(value = "/getallunarchived")
	public ResponseEntity<Response> getAllunArchivedNotes(@RequestHeader(value = "token") String token) {
		List<Notes> notes = service.getAllunArchiveNotes(token);
		if(notes != null) 
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfull", 200, notes));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccessfull", 400));
	}
	
	@GetMapping(value = "/searchByTitle/{title}")
	public ResponseEntity<Response> searchByTitle( @RequestHeader(value = "token") String token, 
			                                       @PathVariable(value = "title") String title) {
		
		     List<Notes> notes = service.getNotesByTitle(token, title);
		     if( notes != null) 
		    	 return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfull", 200, notes));
				else
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccessfull", 400));	 
		     
	}
	
	@GetMapping(value = "/allreminders") 
	public ResponseEntity<Response> getAllReminders( @RequestHeader(value = "token") String token){
	List<Notes> reminders = service.getAllReminders(token);
	if( reminders != null) 
   	 return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfull", 200, reminders));
		else
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccessfull", 400));
	}
}
