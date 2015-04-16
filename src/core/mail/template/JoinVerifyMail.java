package core.mail.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jado.dao.MailAuthDao;
import core.mail.UUIDGenerator;

@Component
public class JoinVerifyMail extends AbstractMailTemplate {
	
	@Autowired
	private String mailingRequestAddress;
	 
	public JoinVerifyMail() {
		subject = "[Ne #] 서비스 가입을 위한 이메일 인증 메일입니다";
	}
	
	public void setMailRequestAddress(String mailingRequestAddress) {
		this.mailingRequestAddress = mailingRequestAddress;
	}

	@Override
	public String getTemplate(String mailRecipient) {
		String uuid = setVerifyKeyOnDB(mailRecipient);
		return getTemplate(mailRecipient, uuid);
	}

	private String setVerifyKeyOnDB(String mailRecipient) {
		String uuid = UUIDGenerator.createUUID();
		MailAuthDao dao = new MailAuthDao();
		dao.insert(mailRecipient, uuid);
		return uuid;
	}
	
	private String getTemplate(String mailRecipient, String uuid) {
		return ""
				+"<div class='mail' style='box-sizing: border-box;width: 100%;margin: 0 auto;min-width: 320px !important;font-family:'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif'>"
				+"	<div class='top-line' style='box-sizing: border-box;width: 100%;height: 15px;background-color: #AD1457;'></div>"
				+"	<div class='header' style='box-sizing: border-box;width: 100%;height: 50px;line-height: 53px;background-color: #DC5361;color: #fff;font-size: 2.8em;padding: 0px 15px;font-weight: bold;letter-spacing: 2px;'>Ne<sup style='font-size: 0.7em;padding: 0px 5px;'>#</sup></div>"
				+"	<div class='subject' style='box-sizing: border-box;background-color: #eee;height: 35px;line-height: 35px;padding: 0px 15px;'>서비스 가입 이메일 인증 메일</div>"
				+"	<div class='body' style='box-sizing: border-box;width: 95%;height: 300px;margin: 10px auto;box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);padding: 5%;'>"
				+"		서비스를 이용하기 위해서는 회원님께서 입력하신 메일 주소에 대한 인증이 필요합니다.<br><br>"
				+"		아래의 인증하기 버튼을 통해 메일주소를 인증하시고 회원가입을 완료하세요!"
			
				+"		<form style='' action='" + mailingRequestAddress +"' method='post'>"
				+"			<input type='hidden' name='requestemail' value='" + mailRecipient + "'>"
				+"			<input type='hidden' name='requestuuid' value='" + uuid + "'>"
				+"			<input type='submit' value='인증하기' style='height:40px; padding: 10px 80px;font-size: 1.1em;display: block;background-color: #FF6273;border: 0px;color: #fff;margin-top: 30px;'>"
				+"		</form>"
				+"	</div>"
				+"	<div class='footer' style='box-sizing: border-box;background-color: #353840;width: 100%;padding: 5%;color: #9E9E9E;overflow: hidden;'>"
				+"	본 메일은 발송전용 메일입니다.<br>"
				+"	회신을 통한 문의는 처리되지 않습니다<br>"
				+"	<span style='float: right;padding: 10px 10px 0px 0px;'>From Ne#</span></div>"
				+"</div>";
	}
}
