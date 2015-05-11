package jado.controller;

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
			return "encryptedReadyFailure";
		}
		return "main";
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String processLogin() {
		return "commons/error/401";
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
