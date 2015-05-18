package jado.controller;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.exception.InsertTargetRecordNotFoundException;
import core.jadopay.PaymentInfo;


@Controller
@RequestMapping(value = "/category")
public class CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	@Autowired
	private CategoryService categoryService;
	@Autowired 
	private ShopService shopService;
	
	//블로그 페이지에서 카테고리명 클릭시 category.jsp 보여줌 
	@RequestMapping(value="/{shopUrl}/{categoryId}", method = RequestMethod.GET)
	public String doGet(Model model, @PathVariable("categoryId")String categoryId, @PathVariable("shopUrl")String url)
			throws ServletException, IOException {
		Shop shop = shopService.getShopByUrl(url);
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
		Shop shop = shopService.getShopByUrl(url);
		model.addAttribute("shop", shop);
		
		Category category = categoryService.getCategory(Integer.parseInt(categoryId));
		model.addAttribute("category", category);
		
		return "productForm";
	}
	
	// categoryForm에서 등록한 내용 post로 받아오기 
	@RequestMapping(value = "/product/upload", method = RequestMethod.POST)
	protected String writePost(String shopUrl, String categoryId, String imgUrl, String name, String price, String stock, String desc, FileInfo fileInfo,
			HttpSession session, Model model) throws InsertTargetRecordNotFoundException, IllegalStateException, IOException {
		
		logger.debug("file info {}", fileInfo.getFile());
		logger.debug("file info {}", fileInfo.getType());
		logger.debug("file info {}", fileInfo.getUrl());
		logger.debug("file info {}", fileInfo.getLocalLocation());
		
		fileInfo.updateLocalLocation();
		logger.debug("file info {}", fileInfo.getLocalLocation());
		
		Product product = new Product(Integer.parseInt(categoryId), name, Integer.parseInt(price), Integer.parseInt(stock), imgUrl, desc);
		categoryService.representImage(fileInfo, product);
		logger.debug("url {}", shopUrl);
		return "redirect:/category/"+shopUrl+"/"+categoryId;
	}
	
	//상품 클릭시 해당 글(상세 페이지) 보여주는 코드 
	@RequestMapping(value="/product/{shopUrl}/{categoryId}/{productId}", method = RequestMethod.GET)
	public String productGet(Model model, @PathVariable("categoryId")String categoryId, @PathVariable("productId")String productId, @PathVariable("shopUrl")String url)  {
		Shop shop = shopService.getShopByUrl(url);
		model.addAttribute("shop", shop);
		
		Category category = categoryService.getCategory(Integer.parseInt(categoryId));
		Product product = categoryService.getProduct(Integer.parseInt(productId));
		List<ProductComment> comments = categoryService.getComments(Integer.parseInt(productId));
		model.addAttribute("category", category);
		model.addAttribute("product", product);
		model.addAttribute("comments", comments);
		model.addAttribute("paymentInfo", new PaymentInfo());
		
		return "showProduct";
	}
	
	//댓글 등록 구현
	@RequestMapping(value = "/product/answer/save", method = RequestMethod.POST)
	protected String commentPost(String shopUrl, String categoryId, String productId, String userId, String content,
			HttpSession session, Model model) {
			
		ProductComment productComment = new ProductComment(Integer.parseInt(productId), userId, content);
		try{
			categoryService.insertproductCommnet(productComment);
		} catch (InsertTargetRecordNotFoundException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "commons/error/errorCommon";
		}
		return "redirect:/category/product/"+shopUrl+"/"+categoryId+"/"+productId;
	}
	
	//댓글 삭제 구현
	@RequestMapping(value = "/product/answer/delete", method = RequestMethod.POST)
	protected String commentDeletePost(String shopUrl, String categoryId, String productId, String userId, String commentTime,
			HttpSession session, Model model) throws ServletException,
			IOException, InsertTargetRecordNotFoundException {
			
		categoryService.deleteProductComment(Integer.parseInt(productId), userId, commentTime);
			
		return "redirect:/category/product/"+shopUrl+"/"+categoryId+"/"+productId;
	}
	
	//product 본문 수정 구현 1 - 글 수정 버튼 클릭시 updateProductForm 페이지로 이동 
	@RequestMapping(value = "/product/update/{shopUrl}/{categoryId}/{productId}", method = RequestMethod.GET)
	public String updateGet(Model model, @PathVariable("categoryId")String categoryId, @PathVariable("productId")String productId, @PathVariable("shopUrl")String url)
			throws ServletException, IOException {
		Shop shop = shopService.getShopByUrl(url);
		model.addAttribute("shop", shop);
		
		Category category = categoryService.getCategory(Integer.parseInt(categoryId));
		Product product = categoryService.getProduct(Integer.parseInt(productId));
		model.addAttribute("category", category);
		model.addAttribute("product", product);
		return "updateProductForm";
	}
		
	//product 본문 수정 구현 2 - updateProductForm에서 쓴 내용 받아오기  
	@RequestMapping(value = "/product/update", method = RequestMethod.POST)
	protected String productUpdatePost(FileInfo fileInfo, String shopUrl, String categoryId, String productId, String imgUrl, String name, String price, String stock, String desc,
			HttpSession session, Model model) throws ServletException,
			IOException, InsertTargetRecordNotFoundException {
		logger.debug("data3 {}", shopUrl);
		logger.debug("data4 {}", categoryId);
		logger.debug("data555 {}", productId);
		logger.debug("data555 {}", imgUrl);
		logger.debug("data555 {}", name);
		logger.debug("data555 {}", price);
		logger.debug("data555 {}", stock);
		logger.debug("data555 {}", desc);

		Product product = new Product(name, Integer.parseInt(price), Integer.parseInt(stock), imgUrl, desc);
		product.setId(Integer.parseInt(productId));
		categoryService.updateProduct(product);		
		categoryService.updateImage(fileInfo, product.getId());
		
		return "redirect:/category/product/"+shopUrl+"/"+categoryId+"/"+productId;
	}
		
	//product 본문 삭제 구현 
	@RequestMapping(value = "/product/delete", method = RequestMethod.POST)
	protected String articleDeletePost(String shopUrl, String categoryId, String productId,
			HttpSession session, Model model) {
		categoryService.deleteProduct(Integer.parseInt(productId));
			
		return "redirect:/category/"+shopUrl+"/"+categoryId;
	}
}
