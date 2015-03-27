package core.mail.template;

public class JoinWelcomeMail extends MailTemplate {

	@Override
	protected String getTemplate(String mailRecipient) {
		return  mailRecipient+"님 회원가입 회원가입을 축하합니다<br>"
				+ "회원님의 메일이 정상적으로 인증되었습니다";
	}
}
