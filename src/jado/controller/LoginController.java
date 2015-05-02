package jado.controller;

import jado.service.LoginService;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/user")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String viewLoginPage(String returnUrl, Model model, HttpSession session, ServletRequest req) {
		// TODO 로그인한 사용자가 로그인 페이지에 접속하기 이전 주소로 돌려주기 위한 기능에 오류가 존재함 - 수정 필요
		logger.debug("접속한 회원의 이전 주소 {}", returnUrl);
		model.addAttribute("url", returnUrl);
		if (req.getAttribute("errorMessage") != null) {
			return "encryptedReadyFailure";
		}
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processLogin( String userId, String password, HttpSession session, Model model, ServletRequest req) {
		userId = (String) req.getAttribute("userId");
		password = (String) req.getAttribute("password");
		
		Result loginResult = loginService.logIn(userId, password, session);
		if(!loginResult.isSuccess()) {
			model.addAttribute("errorMessage", loginResult.getValue("errorMessage"));
			return "loginFailure";
		}
		return "main";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(HttpSession session) {
		session.invalidate();
		return "main";
	}
}