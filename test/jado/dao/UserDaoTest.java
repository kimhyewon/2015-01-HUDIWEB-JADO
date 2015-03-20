package jado.dao;

import jado.model.Customer;
import jado.model.Seller;

import org.junit.Test;

public class UserDaoTest {

	@Test
	public void insertCustomer() {
		Customer user = new Customer("erin3141", "1111", "이경륜" , "01027723883", "경기도 안양시 동안구 비산1동" );
		UserDao.insert(user);
	}

	@Test
	public void insertSeller() {
		Seller user = new Seller("erin3141", "erin", "01027723883", "신한은행", "1111111111" );
		UserDao.insert(user);
	}
}
