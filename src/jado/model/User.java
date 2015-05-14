package jado.model;

public class User {
	protected String id;
	protected int userStatus = 1;	// 초기 가입시 사용자는 활성화 상태 

	public User() { }
	
	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public User(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
