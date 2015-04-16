package jado.controller;

import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;
import jado.service.MailAuthService;
import jado.service.SignUpService;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import core.mail.template.MailTemplateStorage;
import core.util.DecryptRSA;
import core.util.EncryptRSA;

@Controller
public class SignUpController  {
	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);
	@Autowired private SignUpService signUpService;
	@Autowired private MailAuthService mailService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewMainPage(HttpSession session, Model model) {
		if(!encryptPrepareProcess(session, model).isSuccess()) {
			return "encryptedReadyFailure";
		}
		return "main";
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String userGet(String returnUrl, Model model, HttpSession session){
		model.addAttribute("url", returnUrl);
		if(!encryptPrepareProcess(session, model).isSuccess()) {
			return "encrptedReadyFailure";
		}
		return "signUp";
	}

	@RequestMapping(value="/user", method=RequestMethod.POST)
	protected String userPost(@RequestParam("idEncryption") String userId, @RequestParam("pwEncryption") String password,
			@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address,
			String returnUrl, String shopUrl, String shopPhone, String bank, String bankAccount, String isSeller, HttpSession session, Model model) {
		
		model.addAttribute("url", returnUrl);
		
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		session.removeAttribute("__rsaPrivateKey__");
		
		try {
			DecryptRSA rsa = new DecryptRSA(privateKey);
			userId = rsa.decryptRsa(userId);
			password = rsa.decryptRsa(password);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			logger.debug("error : ", e.getMessage());
			return "errorCommon";
		}
		
		Customer user = new Customer(userId, password, name, phone, address);
		
		try{
			signUpService.insertCustomer(user);
		} catch(DuplicateKeyException e){
			model.addAttribute("errorMessage", e.getMessage());
			return "errorCommon";
		} 
		System.out.println(session.getAttribute("isSeller"));
		System.out.println(isSeller);
		//Seller
		if (isSeller != null) {
			Shop shop = new Shop(shopUrl, shopPhone);
			Seller seller = new Seller(userId, shopUrl, bank, bankAccount);
			try{
				signUpService.insertShop(shop);
				signUpService.insertSeller(seller);
			} catch(DuplicateKeyException e){
				model.addAttribute("errorMessage", e.getMessage());
				return "errorCommon";
			}
		}
		
		mailService.send(userId, MailTemplateStorage.Type.JOIN_VERIFY);
		return "main";
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
