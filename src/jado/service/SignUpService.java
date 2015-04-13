package jado.service;

import jado.dao.ShopDao;
import jado.dao.UserDao;
import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.exception.DuplicateUserException;

@Service
public class SignUpService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ShopDao shopDao;

	public void insertCustomer(Customer customer) throws DuplicateUserException {
		Customer tempUser = userDao.selectUserById(customer.getUserId());
		if (tempUser != null) {
			throw new DuplicateUserException("이미 가입된 사용자입니다.");
		}
		userDao.insert(customer);
	}

	public void insertShop(Shop shop) {
		shopDao.insert(shop);
	}

	public void insertSeller(Seller seller) {
		userDao.insert(seller);
	}
}
