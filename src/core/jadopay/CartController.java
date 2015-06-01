package core.jadopay;

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
//@RequestMapping(value = "/shop/{shopUrl}/cart")
@RequestMapping(value="/cart")
public class CartController {
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	CartService cartService;

	//장바구니 담기 버튼 누를시 장바구니 페이지로
	@RequestMapping(value = "/put", method = RequestMethod.POST)
	public String putCartPage(Cart cart, Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		cartService.putCart(cart, userId);
		return "showCart";
	}
	
	//보드에서 장바구니 페이지로
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String viewCartPage() {
		return "showCart";
	}

}