package jado.service;

import jado.dao.ShopDao;
import jado.dao.UserDao;
import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
	@Autowired private UserDao userDao;
	@Autowired private ShopDao shopDao;

	public void insertCustomer(Customer customer) throws DuplicateKeyException {
		Customer tempUser = userDao.selectUserById(customer.getUserId());
		if (tempUser != null) {
			throw new DuplicateKeyException("이미 가입된 사용자입니다.");
		}
		userDao.insert(customer);
		userDao.insertDefaultRole(customer);
	}

	public void insertSeller(Seller seller) {
		Seller tempSeller = userDao.selectSellerById(seller.getUserId());
		if (tempSeller != null) {
			throw new DuplicateKeyException("이미 당신은 판매자로 등록되어 있습니다.");
		}
		userDao.insert(seller);
	}
	
	public void insertShop(Shop shop) {
		Shop tempShop = shopDao.selectByUrl(shop.getUrl());
		if(tempShop != null){
			throw new DuplicateKeyException("이미 똑같은 URL이 존재 합니다");
		}
		shopDao.insert(shop);
	}
}
