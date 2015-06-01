package jado.service;

import java.util.List;

import jado.dao.CartDao;
import jado.dao.MailAuthDao;
import jado.model.Cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.jadopay.PaymentInfo;

@Service
public class CartService {
	private static final Logger logger = LoggerFactory.getLogger(CartService.class);
	
	@Autowired
	private CartDao cartDao;

	public void putCart(Cart cart, String userId) {
		cart.setCustomerId(userId);
		cartDao.insert(cart);
	}

	public List<Cart> getCart(String url, String userId) {
		return cartDao.selectAllForCart(url, userId);
	}

}
