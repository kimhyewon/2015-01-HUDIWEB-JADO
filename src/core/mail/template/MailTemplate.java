package core.mail.template;

public interface MailTemplate {
	String getSubject();
	String getBody(String mailRecipient);
}
