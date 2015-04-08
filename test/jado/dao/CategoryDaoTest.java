package jado.dao;

import java.util.List;

import jado.model.Category;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class CategoryDaoTest {

	private static final Logger log = LoggerFactory.getLogger(CategoryDaoTest.class);

	@Autowired
	private CategoryDao categoryDao;

	@Test
	public void test() {
		Category category = new Category("testName", "testUrl");
		categoryDao.insert(category);
		log.debug("inserting {}", category);
		List<Category> list = categoryDao.selectAllByUrl(category.getShopUrl());
		Category category2 = categoryDao.selectByPk(list.get(0).getId());
		log.debug("selectAll {}", list);
		log.debug("selectPK  {}", category2);
		categoryDao.remove(list.get(0).getId());
		category2 = categoryDao.selectByPk(list.get(0).getId());
		if (category2 == null) {
			log.debug("removed");
		}
	}

}
