package core.mail.template;

import core.mail.template.MailTemplateStorage.Type;

public abstract class AbstractMailTemplate implements MailTemplate {
	public String getSubject(Type joinVerify) {
		return MailTemplateStorage.getSubject(joinVerify);
	}
	
	public String getBody(String mailRecipient) {
		return  getTemplate(mailRecipient);
	}
}
