package jado.service;

import jado.dao.CartDao;
import jado.dao.MailAuthDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	private static final Logger logger = LoggerFactory.getLogger(CartService.class);
	
	@Autowired
	private CartDao cartDao;
	
	
}
