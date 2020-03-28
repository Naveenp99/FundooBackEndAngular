package com.bdlabz.fundoo.Dto;

public class UserDto {

	
	 private long id;
	    private String firstname;
	    private String lastname;
	    private String email;
	    private long mobilenum;
	    private String password;
	    private String conf_password;


		public String getConf_password() {
			return conf_password;
		}
		public void setConf_password(String conf_password) {
			this.conf_password = conf_password;
		}
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
		public long getMobilenum() {
			return mobilenum;
		}
		public void setMobilenum(long mobilenum) {
			this.mobilenum = mobilenum;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		@Override
		public String toString() {
			return "UserDto [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
					+ ", mobilenum=" + mobilenum + ", password=" + password + ", conf_password=" + conf_password + "]";
		}
	
	    
}
