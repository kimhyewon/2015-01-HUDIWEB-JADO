package jado.model;

public class Customer extends User {

	private String password;
	private String name;
	private String phone;
	private String address;
	private String insertTime;
	private String updateTime;
	private String emailValidateStatus;

	public Customer() { }
	
	public Customer(String userId, String password, String name, String phone, String address, String insertTime, String updateTime, String isValidated) {
		super(userId);
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.emailValidateStatus = isValidated;
	}

	public Customer(String userId, String password, String name, String phone, String address, String isValidated) {
		this(userId, password, name, phone, address, null, null, "F");
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

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getEmailValidateStatus() {
		return emailValidateStatus;
	}

	public void setEmailValidateStatus(String emailValidateStatus) {
		this.emailValidateStatus = emailValidateStatus;
	}

	public boolean update(Customer customer2) {
		boolean result = false;
		if (!this.phone.equals(customer2.phone)) {
			this.phone = customer2.phone;
			result = true;
		}
		if (!this.address.equals(customer2.address)) {
			this.address = customer2.address;
			result = true;
		}
		if (!this.address.equals(customer2.address)) {
			this.address = customer2.address;
			result = true;
		}
		return result;
	}
}
