package jado.controller;

import jado.dao.ShopDao;
import jado.dao.UserDao;
import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import core.exception.DuplicateUserException;
import core.exception.PasswordMismatchException;
import core.mail.Mail;
import core.mail.MailSender;
import core.mail.template.MailTemplateStorage;
import core.util.DecryptRSA;
import core.util.EncryptRSA;
import core.util.ServletRequestUtils;

//@WebServlet("/user")
@Controller
public class SignUpController extends HttpServlet {
	
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
//		String url = ServletRequestUtils.getRequiredStringParameter(req, "url");
		String url = req.getParameter("url");
		
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
		req.getRequestDispatcher("/signUp.jsp").forward(req,  resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String url = ServletRequestUtils.getRequiredStringParameter(req, "url");

		//Customer
		String userId = ServletRequestUtils.getRequiredStringParameter(req,"idEncryption");
		String password = ServletRequestUtils.getRequiredStringParameter(req,"pwEncryption");
		String name = ServletRequestUtils.getRequiredStringParameter(req,"name");
		String phone = ServletRequestUtils.getRequiredStringParameter(req,"phone");
		String address = ServletRequestUtils.getRequiredStringParameter(req,"address");
		
		PrivateKey privateKey = (PrivateKey)session.getAttribute("__rsaPrivateKey__");
		session.removeAttribute("__rsaPrivateKey__");
		
		try {
			DecryptRSA rsa = new DecryptRSA(privateKey);
			userId = rsa.decryptRsa(userId);
			password = rsa.decryptRsa(password);
			
		} catch (InvalidKeyException |NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
			forward(req, resp, e.getMessage());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
			forward(req, resp, e.getMessage());
		}
		
		
		
		Customer user = new Customer(userId, password, name, phone, address);
		
		try{
			user.signUp();
			req.setAttribute("userId", userId); 
		} catch(DuplicateUserException | PasswordMismatchException e){
			forward(req, resp, e.getMessage());
		} 

		//Seller
		if (req.getParameter("isSeller") != null) {
			String shopUrl = ServletRequestUtils.getRequiredStringParameter(req,"shopUrl");
			String shopPhone = ServletRequestUtils.getRequiredStringParameter(req,"shopPhone");
			String bank = ServletRequestUtils.getRequiredStringParameter(req,"bank");
			String bankAccount = ServletRequestUtils.getRequiredStringParameter(req,"bankAccount");
			
			Shop shop = new Shop(shopUrl, shopPhone);
			Seller seller = new Seller(userId, shopUrl, bank, bankAccount);
			
			shopDao.insert(shop);
			userDao.insert(seller);
		}
		
		MailSender.send(new Mail(userId, MailTemplateStorage.Type.JOIN_VERIFY));
		resp.sendRedirect("/");
	}

	private void forward(HttpServletRequest req, HttpServletResponse resp, String errorMessage)
			throws ServletException, IOException {
		req.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = req.getRequestDispatcher("/");
		rd.forward(req, resp);
	}
}
