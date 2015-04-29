package jado.controller;

import jado.dao.ShopDao;
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
public class ShopController {
	@Autowired private ShopDao shopDao;
	@Autowired private ShopService shopService;
	
//	아무개 사용자가 남에 샵에 접근할때
	@RequestMapping(value = "/shop/{shopUrl}", method = RequestMethod.GET)
	public String showShop(@PathVariable("shopUrl")String url, Model model) throws ServletException, IOException {
		Shop shop = shopService.settingByUrl(url);
		List<Product> products = shopService.settingProductByUrl(url);
		if (shop == null) return "main";
		model.addAttribute("shop", shop);
		model.addAttribute("products", products);
		return "blogDummy";
	}
	
//	본인이 자기 샵으로 갈때 
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String showShopByUser(Model model,HttpSession session) throws ServletException, IOException {
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.settingById(userId);
		if (shop == null) return "main";
		model.addAttribute("shop", shop);
		return "blogDummy";
	}
}
