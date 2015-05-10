package jado.controller;

import jado.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/user")
public class LoginController {

	@Autowired
	private LoginService loginService;


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String viewLoginPage(Model model) {
		return "login";
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String processLogin() {
		return "commons/error/401";
	}
}