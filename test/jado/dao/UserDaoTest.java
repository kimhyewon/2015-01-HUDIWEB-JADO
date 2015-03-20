package jado.dao;

import jado.model.Customer;

import org.junit.Test;

public class UserDaoTest {

	@Test
	public void test() {
		Customer user = new Customer("erin314", "1111", "이경륜" , "01027723883", "경기도 안양시 동안구 비산1동" );
		UserDao dao = new UserDao();
		dao.insert(user);
	}
}
