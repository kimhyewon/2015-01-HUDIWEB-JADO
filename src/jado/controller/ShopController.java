package jado.controller;

import jado.model.Customer;
import jado.model.PaymentWithProduct;
import jado.model.Product;
import jado.model.Shop;
import jado.service.ShopService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/shop")
public class ShopController {
	@Autowired private ShopService shopService;

	//	본인이 자기 샵으로 갈때 
	@RequestMapping(method = RequestMethod.GET)
	public String showShopByUser(Model model,HttpSession session) throws ServletException, IOException {
		String userId = (String) session.getAttribute("userId");
		String url = shopService.getUrl(userId);
		if (url == null) {
			return "redirect:/";
		}
		return "redirect:/shop/"+url;
	}
	
	//	아무개 사용자가 남에 샵에 접근할때
	@RequestMapping(value = "/{shopUrl}", method = RequestMethod.GET)
	public String showShop(@PathVariable("shopUrl")String url, Model model) throws ServletException, IOException {
		Shop shop = shopService.settingByUrl(url);
		List<Product> products = shopService.settingProductByUrl(url);
		if (shop == null) return "redirect:/";
		model.addAttribute("shop", shop);
		model.addAttribute("products", products);
		return "shopMain"+shop.getTheme();
	}
	
	@RequestMapping(value = "/{shopUrl}/mypage", method = RequestMethod.GET)
	public String myPageForCustomer(@PathVariable("shopUrl")String url, Model model, HttpSession session){
		
		String userId = (String) session.getAttribute("userId");
		
		Customer customer = shopService.getMyInfo(url, userId);
		List<PaymentWithProduct> payments = shopService.getPayments(customer, url);
		Integer paymentsTotal = shopService.getPaymentsTotal(payments);
		
		model.addAttribute("user", customer);
		model.addAttribute("payments", payments);
		model.addAttribute("paymentsTotal", paymentsTotal);		
		return "mypage";
	}
}
