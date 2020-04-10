package com.bdlabz.fundoo.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bdlabz.fundoo.entitymodel.Notes;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

	@Query(value = "select * from notes_table where user_id=:userId and notes_id = :noteId", nativeQuery = true)
	List<Notes> getAllNotesinLabel(Long userId, long noteId);
	
	@Query( value = "SELECT * FROM notes_table WHERE notes_id = :noteId", nativeQuery = true)
	Notes findOnebyID( long noteId);
	
	Notes findByid(long id);
	
	@Query(value = "select * from notes_table where user_id=:userId", nativeQuery = true)
	List<Notes> getAllNotes(Long userId);
	
	@Query(value = "select * from notes_table where user_id=?", nativeQuery = true)
	public List<Notes> searchAllNoteByUserId(long userId);

	@Query(value = "select * from notes_table where user_id=? and notes_id=?", nativeQuery = true)
	public List<Notes> searchAllNotesByNoteId(long userId, long noteId);
	
	@Transactional
	@Modifying
	@Query(value = "update notes_table set notes_color = :color where user_id = :userid and notes_id = :id", nativeQuery = true)
	void updateColor(long userid, long id, String color);
	
	@Modifying
	@Query(value = "delete from notes_table where user_id = :userId", nativeQuery = true)
	void empty(long userId);
	
	@Query(value = "select * from notes_table where user_id = :userId and notes_is_pin = true and notes_is_trash = false and notes_is_archive = false", nativeQuery = true)
	List<Notes> getallpinned(long userId);
	
	@Query(value = "select * from notes_table where user_id = :id and notes_is_pin = false and notes_is_archive = false and notes_is_trash = false",nativeQuery = true)
	List<Notes> getAllunPinnedNotes(long id);
	
	@Query(value = "select * from notes_table where user_id = :id and notes_is_pin = false and notes_is_trash = true and notes_is_archive = false",nativeQuery = true)
	List<Notes> getAllTrashedNotes(long id);
	
	@Query(value = "select * from notes_table where user_id = :id and notes_is_pin = false and notes_is_trash = false and notes_is_archive = true",nativeQuery = true)
	List<Notes> getallArchivedNotes(long id);
	
	@Query(value = "select * from notes_table where user_id = :id and notes_is_archive = false",nativeQuery = true)
	List<Notes> getallunArchivedNotes(long id);
	
	@Transactional
	@Modifying
	@Query(value = "update notes_table set notes_is_pin = :status where user_id = :UserId and notes_id = :noteId", nativeQuery = true)
	void setPin(boolean status, long noteId, long UserId);
	
	@Query(value = "select * from notes_table where notes_title LIKE %:title% OR notes_takeanote LIKE %:title%", nativeQuery = true)
	List<Notes> getNotesByTitle( String title);

     @Query(value = "select * from notes_table where user_id = :id and note_reminder IS NOT NULL", nativeQuery = true)
	List<Notes> getAllRemiders( long id);
     
     @Transactional
     @Modifying
     @Query(value = "update notes_table set note_reminder = NULL where user_id = :userId AND notes_id = :noteId AND note_reminder = :reminder", nativeQuery = true)
     void deleteReminder(long userId, long noteId, String reminder);

     @Query( value = "select * from notes_table where user_id = :uId and notes_id = :nId", nativeQuery = true)
     List<Notes> getallNotes( long nId, long uId);
     
     @Transactional
     @Modifying
     @Query( value = "delete from notes_table where user_id = :userId and notes_title  = :title and notes_takeanote  = :takenote", nativeQuery = true)
 	Notes deleteNotesByUser( String title, String takenote, long userId);
}
