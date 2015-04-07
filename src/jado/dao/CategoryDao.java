package jado.dao;

import jado.model.Category;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import core.jdbc.JdbcTemplate222;
import core.jdbc.RowMapper;

@Repository
public class CategoryDao extends JdbcDaoSupport{ 
	
	public static void insert(final Category category) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "insert into CATEGORY values(null, ?, ?)";
		jdbcTemplate.executeUpdate(sql, category.getName(), category.getShopUrl());
	}

	public static Category select(final Category category) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "select * from CATEGORY where SHOP_URL=?";
		RowMapper<Category> rm = rs -> new Category(rs.getInt(1),rs.getString(2), rs.getString(3));
		return jdbcTemplate.executeQuery(sql, rm, category.getShopUrl());
	}

}
