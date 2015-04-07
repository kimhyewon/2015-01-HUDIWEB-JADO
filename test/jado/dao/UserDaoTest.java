package jado.dao;

import jado.model.Customer;
import jado.model.Seller;
import jado.model.Shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class UserDaoTest {
	private static final Logger log = LoggerFactory.getLogger(UserDaoTest.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private ShopDao shopDao;

	@Test
	public void insertUSER() {
		Customer customer = new Customer("userId1", "password", "name", "phone", "address");
		userDao.insert(customer);
		log.debug("insert CUSTOMER1 : {}", customer);
		customer = userDao.selectCustomerById(customer.getUserId());
		log.debug("insert CUSTOMER1 : {}", customer);
	}

	@Test
	public void insertSeller() throws Exception {
		Customer customer = new Customer("userId2", "password", "name", "phone", "address");

		Shop shop = new Shop("url344", "phone");
		Seller seller = new Seller("userId2", "url344", "bank", "bankAccount");
		log.debug("insert CUSTOMER2 : {}", customer);
		log.debug("insert CUSTSHOP2 : {}", shop);
		log.debug("insert CUSELLER2 : {}", seller);

		userDao.insert(customer);
		shopDao.insert(shop);
		userDao.insert(seller);

		customer = userDao.selectCustomerById(customer.getUserId());
		shop = shopDao.selectByUrl(shop.getUrl());
		seller = userDao.selectSellerById(seller.getUserId());

		log.debug("insert CUSTOMER3 : {}", customer);
		log.debug("insert CUSTSHOP3 : {}", shop);
		log.debug("insert CUSELLER3 : {}", seller);
	}

	@Test
	public void updateCustomer() throws Exception {
		Customer customer1 = new Customer("userId5", "password", "name", "phone", "address");
		userDao.insert(customer1);
		Customer customer2 = new Customer("userId5", "password2", "name", "phone2", "address");
		Customer dbCustomer = userDao.selectUserById(customer1.getUserId());
		if (dbCustomer.update(customer2)) {
			userDao.updateCustomer(dbCustomer);
		}
	}

	@Test
	public void updateSeller() throws Exception {
		Customer customer1 = new Customer("userId6", "password", "name", "phone", "address");
		Shop shop = new Shop("url3", "phone");
		Seller seller = new Seller("userId6", "url3", "bank", "bankAccount");

		userDao.insert(customer1);
		shopDao.insert(shop);
		userDao.insert(seller);

		Seller dbSeller = userDao.selectSellerById(customer1.getUserId());
		Seller conSeller = new Seller("userId6", "url2", "bank2", "bankAccount2");
		log.debug("insert CUSTOMER4 : {}", dbSeller);
		log.debug("insert CUSTOMER4 : {}", conSeller);

		if (dbSeller.update(conSeller)) {
			userDao.updateCustomer(customer1);
		}
		
		
	}

}
