package jado.service;

import jado.dao.UserDao;
import jado.model.Customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.exception.IsNotValidatedMail;
import core.exception.PasswordMismatchException;
import core.exception.UserNotFoundException;

@Service
public class LoginService {
	
	@Autowired private UserDao userDao;

	public boolean logIn(String userId, String password, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		Boolean loginAllowed = true;  
		try {
			checkIsUserExist(request, response, userId);
			checkIsPasswordCorrect(request, response, userId, password);
			checkIsEmailValidated(request, response, userId);
		} catch (UserNotFoundException | PasswordMismatchException | IsNotValidatedMail e) {
			loginAllowed = false;
			forward(request, response, e.getMessage());
		}

		if(loginAllowed) {
			setUserInformationToSessionScope(userId, session);
		}
		
		return true;
	}

	private void checkIsEmailValidated(HttpServletRequest request, HttpServletResponse response, String userId) throws ServletException, IOException, IsNotValidatedMail {
		if (!isEmailValidated(userId))
			throw new IsNotValidatedMail("이메일 인증이 완료 되지 않았습니다. 회원가입 하신 아이디로 이메일이 발송 되었으니 인증해 주시기 바랍니다.");
	}

	private void checkIsPasswordCorrect(HttpServletRequest request, HttpServletResponse response, String userId, String password) throws ServletException, IOException, PasswordMismatchException {
		if (!isPasswordCorrect(userId, password))
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다. 다시 로그인 해주세요.");
	}

	private void checkIsUserExist(HttpServletRequest request, HttpServletResponse response, String userId) throws ServletException, IOException, UserNotFoundException {
		if (selectCustomerById(userId) == null)
			throw new UserNotFoundException("아이디가 존재하지 않습니다 다시 로그인 해주세요");
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
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
		setErrorMessage(request, errorMessage);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/loginFailure.jsp");
		rd.forward(request, response);
	}
	
	private void setErrorMessage(HttpServletRequest request, String message) {
		request.setAttribute("errorMessage", message);
	}
}
