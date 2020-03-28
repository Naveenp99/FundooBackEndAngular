package com.bdlabz.fundoo.entitymodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "label_table")
public class Label {

	@ManyToOne
	private User user;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "label_title")
	private String title;
	
	@ManyToMany(mappedBy = "label")
	@JsonIgnore
	private List<Notes> note;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public List<Notes> getNote() {
		return note;
	}

	public void setNote(List<Notes> note) {
		this.note = note;
	}
	
	
}
