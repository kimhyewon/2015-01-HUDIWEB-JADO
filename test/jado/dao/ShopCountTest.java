package jado.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import core.jdbc.JdbcTemplate;

public class ShopCountTest {

	@Test
	public void test() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select count(SELLER_ID) from SELLER";
	}

}
