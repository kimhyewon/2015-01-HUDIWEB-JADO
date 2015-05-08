package jado.controller;

import jado.model.Category;
import jado.model.Product;
import jado.service.CategoryService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	//블로그 페이지에서 카테고리명 클릭시 category.jsp 보여줌 
	@RequestMapping(value="/{categoryId}", method = RequestMethod.GET)
	public String doGet(Model model, @PathVariable("categoryId")String categoryId)
			throws ServletException, IOException {
		Category category = categoryService.getCategory(Integer.parseInt(categoryId));
		List<Product> products = categoryService.getProducts(Integer.parseInt(categoryId));
		model.addAttribute("category", category);
		model.addAttribute("products", products);
		
		return "category";
	}
}
