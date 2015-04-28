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
		String sql = "insert into ARTICLE values(null, ?, ?, ?, null)";
		Object[] args = new Object[] { article.getBoardId(), article.getTitle(), article.getContent() };
		jdbcTemplate.update(sql, args);
	}

	public Article selectByPk(int articleId) {
		String sql = "select * from ARTICLE where ID=?";
		try {
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Article>(Article.class), articleId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Article> selectAllByBoard(int boardId) {
		String sql = "select * from ARTICLE where BOARD_ID=?";
		try {
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Article>(Article.class), boardId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(Article article) {
		String sql = "update ARTICLE set TITLE=?, CONTENT=? where ID=?";
		Object[] args = new Object[] {article.getTitle(), article.getContent(), article.getId()};
		jdbcTemplate.update(sql, args);
	}

	public void remove(int articleId) {
		String sql = "delete from ARTICLE where ID=?";
		jdbcTemplate.update(sql, articleId);
	}
}
