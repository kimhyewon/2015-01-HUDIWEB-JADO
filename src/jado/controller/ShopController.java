package jado.controller;

import jado.dao.ShopDao;
import jado.model.Board;
import jado.model.Shop;
import jado.service.ShopService;

import java.io.IOException;

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
	
//	아무나 shop url알면 그 shop으로 갈 수 있음 
	@RequestMapping(value = "/shop/{shopUrl}", method = RequestMethod.GET)
	public String showShop(@PathVariable("shopUrl")String url, Model model) throws ServletException, IOException {
		Shop shop = shopDao.selectByUrl(url);
		if (shop == null) return "main";
		model.addAttribute("shop", shop);
		return "blogDummy";
	}
//	본인이 자기 샵으로 갈때 : 이건 필요 없는 경로여서 나중에 없앨 예정
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String showShopByUser(Model model,HttpSession session) throws ServletException, IOException {
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.setting(userId);
		if (shop == null) return "main";
		model.addAttribute("shop", shop);
		return "blogDummy";
	}
}
