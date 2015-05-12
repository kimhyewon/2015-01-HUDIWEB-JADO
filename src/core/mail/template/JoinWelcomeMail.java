package core.mail.template;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JoinWelcomeMail extends AbstractMailTemplate {
	
	public JoinWelcomeMail() {
		subject = "[Ne #] 서비스 가입을 축하합니다";
		templateLocation ="./velocity/joinWelcomeMail.vm";
	}
	
	@Override
	protected void getModel(Map<String, Object> mailParameterMap) {
		model.put("addressToMain", mailRequestAddress+"/");
		model.put("mailRecipient", (String)mailParameterMap.get("mailRecipient"));
	}
}
