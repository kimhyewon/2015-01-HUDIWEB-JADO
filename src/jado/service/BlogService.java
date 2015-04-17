package jado.service;

import jado.dao.ShopDao;
import jado.model.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
	@Autowired private ShopDao shopDao;
	
	public void insertShop(Shop shop) {
	
		shopDao.insert(shop);
	}

	
}
