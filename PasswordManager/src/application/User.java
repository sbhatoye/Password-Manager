package application;

/**
 *  This class contains getter and setter methods for user information 
 *  
 **/

public class User extends Login {

	private String ID;
	private String website;
	private String email;
	private String username;
	private String password;
	private String notes;
	private String ownership;

	public User() {
		super();
		this.ID = ID;
		this.website = website;
		this.email = email;
		this.username = username;
		this.password = password;
		this.notes = notes;
		this.ownership = ownership;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
