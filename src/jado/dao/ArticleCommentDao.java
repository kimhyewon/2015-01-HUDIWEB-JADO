package jado.dao;

import java.util.ArrayList;
import java.util.List;

import jado.model.ArticleComment;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class ArticleCommentDao {
	public static void insert(final ArticleComment articleComment) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into ARTICLE_COMMENT values(?, ?, ?, ?, NOW(), ?)";
		jdbcTemplate.executeUpdate(sql,articleComment.getShopUrl(), articleComment.getArticleTitle(), articleComment.getBoardName(), articleComment.getUserId(), articleComment.getContent());
	}

	public static ArticleComment select(ArticleComment articleComment) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select * from ARTICLE_COMMENT where SHOP_URL=? and BOARD_NAME=? and ARTICLE_TITLE=? and USER_ID=?";
		RowMapper<ArticleComment> rm = rs -> new ArticleComment(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
		return jdbcTemplate.executeQuery(sql, rm, articleComment.getShopUrl(), articleComment.getBoardName(), articleComment.getArticleTitle(), articleComment.getUserId());
	}
	
//	select all
	public static List<ArticleComment> selectAll(ArticleComment articleComment) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select * from ARTICLE_COMMENT where SHOP_URL=? and BOARD_NAME=? and ARTICLE_TITLE=? and USER_ID=?";
		RowMapper<List> rm = rs -> new ArrayList<ArticleComment>();
		return jdbcTemplate.executeQuery(sql, rm, articleComment.getShopUrl(), articleComment.getBoardName(), articleComment.getArticleTitle(), articleComment.getUserId());
	}
}
