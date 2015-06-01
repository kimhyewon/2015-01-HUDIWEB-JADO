package jado.service;

import java.util.HashMap;
import java.util.Map;

import jado.dao.ShopDao;
import jado.dao.UserDao;
import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ShopDao shopDao;

	public Map<String, Object> getUserInfo(String userId) {
		Map<String, Object> userInfo = new HashMap<String, Object>();
		Customer customer = userDao.selectUserById(userId);
		userInfo.put("customer", customer);
		Seller seller = userDao.selectSellerById(userId);
		if (seller != null) {
			userInfo.put("seller", seller);
			userInfo.put("shop", shopDao.selectByUrl(seller.getShopUrl()));
			userInfo.put("isSeller", true);
		}
		return userInfo;
	}

	@Transactional
	public void setSellerInfo(Shop shop, Seller seller) {
		if (userDao.selectSellerById(seller.getId()) == null) {
			insertShop(shop);
			insertSeller(seller);
			return;
		}
		updateShop(shop);
		updateSeller(seller);
	}

	@Transactional
	public void insertCustomer(Customer customer) throws DuplicateKeyException {
		Customer tempUser = userDao.selectUserById(customer.getId());
		if (tempUser != null) {
			throw new DuplicateKeyException("이미 가입된 사용자입니다.");
		}
		userDao.insert(customer);
		userDao.insertDefaultRole(customer);
	}

	private void insertSeller(Seller seller) {
		Seller tempSeller = userDao.selectSellerById(seller.getId());
		if (tempSeller != null) {
			throw new DuplicateKeyException("이미 당신은 판매자로 등록되어 있습니다.");
		}
		userDao.insert(seller);
		
	}

	private void insertShop(Shop shop) {
		Shop tempShop = shopDao.selectByUrl(shop.getUrl());
		if (tempShop != null) {
			throw new DuplicateKeyException("이미 똑같은 URL이 존재 합니다");
		}
		shopDao.insert(shop);
	}
	
	public Seller selectSellerById(String userId) {
		return userDao.selectSellerById(userId);
	}
	
	public void updateCustomer(Customer customerFromEdit) {
		Customer customer = userDao.selectUserById(customerFromEdit.getId());
		if (customer.update(customerFromEdit)) {
			userDao.updateCustomer(customerFromEdit);
		}
	}

	private void updateSeller(Seller sellerFromEdit) {
		Seller seller = userDao.selectSellerById(sellerFromEdit.getId());
		if (seller.update(sellerFromEdit)) {
			userDao.updateSeller(seller);
		}
	}
	
	private void updateShop(Shop shopFromEdit) {
		Shop shop = shopDao.selectByUrl(shopFromEdit.getUrl());
		if (shop.updateFromUserPage(shopFromEdit)) {
			shopDao.updateInfo(shop);
		}
	}

	public void updateDeleteUser(String userId) {
		userDao.updateDeleteUser(userId);
	}
}
