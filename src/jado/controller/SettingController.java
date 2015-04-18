package jado.controller;

import jado.dao.ShopDao;
import jado.model.Board;
import jado.model.Shop;
import jado.service.ShopService;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller

@RequestMapping(value = "/setting")
public class SettingController {
	private static final Logger logger = LoggerFactory.getLogger(SettingController.class);
	@Autowired private ShopDao shopDao;
	@Autowired private ShopService shopService;
	
//	샵 설정 화면으로 감
	@RequestMapping(method = RequestMethod.GET)
	public String formSetting(Model model,HttpSession session) throws ServletException, IOException {
		logger.debug("start");
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.setting(userId);
		if (shop == null) return "main";
		model.addAttribute("shop", shop);
		return "setting";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String editSetting(Model model,HttpSession session) throws ServletException, IOException {
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.setting(userId);
		if (shop == null) return "main";
		model.addAttribute("shop", shop);
		return "/shop/"+shop.getUrl();
	}
	@RequestMapping(value = "/api/theme", method = RequestMethod.GET)
	public String editTheme(Model model,HttpSession session) throws ServletException, IOException {
//		image 파일과, shop url을 받는다.
//		image를 userImg/shop/banner/shopurl.png 로 저장한다.
//		shopUrl정보의 banner_url을 수정 한다.
		return "blogDummy";
	}
	
	@RequestMapping(value = "/api/bannerUrl", method = RequestMethod.POST)
	public String editBanner(Model model,HttpSession session) throws ServletException, IOException {
//		image 파일과, shop url을 받는다.
//		image를 userImg/shop/banner/shopurl.png 로 저장한다.
//		shopUrl정보의 banner_url을 수정 한다.
		return "blogDummy";
	}
	@RequestMapping(value = "/api/mainUrl", method = RequestMethod.POST)
	public String editMain(Model model,HttpSession session) throws ServletException, IOException {
//		image 파일과, shop url을 받는다.
//		
		return "blogDummy";
	}
	@RequestMapping(value = "/api/logoUrl", method = RequestMethod.POST)
	public String editLogo(Model model,HttpSession session) throws ServletException, IOException {
//		image 파일과, shop url을 받는다.
//		
		return "blogDummy";
	}



//	@RequestMapping(value = "/api/logoUrl", method = RequestMethod.POST)
	public String editLogo22222222222222(ArrayList<Board> boards) throws ServletException, IOException {
//		image 파일과, shop url을 받는다.
//		
		return "blogDummy";
	}

	
}
