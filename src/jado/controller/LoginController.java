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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.exception.IsNotValidatedMail;
import core.exception.PasswordMismatchException;
import core.exception.UserNotFoundException;
import core.util.DecryptRSA;
import core.util.EncryptRSA;
import core.util.ServletRequestUtils;

@WebServlet("/user/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String url = req.getParameter("url");
		System.out.println(url);

		try {
			EncryptRSA rsa = new EncryptRSA();
			session.setAttribute("__rsaPrivateKey__", rsa.getPrivateKey());
			req.setAttribute("publicKeyModulus", rsa.getPublicKeyModulus());
			req.setAttribute("publicKeyExponent", rsa.getPublicKeyExponent());

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			forward(req, resp, e.getMessage());
		}

		req.setAttribute("url", url);
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String userId = ServletRequestUtils.getRequiredStringParameter(request,
				"idEncryption");
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
		
		Customer customer = new Customer(userId, password);
		try {
			customer.login();
			session.setAttribute("userId", customer.getUserId());

			if (UserDao.selectSellerById(userId) != null) {
				session.setAttribute("isSeller", true);
			}
//			if (url == null || url == "") {
//				response.sendRedirect("/");
//			}
			response.sendRedirect("/");

		} catch (UserNotFoundException | PasswordMismatchException | IsNotValidatedMail e) {
			forward(request, response, e.getMessage());
		}
	}

	private void forward(HttpServletRequest request,
			HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = request.getRequestDispatcher("/loginFailure.jsp");
		rd.forward(request, response);

	}
}
