package jado.controller;

import jado.model.Category;
import jado.model.FileInfo;
import jado.model.Notice;
import jado.model.Product;
import jado.model.ProductComment;
import jado.model.Shop;
import jado.service.ProductService;
import jado.service.ShopService;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import core.exception.InsertTargetRecordNotFoundException;
import core.jadopay.PaymentInfo;
import core.util.ModelAndViewUtils;

@Controller
@RequestMapping(value = "/shop/{shopUrl}/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;
	@Autowired
	private ShopService shopService;

	// product 본문 보기
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public String productRecieve(Model model, @PathVariable("shopUrl") String shopUrl, @PathVariable("productId") String productId, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.getShopByUrl(shopUrl, userId);
		Product product = productService.getProduct(Integer.parseInt(productId));
		Category category = shopService.getCategory(product.getCategoryId(), shop.getCategorys());
		List<ProductComment> comments = productService.getComments(product.getId());

		model.addAttribute("shop", shop);
		model.addAttribute("category", category);
		model.addAttribute("product", product);
		model.addAttribute("comments", comments);
		model.addAttribute("paymentInfo", new PaymentInfo());
		return "showProduct";
	}

	// product 만들기 폼 보내주기
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String uploadGet(Model model, String categoryId, @PathVariable("shopUrl") String url, HttpSession session) {
		logger.debug("hello");
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.getShopByUrl(url, userId);
		model.addAttribute("shop", shop);
		model.addAttribute("categoryId", categoryId);
		return "productForm";
	}

	// product 만들기 폼 받아오기
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView writePost(Product product, FileInfo fileInfo, @PathVariable("shopUrl") String url) {
		fileInfo.setFileNameByUUID();
		fileInfo.updateLocalLocation();
		product.setImgUrl(fileInfo.getLocalLocation());
		try {
			productService.representImage(fileInfo, product);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return ModelAndViewUtils.renderToNoticeForSeller(new Notice("Fail", "상품을 등록을 다시 시도해 주세요 "));
		}
		return new ModelAndView("redirect:/shop/"+url+"/category/"+product.getCategoryId());
	}

	// product 수정 폼 보내주기
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateGet(Model model, String productId, @PathVariable("shopUrl") String url, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.getShopByUrl(url, userId);
		Product product = productService.getProduct(Integer.parseInt(productId));

		model.addAttribute("shop", shop);
		model.addAttribute("product", product);
		return "updateProductForm";
	}

	// product 수정 폼 받아오기
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView productUpdatePost(FileInfo fileInfo, Product product, @PathVariable("shopUrl") String url) {
		fileInfo.setFileNameByUUID();
		fileInfo.updateLocalLocation();
		product.setImgUrl(fileInfo.getLocalLocation());
		try {
			if (!fileInfo.getFile().isEmpty()) {
				productService.updateImage(fileInfo, product);
			}
			productService.updateProduct(product);
		} catch (IllegalStateException | IOException e) {
			return ModelAndViewUtils.renderToNoticeForSeller(new Notice("Notice", "상품 이미지에 문제가 있어 상품 이미지 수정에 실폐하셨습니다."));
		} catch (InsertTargetRecordNotFoundException e){
			return ModelAndViewUtils.renderToNoticeForSeller(new Notice("Notice",  e.getMessage()));
		}
		return new ModelAndView("redirect:/shop/"+url+"/product/"+product.getId());
	}

	// product 본문 삭제 구현
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView productDelete(HttpSession session, Integer productId, Model model, @PathVariable("shopUrl") String url) {
		String userId = (String) session.getAttribute("userId");
		Product product = productService.getProduct(productId);
		if (!productService.deleteProduct(product.getId(), userId)) {
			return ModelAndViewUtils.renderToNoticeForSeller(new Notice("Fail", "댓글이 존재하거나 제품이 이미 팔렸기 때문에 삭제 할 수 있습니다"));
		}
		return new ModelAndView("redirect:/shop/"+url+"/category/"+product.getCategoryId());
	}
}
