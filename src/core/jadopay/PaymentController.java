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