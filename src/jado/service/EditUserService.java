package jado.service;

import jado.dao.ShopDao;
import jado.dao.UserDao;
import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditUserService {

	@Autowired UserDao userDao;
	@Autowired ShopDao shopDao;

	public Customer selectUserById(String userId) {
		return userDao.selectUserById(userId);
	}

	public Seller selectSellerById(String userId) {
		return userDao.selectSellerById(userId);
	}

	public Shop selectShopByUrl(String url) {
		return shopDao.selectByUrl(url);
	}

	public void updateCustomer(Customer customer) {
		userDao.updateCustomer(customer);
	}

	public void updateSeller(Seller seller) {
		userDao.updateSeller(seller);
	}

}
