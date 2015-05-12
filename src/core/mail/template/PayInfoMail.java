package core.mail.template;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PayInfoMail extends AbstractMailTemplate {
	
	public PayInfoMail() {
		subject = "[Ne #] 결제정보 안내 메일입니다";
		templateLocation ="./velocity/payInfoMail.vm";
	}

	@Override
	protected void getModel(Map<String, Object> mailParameterMap) {
		model.put("userId", mailParameterMap.get("userId"));
		model.put("productName", mailParameterMap.get("productName"));
		model.put("price", mailParameterMap.get("price"));
		model.put("cardCompany", mailParameterMap.get("cardCompany"));
		model.put("shopUrl", mailParameterMap.get("shopUrl"));
		model.put("addressToMain", mailRequestAddress+"/");
	}
}
