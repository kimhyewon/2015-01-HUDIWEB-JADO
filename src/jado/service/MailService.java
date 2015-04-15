package jado.service;


import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import core.mail.Mail;
import core.mail.template.MailTemplateStorage;

@Service
public class MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	@Autowired
    private EmailSender emailSender;
	
	@Async
	public void sendJoinVerify(String userId) {
		Mail mail = new Mail(userId, MailTemplateStorage.Type.JOIN_VERIFY);
		try {
			emailSender.sendEmail(mail);
		} catch (MessagingException e) {
			logger.debug("mail error {}", e.getMessage());
			e.printStackTrace();
		}
	}


}