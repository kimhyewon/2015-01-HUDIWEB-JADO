package jado.controller;

import jado.model.Notice;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.util.EncryptRSA;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewMainPage(HttpSession session, Model model) {
		if (!encryptPrepareProcess(session, model).isSuccess()) {
			model.addAttribute("notice", new Notice("Encrpted Fail", "암호화 준비중에 오류가 발생하여 요청하신 작업을 중단하였습니다."));
			return "notice";
		}
		return "main";
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String InvalidAccess(Model model) {
		model.addAttribute("notice", new Notice("Access Denied", "당신은 접근 권한이 없습니다."));
		return "notice";
	}

	private Result encryptPrepareProcess(HttpSession session, Model model) {
		EncryptRSA rsa;
		try {
			rsa = new EncryptRSA();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			return new Result(false, e.getMessage());
		}
		rsa.encrypt(session, model);
		return new Result(true);
	}
}
