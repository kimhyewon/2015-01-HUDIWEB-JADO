package jado.dao;

import org.junit.Test;

import core.jdbc.JdbcTemplate222;

public class ShopCountTest {

	@Test
	public void test() {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "select count(SELLER_ID) from SELLER";
	}

}
