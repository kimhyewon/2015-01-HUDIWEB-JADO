package jado.controller;

import jado.model.NormalUser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		
		if (req.getParameter("a") != null) {
			
		} else {
			
		}
		NormalUser user = new NormalUser(userId, password, name, phone, address);
		
		resp.sendRedirect("/");
		
	}
}
