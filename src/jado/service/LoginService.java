package jado.service;

import jado.dao.UserDao;
import jado.model.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import core.exception.IsNotValidatedMail;
import core.exception.PasswordMismatchException;
import core.exception.UserNotFoundException;

public class LoginService {

	@Autowired
	private UserDao userDao;

	public boolean logIn(String userId, String password, HttpServletRequest request, HttpSession session) {
		checkIsUserExist(request, userId);
		checkIsPasswordCorrect(request, userId, password);
		checkIsEmailValidated(request, userId);
		setUserInformationToSessionScope(userId, session);
		return true;
	}

	private void checkIsEmailValidated(HttpServletRequest request, String userId) {
		try {
			if (!isEmailValidated(userId))
				throw new IsNotValidatedMail("이메일 인증이 완료 되지 않았습니다. 회원가입 하신 아이디로 이메일이 발송 되었으니 인증해 주시기 바랍니다.");
		} catch (IsNotValidatedMail e) {
			setErrorMessage(request, e.getMessage());
		}
	}

	private void checkIsPasswordCorrect(HttpServletRequest request, String userId, String password) {
		try {
			if (!isPasswordCorrect(userId, password))
				throw new PasswordMismatchException("비밀번호가 일치하지 않습니다. 다시 로그인 해주세요.");
		} catch (PasswordMismatchException e) {
			setErrorMessage(request, e.getMessage());
		}
	}

	private void checkIsUserExist(HttpServletRequest request, String userId) {
		try {
			if (selectCustomerById(userId) == null)
				throw new UserNotFoundException("아이디가 존재하지 않습니다 다시 로그인 해주세요");
		} catch (UserNotFoundException e) {
			setErrorMessage(request, e.getMessage());
		}
	}
	
	private void setErrorMessage(HttpServletRequest request, String message) {
		request.setAttribute("errorMessage", message);
	}

	private boolean IsExistSeller(String userId) {
		return userDao.isExistSeller(userId);
	}

	private Customer selectCustomerById(String userId) {
		return userDao.selectCustomerById(userId);
	}

	private boolean isPasswordCorrect(String userId, String password) {
		return userDao.IsPasswordCorrect(userId, password);
	}

	private boolean isEmailValidated(String userId) {
		return userDao.IsEmailValidated(userId);
	}

	private void setUserInformationToSessionScope(String userId, HttpSession session) {
		session.setAttribute("userId", userId);

		if (IsExistSeller(userId)) {
			session.setAttribute("isSeller", true);
		}
	}
}
