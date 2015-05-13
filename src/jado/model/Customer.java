package jado.model;

import java.sql.Timestamp;

public class Customer extends User {
	private String password;
	private String name;
	private String phone;
	private String address;
	private Timestamp insertTime;
	private Timestamp updateTime;
	private String emailValidateStatus;

	public Customer() {
	}
	
	public Customer(String userId, String password, String name, String phone, String address, Timestamp insertTime, Timestamp updateTime, String emailValidateStatus) {
		super(userId);
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.emailValidateStatus = emailValidateStatus;
	}

	public Customer(String userId, String password, String name, String phone, String address, String isValidated) {
		this(userId, password, name, phone, address, null, null, null);
	}

	public Customer(String userId, String password, String name, String phone, String address) {
		this(userId, password, name, phone, address, null);

	}

	public Customer(String userId, String password) {
		this(userId, password, null, null, null);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getEmailValidateStatus() {
		return emailValidateStatus;
	}
	
	public void setEmailValidateStatus(String emailValidateStatus) {
		this.emailValidateStatus = emailValidateStatus;
	}

	public boolean update(Customer customer) {
		boolean result = false;
		if (!this.phone.equals(customer.phone)) {
			this.phone = customer.phone;
			result = true;
		}
		if (!this.address.equals(customer.address)) {
			this.address = customer.address;
			result = true;
		}
		if (!this.address.equals(customer.address)) {
			this.address = customer.address;
			result = true;
		}
		return result;
	}

	@Override
	public String toString() {
		return "Customer [password=" + password + ", name=" + name + ", phone=" + phone + ", address=" + address + ", insertTime=" + insertTime + ", updateTime=" + updateTime
				+ ", emailValidateStatus=" + emailValidateStatus + "]";
	}
	
	
	
}
