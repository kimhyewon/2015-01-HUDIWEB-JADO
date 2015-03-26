package core.mail.template;

import jado.dao.MailAuthDao;
import core.mail.RequestResult;
import core.mail.UUIDGenerator;

public class JoinVerifyMail extends MailTemplate {
	String subject = "[JADO] 서비스 가입을 위한 이메일 인증 메일입니다";
	
	@Override
	public String getBody(String mailRecipient) {
		String uuid = setVerifyKeyOnDB(mailRecipient);
		return  getTemplate(mailRecipient, uuid);
	}

	private String setVerifyKeyOnDB(String mailRecipient) {
		String uuid = UUIDGenerator.createUUID();
		MailAuthDao dao = new MailAuthDao();
		dao.insert(mailRecipient, uuid);
		return uuid;
	}

	private String getTemplate(String mailRecipient, String uuid) {
		return ""
				+" <h1>JADO 인증</h1>"
				+ "<form style='' action='"
				+ RequestResult.MailingRequestAddress
				+ "' method='post' id='verify'>"
				+ "<input type='hidden' name='requestemail' value='"
				+ mailRecipient
				+ "'>"
				+ "<input type='hidden' name='requestuuid' value='"
				+ uuid
				+ "'>"
				 + "<input type='submit' value='인증하기'>";
	}
	
	@Override
	public String getSubject() {
		return subject;
	}
}
