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

	public boolean login(String checkedPassword) throws UserNotFoundException,
			PasswordMismatchException, IsNotValidatedMail {
		if (!password.equals(checkedPassword)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다. 다시 로그인 해주세요.");
		}
		if(!isValidated.equals("T")){
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


	public boolean update(Customer customer2) {
		boolean result = false;
		if(!this.phone.equals(customer2.phone)){
			this.phone = customer2.phone;
			result = true;
		}
		if(!this.address.equals(customer2.address)){
			this.address = customer2.address;
			result = true;
		}
		if(!this.address.equals(customer2.address)){
			this.address = customer2.address;
			result = true;
		}
		return result;
	}
}
