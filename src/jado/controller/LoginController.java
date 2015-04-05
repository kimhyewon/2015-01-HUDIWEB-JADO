package jado.controller;

import jado.service.LoginService;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
public class LoginController extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping("/user/login")
	public String firstSpringTest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("접속한 회원의 이전 주소 {}", req.getParameter("url"));

		HttpSession session = req.getSession();
		encryptPrepareProcess(req, resp, session);

		req.setAttribute("url", req.getParameter("url"));
		return "login";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public String loginPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String userId = ServletRequestUtils.getRequiredStringParameter(request, "idEncryption");
		String password = ServletRequestUtils.getRequiredStringParameter(request, "pwEncryption");
		String url = ServletRequestUtils.getRequiredStringParameter(request, "url");

		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		session.removeAttribute("__rsaPrivateKey__");

		try {
			DecryptRSA rsa = new DecryptRSA(privateKey);
			userId = rsa.decryptRsa(userId);
			password = rsa.decryptRsa(password);

		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
			forward(request, response, e.getMessage());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
			forward(request, response, e.getMessage());
		}

		loginService.logIn(userId, password, request, session);
		return "main";
	}
	
	private void encryptPrepareProcess(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
		try {
			EncryptRSA rsa = new EncryptRSA();
			session.setAttribute("__rsaPrivateKey__", rsa.getPrivateKey());
			req.setAttribute("publicKeyModulus", rsa.getPublicKeyModulus());
			req.setAttribute("publicKeyExponent", rsa.getPublicKeyExponent());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			forward(req, resp, e.getMessage());
		}
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = request.getRequestDispatcher("/loginFailure.jsp");
		rd.forward(request, response);

	}
}
