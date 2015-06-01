package core.jadopay;

import java.util.List;

import jado.model.Cart;
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
public class CartController {
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	CartService cartService;

	//장바구니 담기 버튼 누를시 장바구니 페이지로
	@RequestMapping(value = "/cart/put", method = RequestMethod.POST)
	public String putCartPage(Cart cart, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		if(userId == null){
			return "redirect:/shop/"+cart.getShopUrl();
		}
		cartService.putCart(cart, userId);
		return "redirect:/shop/"+cart.getShopUrl()+"/info";
	}

	//보드에서 장바구니 페이지로
	@RequestMapping(value = "/shop/{shopUrl}/info", method = RequestMethod.GET)
	public String viewCartPage(@PathVariable("shopUrl") String url, Model model,HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		List<Cart> items = cartService.getCart(url, userId);
		model.addAttribute("items", items);
		return "showCart";
	}

}