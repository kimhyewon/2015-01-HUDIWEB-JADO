package jado.model;

public class User {
	protected String userId;
	protected int userStatus = 1;	// 초기 가입시 사용자는 활성화 상태 

	public User() { }
	
	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

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
