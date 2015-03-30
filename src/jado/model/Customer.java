package jado.model;

import core.exception.DuplicateUserException;
import core.exception.IsNotValidatedMail;
import core.exception.PasswordMismatchException;
import core.exception.UserNotFoundException;
import core.jdbc.JdbcTemplate;
import jado.dao.UserDao;

public class Customer extends User {

	private String password;
	private String name;
	private String phone;
	private String address;
	private String isValidated;

	public boolean login() throws UserNotFoundException,
			PasswordMismatchException, IsNotValidatedMail {
		Customer user = UserDao.selectUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("존재하지 않는 ID입니다.");
		}
		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다. 다시 로그인 해주세요.");
		}
		if(user.isValidated.equals("F")){
			throw new IsNotValidatedMail("이메일 인증이 와료 되지 않았습니다. 회원가입 하신 아이디로 이메일이 발송 되었으니 인증해 주시기 바랍니다.");
		}
		return true;
	}

	public void signUp() throws DuplicateUserException,
			PasswordMismatchException {

		Customer tempUser = UserDao.selectUserById(this.userId);
		if (tempUser != null) {
			throw new DuplicateUserException("이미 가입된 사용자입니다.");
		}
		UserDao.insert(this);
	}

	private boolean matchPassword(String newPassword) {
		return this.password.equals(newPassword);
	}

	// Constructor
	public Customer(String userId, String password, String name, String phone,
			String address, String isValidated) {
		super(userId);
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.isValidated = isValidated;
	}
	
	public Customer(String userId, String password, String name, String phone,
			String address) {
		this(userId, password, name, phone, address, null);

	}

	public Customer(String userId, String password) {
		this(userId, password, null, null, null);
	}

	// Getter
	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	// Setter
	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}
}
