package core.mail;

public class MailSender {
	public static void send(Mail mail) {
		Thread mailSendThread = new MailSendThread(mail);
		mailSendThread.start();
	}
}
