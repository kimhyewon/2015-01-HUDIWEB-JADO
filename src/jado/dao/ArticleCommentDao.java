package jado.dao;

import jado.model.ArticleComment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleCommentDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insert(final ArticleComment ac) {
		String sql = "insert into ARTICLE_COMMENT values(?, ?, null, ?)";
		return jdbcTemplate.update(sql, ac.getArticleId(), ac.getUserId(), ac.getContent());
	}

	public ArticleComment findByPk(final ArticleComment ac) {
		String sql = "select * from ARTICLE_COMMENT where ARTICLE_ID=? and USER_ID=? and COMMENT_TIME=?";
		Object[] args = new Object[] {ac.getArticleId(), ac.getUserId(), ac.getCommentTime() };
		try {
			return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<ArticleComment>(ArticleComment.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<ArticleComment> findByArticle(final int articleId) {
		String sql = "select * from ARTICLE_COMMENT where ARTICLE_ID=?";
		try {
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleComment>(ArticleComment.class), articleId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void remove(int articleId, String userId, String commentTime) {
		String sql = "delete from ARTICLE_COMMENT where ARTICLE_ID=? and USER_ID=? and COMMENT_TIME=?";
		jdbcTemplate.update(sql, articleId, userId, commentTime);
	}
}
