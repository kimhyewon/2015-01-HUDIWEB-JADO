package jado.dao;

import jado.model.Product;

import org.junit.Test;

public class ProductDaoTest {

	@Test
	public void test() {
		Product product = new Product(1, "곰돌이 가방 ", 1000, 10, "imgurl", "desc");
		ProductDao.insert(product);
		int productId = 1;
		Product product2 = ProductDao.select(productId );

		System.out.println(product);
		System.out.println(product2);
	}

}
