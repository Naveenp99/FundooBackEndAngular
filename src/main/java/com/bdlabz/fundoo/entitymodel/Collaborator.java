package com.bdlabz.fundoo.entitymodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "collaborator_table")
public class Collaborator {
    @Id
	@GeneratedValue
	private long id;

	@Column(name = "collaborate_to")
	private String collaborate_email_to;

	@Column(name = "notes_id")
	private long noteId;
	
	@ManyToOne
	@JsonIgnore
	private Notes note;
	
	
	
	public long getNoteId() {
		return noteId;
	}

	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}

	public Notes getNote() {
		return note;
	}

	public void setNote(Notes note) {
		this.note = note;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCollaborate_email_to() {
		return collaborate_email_to;
	}

	public void setCollaborate_email_to(String collaborate_email_to) {
		this.collaborate_email_to = collaborate_email_to;
	}
	
	
}
