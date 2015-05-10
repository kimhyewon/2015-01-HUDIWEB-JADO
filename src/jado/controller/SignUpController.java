package jado.controller;

import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;
import jado.service.MailAuthService;
import jado.service.SignUpService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
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
import core.util.EncryptRSA;

@Controller
public class SignUpController {
	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);
	@Autowired
	private SignUpService signUpService;
	@Autowired
	private MailAuthService mailService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewMainPage(HttpSession session, Model model) {
		if (!encryptPrepareProcess(session, model).isSuccess()) {
			return "encryptedReadyFailure";
		}
		return "main";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String userGet(ServletRequest req) {
		if (req.getAttribute("errorMessage") != null) {
			return "encryptedReadyFailure";
		}
		return "signUp";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	protected String userPost(@RequestParam("j_username") String userId, @RequestParam("j_password") String password, 
			Customer user, Shop shop, Seller seller, String isSeller, Model model,
			ServletRequest req) {
		if (req.getAttribute("errorMessage") != null) {
			return "errorCommon";
		}
		user.setUserId(userId);
		user.setPassword(password);

		try {
			signUpService.insertCustomer(user);
		} catch (DuplicateKeyException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "errorCommon";
		}

		// Seller
		if (isSeller != null) {
			logger.debug("seller {}", seller);
			logger.debug("shop {}", shop);
			seller.setUserId(user.getUserId());
			shop.setUrl(seller.getShopUrl());

			try {
				signUpService.insertShop(shop);
				signUpService.insertSeller(seller);
			} catch (DuplicateKeyException e) {
				model.addAttribute("errorMessage", e.getMessage());
				return "errorCommon";
			}
		}

		Map<String, Object> mailParameterMap = new HashMap<>();
		mailParameterMap.put("mailRecipient", userId);
		mailService.send(mailParameterMap, MailTemplateStorage.Type.JOIN_VERIFY);
		logger.info("메일 발송 요청 비동기 메소드를 실행시켰습니다");
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
