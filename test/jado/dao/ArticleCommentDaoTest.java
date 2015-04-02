package jado.dao;

import static org.junit.Assert.*;
import jado.model.ArticleComment;

import org.junit.Test;

public class ArticleCommentDaoTest {

	@Test
	public void test() {
		ArticleComment articleComment = new ArticleComment("testUrl", "title1", "공지사항","erin314@naver.com","감사합니다");
		ArticleCommentDao.insert(articleComment);
		ArticleComment articleComment2 = ArticleCommentDao.select(articleComment);
		
		System.out.println(articleComment);
		System.out.println(articleComment2);
	}

}
