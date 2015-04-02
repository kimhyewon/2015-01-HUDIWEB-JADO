package jado.dao;


import jado.model.Article;

import org.junit.Test;

public class ArticleDaoTest {

	@Test
	public void test() {
		Article article = new Article( "testUrl", "공지사항", "title3", "content");
		ArticleDao.insert(article);
		Article article2 = ArticleDao.select(article);
		
		System.out.println(article);
		System.out.println(article2);
	}

}
