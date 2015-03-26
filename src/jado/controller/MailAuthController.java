package jado.controller;

import jado.dao.MailAuthDao;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.exception.PasswordMismatchException;
import core.exception.UserNotFoundException;
import core.mail.Mail;
import core.mail.MailSender;
import core.util.ServletRequestUtils;

@WebServlet("/user/mailAuth")
public class MailAuthController extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(MailAuthController.class);
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userEmail = ServletRequestUtils.getRequiredStringParameter(request, "requestemail");
		String uuid = ServletRequestUtils.getRequiredStringParameter(request, "requestuuid");
		
		if(new MailAuthDao().verify(userEmail, uuid))
		{
			new UserDao().updateMailAuthStatus();
			MailSender.send(new Mail(userEmail, "joinWelcome"));

			// TODO 인증 성공 페이지로 이동
			logger.debug("인증성공");
			
		} else {
			// TODO 인증 실패 페이지로 이동
			logger.debug("인증실패");
		}
	}
}
