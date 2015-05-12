package core.mail.template;

import java.util.Map;

import jado.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserMail extends AbstractMailTemplate {

	@Autowired
	private UserDao userDao;

	public DeleteUserMail() {
		subject = "[Ne #] 서비스 탈퇴을 위한 확인 메일입니다.";
		templateLocation = "./velocity/deleteUserMail.vm";
	}

	@Override
	protected void getModel(Map<String, Object> mailParameterMap) {
		String mailRecipient = (String) mailParameterMap.get("mailRecipient");
		model.put("addressToMain", mailRequestAddress+"/");
		model.put("updateTime", userDao.selectUserById(mailRecipient).getUpdateTime());
		model.put("deleteInfo", "회원님이 탈퇴 요청을 하셨습니다.");
		model.put("mailRecipient", mailRecipient);
	}
}
