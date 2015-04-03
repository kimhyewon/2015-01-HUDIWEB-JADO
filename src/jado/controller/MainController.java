package jado.controller;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import core.util.EncryptRSA;

@Controller
public class MainController extends HttpServlet {

	@RequestMapping("/main")
	public String firstSpringTest() {
		return "main";
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		try {
			EncryptRSA rsa = new EncryptRSA();
			session.setAttribute("__rsaPrivateKey__", rsa.getPrivateKey());
			req.setAttribute("publicKeyModulus", rsa.getPublicKeyModulus());
			req.setAttribute("publicKeyExponent", rsa.getPublicKeyExponent());

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			forward(req, resp, e.getMessage());
		}
		
		req.getRequestDispatcher("/main.jsp").forward(req,  resp);
	}

	private void forward(HttpServletRequest req, HttpServletResponse resp, String errorMessage)
			throws ServletException, IOException {
		req.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = req.getRequestDispatcher("/");
		rd.forward(req, resp);
	}
}
