package core.mail.template;

import java.util.HashMap;
import java.util.Map;

public class MailSubject {
	static Map<String, String> mailSubjects = new HashMap<>();
	
	static {
		mailSubjects.put("joinVerify", "[JADO] 서비스 가입을 위한 이메일 인증 메일입니다");
		mailSubjects.put("joinWelcome", "[JADO] 서비스 가입을 축하합니다");
	}
	
	public static String getSubject(String mailType) {
		return mailSubjects.get(mailType);
	}
}
