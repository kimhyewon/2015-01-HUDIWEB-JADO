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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import core.exception.InsertTargetRecordNotFoundException;
import core.exception.NotExistFileException;
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
		String userId = (String) session.getAttribute("userId");
		Shop shop = shopService.getShopByUrl(url, userId);
		model.addAttribute("shop", shop);
		model.addAttribute("categoryId", categoryId);
		return "productForm";
	}

	// product 만들기 폼 받아오기
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView writePost(Product product, FileInfo fileInfo) {
		fileInfo.setFileNameByUUID();
		fileInfo.updateLocalLocation();
		product.setImgUrl(fileInfo.getLocalLocation());
		try {
			productService.representImage(fileInfo, product);
		} catch (Exception e) {
			return ModelAndViewUtils.renderToNoticeForSeller(new Notice("Fail", "상품을 등록을 다시 시도해 주세요 "));
		}
		return ModelAndViewUtils.renderToNoticeForSeller(new Notice("Success", "상품을 등록했습니다 "));
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
	public ModelAndView productUpdatePost(FileInfo fileInfo, Product product) {
		fileInfo.setFileNameByUUID();
		fileInfo.updateLocalLocation();
		product.setImgUrl(fileInfo.getLocalLocation());
		Notice notice = new Notice("Success", "상품 정보 및 상품 이미지가 수정 되었습니다.");
		try {
			productService.updateImage(fileInfo);
		} catch (IllegalStateException | IOException e) {
			notice = new Notice("Notice", "상품 이미지에 문제가 있어 상품 이미지 수정에 실폐하셨습니다.");
		} catch (NotExistFileException e) {
			notice = new Notice("Notice", "상품이미지는 그대로인 채로 상품 정보만 수정되었습니다");
		}
		
		try {
			productService.updateProduct(product);
		} catch (InsertTargetRecordNotFoundException e) {
			notice = new Notice("Notice", e.getMessage());
		} 
		return ModelAndViewUtils.renderToNoticeForSeller(notice);
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
