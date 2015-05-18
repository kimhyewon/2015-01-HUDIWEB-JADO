package jado.controller;

import jado.model.Category;
import jado.model.Product;
import jado.model.ProductComment;
import jado.model.Shop;
import jado.service.ProductService;
import jado.service.ShopService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.jadopay.PaymentInfo;

@Controller
@RequestMapping(value = "/shop/{shopUrl}/product")
public class ProductController {
	@Autowired private ProductService productService;
	@Autowired private ShopService shopService;

	// product receive 
	@RequestMapping(value="/{productId}", method = RequestMethod.GET)
	public String showShopByUser(Model model, @PathVariable("productId")String productId) throws ServletException, IOException {
		Product product = productService.getProduct(Integer.parseInt(productId));
		List<ProductComment> comments = productService.getComments(product.getId());
		Category category = productService.getCategory(product.getCategoryId());
		Shop shop = shopService.getShopByUrl(category.getShopUrl());
		
		model.addAttribute("shop", shop);
		model.addAttribute("category", category);
		model.addAttribute("product", product);
		model.addAttribute("comments", comments);
		model.addAttribute("paymentInfo", new PaymentInfo());
		return "showProduct";	
	}
	

}
