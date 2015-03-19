package jado.controller;

import jado.dao.UserDao;
import jado.model.NormalUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/edit")
public class EditUserController extends HttpServlet{
	UserDao userdao = new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");

		NormalUser user = userdao.findUser(userId); 
		req.setAttribute("user", user);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String userId = req.getParameter("userId");
		
		//userdao.update();
		
	}
}
