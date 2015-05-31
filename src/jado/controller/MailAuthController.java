package jado.controller;

import jado.dao.UserDao;
import jado.model.Notice;
import jado.model.User;
import jado.service.MailAuthService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import core.mail.template.MailTemplateStorage;
import core.mail.template.MailTemplateStorage.Type;
import core.util.AuthorityManager;
import core.util.ModelAndViewUtils;

@Controller
@RequestMapping(value = "/mailAuth")
public class MailAuthController {
	@Autowired
	private MailAuthService mailAuthService;
	@Autowired
	private AuthorityManager authorityManager;
	@Autowired
	private UserDao userDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView InvalidAccess(Model model) {
		return ModelAndViewUtils.renderToNotice(new Notice("Invalid  access", "당신은 접근 권한이 없습니다."));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processMailAuth(@RequestParam("requestemail") String userEmail, @RequestParam("requestuuid") String uuid, HttpSession session) {
		if (mailAuthService.isAlreadyVerified(userEmail)) {
			return ModelAndViewUtils.renderToNotice(new Notice("Already Success", "당신은 이미 인증된 회원 입니다."));
		}

		if (!mailAuthService.verify(userEmail, uuid)) {
			return ModelAndViewUtils.renderToNotice(new Notice("E-mail authentication failed", "이메일 인증에 실폐하셨습니다. 다시 시도해 주시기 바랍니다."));
		}

		mailAuthService.updateTypeOfUserRole(userEmail);
		Map<String, Object> mailParameterMap = new HashMap<>();
		mailParameterMap.put("mailRecipient", userEmail);
		mailAuthService.send(mailParameterMap, Type.JOIN_WELCOME);
		authorityManager.setUserAuthority(new User(userEmail), userDao.typeOfMailAuthStatus(userEmail), session);
		return ModelAndViewUtils.renderToNotice(new Notice("Success", "이메일 인증에 성공하셨습니다. 감사합니다."));
	}
	
	@RequestMapping(value="/resend", method = RequestMethod.GET)
	public void resendMail(HttpServletResponse resp, HttpServletRequest request, HttpSession session) throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html; charset=utf-8");
		
		String referrer = request.getHeader("referer");
		
		if(session.getAttribute("userId") == null) {
			out.print("<script>alert('부적절한 접근')</script>");
			out.print("<script>location.href='"+referrer+"'</script>");
			out.flush();
			out.close();
			return;
		}
		
		Map<String, Object> mailMap = new HashMap<>();
		mailMap.put("mailRecipient", (String)session.getAttribute("userId"));
		mailAuthService.send(mailMap, MailTemplateStorage.Type.JOIN_VERIFY_RESEND);
		
		out.print("<script>alert('재발송 완료');</script>");
		out.print("<script>location.href='"+referrer+"'</script>");
		out.flush();
		out.close();
	}
	
}
