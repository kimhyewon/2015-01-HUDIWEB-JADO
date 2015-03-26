package core.mail.template;

public class JoinWelcomeMail extends MailTemplate {
	String subject = "[JADO] 서비스 가입을 축하합니다";

	@Override
	public String getSubject() {
		return subject;
	}

	@Override
	public String getBody(String mailRecipient) {
		return  mailRecipient+"님 회원가입 회원가입을 축하합니다"
			+ "회원님의 메일이 정상적으로 인증되었습니다";
	}
}
