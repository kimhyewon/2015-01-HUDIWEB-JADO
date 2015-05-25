package jado.controller;

import jado.model.Customer;
import jado.model.Notice;
import jado.model.Seller;
import jado.model.Shop;
import jado.service.MailAuthService;
import jado.service.UserService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import core.mail.template.MailTemplateStorage;
import core.util.AuthorityManager;
import core.util.ModelAndViewUtils;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private MailAuthService mailService;
	@Autowired
	private AuthorityManager authorityManager;
	
	// 회원가입 페이지로 이동
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView userGet(Notice notice) {
		return ModelAndViewUtils.render("signUp", notice);
	}

	// 회원 정보수정 페이지로 이동
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView viewEditUserInfoPage(HttpSession session, Notice notice, ModelAndView mav) {
		String userId = (String) session.getAttribute("userId");
		mav.addAllObjects(userService.getUserInfo(userId));
		return ModelAndViewUtils.render("editUser", notice, mav);
	}

	// 회원 정보 삭제 요청
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView processeUserPage(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		userService.updateDeleteUser(userId);
		session.invalidate();
		mailService.send(userId, MailTemplateStorage.Type.DELETE_USER);
		return ModelAndViewUtils.renderToNotice(new Notice("Success", "탈퇴된줄 알았지? 넌 우리 서비스에서 벚어날수 없어"));
	}

	// 회원 로그인 페이지로 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView viewLoginPage(Notice notice) {
		return ModelAndViewUtils.render("login", notice);
	}

	// 회원가입 요청
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView userPost(@RequestParam("j_username") String userId, @RequestParam("j_password") String password, Customer user, Shop shop, Seller seller,
			String isSeller, Notice notice) {
		if (notice.getHeader() != null) {
			return ModelAndViewUtils.renderToNotice(notice);
		}
		user.setId(userId);
		user.setPassword(password);
		seller.setId(user.getId());
		shop.setUrl(seller.getShopUrl());

		try {
			userService.insertCustomer(user);
			if (isSeller != null) {
				userService.setSellerInfo(shop, seller);
			}
		} catch (DuplicateKeyException e) {
			return ModelAndViewUtils.renderToNotice(new Notice("Error", e.getMessage()));
		}
		mailService.send(userId, MailTemplateStorage.Type.JOIN_VERIFY);
		return ModelAndViewUtils.renderToNotice(new Notice("Success", userId + " 님, 회원가입이 요청 이메일을 보냈습니다. "));
	}

	// 회원 정보 수정 요청
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editUserInfo(Customer user, Shop shop, Seller seller, String isSeller, HttpSession session, Notice notice) {
		if (notice.getHeader() != null) {
			return ModelAndViewUtils.renderToNotice(notice);
		}

		String userId = (String) session.getAttribute("userId");
		user.setId(userId);
		seller.setId(userId);
		shop.setUrl(seller.getShopUrl());
		
		/*
		 * 이 부분은 원자적으로 처리될 필요가 있을것 같아요
		 * 제 생각엔 CustomerService를 만들고 
		 * CustomerService#updateCustomer 로 하면 좋을 것 같아요!!
		 */
		//// 시작 ////
		try {
			userService.updateCustomer(user);
			
			if (isSeller != null){
				userService.setSellerInfo(shop, seller);
				authorityManager.setUserAuthority(user, "ROLE_SELLER");
			}
		} catch (DuplicateKeyException e) {
			return ModelAndViewUtils.renderToNotice(new Notice("Error", e.getMessage()));
		}
		//// 끝 ////
		
		return ModelAndViewUtils.renderToNotice(new Notice("Success", userId + " 님, 회원 정보를 수정 하였습니다. "));
	}

}
