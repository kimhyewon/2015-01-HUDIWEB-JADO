package jado.controller;

import jado.model.Article;
import jado.model.ArticleComment;
import jado.model.Board;
import jado.model.Category;
import jado.model.FileInfo;
import jado.model.Product;
import jado.model.ProductComment;
import jado.model.Shop;
import jado.service.CategoryService;
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

import core.exception.ForignKeyException;


@Controller
@RequestMapping(value = "/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired 
	private ShopService shopService;
	
	//블로그 페이지에서 카테고리명 클릭시 category.jsp 보여줌 
	@RequestMapping(value="/{shopUrl}/{categoryId}", method = RequestMethod.GET)
	public String doGet(Model model, @PathVariable("categoryId")String categoryId, @PathVariable("shopUrl")String url)
			throws ServletException, IOException {
		Shop shop = shopService.settingByUrl(url);
		model.addAttribute("shop", shop);
		
		Category category = categoryService.getCategory(Integer.parseInt(categoryId));
		List<Product> products = categoryService.getProducts(Integer.parseInt(categoryId));
		model.addAttribute("category", category);
		model.addAttribute("products", products);
		
		return "category";
	}
	
	//상품 등록 클릭시 categoryForm으로 이동 
	@RequestMapping(value = "/product/upload/{shopUrl}/{categoryId}", method = RequestMethod.GET)
	public String uploadGet(Model model, @PathVariable("categoryId")String categoryId, @PathVariable("shopUrl")String url)
			throws ServletException, IOException {
		Shop shop = shopService.settingByUrl(url);
		model.addAttribute("shop", shop);
		
		Category category = categoryService.getCategory(Integer.parseInt(categoryId));
		model.addAttribute("category", category);
		
		return "categoryForm";
	}
	
	// categoryForm에서 등록한 내용 post로 받아오기 
	@RequestMapping(value = "/product/upload", method = RequestMethod.POST)
	protected String writePost(String shopUrl, String categoryId, String imgUrl, String name, String price, String stock, String desc, FileInfo fileInfo,
			HttpSession session, Model model) throws ServletException,
			IOException, ForignKeyException {

		Product product = new Product(Integer.parseInt(categoryId), name, Integer.parseInt(price), Integer.parseInt(stock), imgUrl, desc);
		categoryService.insertProduct(product);
		
//		fileInfo.updateLocalLocation();
//		categoryService.representImage(fileInfo);
		
		return "redirect:/category/"+shopUrl+"/"+categoryId;
	}
	
	//상품 클릭시 해당 글(상세 페이지) 보여주는 코드 
	@RequestMapping(value="/product/{categoryId}/{productId}", method = RequestMethod.GET)
	public String productGet(Model model, @PathVariable("categoryId")String categoryId, @PathVariable("productId")String productId)
			throws ServletException, IOException {
		Category category = categoryService.getCategory(Integer.parseInt(categoryId));
		Product product = categoryService.getProduct(Integer.parseInt(productId));
		List<ProductComment> comments = categoryService.getComments(Integer.parseInt(productId));
		model.addAttribute("category", category);
		model.addAttribute("product", product);
		model.addAttribute("comments", comments);

		return "showProduct";
	}
	
	//댓글 등록 구현
	@RequestMapping(value = "/product/answer/save", method = RequestMethod.POST)
	protected String commentPost(String categoryId, String productId, String userId, String content,
			HttpSession session, Model model) throws ServletException,
			IOException, ForignKeyException {
			
		ProductComment productComment = new ProductComment(Integer.parseInt(productId), userId, content);
		categoryService.insertproductCommnet(productComment);
		
		return "redirect:/category/product/"+productId+"/"+categoryId;
	}
	
	//댓글 삭제 구현
	@RequestMapping(value = "/product/answer/delete", method = RequestMethod.POST)
	protected String commentDeletePost(String categoryId, String productId, String userId, String commentTime,
			HttpSession session, Model model) throws ServletException,
			IOException, ForignKeyException {
			
		categoryService.deleteProductComment(Integer.parseInt(productId), userId, commentTime);
			
		return "redirect:/category/product/"+categoryId+"/"+productId;
	}
}
