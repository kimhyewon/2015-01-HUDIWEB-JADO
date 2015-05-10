package jado.controller;

import java.util.HashMap;
import java.util.Map;

import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;
import jado.service.MailAuthService;
import jado.service.UserService;

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

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired private UserService userService;
	@Autowired private MailAuthService mailService;

	//회원가입 페이지로 이동
	@RequestMapping(method = RequestMethod.GET)
	public String userGet(ServletRequest req) {
		if (req.getAttribute("errorMessage") != null) {
			return "encryptedReadyFailure";
		}
		return "signUp";
	}

	//회원가입 요청
	@RequestMapping(method = RequestMethod.POST)
	protected String userPost(@RequestParam("j_username") String userId, @RequestParam("j_password") String password, Customer user, Shop shop, Seller seller, String isSeller, Model model,
			ServletRequest req) {

		if (req.getAttribute("errorMessage") != null) {
			return "errorCommon";
		}
		
		user.setUserId(userId);
		user.setPassword(password);
		seller.setUserId(user.getUserId());
		shop.setUrl(seller.getShopUrl());

		try {
			userService.insertCustomer(user);
			if (isSeller != null) {
				userService.setSellerInfo(shop, seller);
			}
		} catch (DuplicateKeyException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "errorCommon";
		}

		Map<String, Object> mailParameterMap = new HashMap<>();
		mailParameterMap.put("mailRecipient", userId);
		mailService.send(mailParameterMap, MailTemplateStorage.Type.JOIN_VERIFY);
		logger.info("메일 발송 요청 비동기 메소드를 실행시켰습니다");
		return "main";
	}

	//회원 정보수정 페이지로 이동 
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String viewEditUserInfoPage(HttpSession session, Model model, ServletRequest req) {
		String userId = (String) session.getAttribute("userId");
		Map<String, Object> userInfo = userService.getUserInfo(userId);
		model.addAllAttributes(userInfo);
		if (req.getAttribute("errorMessage") != null) {
			return "encryptedReadyFailure";
		}
		return "editUser";
	}

	//회원 정보 수정 요청
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	protected String editUserInfo(Customer user, Shop shop, Seller seller, String isSeller, HttpSession session, Model model, ServletRequest req) {

		if (req.getAttribute("errorMessage") != null) {
			return "errorCommon";
		}
		String userId = (String) session.getAttribute("userId");
		user.setUserId(userId);
		seller.setUserId(userId);
		shop.setUrl(seller.getShopUrl());
		userService.updateCustomer(user);
		if (isSeller != null) {
			try {
				userService.setSellerInfo(shop, seller);
			} catch (DuplicateKeyException e) {
				model.addAttribute("errorMessage", e.getMessage());
				return "errorCommon";
			}
		}
		return "main";
	}

	//회원 정보 삭제 요청 
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String processeUserPage(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		userService.updateDeleteUser(userId);
		session.invalidate();

		Map<String, Object> mailParameterMap = new HashMap<>();
		mailParameterMap.put("mailRecipient", userId);
		mailService.send(mailParameterMap, MailTemplateStorage.Type.DELETE_USER);
		logger.info("메일 발송 요청 비동기 메소드를 실행시켰습니다");
		// TODO [우선순위 : 보통]
		// 또한 그냥 main로 돌아가는것이 아니라 회원탈퇴가 성공적으로 처리되었음을 알려주는 페이지로 이동하는것이 좋을 듯
		return "main";
	}

	//회원 로그인 페이지로 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String viewLoginPage(Model model) {
		return "login";
	}

}
