package jado.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import jado.model.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, jdbcTemplate.getDataSource());
	}

	public void insert(final Article article) {
		String sql = "insert into ARTICLE values(?, ?, ?, ?, null)";
		Object[] args = new Object[] { article.getShopUrl(), article.getBoardName(), article.getTitle(), article.getContent() };
		jdbcTemplate.update(sql, args);
	}

	public Article selectByPk(Article article) {
		String sql = "select * from ARTICLE where SHOP_URL=? and BOARD_NAME=? and TITLE=?";
		Object[] args = new Object[] { article.getShopUrl(), article.getBoardName(), article.getTitle() };
		try {
			return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<Article>(Article.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Article> selectAllByBoard(Article article) {
		String sql = "select * from ARTICLE where SHOP_URL=? and BOARD_NAME=?";
		Object[] args = new Object[] { article.getShopUrl(), article.getBoardName() };
		try {
			return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Article>(Article.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(Article article) {
		String sql = "update ARTICLE set CONTENT=? where SHOP_URL=? and BOARD_NAME=? and TITLE=?";
		Object[] args = new Object[] { article.getContent(), article.getShopUrl(), article.getBoardName(), article.getTitle() };
		jdbcTemplate.update(sql, args);
	}

	public void remove(Article article) {
		String sql = "delete from ARTICLE where SHOP_URL=? and BOARD_NAME=? and TITLE=?";
		Object[] args = new Object[] { article.getShopUrl(), article.getBoardName(), article.getTitle() };
		jdbcTemplate.update(sql, args);
	}
}
