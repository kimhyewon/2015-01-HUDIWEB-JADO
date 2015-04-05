package jado.controller;

import jado.model.Customer;
import jado.model.Seller;
import jado.service.EditUserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.util.ServletRequestUtils;

@Controller
public class EditUserController {
	
	@Autowired private EditUserService editUserService;

	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public void viewEditUserInfoPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		Customer customer = editUserService.selectUserById(userId);
		req.setAttribute("customer", customer);
		if(session.getAttribute("isSeller") != null) {
			Seller seller = editUserService.selectSellerById(userId);
			req.setAttribute("seller", seller);
		}
		resp.sendRedirect("/editUser.jsp");
	}
	
	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	protected void editUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		//Normal User
		String userId = (String)session.getAttribute("userId");
		String password = ServletRequestUtils.getRequiredStringParameter(req, "pwEncryption");
		String name = ServletRequestUtils.getRequiredStringParameter(req, "name");
		String phone = ServletRequestUtils.getRequiredStringParameter(req, "phone");
		String address = ServletRequestUtils.getRequiredStringParameter(req, "address");
		editUserService.updateCustomer(new Customer(userId, password, name, phone, address));

		//Seller
		if (req.getParameter("isSeller") != null) {
			String shopUrl = ServletRequestUtils.getRequiredStringParameter(req, "shopUrl");
			//TODO 아래 변수 사용하지 않지만 DB 스키마를 보면 필요할 것 같은 데이터이다.
			//@WooJaeWoo 수정요청드림
			String shopPhone = ServletRequestUtils.getRequiredStringParameter(req, "shopPhone");
			String bank = ServletRequestUtils.getRequiredStringParameter(req, "bank");
			String bankAccount = ServletRequestUtils.getRequiredStringParameter(req, "bankAccount");
			editUserService.updateSeller(new Seller(userId, shopUrl, bank, bankAccount));
		}

		resp.sendRedirect("/");
	}
}
