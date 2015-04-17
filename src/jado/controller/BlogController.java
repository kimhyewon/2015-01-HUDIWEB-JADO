package jado.controller;

import jado.dao.ShopDao;
import jado.model.Shop;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BlogController {
	@Autowired private ShopDao shopDao;
	
	@RequestMapping(value = "/shop/{shopUrl}", method = RequestMethod.GET)
	public String showShop(@PathVariable("shopUrl")String url, Model model) throws ServletException, IOException {
		Shop shop = shopDao.selectByUrl(url);
		if (shop == null) {
			return "main";
		}
		model.addAttribute("shop", shop);
		return "blogDummy";
	}
	
	
	@RequestMapping(value = "/setting/{shopUrl}", method = RequestMethod.GET)
	public String formSetting(@PathVariable("shopUrl")String url, Model model) throws ServletException, IOException {
		Shop shop = shopDao.selectByUrl(url);
		if (shop == null) return "settingShop";
		model.addAttribute("shop", shop);
		return "blogDummy";
	}
	
	@RequestMapping(value = "/setting/{shopUrl}", method = RequestMethod.POST)
	public String editSetting(@PathVariable("shopUrl")String url, Model model) throws ServletException, IOException {
		Shop shop = shopDao.selectByUrl(url);
		if (shop == null) return "settingShop";
		model.addAttribute("shop", shop);
		return "blogDummy";
	}
	
	
}
