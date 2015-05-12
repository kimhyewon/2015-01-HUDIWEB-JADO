package jado.controller;

import jado.model.Notice;
import jado.service.MailAuthService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import core.mail.template.MailTemplateStorage.Type;
import core.util.ModelAndViewUtils;

@Controller
@RequestMapping(value = "/mailAuth")
public class MailAuthController {

	@Autowired
	private MailAuthService mailAuthService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView InvalidAccess(Model model) {
		return ModelAndViewUtils.renderToNotice(new Notice("Invalid  access", "당신은 접근 권한이 없습니다."));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processMailAuth(@RequestParam("requestemail") String userEmail, @RequestParam("requestuuid") String uuid) {
		if (mailAuthService.isAlreadyVerified(userEmail)) {
			return ModelAndViewUtils.renderToNotice(new Notice("Already Success", "당신은 이미 인증된 회원 입니다."));
		}

		if (!mailAuthService.verify(userEmail, uuid)) {
			return ModelAndViewUtils.renderToNotice(new Notice("E-mail authentication failed", "이메일 인증에 실폐하셨습니다. 다시 시도해 주시기 바랍니다."));
		}

		mailAuthService.updateTypeOfUserRole(userEmail);
		Map<String, Object> mailParameterMap = new HashMap<>();
		mailParameterMap.put("mailRecipient", userEmail);
		mailAuthService.send(mailParameterMap, Type.JOIN_WELCOME);

		return ModelAndViewUtils.renderToNotice(new Notice("Success", "이메일 인증에 성공하셨습니다. 감사합니다."));
	}
}
