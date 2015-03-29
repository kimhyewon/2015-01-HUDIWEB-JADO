package core.mail.template;

import java.util.HashMap;
import java.util.Map;

import core.mail.MailType;

public class MailTemplateStorage {
	
	private static Map<String, AbstractMailTemplate> templates = new HashMap<>();
	private static Map<String, String> mailSubjects = new HashMap<>();
	
	static {
		templates.put("joinVerify", new JoinVerifyMail());
		templates.put("joinWelcome", new JoinWelcomeMail());
	}
	
	static {
		mailSubjects.put("joinVerify", "[Ne #] 서비스 가입을 위한 이메일 인증 메일입니다");
		mailSubjects.put("joinWelcome", "[Ne #] 서비스 가입을 축하합니다");
	}
	
	public static String getSubject(MailType mailType) {
		return mailSubjects.get(mailType.getValue());
	}
	
	public static AbstractMailTemplate getBody(MailType joinVerify) {
		return templates.get(joinVerify.getValue());
	}
}
