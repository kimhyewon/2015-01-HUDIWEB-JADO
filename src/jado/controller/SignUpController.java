package jado.controller;

import jado.dao.ShopDao;
import jado.dao.UserDao;
import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.exception.DuplicateUserException;
import core.exception.PasswordMismatchException;
import core.mail.Mail;
import core.mail.MailSender;
import core.mail.template.MailTemplateStorage;
import core.util.ServletRequestUtils;

@WebServlet("/user")
public class SignUpController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = ServletRequestUtils.getRequiredStringParameter(req, "url");
		
		req.setAttribute("url", url);
		req.getRequestDispatcher("/signUp.jsp").forward(req,  resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = ServletRequestUtils.getRequiredStringParameter(req, "url");

		//Customer
		String userId = ServletRequestUtils.getRequiredStringParameter(req,"userId");
		String password = ServletRequestUtils.getRequiredStringParameter(req,"pwEncryption");
		String name = ServletRequestUtils.getRequiredStringParameter(req,"name");
		String phone = ServletRequestUtils.getRequiredStringParameter(req,"phone");
		String address = ServletRequestUtils.getRequiredStringParameter(req,"address");
		
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
			String shopAddress = ServletRequestUtils.getRequiredStringParameter(req,"shopAddress");
			String bank = ServletRequestUtils.getRequiredStringParameter(req,"bank");
			String bankAccount = ServletRequestUtils.getRequiredStringParameter(req,"bankAccount");
			
			Shop shop = new Shop(shopUrl, shopPhone, shopAddress);
			Seller seller = new Seller(userId, shopUrl, bank, bankAccount);
			
			ShopDao.insert(shop);
			UserDao.insert(seller);
		}
		
		MailSender.send(new Mail(userId, MailTemplateStorage.Type.JOIN_VERIFY));
		
		resp.sendRedirect(url);
	}

	private void forward(HttpServletRequest req, HttpServletResponse resp, String errorMessage)
			throws ServletException, IOException {
		req.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = req.getRequestDispatcher("/");
		rd.forward(req, resp);
	}
}
