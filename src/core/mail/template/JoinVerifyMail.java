package core.mail.template;

import jado.dao.MailAuthDao;
import core.mail.EmailRequestResult;
import core.mail.UUIDGenerator;

public class JoinVerifyMail extends MailTemplate {
	
	@Override
	protected String getTemplate(String mailRecipient) {
		String uuid = setVerifyKeyOnDB(mailRecipient);
		return getTemplate(mailRecipient, uuid);
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
				+ EmailRequestResult.MailingRequestAddress
				+ "' method='post' id='verify'>"
				+ "<input type='hidden' name='requestemail' value='"
				+ mailRecipient
				+ "'>"
				+ "<input type='hidden' name='requestuuid' value='"
				+ uuid
				+ "'>"
				 + "<input type='submit' value='인증하기'>";
	}
}
