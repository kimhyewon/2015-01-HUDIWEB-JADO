package core.mail;


import core.mail.template.AbstractMailTemplate;


public class Mail {
	
	String mailRecipient;
	String mailSubject;
	String mailBody;
	
	public Mail(String mailRecipient, AbstractMailTemplate template) {
		this.mailRecipient = mailRecipient;
		this.mailSubject = template.getSubject();
		this.mailBody = template.getBody(mailRecipient);
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

	public void setMailRecipient(String mailRecipient) {
		this.mailRecipient = mailRecipient;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	
}
