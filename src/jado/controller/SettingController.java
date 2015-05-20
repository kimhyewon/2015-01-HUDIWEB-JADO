package jado.controller;

import jado.dao.ShopDao;
import jado.model.Board;
import jado.model.Category;
import jado.model.Notice;
import jado.model.Shop;
import jado.model.FileInfo;
import jado.service.ShopService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import core.exception.NotExistFileException;
import core.util.ModelAndViewUtils;

@Controller
@RequestMapping(value = "/setting")
public class SettingController {
	private static final Logger logger = LoggerFactory.getLogger(SettingController.class);
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private ShopService shopService;

	// 샵 설정 화면으로 감
	@RequestMapping(method = RequestMethod.GET)
	public String formSetting(Model model, HttpSession session) throws ServletException, IOException {
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.settingById(userId);
		if (shop == null)
			return "main";
		model.addAttribute("shop", shop);
		return "setting";
	}
	
	//edit Shop(url, title, phone, footer)
	@RequestMapping(method = RequestMethod.POST)
	public String editSetting(Model model, HttpSession session, Shop shop) throws ServletException, IOException {
		logger.debug("shop {}", shop);
		shop = shopService.settingEditInfo(shop);
		if (shop == null)
			return "main";
		model.addAttribute("shop", shop);
		return "redirect:/shop";
	}

	@RequestMapping(value = "/api/image", method = RequestMethod.POST)
	public ModelAndView editImage(Model model, FileInfo fileInfo){
		fileInfo.updateLocalLocation();
		try {
			shopService.settingEditImage(fileInfo);
		} catch (IllegalStateException | IOException | NotExistFileException e) {
			return ModelAndViewUtils.renderToNotice(new Notice("Sucess", "이미지에 문제가 있습니다."));
		}
		return new ModelAndView("redirect:/shop");
	}
	@RequestMapping(value = "/api/theme", method = RequestMethod.GET)
	public String editTheme(Model model, int theme, HttpSession session) throws ServletException, IOException {
		String userId = (String) session.getAttribute("userId");
		shopService.settingEditTheme(theme, userId);
		return "redirect:/shop";
	}

	@RequestMapping(value = "/api/board/delete", method = RequestMethod.GET)
	public String boardDelete(Board board) throws ServletException, IOException {
		shopService.boardDelete(board);
		return "redirect:/setting";
	}

	@RequestMapping(value = "/api/board/insert", method = RequestMethod.POST)
	public String boardInsert(@RequestParam("board") List<String> boards, String shopUrl) throws ServletException, IOException {
		shopService.boardInsert(boards, shopUrl);
		return "redirect:/setting";
	}

	@RequestMapping(value = "/api/category/delete", method = RequestMethod.GET)
	public String categoryDelete(Category category) throws ServletException, IOException {
		shopService.categoryDelete(category);
		return "redirect:/setting";
	}

	@RequestMapping(value = "/api/category/insert", method = RequestMethod.POST)
	public String categoryInsert(@RequestParam("category") List<String> categorys, String shopUrl) throws ServletException, IOException {
		shopService.categoryInsert(categorys, shopUrl);
		return "redirect:/setting";
	}
}
