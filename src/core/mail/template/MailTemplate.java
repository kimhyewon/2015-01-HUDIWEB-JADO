package core.mail.template;

public abstract class MailTemplate {
	public String getSubject(String mailTemplate) {
		return MailSubject.getSubject(mailTemplate);
	}
	
	public String getBody(String mailRecipient) {
		return  getTemplate(mailRecipient);
	}
	
	abstract protected String getTemplate(String mailRecipient);
}
