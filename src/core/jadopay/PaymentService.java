package core.jadopay;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentService {

	@Autowired
	private PaymentDao payDao;

	public PaymentProcessInfo getPaymentProcessInfo(PaymentInfo info, HttpSession session) {
		return new PaymentProcessInfo((String) session.getAttribute("userId"), info.getShopUrl(), info.getProductId(),  info.getProductAmount(), payDao.getPrice(
				info.getProductId()).intValue() * info.getProductAmount());
	}

	public void processPay(String userId, String shopUrl, String cardCompany, int price, int productId) {
		payDao.processPay(userId, shopUrl, cardCompany, price, productId);

	}

	public String getProductName(int productId) {
		return payDao.getProductName(productId);
	}
}
