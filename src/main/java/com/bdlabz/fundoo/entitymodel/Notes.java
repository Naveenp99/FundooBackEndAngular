package com.bdlabz.fundoo.entitymodel;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "notes_table")
public class Notes {
	@Id
	@GeneratedValue
	@Column(name = "notes_id")
    private long id;
	@Column(name = "notes_title")
	private String title;
	@Column(name = "notes_takeanote")
	private String take_a_note;
	@Column(name = "notes_color")
	private String color;	

	@Column(name = "note_reminder")
	private String reminder;
	
	@ManyToMany
	@JsonIgnore
	private List<Label> label;
	
	
	public List<Label> getLabel() {
		return label;
	}
	public void setLabel(List<Label> label) {
		this.label = label;
	}

	private Date update_date;
	
	@Column(name = "notes_createdate")
	private Date create_date;
	
	@Column(name = "notes_is_pin",columnDefinition = "boolean default false")
	private boolean pin;
	@Column(name = "notes_is_archive",columnDefinition = "boolean default false")
	private boolean archive;
	@Column(name = "notes_is_trash",columnDefinition = "boolean default false")
	private boolean trash;
	public boolean isTrash() {
		return trash;
	}
	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	@ManyToOne
	@JoinColumn(name="userId")
	@JsonIgnore
	private User user;
	
	@OneToMany
	@JsonIgnore
	private List<Collaborator> collaborator;
	
	public List<Collaborator> getCollaborator() {
		return collaborator;
	}
	public void setCollaborator(List<Collaborator> collaborator) {
		this.collaborator = collaborator;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTake_a_note() {
		return take_a_note;
	}
	public void setTake_a_note(String take_a_note) {
		this.take_a_note = take_a_note;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isPin() {
		return pin;
	}
	public void setPin(boolean pin) {
		this.pin = pin;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getReminder() {
		return reminder;
	}
	public void setreminder(String reminder) {
		this.reminder = reminder;
	}
	public void setReminder(String time) {
		this.reminder = time;
	}
	
	
}
