package jado.dao;

import core.jdbc.JdbcTemplate;
import jado.model.NormalUser;
import jado.model.Seller;

public class UserDao {
	public static void insert(NormalUser normalUser) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into USER values (?, ?, ?, ?, ? ,NOW(), null)";
		jdbcTemplate.update(sql, normalUser.getUserId(),
				normalUser.getPassword(), normalUser.getName(),
				normalUser.getPhone(), normalUser.getAddress());
	}

	public static void insert(Seller seller) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into SELLER values (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, seller.getUserId(), seller.getShopUrl(), seller.getShopPhone(),
				seller.getBank(), seller.getBankAccount());
	}
	
	public static void update(NormalUser normalUser) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		//String sql = "UPDATE USER SET "
	}
	
	public static void update(Seller seller) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
	}

	public static NormalUser findUser(String userId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return null;
	}
}
