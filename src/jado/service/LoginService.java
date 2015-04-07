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
			checkIsUserExist(userId);
			checkIsPasswordCorrect(userId, password);
			checkIsEmailValidated(userId);
		} catch (UserNotFoundException | PasswordMismatchException e) {
			loginAllowed = false;
			return new Result(false, new ResultValue("errorMessage", e.getMessage()));
		}  catch (IsNotValidatedMail e){
			loginAllowed = false;
			return new Result(false, new ResultValue("errorMessage", e.getMessage()));
		}

		if(loginAllowed) {
			setUserInformationToSessionScope(userId, session);
		}
		
		return new Result(true);
	}

	private void checkIsEmailValidated(String userId) throws IsNotValidatedMail {
		if (!isEmailValidated(userId))
			throw new IsNotValidatedMail("이메일 인증이 완료 되지 않았습니다. 회원가입 하신 아이디로 이메일이 발송 되었으니 인증해 주시기 바랍니다.");
	}

	private void checkIsPasswordCorrect(String userId, String password) throws PasswordMismatchException {
		if (!isPasswordCorrect(userId, password))
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다. 다시 로그인 해주세요.");
	}

	private void checkIsUserExist(String userId) throws UserNotFoundException {
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
	
//	private void forward(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
//		setErrorMessage(request, errorMessage);
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/loginFailure.jsp");
//		rd.forward(request, response);
//	}
	
//	private void setErrorMessage(HttpServletRequest request, String message) {
//		request.setAttribute("errorMessage", message);
//	}
}
