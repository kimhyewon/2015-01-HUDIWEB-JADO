package jado.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import jado.model.Category;

public class CategoryDao {
	
	public static void insert(final Category category) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into CATEGORY values(null, ?, ?)";
		jdbcTemplate.executeUpdate(sql, category.getName(), category.getShopUrl());
	}

	public static Category select(final Category category) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select * from CATEGORY where SHOP_URL=?";
		RowMapper<Category> rm = rs -> new Category(rs.getInt(1),rs.getString(2), rs.getString(3));
		return jdbcTemplate.executeQuery(sql, rm, category.getShopUrl());
	}

}
