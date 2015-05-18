package jado.controller;

import jado.model.Notice;
import jado.model.Product;
import jado.model.ProductComment;
import jado.model.Shop;
import jado.service.ProductService;
import jado.service.ShopService;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import core.jadopay.PaymentInfo;
import core.util.ModelAndViewUtils;

@Controller
@RequestMapping(value = "/shop/{shopUrl}/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ShopService shopService;

	// product 본문 보기
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public String productRecieve(Model model, @PathVariable("productId") String productId, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		Product product = productService.getProduct(Integer.parseInt(productId));
		List<ProductComment> comments = productService.getComments(product.getId());
		Shop shop = shopService.getShopByCategoryId(product.getCategoryId(), userId);

		model.addAttribute("shop", shop);
		model.addAttribute("product", product);
		model.addAttribute("comments", comments);
		model.addAttribute("paymentInfo", new PaymentInfo());
		return "showProduct";
	}

	// product 본문 삭제 구현
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView productDelete(HttpSession session, String productId, Model model) {
		String userId = (String) session.getAttribute("userId");
		if (productService.deleteProduct(Integer.parseInt(productId), userId)) {
			return ModelAndViewUtils.renderToNoticeForSeller(new Notice("Success", "상품정보를 삭제 하였습니다")); 
		}
		return ModelAndViewUtils.renderToNoticeForSeller(new Notice("Fail", "댓글이 존재하기 때문에 삭제 할 수 있습니다"));
	}

}
