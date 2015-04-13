package jado.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import jado.model.Customer;
import jado.model.Seller;
import jado.service.EditUserService;
import jado.service.RemoveUserService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import core.util.EncryptRSA;

@Controller
@RequestMapping(value = "/user")
public class UserController  {
	
	@Autowired private RemoveUserService removeUserService;
	@Autowired private EditUserService editUserService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String viewEditUserInfoPage(HttpSession session, Model model) {
		String userId = (String) session.getAttribute("userId");
		Customer customer = editUserService.selectUserById(userId);
		model.addAttribute("customer", customer);
				
		if(session.getAttribute("isSeller") != null) {
			Seller seller = editUserService.selectSellerById(userId);
			model.addAttribute("seller", seller);
		}
		
		if(!encryptPrepareProcess(session, model).isSuccess()) {
			return "encryptedReadyFailure";
		}
		return "editUser";
	}
	
	// TODO [To. 우재우님] - 우재우님 아래에 기재된 사항 확인부탁드립니다.
	// EditUserController 최초 생성시 shopPhone이라는 변수를 사용하셨는데
	// 실제로 수정하면서 보니 해당변수가 unused상태임을 확인하였습니다.
	// 그런데 DB 스키마를 보면 필요할 것 같은 데이터인데 어떻게 처리해야 할 지 모르겠습니다. 확인부탁드립니다.
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String viewRemoveUserPage() {
		// TODO [우선순위 : 보통] - 이부분은 추후 블로그 개설이 가능해지게 되면 수정이 필요한 부분입니다.
		return "blogDummy";
	}
	
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public String processUserDelete(@RequestParam("userId") String userId, HttpSession session) {
		if(session.getAttribute("isSeller") != null){
			removeUserService.removeSeller(userId);
		}
		removeUserService.removeCustomer(userId);
		
		// TODO [우선순위 : 보통] - 회원 탈퇴 후에는 Session에 있는 모든 정보를 삭제하는것이 더 좋을것 같다는 생각이 듬
		// 또한 그냥 blog로 돌아가는것이 아니라 회원탈퇴가 성공적으로 처리되었음을 알려주는 페이지로 이동하는것이 좋을 듯
		session.invalidate();
		return "blogDummy";
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
