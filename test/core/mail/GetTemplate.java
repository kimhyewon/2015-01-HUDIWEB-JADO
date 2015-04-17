package core.mail;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import core.mail.template.AbstractMailTemplate;
import core.mail.template.MailTemplateStorage;
import core.mail.template.MailTemplateStorage.Type;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/applicationContext.xml","file:webapps/WEB-INF/jado-servlet.xml"})
public class GetTemplate {
	private static final Logger logger = LoggerFactory.getLogger(GetTemplate.class);

	@Autowired
	private MailTemplateStorage mailTemplateStorage;

	@Test
	public void insert() {
		AbstractMailTemplate template = mailTemplateStorage.getTemplate(Type.JOIN_WELCOME);
		String body = template.getBody("hello");
	}

}
