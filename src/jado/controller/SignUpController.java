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
import core.util.ServletRequestUtils;

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
		String userId = ServletRequestUtils.getRequiredStringParameter(req,"userId");
		String password = ServletRequestUtils.getRequiredStringParameter(req,"password");
		String checkPassword = ServletRequestUtils.getRequiredStringParameter(req,"checkPassword");
		String name = ServletRequestUtils.getRequiredStringParameter(req,"name");
		String phone = ServletRequestUtils.getRequiredStringParameter(req,"phone");
		String address = ServletRequestUtils.getRequiredStringParameter(req,"address");
		
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
			String shopUrl = ServletRequestUtils.getRequiredStringParameter(req,"shopUrl");
			String shopPhone = ServletRequestUtils.getRequiredStringParameter(req,"shopPhone");
			String bank = ServletRequestUtils.getRequiredStringParameter(req,"bank");
			String bankAccount = ServletRequestUtils.getRequiredStringParameter(req,"bankAccount");
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
