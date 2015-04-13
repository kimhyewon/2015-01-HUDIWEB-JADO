package jado.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/board")
public class BoardController {
	@RequestMapping(method = RequestMethod.GET)
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "board";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String wirteGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "boardForm";
	}
	
}
