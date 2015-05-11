package core.mail.template;

import java.util.Map;

public interface MailTemplate {
	String getSubject();
	String getBody(Map<String, Object> mailParameterMap);
}
