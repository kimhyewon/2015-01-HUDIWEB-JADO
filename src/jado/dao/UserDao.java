package jado.dao;

import jado.model.NormalUser;
import jado.model.Seller;

import java.sql.ResultSet;
import java.sql.SQLException;



import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

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
	
	public NormalUser selectUsrById(final String userId) {
		RowMapper<NormalUser> rm = resultSetOfUsr();
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT * FROM USER WHERE USER_ID=?";
		return jdbcTemplate.queryForObject(sql, rm, userId);
	}
	
	private RowMapper<NormalUser> resultSetOfUsr() {
		return new RowMapper<NormalUser>() {
			@Override
			public NormalUser mapRow(ResultSet rs) throws SQLException {
				return new NormalUser(rs.getString("USER_ID"), rs.getString("PASSWORD"), rs.getString("NAME"),
						rs.getString("PHONE"), rs.getString("ADDRESS"));
			}
		};
	}

}
