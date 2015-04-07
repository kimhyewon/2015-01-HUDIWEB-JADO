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
		if(!encryptPrepareProcess(session, model).isSuccess()) {
			return "encryptedReadyFailure";
		}
		
		return "main";
	}
	
	private Result encryptPrepareProcess(HttpSession session, Model model) {
		try {
			EncryptRSA rsa = new EncryptRSA();
			session.setAttribute("__rsaPrivateKey__", rsa.getPrivateKey());
			model.addAttribute("publicKeyModulus", rsa.getPublicKeyModulus());
			model.addAttribute("publicKeyExponent", rsa.getPublicKeyExponent());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			session.removeAttribute("__rsaPrivateKey__");
			model.addAttribute("errorMessage", e.getMessage());
			return new Result(false, e.getMessage());
		}
		return new Result(true);
	}
}
