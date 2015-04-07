package jado.dao;

import static org.junit.Assert.*;

import java.util.List;

import jado.model.ArticleComment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class ArticleCommentDaoTest {
	private static final Logger log = LoggerFactory.getLogger(UserDaoTest.class);

	@Autowired
	private ArticleCommentDao articleCommentDao;

	@Test
	public void insert() {
		ArticleComment articleComment = new ArticleComment("testUrl", "오픈 기념 이벤트", "공지사항", "erin3142@naver.com", "감사합니다2222");
		articleCommentDao.insert(articleComment);
		log.debug("articleComment1 : {}", articleComment);

		List<ArticleComment> articleComment2 = articleCommentDao.findByArticle(articleComment);
		log.debug("articleComment2 : {}", articleComment2);
	}

	@Test
	public void selectAll() throws Exception {
		ArticleComment articleComment = new ArticleComment("testUrl", "오픈 기념 이벤트", "공지사항", "erin314@naver.com", "감사합니다2222");
		articleCommentDao.insert(articleComment);

		List<ArticleComment> comments = articleCommentDao.findByArticle(articleComment);
		System.out.println("comments");
		System.out.println(comments.size());
		System.out.println(comments.get(0));
		ArticleComment comment = articleCommentDao.findByPk(comments.get(0));
		System.out.println("comments");
		System.out.println(comment);

	}

}
