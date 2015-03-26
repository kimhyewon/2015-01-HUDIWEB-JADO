package core.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSender {
	private static final Logger logger = LoggerFactory.getLogger(MailSender.class);
	static String host = MailConfig.getHost();
	static String from = MailConfig.getFrom();
	static String password = MailConfig.getPassword();
	static Properties props = System.getProperties();
			
	public static void send(Mail mail) {
		initProperties();

		String mailRecipient = mail.getMailRecipient();
		String mailBody = mail.getMailBody(); 

		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress toAddress = new InternetAddress(mailRecipient);

			message.addRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject(mail.getMailSubject());
			message.setText(mailBody, "utf-8", "html");
			
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			
			logger.info("메일이 정상적으로 발송되었습니다. email:{} uuid:{} verifyAddress:{}", mailRecipient, "uuid",
					RequestResult.MailingRequestAddress);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private static void initProperties() {
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
	}
}
