package jado.dao;

import jado.model.ArticleComment;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleCommentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, jdbcTemplate.getDataSource());
	}

	public int insert(final ArticleComment ac) {
		String sql = "insert into ARTICLE_COMMENT values(?, ?, ?, ?, null, ?)";
		Object[] args = new Object[] { ac.getShopUrl(), ac.getArticleTitle(), ac.getBoardName(), ac.getUserId(), ac.getContent() };
		return jdbcTemplate.update(sql, args);
	}

	public ArticleComment findByPk(final ArticleComment ac) {
		String sql = "select * from ARTICLE_COMMENT where SHOP_URL=? and ARTICLE_TITLE=? and BOARD_NAME=? and USER_ID=? and COMMENT_TIME=?";
		Object[] args = new Object[] { ac.getShopUrl(), ac.getArticleTitle(), ac.getBoardName(), ac.getUserId(), ac.getCommentTime() };

		try {
			return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<ArticleComment>(ArticleComment.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<ArticleComment> findByArticle(final ArticleComment ac) {
		String sql = "select * from ARTICLE_COMMENT where SHOP_URL=? and ARTICLE_TITLE=? and BOARD_NAME=?";
		Object[] args = new Object[] { ac.getShopUrl(), ac.getArticleTitle(), ac.getBoardName() };

		try {
			return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<ArticleComment>(ArticleComment.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}