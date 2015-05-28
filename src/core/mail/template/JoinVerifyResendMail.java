package core.mail.template;


import jado.dao.MailAuthDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.mail.UUIDGenerator;

@Component
public class JoinVerifyResendMail extends JoinVerifyMail {

	@Autowired
	private MailAuthDao mailDao;
	
	@Override
	protected String setVerifyKeyOnDB(String mailRecipient) {
		String uuid = UUIDGenerator.createUUID();
		mailDao.update(mailRecipient, uuid);
		return uuid;
	}
}
