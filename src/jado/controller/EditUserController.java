package jado.controller;

import jado.model.Customer;
import jado.model.Seller;
import jado.service.EditUserService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditUserController {
	
	@Autowired private EditUserService editUserService;

	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public String viewEditUserInfoPage(HttpSession session, Model model) {
		String userId = (String) session.getAttribute("userId");
		Customer customer = editUserService.selectUserById(userId);
		model.addAttribute("customer", customer);
		
		if(session.getAttribute("isSeller") != null) {
			Seller seller = editUserService.selectSellerById(userId);
			model.addAttribute("seller", seller);
		}
		
		return "editUser";
	}
	
	// TODO [To. 우재우님] - 우재우님 아래에 기재된 사항 확인부탁드립니다.
	// EditUserController 최초 생성시 shopPhone이라는 변수를 사용하셨는데
	// 실제로 수정하면서 보니 해당변수가 unused상태임을 확인하였습니다.
	// 그런데 DB 스키마를 보면 필요할 것 같은 데이터인데 어떻게 처리해야 할 지 모르겠습니다. 확인부탁드립니다.
	@RequestMapping(value = "/user/edit", method = RequestMethod.POST)
	protected String editUserInfo(@RequestParam("pwEncryption") String password, @RequestParam("name") String name, 
			@RequestParam("phone") String phone, @RequestParam("address") String address, 
			String shopUrl, String shopPhone, String bank, String bankAccount, HttpSession session) {
		// Normal User
		String userId = (String)session.getAttribute("userId");
		editUserService.updateCustomer(new Customer(userId, password, name, phone, address));

		// Seller
		if (session.getAttribute("isSeller") != null) {
			editUserService.updateSeller(new Seller(userId, shopUrl, bank, bankAccount));
		}

		return "main";
	}
}
