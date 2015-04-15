package jado.service;

import javax.mail.MessagingException;

import jado.dao.MailAuthDao;
import jado.dao.UserDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import core.mail.EmailSender;
import core.mail.Mail;
import core.mail.VelocityEmailSender;
import core.mail.template.MailTemplateStorage;

@Service
public class MailAuthService {
	private static final Logger logger = LoggerFactory.getLogger(MailAuthService.class);
	
	@Autowired private MailAuthDao mailAuthDao;
	@Autowired private UserDao userDao;
	@Autowired private EmailSender emailSender;
	@Autowired private VelocityEmailSender velocityEmailSender;

	
	public boolean isAlreadyVerified(String userEmail) {
		return mailAuthDao.isAlreadyVerified(userEmail);
	}

	public boolean verify(String userEmail, String uuid) {
		return mailAuthDao.verify(userEmail, uuid);
	}

	public void updateMailAuthStatus() {
		userDao.updateMailAuthStatus();
	}
	
	
	@Async
	public void send(String mailRecipient, MailTemplateStorage.Type joinVerify) {
		Mail mail = new Mail(mailRecipient, joinVerify);
		try {
			emailSender.sendEmail(mail);
		} catch (MessagingException e) {
			logger.debug("mail error {}", e.getMessage());
			e.printStackTrace();
		}
	}
}
