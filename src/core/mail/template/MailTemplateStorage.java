package core.mail.template;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailTemplateStorage {
	private static final Logger logger = LoggerFactory.getLogger(MailTemplateStorage.class);
	
	@Autowired
	private JoinVerifyMail joinVerifyMail;
	@Autowired
	private JoinWelcomeMail joinWelcomeMail;
	
	private  Map<Type, AbstractMailTemplate> templates = new HashMap<>();
	
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
	
	public MailTemplateStorage() {
//		[TODO] auto wired 했는데 값은 null 이다
		logger.debug("joinVerifyMail {}",joinVerifyMail.toString()); 
		templates.put(Type.JOIN_VERIFY, joinVerifyMail);
		templates.put(Type.JOIN_WELCOME, joinWelcomeMail);

	}

	public AbstractMailTemplate getTemplate(Type joinVerify) {
		return templates.get(joinVerify);
	}
}
