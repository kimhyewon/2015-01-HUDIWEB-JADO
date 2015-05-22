package jado.controller;

import jado.model.Article;
import jado.model.Board;
import jado.model.Category;
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
	@Autowired
	private ShopService shopService;

	// 본인이 자기 샵으로 갈때
	@RequestMapping(method = RequestMethod.GET)
	public String showShopByUser(Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		String url = shopService.getUrl(userId);
		if (url == null) {
			return "redirect:/";
		}
		return "redirect:/shop/" + url;
	}

	// 아무개 사용자가 남에 샵에 접근할때
	@RequestMapping(value = "/{shopUrl}", method = RequestMethod.GET)
	public String showShop(@PathVariable("shopUrl") String url, Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.getShopByUrl(url, userId);
		List<Product> products = shopService.settingProductByUrl(url);
		if (shop == null)
			return "redirect:/";
		model.addAttribute("shop", shop);
		model.addAttribute("products", products);
		model.addAttribute("isMyShop", false);
		return "shopMain" + shop.getTheme();
	}

	// 블로그 페이지에서 카테고리명 클릭시 category.jsp 보여줌
	@RequestMapping(value = "/{shopUrl}/category/{categoryId}", method = RequestMethod.GET)
	public String showProductList(Model model, @PathVariable("categoryId") String categoryId, @PathVariable("shopUrl") String url, HttpSession session) throws ServletException, IOException {
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.getShopByUrl(url, userId);
		model.addAttribute("shop", shop);

		Category category = shopService.getCategory(Integer.parseInt(categoryId));
		List<Product> products = shopService.getProducts(Integer.parseInt(categoryId));
		model.addAttribute("category", category);
		model.addAttribute("products", products);

		return "category";
	}

	// 블로그 페이지에서 board명 클릭시 article list 보여줌
	@RequestMapping(value = "/{shopUrl}/board/{boardId}", method = RequestMethod.GET)
	public String showArticleList(Model model, @PathVariable("boardId") String boardId, @PathVariable("shopUrl") String url, HttpSession session) throws ServletException, IOException {
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.getShopByUrl(url, userId);
		Board board = shopService.getBoard(Integer.parseInt(boardId), shop.getBoards());
		List<Article> articles = shopService.getArticles(Integer.parseInt(boardId));

		model.addAttribute("shop", shop);
		model.addAttribute("articles", articles);
		model.addAttribute("board", board);
		return "board";
	}

	@RequestMapping(value = "/{shopUrl}/mypage", method = RequestMethod.GET)
	public String myPageForCustomer(@PathVariable("shopUrl") String url, Model model, HttpSession session) {

		String userId = (String) session.getAttribute("userId");
		// 잘못 된 user가 들어온 경우 막기
		Customer customer = shopService.getMyInfo(url, userId);
		List<PaymentWithProduct> payments = shopService.getPayments(customer, url);
		Integer paymentsTotal = shopService.getPaymentsTotal(payments);

		model.addAttribute("user", customer);
		model.addAttribute("payments", payments);
		model.addAttribute("paymentsTotal", paymentsTotal);
		return "mypage";
	}

}
