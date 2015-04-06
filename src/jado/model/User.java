package jado.model;

public class User {
	protected String userId;

	public User() { }
	
	public User(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
