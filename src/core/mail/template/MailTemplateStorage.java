package core.mail.template;

import java.util.HashMap;
import java.util.Map;

public class MailTemplateStorage {
	
	private static Map<Type, AbstractMailTemplate> templates = new HashMap<>();
	private static Map<Type, String> mailSubjects = new HashMap<>();
	
	public static enum Type {
		// Join Type
		JOIN_VERIFY(1), JOIN_WELCOME(2);

		// 이곳에 새로운 타입의 메일들의 상수를 추가하면 됨
		// Ex) BUY_VERIFY(3), BUY_RECEIPT(4)
		
		private int value;

		private Type(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	
	static {
		templates.put(Type.JOIN_VERIFY, new JoinVerifyMail());
		templates.put(Type.JOIN_WELCOME, new JoinWelcomeMail());
	}
	
	static {
		mailSubjects.put(Type.JOIN_VERIFY, "[Ne #] 서비스 가입을 위한 이메일 인증 메일입니다");
		mailSubjects.put(Type.JOIN_WELCOME, "[Ne #] 서비스 가입을 축하합니다");
	}
	
	public static String getSubject(Type mailType) {
		return mailSubjects.get(mailType);
	}
	
	public static AbstractMailTemplate getBody(Type joinVerify) {
		return templates.get(joinVerify);
	}
}
