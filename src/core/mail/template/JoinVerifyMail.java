package core.mail.template;


import jado.dao.MailAuthDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import core.mail.UUIDGenerator;

@Component
public class JoinVerifyMail extends AbstractMailTemplate {

	@Autowired
	private MailAuthDao mailDao;
	
	private String mailRequestAddress;
	 
	
	@Value("${mail.requestAddress}")
	public void setMailRequestAddress(String mailRequestAddress) {
		this.mailRequestAddress = mailRequestAddress;
	}
	
	public JoinVerifyMail() {
		subject = "[Ne #] 서비스 가입을 위한 이메일 인증 메일입니다";
		templateLocation ="./velocity/joinVerifyMail.vm";
	}

	private String setVerifyKeyOnDB(String mailRecipient) {
		String uuid = UUIDGenerator.createUUID();
		mailDao.insert(mailRecipient, uuid);
		return uuid;
	}

	protected void getModel(String mailRecipient) {
		String uuid = setVerifyKeyOnDB(mailRecipient);
		model.put("uuid", uuid);
		model.put("mailRequestAddress", mailRequestAddress);
		model.put("mailRecipient", mailRecipient);
		
	}
}
