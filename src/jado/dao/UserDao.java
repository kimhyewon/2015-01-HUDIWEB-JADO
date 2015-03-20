package jado.dao;

import jado.model.Customer;
import jado.model.Seller;

import java.sql.ResultSet;
import java.sql.SQLException;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class UserDao {
	public static void insert(Customer normalUser) {
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
	
	public static void update(Customer normalUser) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		//String sql = "UPDATE USER SET "
	}
	
	public static void update(Seller seller) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
	}

	public static Customer findUser(String userId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		return null;
	}
	
	public Customer selectUsrById(final String userId) {
		RowMapper<Customer> rm = resultSetOfUsr();
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT * FROM USER WHERE USER_ID=?";
		return jdbcTemplate.queryForObject(sql, rm, userId);
	}
	
	private RowMapper<Customer> resultSetOfUsr() {
		return new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs) throws SQLException {
				return new Customer(rs.getString("USER_ID"), rs.getString("PASSWORD"), rs.getString("NAME"),
						rs.getString("PHONE"), rs.getString("ADDRESS"));
			}
		};
	}
	
	public Seller selectSellerById(final String userId) {
		RowMapper<Customer> rm = resultSetOfUsr();
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT * FROM USER WHERE USER_ID=?";
		//return jdbcTemplate.queryForObject(sql, rm, userId);
		return null;
	}
	
	private RowMapper<Customer> resultSetOfSeller() {
		return new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs) throws SQLException {
				return new Customer(rs.getString("USER_ID"), rs.getString("PASSWORD"), rs.getString("NAME"),
						rs.getString("PHONE"), rs.getString("ADDRESS"));
			}
		};
	}
}
