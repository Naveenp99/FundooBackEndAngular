package com.bdlabz.fundoo.service;

import java.util.List;

import com.bdlabz.fundoo.Dto.NoteDto;
import com.bdlabz.fundoo.entitymodel.Label;
import com.bdlabz.fundoo.entitymodel.Notes;

public interface NoteService {

	boolean createNote(NoteDto dto, String token);

	int updatePin(String token, long id);

	int updateArchive(String token, long id);

	int updateTrash(String token, long id);

	boolean deleteNote(String token, long id);

	Notes getSingleNote(String token, long id);
	
	List<Notes> getAllNotes(String token);

	List<Label> getAllLabel(String token, long noteid);
	
	boolean updateNotes(NoteDto dto, String token, long noteid);
	
	boolean updateColor(String token, long noteid, String color);
	
	boolean reminder(String time, String token, long noteid);

	boolean emptyBin(String token);
	
	List<Notes> getAllPinedNotes(String token);
	
	List<Notes> getAllunPinnedNotes(String token);
	
	List<Notes> getAllTrashedNotes(String token);
	
	List<Notes> getAllArchiveNotes(String token);
	
	List<Notes> getAllunArchiveNotes(String token);
	
	List<Notes> getNotesByTitle( String token, String title);
	
	List<Notes> getAllReminders( String token);
	
	boolean deleteReminder( String token, long noteId, String reminder);
	
}
