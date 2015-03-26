package core.mail.template;

import java.util.HashMap;
import java.util.Map;

public class MailTemplateStorage {
	
	private static Map<String, MailTemplate> templates = new HashMap<>();
	
	static {
		templates.put("joinVerify", new JoinVerifyMail());
		templates.put("joinWelcome", new JoinWelcomeMail());
	}
	
	public static MailTemplate getMail(String type) {
		return templates.get(type);
	}
}
