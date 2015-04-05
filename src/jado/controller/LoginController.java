package jado.controller;

import jado.service.LoginService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.util.DecryptRSA;
import core.util.EncryptRSA;
import core.util.ServletRequestUtils;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String viewLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO 로그인한 사용자가 로그인 페이지에 접속하기 이전 주소로 돌려주기 위한 기능에 오류가 존재함 - 수정 필요
		logger.debug("접속한 회원의 이전 주소 {}", req.getParameter("url"));
		req.setAttribute("url", req.getParameter("url"));

		encryptPrepareProcess(req, resp);
		return "login";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public String processLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = ServletRequestUtils.getRequiredStringParameter(request, "idEncryption");
		String password = ServletRequestUtils.getRequiredStringParameter(request, "pwEncryption");
		
		HttpSession session = request.getSession();
		PrivateKey privateKey = getPrivateKeyAndDestroyKey(request, session);
		
		userId = decryptString(privateKey, request, response, userId);
		password = decryptString(privateKey, request, response, password);

		if(loginService.logIn(userId, password, request, response, session))
			return "main";
		else
			return "loginFailure";
	}

	private String decryptString(PrivateKey privateKey, HttpServletRequest request, HttpServletResponse response, String decryptTargetString) throws ServletException, IOException  {
		String decryptedString = null;
		try {
			DecryptRSA rsa = new DecryptRSA(privateKey);
			decryptedString = rsa.decryptRsa(decryptTargetString);
		} catch (Exception e) {
			forward(request, response, e.getMessage());
		}
		return decryptedString;
	}

	private PrivateKey getPrivateKeyAndDestroyKey(HttpServletRequest request, HttpSession session) {
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		session.removeAttribute("__rsaPrivateKey__");
		return privateKey;
	}

	private void encryptPrepareProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			EncryptRSA rsa = new EncryptRSA();
			session.setAttribute("__rsaPrivateKey__", rsa.getPrivateKey());
			req.setAttribute("publicKeyModulus", rsa.getPublicKeyModulus());
			req.setAttribute("publicKeyExponent", rsa.getPublicKeyExponent());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			forward(req, resp, e.getMessage());
		}
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/loginFailure.jsp");
		rd.forward(request, response);
	}
}
