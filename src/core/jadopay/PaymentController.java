package core.jadopay;

import jado.service.MailAuthService;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import core.mail.template.MailTemplateStorage.Type;

@Controller
@RequestMapping(value = "/pay")
public class PaymentController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private MailAuthService emailSender;
	
	// For example
	@RequestMapping(value = "/example", method = RequestMethod.GET)
	public String viewPaymentExamplePage(Model model) {
		model.addAttribute("paymentInfo", new PaymentInfo());
		return "jadoPay/payExample";
	}

	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public String viewPaymentPage(@Valid PaymentInfo payinfo, BindingResult bindingResult, Model model, HttpSession session) {
		
		logger.debug(payinfo.toString());
		
		if (bindingResult.hasErrors()) {
			printBindingErrors(bindingResult);
			return "jadoPay/payExample";
		}
		
		PaymentProcessInfo payInfo = paymentService.getPaymentProcessInfo(payinfo, session);
		model.addAttribute("payInfo", payInfo);
		return "jadoPay/payInfo";
	}

	
	@RequestMapping(value = "/process", method = RequestMethod.POST)
	public String processPayment(@RequestParam("cardCompany") String cardCompany, @RequestParam("userId") String userId, @RequestParam("shopUrl") String shopUrl, @RequestParam("productId") int productId,@RequestParam("price") int price, Model model) throws MessagingException {

		/*
		 * 제 생각에 '돈'을 다루기 때문에 조금 더 명확해야할 것 같아요.
		 * 특히 int가 아니라 BigDecimal이 좋을 것 같기도 한데.. 이건 제가 아직 안 써봐서 피드백드릴 내용은 없어요.
		 * 공부해서 알려주세요 :)
		 * 
		 * 그리고 아래 로직이 좀 이상한건..
		 * 상식적으로 지불을 수행하고 그 (성공/실패) 결과를 메일로 보내주는건지
		 * 아니면 지불이 성공했을 때만 메일을 보내주는지 이게 불분명하네요.
		 * 
		 * 만약 후자라면
		 * processPay 로직 하단에 메일발송 기능이 추가되어야 할 것 같아요.
		 * 
		 * 또.
		 * 도중 지불이 실패할 경우에 어떻게 처리할 것인지가 불문명하네요.
		 * 예를 들어 롤백을 할지, 아니면 무시 후 실패 결과만 통보할지
		 * 만약 어쨋든 로직의 원자화가 중요한 이슈일 것 같습니다
		 * 
		 * 만약 스프링 @Transactional을 쓴다면
		 * 반드시 PaymentService를 interface화 해야 적용이 될거에요.
		 */
		paymentService.processPay(userId, shopUrl, cardCompany, price, productId);

		model.addAttribute("cardCompany", cardCompany);
		model.addAttribute("userId", userId);
		model.addAttribute("mailRecipient", userId);
		model.addAttribute("shopUrl", shopUrl);
		model.addAttribute("price", price );
		model.addAttribute("productName", paymentService.getProductName(productId) );

		// emailSender.sendEmail(new Mail(userId, mailTemplateStorage.getTemplate(MailTemplateStorage.Type.PAY_INFO)));
		emailSender.send(model.asMap(), Type.PAY_INFO);
		return "jadoPay/paySuccess";
	}


	private void printBindingErrors(BindingResult bindingResult) {
		List<ObjectError> errors = bindingResult.getAllErrors();

		logger.debug("Binding Error 발생");
		
		for (ObjectError error : errors) {
			logger.debug("error : {}", error.getDefaultMessage());
		}
	}
}