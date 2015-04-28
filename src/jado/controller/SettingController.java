package jado.controller;

import jado.dao.ShopDao;
import jado.model.Board;
import jado.model.Category;
import jado.model.Shop;
import jado.model.FileInfo;
import jado.service.ShopService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/setting")
public class SettingController {
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private ShopService shopService;

	// 샵 설정 화면으로 감
	@RequestMapping(method = RequestMethod.GET)
	public String formSetting(Model model, HttpSession session) throws ServletException, IOException {
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.setting(userId);
		if (shop == null)
			return "main";
		model.addAttribute("shop", shop);
		return "setting";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String editSetting(Model model, HttpSession session, String url, String title, String phone, String footer) throws ServletException, IOException {
		Shop shop = new Shop(url, title, phone, footer);
		shop = shopService.settingEditInfo(shop);
		if (shop == null)
			return "main";
		model.addAttribute("shop", shop);
		return "redirect:/shop";
	}

	@RequestMapping(value = "/api/image", method = RequestMethod.POST)
	public String editImage(Model model, FileInfo fileInfo) throws ServletException, IOException {
		if (fileInfo.getFile() == null) {
			model.addAttribute("errorMessage", "이미지를 다시 업로드 해주세요 ");
			return "setting";
		}
		fileInfo.updateLocalLocation();
		shopService.settingEditImage(fileInfo);
		return "redirect:/setting";
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
