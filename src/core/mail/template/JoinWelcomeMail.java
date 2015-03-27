package core.mail.template;

public class JoinWelcomeMail extends MailTemplate {

	@Override
	protected String getTemplate(String mailRecipient) {
		return  ""
				+"<div class='mail' style='box-sizing: border-box;width: 100%;margin: 0 auto;min-width: 320px !important;'>"
				+"	<div class='top-line' style='box-sizing: border-box;width: 100%;height: 15px;background-color: #AD1457;'></div>"
				+"	<div class='header' style='box-sizing: border-box;width: 100%;height: 50px;line-height: 53px;background-color: #DC5361;color: #fff;font-size: 2.8em;padding: 0px 15px;font-weight: bold;letter-spacing: 2px;'>Ne<sup style='font-size: 0.7em;padding: 0px 5px;'>#</sup></div>"
				+"	<div class='subject' style='box-sizing: border-box;background-color: #eee;height: 35px;line-height: 35px;padding: 0px 15px;'>서비스 가입을 환영합니다</div>"
				+"	<div class='body' style='box-sizing: border-box;width: 95%;height: 300px;margin: 10px auto;box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);padding: 5%;'>"
				+"		"  + mailRecipient +" 회원님!<br><br>"
				+""
				+"		회원가입을 축하드립니다!<br><br>"
				+""
				+"		지금부터 Ne# 의 모든 서비스를 이용할 수 있습니다.<br><br>"
				+""
				+"		감사합니다"
				+"	</div>"
				+"	<div class='footer' style='box-sizing: border-box;background-color: #353840;width: 100%;padding: 5%;color: #9E9E9E;overflow: hidden;'>"
				+"	본 메일은 발송전용 메일입니다.<br>"
				+"	회신을 통한 문의는 처리되지 않습니다<br>"
				+"	<span style='float: right;padding: 10px 10px 0px 0px;'>From Ne#</span></div>"
				+"</div>";
	}
}
