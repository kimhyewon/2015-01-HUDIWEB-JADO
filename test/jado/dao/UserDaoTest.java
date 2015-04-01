package jado.dao;

import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;

import org.junit.Test;

public class UserDaoTest {

	@Test
	public void insertCustomer() {
		Customer user = new Customer("erin3141", "1111", "이경륜" , "01027723883", "경기도 안양시 동안구 비산1동");
		UserDao.insert(user);
	}

	@Test
	public void insertShop(){
		Shop shop = new Shop("erin314", "르네상스 옷가게", "01033334444", "/web-inf/image/erin314_banner", "/web-inf/image/erin314_logo", "theme1", "address", "footer: 안녕하세요 우리 가게는 물건을 팔아요 히야~ 죽인다!!@_@");
		ShopDao.insert(shop);
	}
	@Test
	public void insertSeller() {
		Seller user = new Seller("erin3141", "erin314", "신한은행", "1111111111");
		UserDao.insert(user);
	}
	
}
