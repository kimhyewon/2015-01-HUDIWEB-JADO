package jado.controller;

import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;
import jado.service.SignUpService;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import core.exception.DuplicateUserException;
import core.exception.PasswordMismatchException;
import core.mail.Mail;
import core.mail.MailSender;
import core.mail.template.MailTemplateStorage;
import core.util.DecryptRSA;
import core.util.EncryptRSA;

@Controller
public class SignUpController  {
	
	@Autowired
	private SignUpService signUpService;
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String userGet(String returnUrl, Model model, HttpSession session){
		
		model.addAttribute("url", returnUrl);
		
		if(!encryptPrepareProcess(session, model).isSuccess()) {
			return "errorCommon";
		}
		return "signUp";
	}

	// TODO [To. 우재우님] - 우재우님 아래에 기재된 사항 확인부탁드립니다.
	// EditUserController 최초 생성시 shopPhone이라는 변수를 사용하셨는데
	// 실제로 수정하면서 보니 해당변수가 unused상태임을 확인하였습니다.
	// 그런데 DB 스키마를 보면 필요할 것 같은 데이터인데 어떻게 처리해야 할 지 모르겠습니다. 확인부탁드립니다.
	@RequestMapping(value="/user", method=RequestMethod.POST)
	protected String userPost(@RequestParam("idEncryption") String userId, @RequestParam("pwEncryption") String password,
			@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address,
			String returnUrl, String shopUrl, String shopPhone, String bank, String bankAccount, HttpSession session, Model model) {
		
		model.addAttribute("url", returnUrl);
		
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		session.removeAttribute("__rsaPrivateKey__");
		
		try {
			DecryptRSA rsa = new DecryptRSA(privateKey);
			userId = rsa.decryptRsa(userId);
			password = rsa.decryptRsa(password);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			// TODO [우선순위 : 보통이상] - 암호화 준비 및 복호화시 오류 발생하면 이동할 공통적인 페이지가 필요할 듯
			// 지금은 더 급한 작업들이 있어서 우선 loginFailure페이지로 보냈음
			return "loginFailure";
		}
		
		Customer user = new Customer(userId, password, name, phone, address);
		
		try{
			signUpService.insertCustomer(user);
			model.addAttribute("userId", userId);
		} catch(DuplicateUserException | PasswordMismatchException e){
			// TODO 오류 메시지만 출력해 줄 수 있는 공통 에러 처리 페이지를 만들 필요가 있을 듯
			// 우선은 일단 loginFailure로 보냄
			return "loginFailure";
		} 

		//Seller
		if (session.getAttribute("isSeller") != null) {
			Shop shop = new Shop(shopUrl, shopPhone);
			Seller seller = new Seller(userId, shopUrl, bank, bankAccount);
			
			signUpService.insertShop(shop);
			signUpService.insertSeller(seller);
		}
		
		MailSender.send(new Mail(userId, MailTemplateStorage.Type.JOIN_VERIFY));
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
