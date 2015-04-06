package jado.dao;

import jado.model.Category;

import org.junit.Test;

public class CategotyDaoTest {

	@Test
	public void test() {
		Category category = new Category("testName", "testUrl");
		CategoryDao.insert(category);
		Category category2 = CategoryDao.select(category);

		System.out.println(category);
		System.out.println(category2);
	}

}
