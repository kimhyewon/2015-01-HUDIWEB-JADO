package core.mail.template;


import java.util.Map;

import jado.dao.MailAuthDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.mail.UUIDGenerator;

@Component
public class JoinVerifyMail extends AbstractMailTemplate {

	@Autowired
	private MailAuthDao mailDao;
	
	public JoinVerifyMail() {
		subject = "[Ne #] 서비스 가입을 위한 이메일 인증 메일입니다";
		templateLocation ="./velocity/joinVerifyMail.vm";
	}

	private String setVerifyKeyOnDB(String mailRecipient) {
		String uuid = UUIDGenerator.createUUID();
		mailDao.insert(mailRecipient, uuid);
		return uuid;
	}

	protected void getModel(Map<String, Object> mailParameterMap) {
		String mailRecipient = (String)mailParameterMap.get("mailRecipient");
		String uuid = setVerifyKeyOnDB(mailRecipient);
		model.put("uuid", uuid);
		model.put("mailRequestAddress", mailRequestAddress+"/user/mailAuth");
		model.put("addressToMain", mailRequestAddress+"/");
		model.put("mailRecipient", mailRecipient);
	}
}
