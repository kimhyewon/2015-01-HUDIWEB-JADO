package jado.controller;

import jado.service.RemoveUserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RemoveUserController  {
	
	@Autowired private RemoveUserService removeUserService;
	
	@RequestMapping(value = "/user/delete", method = RequestMethod.GET)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/blogDummy.jsp").forward(req,  resp);
	}
	
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	protected void processUserDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		HttpSession session = req.getSession();
		
		// TODO 여기 조금 이상한것 같음
		// TODO 여기 개발 담당했던 @kimhyewon 확인요청드림
		String userId = (String)session.getAttribute("userId");
		Boolean isSeller = (Boolean)session.getAttribute("isSeller");
		
		if(req.getParameter("isSeller") != null){
			removeUserService.removeSeller(userId);
		}
		removeUserService.removeCustomer(userId);
		
		resp.sendRedirect("/blogDummy.jsp");
	}
}
