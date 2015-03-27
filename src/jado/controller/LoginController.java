package jado.controller;

import jado.dao.UserDao;
import jado.model.Customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.exception.PasswordMismatchException;
import core.exception.UserNotFoundException;
import core.util.ServletRequestUtils;



@WebServlet("/user/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = ServletRequestUtils.getRequiredStringParameter(req, "url");
		
		req.setAttribute("url", url);
		req.getRequestDispatcher("/login.jsp").forward(req,  resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = ServletRequestUtils.getRequiredStringParameter(request, "userId");
		String password = ServletRequestUtils.getRequiredStringParameter(request, "pwEncryption");
		String url = ServletRequestUtils.getRequiredStringParameter(request, "url");
		try {
			Customer.login(userId, password);
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId); 

			if(UserDao.selectSellerById(userId) != null) {
				session.setAttribute("isSeller", true);
			}
			response.sendRedirect(url);
		} catch (UserNotFoundException | PasswordMismatchException e) {
			forward(request, response, e.getMessage());
		}
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String errorMessage)
		throws ServletException, IOException {
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);
		
	}
}
