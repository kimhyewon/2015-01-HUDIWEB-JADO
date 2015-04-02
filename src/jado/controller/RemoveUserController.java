package jado.controller;

import jado.dao.UserDao;
import jado.model.Customer;
import jado.model.Seller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.util.ServletRequestUtils;

@WebServlet("/user/delete")
public class RemoveUserController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/blogDummy.jsp").forward(req,  resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		HttpSession session = req.getSession();
		
		String userId = (String)session.getAttribute("userId");
		String password = (String)session.getAttribute("password");
		
		Boolean isSeller = (Boolean)session.getAttribute("isSeller");
//		System.out.println(isSeller);
		
		if(req.getParameter("isSeller") != null){
			UserDao.removeSeller(userId);
		}
		UserDao.removeCustomer(userId);
		

		
		resp.sendRedirect("/blogDummy.jsp");
	}
}
