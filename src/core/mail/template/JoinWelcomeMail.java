package core.mail.template;

import org.springframework.stereotype.Component;

@Component
public class JoinWelcomeMail extends AbstractMailTemplate {
	
	public JoinWelcomeMail() {
		subject = "[Ne #] 서비스 가입을 축하합니다";
		templateLocation ="./velocity/joinWelcomeMail.vm";
	}
	
	@Override
	protected void getModel(String mailRecipient) {
		model.put("mailRecipient", mailRecipient);
	}
}
