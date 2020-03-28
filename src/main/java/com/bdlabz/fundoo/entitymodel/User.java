package com.bdlabz.fundoo.entitymodel;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_table")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
    private long id;
	@Column(name = "user_firstname")
    private String firstname;
	@Column(name = "user_lastname")
    private String lastname;
	@Column(name = "user_email")
    private String email;
	
	@Column(name = "user_password")
    private String password;
	@Column(name = "user_date")
    private Date date;
	@Column(name = "user_verified",columnDefinition = "boolean default false")
    private boolean is_mail_verified;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Notes> notes;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isIs_mail_verified() {
		return is_mail_verified;
	}
	public void setIs_mail_verified(boolean is_mail_verified) {
		this.is_mail_verified = is_mail_verified;
	}
	public List<Notes> getNotes() {
		return notes;
	}
	public void setNotes(List<Notes> notes) {
		this.notes = notes;
	}
    
}
