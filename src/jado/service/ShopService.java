package jado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jado.dao.BoardDao;
import jado.dao.CategoryDao;
import jado.dao.ShopDao;
import jado.dao.UserDao;
import jado.model.Seller;
import jado.model.Shop;

@Service
public class ShopService {
	@Autowired private ShopDao shopDao;
	@Autowired private BoardDao boardDao;
	@Autowired private CategoryDao categoryDao;
	@Autowired private UserDao userDao;
	
	public Shop setting(String userId) {
		if (userId == null) {
			//TODO login페이지가서 로그인 하도록 해야함 
			return null;
		}
		Seller seller = userDao.selectSellerById(userId);
		if(seller == null) return null;
		Shop shop = shopDao.selectByUrl(seller.getShopUrl());
		shop.setBoards(boardDao.selectAllByUrl(shop.getUrl()));
		shop.setCategorys(categoryDao.selectAllByUrl(shop.getUrl()));
		return shop;
	}
	
	

}
