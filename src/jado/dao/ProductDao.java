package jado.dao;

import core.jdbc.JdbcTemplate222;
import core.jdbc.RowMapper;
import jado.model.Product;

public class ProductDao {

	public static void insert(final Product product) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "insert into Product values(null, ?, ?, ?, ?, ?, ?, null)";
		jdbcTemplate.executeUpdate(sql, product.getCategoryId(), product.getName(), product.getPrice(), product.getStock(), product.getImgUrl(), product.getDesc());
	}

	public static Product select(final int productId) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "select * from Product where ID=?";
		RowMapper<Product> rm = rs -> new Product(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8));
		return jdbcTemplate.executeQuery(sql, rm, productId);
	}

}
