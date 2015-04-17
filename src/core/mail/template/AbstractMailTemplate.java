package core.mail.template;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;


@Component
public abstract class AbstractMailTemplate implements MailTemplate {

	@Autowired
	protected VelocityEngine velocityEngine;
	
	
	protected String subject;
	protected String templateLocation;
	protected Map<String, Object> model = new HashMap<String, Object>();


	
	public String getSubject() {
		return subject;
	}

	public String getBody(String mailRecipient) {
		getModel(mailRecipient);
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation, "utf-8", model);
	}

	protected abstract void getModel(String mailRecipient);

}
