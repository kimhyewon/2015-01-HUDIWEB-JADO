package jado.dao;

import java.util.List;

import jado.model.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(final Article article) {
		String sql = "insert into ARTICLE values(null, ?, ?, ?, null)";
		jdbcTemplate.update(sql, article.getBoardId(), article.getTitle(), article.getContent());
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
		String sql = "select * from ARTICLE where BOARD_ID=? order by ID DESC";
		try {
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Article>(Article.class), boardId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(Article article) {
		String sql = "update ARTICLE set TITLE=?, CONTENT=? where ID=?";
		jdbcTemplate.update(sql, article.getTitle(), article.getContent(), article.getId());
	}

	public void remove(int articleId) {
		String sql = "delete from ARTICLE where ID=?";
		jdbcTemplate.update(sql, articleId);
	}
}
