package jado.dao;

import jado.model.Shop;
import core.jdbc.JdbcTemplate;

public class ShopDao {
	public static void insert(final Shop shop) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into shop values(?, ?, ?, ?, ? ,? ,? )";
		jdbcTemplate.executeUpdate(sql, shop.getUrl(), shop.getTitle(),
				shop.getPhone(), shop.getBanner_url(), shop.getLogo_url(),
				shop.getTheme(), shop.getFooter());
	}
}