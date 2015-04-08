package jado.service;

import jado.controller.Result;
import jado.controller.ResultValue;
import jado.dao.UserDao;
import jado.model.Customer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.exception.IsNotValidatedMail;
import core.exception.PasswordMismatchException;
import core.exception.UserNotFoundException;

@Service
public class LoginService {
	
	@Autowired private UserDao userDao;

	public Result logIn(String userId, String password, HttpSession session) {
		Boolean loginAllowed = true;  
		try {
			Customer user = getCustomer(userId);
			checkIsPasswordCorrect(user, password);
			checkIsEmailValidated(user, userId);
		} catch (UserNotFoundException | PasswordMismatchException | IsNotValidatedMail e) {
			loginAllowed = false;
			return new Result(false, new ResultValue("errorMessage", e.getMessage()));
		}

		if(loginAllowed) {
			setUserInformationToSessionScope(userId, session);
		}
		
		return new Result(true);
	}

	private void checkIsEmailValidated(Customer user, String userId) throws IsNotValidatedMail {
		if(!user.getEmailValidateStatus().equals("T"))
			throw new IsNotValidatedMail("이메일 인증이 완료 되지 않았습니다. 회원가입 하신 아이디로 이메일이 발송 되었으니 인증해 주시기 바랍니다.");
	}

	private void checkIsPasswordCorrect(Customer user, String password) throws PasswordMismatchException {
		if(!user.getPassword().equals(password))
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다. 다시 로그인 해주세요.");
	}

	private Customer getCustomer(String userId) throws UserNotFoundException {
		Customer user = selectCustomerById(userId);
		if (user == null)
			throw new UserNotFoundException("아이디가 존재하지 않습니다 다시 로그인 해주세요");
		return user;
	}

	private boolean IsExistSeller(String userId) {
		return userDao.isExistSeller(userId);
	}

	private Customer selectCustomerById(String userId) {
		return userDao.selectCustomerById(userId);
	}

	private void setUserInformationToSessionScope(String userId, HttpSession session) {
		session.setAttribute("userId", userId);

		if (IsExistSeller(userId)) {
			session.setAttribute("isSeller", true);
		}
	}
}
