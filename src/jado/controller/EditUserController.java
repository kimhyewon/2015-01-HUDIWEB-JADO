package jado.controller;

import jado.dao.UserDao;
import jado.model.Customer;
import jado.model.Seller;
import jado.model.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.util.ServletRequestUtils;

@WebServlet("/user/edit")
public class EditUserController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		Customer customer = UserDao.selectUserById(userId);
		req.setAttribute("customer", customer);
		if(session.getAttribute("isSeller") != null) {
			Seller seller = UserDao.selectSellerById(userId);
			req.setAttribute("seller", seller);
		}
		resp.sendRedirect("/editUser.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		//Normal User
		String userId = (String)session.getAttribute("userId");
		String password = ServletRequestUtils.getRequiredStringParameter(req, "password");
		String name = ServletRequestUtils.getRequiredStringParameter(req, "name");
		String phone = ServletRequestUtils.getRequiredStringParameter(req, "phone");
		String address = ServletRequestUtils.getRequiredStringParameter(req, "address");

		UserDao.updateCustomer(new Customer(userId, password, name, phone, address));

		//Seller
		if (req.getParameter("isSeller") != null) {
			String shopUrl = ServletRequestUtils.getRequiredStringParameter(req, "shopUrl");
			String shopPhone = ServletRequestUtils.getRequiredStringParameter(req, "shopPhone");
			String bank = ServletRequestUtils.getRequiredStringParameter(req, "bank");
			String bankAccount = ServletRequestUtils.getRequiredStringParameter(req, "bankAccount");
//			UserDao.updateSeller(new Seller(userId, shopUrl, shopPhone, bank, bankAccount));	//고쳐야함   
		}

		resp.sendRedirect("/");
	}
}
