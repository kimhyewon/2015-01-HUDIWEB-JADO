package jado.controller;

import jado.dao.MailAuthDao;
import jado.dao.UserDao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mail.Mail;
import core.mail.MailSender;
import core.util.ServletRequestUtils;

@WebServlet("/user/mailAuth")
public class MailAuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MailAuthController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO 공통 에러 페이지가 만들어 지면 리다이렉트 시키기
		resp.getWriter().print("<h1>Invalid  access</h1>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userEmail = ServletRequestUtils.getRequiredStringParameter(request, "requestemail");
		String uuid = ServletRequestUtils.getRequiredStringParameter(request, "requestuuid");
		
		MailAuthDao mailAuthDao = new MailAuthDao();
		
		if(mailAuthDao.isAlreadyVerified(userEmail)) {
			response.getWriter().print("<h1>Your email is Already authenticated</h1>");
			logger.debug("메일 중복 인증 시도가 발생함");
			return;
		}
		
		if(mailAuthDao.verify(userEmail, uuid))
		{
			new UserDao().updateMailAuthStatus();
			MailSender.send(new Mail(userEmail, "joinWelcome"));

			// TODO 인증 성공 페이지로 이동
			response.getWriter().print("<h1>Your email is successfully authenticated</h1>");
			logger.debug("메일 인증 성공");
			
		} else {
			// TODO 인증 실패 페이지로 이동
			response.getWriter().print("<h1>Your e-mail authentication failed</h1>");
			logger.debug("메일 인증 실패");
		}
	}
}
