package core.mail.template;

import core.mail.MailType;

public abstract class MailTemplate {
	public String getSubject(MailType joinVerify) {
		return MailTemplateStorage.getSubject(joinVerify);
	}
	
	public String getBody(String mailRecipient) {
		return  getTemplate(mailRecipient);
	}
	
	abstract protected String getTemplate(String mailRecipient);
}
