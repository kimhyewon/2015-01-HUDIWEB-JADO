package core.mail;

import core.mail.template.MailTemplate;
import core.mail.template.MailTemplateStorage;

public class Mail {
	String mailRecipient;
	String mailSubject;
	String mailBody;
	
	public Mail(String mailRecipient, String mailTemplate) {
		MailTemplate mt = MailTemplateStorage.getMail(mailTemplate);
		
		this.mailRecipient = mailRecipient;
		this.mailSubject = mt.getSubject(mailTemplate);
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
