package jado.controller;

import jado.service.MailAuthService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import core.mail.template.MailTemplateStorage.Type;

@Controller
@RequestMapping(value ="/mailAuth")
public class MailAuthController {

	@Autowired
	private MailAuthService mailAuthService;

	@RequestMapping(method = RequestMethod.GET)
	public String InvalidAccess(Model model) {
		model.addAttribute("title", "Invalid  access");
		model.addAttribute("message", "당신은 접근 권한이 없습니다.");
		return "notice";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processMailAuth(@RequestParam("requestemail") String userEmail, @RequestParam("requestuuid") String uuid, Model model) {
		if (mailAuthService.isAlreadyVerified(userEmail)) {
			model.addAttribute("title", "Already Success");
			model.addAttribute("message", "당신은 이미 인증된 회원 입니다.");
			return "notice";
		}
		
		if (!mailAuthService.verify(userEmail, uuid)) {
			model.addAttribute("title", "E-mail authentication failed");
			model.addAttribute("message", "이메일 인증에 실폐하셨습니다. 다시 시도해 주시기 바랍니다.");
			return "notice";
		}

		mailAuthService.updateTypeOfUserRole(userEmail);
		Map<String, Object> mailParameterMap = new HashMap<>();
		mailParameterMap.put("mailRecipient", userEmail);
		mailAuthService.send(mailParameterMap, Type.JOIN_WELCOME);

		model.addAttribute("title", "Success");
		model.addAttribute("message", "이메일 인증에 성공하셨습니다. 감사합니다.");
		return "notice";
	}
}
