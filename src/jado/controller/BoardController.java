package jado.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BoardController {
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "board";
	}

//	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
//	public String processLogin(@RequestParam("idEncryption") String userId, @RequestParam("pwEncryption") String password, HttpSession session, Model model) {
//		Result decryptedUserId; 
//		Result decryptedUserPassword;
//		
//		PrivateKey privateKey = getPrivateKeyAndDestroyKey(session);
//		decryptedUserId = decryptString(privateKey, model,  userId);
//		decryptedUserPassword = decryptString(privateKey, model, password);
//
//		if(!(decryptedUserId.isSuccess() && decryptedUserPassword.isSuccess())){
//			model.addAttribute("errorMessage", "암호화 전송된 정보를 해독하는 과정에서 오류 발생, 페이지 새로고침후 재시도 바람");
//			return "loginFailure";
//		}
//		
//		userId = (String)decryptedUserId.getValue("decryptedString");
//		password = (String)decryptedUserPassword.getValue("decryptedString");
//
//		Result loginResult = loginService.logIn(userId, password, session); 
//		if(!loginResult.isSuccess()) {
//			model.addAttribute("errorMessage", loginResult.getValue("errorMessage"));
//			return "loginFailure";
//		}
//		
//		return "main";
//	}
}
