package jado.dao;

import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;

import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {

	
	@Before
	public void setup() {
		
	}
	@Test
	public void insertUser() throws Exception {
		Customer customer = new Customer("userId1", "password", "name", "phone", "address");
		UserDao.insert(customer);
	}

	@Test
	public void insertSeller() throws Exception {
		Customer customer = new Customer("userId2", "password", "name", "phone", "address");
		Shop shop = new Shop("url2", "phone");
		Seller seller = new Seller("userId2", "url2", "bank", "bankAccount");
		UserDao.insert(customer);
		ShopDao.insert(shop);
		UserDao.insert(seller);
	}
	
	@Test
	public void updateCustomer() throws Exception {
		Customer customer1 = new Customer("userId5", "password", "name", "phone", "address");
		UserDao.insert(customer1);
		
		Customer customer2 = new Customer("userId5", "password2", "name", "phone2", "address");
		
		Customer dbCustomer = UserDao.selectUserById(customer1.getUserId());
		if(dbCustomer.update(customer2)){
			UserDao.updateCustomer(dbCustomer);
		}
	}
	@Test
	public void updateSeller() throws Exception {
		Customer customer1 = new Customer("userId6", "password", "name", "phone", "address");
		Shop shop = new Shop("url3", "phone");
		Seller seller = new Seller("userId6", "url3", "bank", "bankAccount");
		
		UserDao.insert(customer1);
		UserDao.insert(seller);
		ShopDao.insert(shop);
		
		Seller dbSeller = UserDao.selectSellerById(customer1.getUserId());
		Seller conSeller = new Seller("userId6", "url2", "bank2", "bankAccount2");
		
		if(dbSeller.update(conSeller)){
			UserDao.updateCustomer(customer1);
		}
	}

}
