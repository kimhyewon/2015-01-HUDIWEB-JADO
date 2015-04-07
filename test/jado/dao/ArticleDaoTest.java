package jado.dao;



import java.util.List;

import jado.model.Article;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class ArticleDaoTest {
	private static final Logger log = LoggerFactory.getLogger(ArticleDaoTest.class);
	
	@Autowired
	private ArticleDao articleDao;
	
	@Test
	public void insertAndSelectTest() {
		Article article = new Article( "testUrl", "공지사항", "title4", "content");
		articleDao.insert(article);
		log.debug("articleComment1 : {}", article);
		List<Article> articles = articleDao.selectAllByBoard(article);
		article = articleDao.selectByPk(article);
		log.debug("articleComment1 : {}", article);		
		log.debug("articleComment1 : {}", articles);
	}
	@Test
	public void updateTest() throws Exception {
		Article article = new Article( "testUrl", "공지사항", "title5", "content111");
		articleDao.insert(article);
		log.debug("articleComment1_notinsert : {}", article);
		article = articleDao.selectByPk(article);
		log.debug("articleComment1_insert    : {}", article);
		
		Article temp = new Article("testUrl", "공지사항", "title4", "updated"); 
		article.update(temp);
		articleDao.update(article);
		article = articleDao.selectByPk(article);
		log.debug("articleComment1_update    : {}", article);
		
		articleDao.remove(article);
		article = articleDao.selectByPk(article);
		if (article == null) {
			log.debug("removed");
		}
		
	}

}
