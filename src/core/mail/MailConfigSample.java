package core.mail;

public class MailConfigSample {
	private final static String host = "smtp.gmail.com";
	private final static String from = "user_ID";
	private final static String password = "user_PW";
	
	public static String getHost() {
		return host;
	}
	
	public static String getFrom() {
		return from;
	}
	
	public static String getPassword() {
		return password;
	}
}
