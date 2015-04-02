package jado.dao;

import jado.model.Article;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class ArticleDao {
	public static void insert(final Article article) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into ARTICLE values(?, ?, ?, ?, NOW())";
		jdbcTemplate.executeUpdate(sql,article.getShopUrl(), article.getBoardName(), article.getTitle(), article.getContent());
	}

	public static Article select(Article article) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select * from ARTICLE where SHOP_URL=? and BOARD_NAME=? and TITLE=?";
		RowMapper<Article> rm = rs -> new Article(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
		return jdbcTemplate.executeQuery(sql, rm, article.getShopUrl(), article.getBoardName(), article.getTitle());
	}
}
