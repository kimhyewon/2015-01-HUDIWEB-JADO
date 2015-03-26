package core.mail.template;

public abstract class MailTemplate {
	
	abstract public String getSubject();
	abstract public String getBody(String mailRecipient);
}
