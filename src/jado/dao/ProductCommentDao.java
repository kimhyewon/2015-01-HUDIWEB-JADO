package jado.dao;

import jado.model.ArticleComment;
import jado.model.ProductComment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import core.jdbc.JdbcTemplate222;
import core.jdbc.RowMapper;

@Repository
public class ProductCommentDao extends JdbcDaoSupport{
	public static void insert(final ProductComment productComment) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "insert into PRODUCT_COMMENT values(?, ?, null, ?)";
		jdbcTemplate.executeUpdate(sql,productComment.getProductId(), productComment.getUserId(), productComment.getContent());
	}

	public static ProductComment select(final ProductComment productComment) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "select * from PRODUCT_COMMENT where PRODUCT_ID=? and USER_ID=?";
		RowMapper<ProductComment> rm = rs -> new ProductComment(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));
		return jdbcTemplate.executeQuery(sql, rm, productComment.getProductId(), productComment.getUserId());
	}
	
//	select all
	public static List<ArticleComment> selectAll(ArticleComment articleComment) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "select * from ARTICLE_COMMENT where SHOP_URL=? and BOARD_NAME=? and ARTICLE_TITLE=? and USER_ID=?";
		RowMapper<List> rm = rs -> new ArrayList<ArticleComment>();
		return jdbcTemplate.executeQuery(sql, rm, articleComment.getShopUrl(), articleComment.getBoardName(), articleComment.getArticleTitle(), articleComment.getUserId());
	}

}
