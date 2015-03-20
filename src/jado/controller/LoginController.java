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



@WebServlet("/user/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/login.jsp").forward(req,  resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		try {
			Customer.login(userId, password);
			HttpSession session = request.getSession(); //이 줄 추가 
			session.setAttribute("userId", userId); 

			if(UserDao.selectSellerById(userId) != null) {
				session.setAttribute("isSeller", true);
			}
			response.sendRedirect("/html/blogDummy.html");
		} catch (UserNotFoundException e) {
			request.setAttribute("errorMessage", "존재하지 않는 사용자 입니다. 다시 로그인하세요.");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		} catch (PasswordMismatchException e) {
			request.setAttribute("errorMessage", "비밀번호가 틀렸습니다. 다시 로그인하세요.");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
	}
}
