package jado.dao;

import core.jdbc.JdbcTemplate;
import jado.model.NormalUser;
import jado.model.Seller;

public class UserDao {
	public void insert(NormalUser normalUser) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "INSERT INTO USER VALUES (?, ?, ?, ?, ? ,NOW(), null)";
		jdbcTemplate.update(sql, normalUser.getUserId(),
				normalUser.getPassword(), normalUser.getName(),
				normalUser.getPhone(), normalUser.getAddress());
	}

	public void insert(Seller seller) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "INSERT INTO SELLER VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, seller.getShopUrl(), seller.getShopPhone(),
				seller.getBank(), seller.getBankAccount());

	}
}
