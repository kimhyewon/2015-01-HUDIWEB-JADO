package core.mail;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class VelocityTest {
	@Test
	public void test() {
		Properties props = new Properties();
        props.setProperty("resource.loader", "class");
        props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("mailRecipient", "javajigi@slipp.net");
		VelocityEngine velocityEngine = new VelocityEngine(props);
		String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "./velocity/joinWelcomeMail.vm", "utf-8", model);
		System.out.println("body : " + body);
	}
}
