package core.mail;

import core.mail.template.AbstractMailTemplate;
import core.mail.template.MailTemplateStorage;

public class Mail {
	String mailRecipient;
	String mailSubject;
	String mailBody;
	
	public Mail(String mailRecipient, MailType joinVerify) {
		AbstractMailTemplate mt = MailTemplateStorage.getBody(joinVerify);
		
		this.mailRecipient = mailRecipient;
		this.mailSubject = mt.getSubject(joinVerify);
		this.mailBody = mt.getBody(mailRecipient);
	}
	
	public String getMailRecipient() {
		return mailRecipient;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public String getMailBody() {
		return mailBody;
	}
}
