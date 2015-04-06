package jado.dao;

import jado.model.ProductComment;

import org.junit.Test;

public class ProductCommentDaoTest {

	@Test
	public void test() {
		ProductComment productComment = new ProductComment(1,"erin314@naver.com","스타일이 구림 ");
		ProductCommentDao.insert(productComment);
		
		ProductComment productComment2 = ProductCommentDao.select(productComment);

		System.out.println(productComment);
		System.out.println(productComment2);
	}

}
