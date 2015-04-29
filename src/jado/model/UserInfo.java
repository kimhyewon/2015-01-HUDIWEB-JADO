package jado.model;

import org.springframework.stereotype.Component;

@Component
public class UserInfo {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
