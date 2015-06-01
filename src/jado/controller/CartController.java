package jado.controller;

import jado.service.CartService;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private CartService cartService;
	
	// 장바구니내 물건 보기
	@RequestMapping(value = "/list}", method = RequestMethod.GET)
	public String productRecieve(Model model, @PathVariable("shopUrl") String shopUrl, @PathVariable("productId") String productId, HttpSession session) {
		return "cart";
	}
}
