package jado.controller;

import jado.model.Shop;
import jado.service.BlogService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BlogController {
	@Autowired private BlogService blogService;
	
	@RequestMapping(value = "/{shopUrl}", method = RequestMethod.GET)
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Shop shop = new Shop();
		blogService.insertShop(shop);
		return "blogDummy";
	}
}
