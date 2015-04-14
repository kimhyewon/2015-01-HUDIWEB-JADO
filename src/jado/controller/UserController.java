package jado.controller;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;
import jado.service.EditUserService;
import jado.service.RemoveUserService;
import jado.service.SignUpService;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import core.util.DecryptRSA;
import core.util.EncryptRSA;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired private SignUpService signUpService;
	@Autowired private EditUserService editUserService;
	@Autowired private RemoveUserService removeUserService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String viewEditUserInfoPage(HttpSession session, Model model) {
		String userId = (String) session.getAttribute("userId");
		Customer customer = editUserService.selectUserById(userId);
		model.addAttribute("customer", customer);

		if (session.getAttribute("isSeller") != null) {
			Seller seller = editUserService.selectSellerById(userId);
			Shop shop = editUserService.selectShopByUrl(seller.getShopUrl());
			model.addAttribute("seller", seller);
			model.addAttribute("shop", shop);
		}

		if (!encryptPrepareProcess(session, model).isSuccess()) {
			return "encryptedReadyFailure";
		}
		return "editUser";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	protected @ResponseBody String editUserInfo(@RequestParam("pwEncryption") String password, @RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("address") String address, String shopUrl, String shopPhone, String bank, String bankAccount, String isSeller, HttpSession session, Model model) {
		
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		session.removeAttribute("__rsaPrivateKey__");
		try {
			DecryptRSA rsa = new DecryptRSA(privateKey);
			password = rsa.decryptRsa(password);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			logger.debug("error : ", e.getMessage());
			return "errorCommon";
		}
		
		String userId = (String) session.getAttribute("userId");
		Customer user = new Customer(userId, password, name, phone, address);		
		Shop shop = new Shop(shopUrl, shopPhone);
		Seller seller = new Seller(userId, shopUrl, bank, bankAccount);
		
		editUserService.updateCustomer(user);
		if (isSeller == null) return "main";
		if(session.getAttribute("isSeller")==null){
			signUpService.insertShop(shop);
			signUpService.insertSeller(seller);
		}else{
			editUserService.updateShop(shop);
			editUserService.updateSeller(seller);		
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
		if (session.getAttribute("isSeller") != null) {
			removeUserService.removeSeller(userId);
		}
		removeUserService.removeCustomer(userId);

		// TODO [우선순위 : 보통] - 회원 탈퇴 후에는 Session에 있는 모든 정보를 삭제하는것이 더 좋을것 같다는 생각이
		// 듬
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
