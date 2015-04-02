package jado.dao;

import jado.model.Customer;
import jado.model.Shop;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class ShopDao {
	public static void insert(final Shop shop) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into shop values(?, ?, ?, ?, ? ,? ,? )";
		jdbcTemplate.executeUpdate(sql, shop.getUrl(), shop.getTitle(), shop.getPhone(), shop.getBanner_url(), shop.getMain_url(), shop.getLogo_url(), shop.getTheme(), shop.getFooter());
	}

	public static void update(final Shop shop) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "update SHOP set TITLE = ?, PHONE = ?, BANNER_URL=?, MAIN_URL=?, LOGO_URL=?, THEME=?,  FOOTER=? where URL = ?";
		jdbcTemplate.executeUpdate(sql, shop.getTitle(), shop.getPhone(), shop.getBanner_url(), shop.getMain_url(), shop.getLogo_url(), shop.getTheme(), shop.getFooter(), shop.getUrl());
	}

	public static Shop selectByUrl(final String url) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select * from SHOP where URL=?";
		RowMapper<Shop> rm = rs -> new Shop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
		return jdbcTemplate.executeQuery(sql, rm, url);
	}

	public static void remove(final String url) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "delete from SHOP where URL = ?";
		jdbcTemplate.executeUpdate(sql);
	}

}