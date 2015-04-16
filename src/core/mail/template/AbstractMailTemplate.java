package core.mail.template;

import org.springframework.stereotype.Component;


@Component
public abstract class AbstractMailTemplate implements MailTemplate {
	String subject;
	
	public String getSubject() {
		return subject;
	}

	public String getBody(String mailRecipient) {
		return  getTemplate(mailRecipient);
	}
}
