package jado.service;

import jado.dao.ShopDao;
import jado.dao.UserDao;
import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

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

	public void updateCustomer(Customer customerFromEdit) {
		Customer customer = userDao.selectUserById(customerFromEdit.getUserId());
		if(customer.update(customerFromEdit)){
			userDao.updateCustomer(customerFromEdit);			
		}
	}

	public void updateSeller(Seller sellerFromEdit) {
		Seller seller = userDao.selectSellerById(sellerFromEdit.getUserId());
		if (seller.update(sellerFromEdit)) {
			userDao.updateSeller(seller);
		}
	}
	
	public void updateShop(Shop shopFromEdit) {
		Shop shop = shopDao.selectByUrl(shopFromEdit.getUrl());
		if(shop.updateFromUserPage(shopFromEdit)){
			shopDao.update(shop);
		}
	}
	
	public void removeSeller(String userId) {
		userDao.removeSeller(userId);
	}

	public void removeCustomer(String userId) {
		userDao.removeCustomer(userId);
	}

}
