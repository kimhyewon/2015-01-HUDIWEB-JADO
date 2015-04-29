package jado.dao;



import java.util.List;

import jado.model.Article;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/applicationContext.xml","file:webapps/WEB-INF/jado-servlet.xml"})
public class ArticleDaoTest {
	private static final Logger log = LoggerFactory.getLogger(ArticleDaoTest.class);
	
	@Autowired
	private ArticleDao articleDao;
	
	@Test
	public void insertAndSelectTest() {
		Article article = new Article( 1, "안녕하세요 오픈 했습니다", " content");
		articleDao.insert(article);
		log.debug("article null: {}", article);
		List<Article> articles = articleDao.selectAllByBoard(1);
		article = articleDao.selectByPk(4);
		log.debug("article read: {}", article);		
		log.debug("articles : {}", articles);
	}
	
	@Test
	public void updateTest() throws Exception {
		Article article = articleDao.selectByPk(3);
		log.debug("article_update1    : {}", article);
		Article temp = new Article("title4", "updated"); 
		article.update(temp);
		articleDao.update(article);
		article = articleDao.selectByPk(3);
		log.debug("article_update2    : {}", article);
		
		articleDao.remove(3);
		article = articleDao.selectByPk(3);
		if (article == null) {
			log.debug("removed");
		}
		
	}

}
