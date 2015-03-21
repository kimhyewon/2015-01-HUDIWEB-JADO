package jado.controller;

import jado.dao.UserDao;
import jado.model.Customer;
import jado.model.Seller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.exception.DuplicateUserException;
import core.exception.PasswordMismatchException;

@WebServlet("/user")
public class SignUpController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.sendRedirect("/signUp.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//Customer
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String checkPassword = req.getParameter("checkPassword");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		
		Customer user = new Customer(userId, password, name, phone, address);
		HttpSession session = req.getSession();
		
		try{
			user.signUp(checkPassword);
			session.setAttribute("userId", userId); 
		} catch(DuplicateUserException | PasswordMismatchException e){
			forward(req, resp, e.getMessage());
		} 

		//Seller
		if (req.getParameter("isSeller") != null) {
			String shopUrl = req.getParameter("shopUrl");
			String shopPhone = req.getParameter("shopPhone");
			String bank = req.getParameter("bank");
			String bankAccount = req.getParameter("bankAccount");
			UserDao.insert(new Seller(userId, shopUrl, shopPhone, bank, bankAccount));
			session.setAttribute("isSeller", true);
		}
		resp.sendRedirect("/");
	}

	private void forward(HttpServletRequest req, HttpServletResponse resp, String errorMessage)
			throws ServletException, IOException {
		req.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = req.getRequestDispatcher("/");
		rd.forward(req, resp);
	}
}
