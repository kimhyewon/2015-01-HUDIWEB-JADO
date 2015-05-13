package jado.service;

import java.util.HashMap;
import java.util.Map;

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
import core.mail.template.MailTemplate;
import core.mail.template.MailTemplateStorage;

@Service
public class MailAuthService {
	private static final Logger logger = LoggerFactory.getLogger(MailAuthService.class);

	@Autowired
	private MailAuthDao mailAuthDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private MailTemplateStorage mailTemplateStorage;

	public boolean isAlreadyVerified(String userEmail) {
		String role = mailAuthDao.userRole(userEmail);
		if (role == null)
			return true;
		if (role.equals("ROLE_EMAIL_NOT_VERIFIED_USER"))
			return false;
		return true;
	}

	public boolean verify(String userEmail, String uuid) {
		Integer result = mailAuthDao.verify(userEmail, uuid);
		if (result == null)
			return false;
		if (result.equals(new Integer(1)))
			return true;
		return false;
	}

	public void updateTypeOfUserRole(String userEmail) {
		userDao.updateUserRole(userEmail);
		userDao.updateUserEmailValidateStatus(userEmail);
	}

	@Async
	public void send(Map<String, Object> mailParameterMap, MailTemplateStorage.Type mailType) {
		MailTemplate template = mailTemplateStorage.getTemplate(mailType);
		Mail mail = new Mail(mailParameterMap, template);

		try {
			logger.info("메일 발송 요청 작업을 Google smtp서버로 보냈습니다");
			emailSender.sendEmail(mail);
			logger.info("비동기작업으로 진행되었던 메일 발송이 완료되었습니다");
		} catch (MessagingException e) {
			logger.debug("mail error {}", e.getMessage());
		}
	}

	@Async
	public void send(String userId, MailTemplateStorage.Type mailType) {
		Map<String, Object> mailParameterMap = new HashMap<>();
		mailParameterMap.put("mailRecipient", userId);
		send(mailParameterMap, mailType);
	}
}
