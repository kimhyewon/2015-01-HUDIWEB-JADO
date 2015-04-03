package jado.controller;

import jado.dao.UserDao;
import jado.model.Customer;

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

import core.exception.IsNotValidatedMail;
import core.exception.PasswordMismatchException;
import core.exception.UserNotFoundException;
import core.util.DecryptRSA;
import core.util.EncryptRSA;
import core.util.ServletRequestUtils;

//@WebServlet("/user/login")
@Controller
public class LoginController extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private UserDao userDao;
	
	@Autowired
	public void setDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@RequestMapping("/user/login")
	public String firstSpringTest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String url = req.getParameter("url");
		System.out.println(url);

		try {
			EncryptRSA rsa = new EncryptRSA();
			session.setAttribute("__rsaPrivateKey__", rsa.getPrivateKey());
			req.setAttribute("publicKeyModulus", rsa.getPublicKeyModulus());
			req.setAttribute("publicKeyExponent", rsa.getPublicKeyExponent());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			forward(req, resp, e.getMessage());
		}

		req.setAttribute("url", url);
		return "login";
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public String loginPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String userId = ServletRequestUtils.getRequiredStringParameter(request,
				"idEncryption");
		logger.debug("받은상태 바로 : 복호화 전 {}", userId);
		String password = ServletRequestUtils.getRequiredStringParameter(
				request, "pwEncryption");
		String url = ServletRequestUtils.getRequiredStringParameter(request,
				"url");
		
		PrivateKey privateKey = (PrivateKey)session.getAttribute("__rsaPrivateKey__");
		session.removeAttribute("__rsaPrivateKey__");
		
		try {
			DecryptRSA rsa = new DecryptRSA(privateKey);
			userId = rsa.decryptRsa(userId);
			password = rsa.decryptRsa(password);
			
		} catch (InvalidKeyException |NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
			forward(request, response, e.getMessage());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
			forward(request, response, e.getMessage());
		}
		
		try {
			logger.debug("문자열인가요? {} ", userId );
			Customer user = userDao.selectCustomerById(userId);
			user.login(password);
			session.setAttribute("userId", userId);

			if (userDao.selectSellerById(userId) != null) {
				session.setAttribute("isSeller", true);
			}
//			if (url == null || url == "") {
//				response.sendRedirect("/");
//			}
//			response.sendRedirect("/");
			
		} catch (UserNotFoundException | PasswordMismatchException | IsNotValidatedMail e) {
			request.setAttribute("errorMessage", e.getMessage());
			return "loginFailure";
		} catch (Exception e) {
			return "loginFailure";
		}
		return "main";
	}

	private void forward(HttpServletRequest request,
			HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = request.getRequestDispatcher("/loginFailure.jsp");
		rd.forward(request, response);

	}
}
