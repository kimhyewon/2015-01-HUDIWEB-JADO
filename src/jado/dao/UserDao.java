package jado.dao;

import jado.model.Customer;
import jado.model.Seller;

import java.sql.ResultSet;
import java.sql.SQLException;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class UserDao {
	public static void insert(Customer customer) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into user values(?, ?, ?, ?, ? ,now(), null)";
		jdbcTemplate.update(sql, customer.getUserId(),
				customer.getPassword(), customer.getName(),
				customer.getPhone(), customer.getAddress());
	}

	public static void insert(Seller seller) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into seller values (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, seller.getUserId(), seller.getShopUrl(),
				seller.getShopPhone(), seller.getBank(),
				seller.getBankAccount());
	}
	public static void updateCustomer(Customer customer) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "update USER set (PHONE, ADDRESS) values (?, ?)";
		jdbcTemplate.update(sql, customer.getPhone(), customer.getAddress());
	}

	public static void updateSeller(Seller seller) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "update SELLER set (SHOP_URL, SHOP_PHONE, BANK, BANK_ACCOUNT) values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, seller.getShopUrl(), seller.getShopPhone(), seller.getBank(), seller.getBankAccount());
	}
	
	public static Customer selectUserById(final String userId) {
		RowMapper<Customer> rm = resultSetOfUsr();
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select * from USER where USER_ID=?";
		return jdbcTemplate.queryForObject(sql, rm, userId);
	}
	
	private static RowMapper<Customer> resultSetOfUsr() {
		return new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs) throws SQLException {
				return new Customer(rs.getString("USER_ID"), rs.getString("PASSWORD"), rs.getString("NAME"),
						rs.getString("PHONE"), rs.getString("ADDRESS"));
			}
		};
	}
	
	public static Seller selectSellerById(final String userId) {
		RowMapper<Seller> rm = resultSetOfSeller();
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select * from SELLER where SELLER_ID=?";
		return jdbcTemplate.queryForObject(sql, rm, userId);
	}
	
	private static RowMapper<Seller> resultSetOfSeller() {
		return new RowMapper<Seller>() {
			@Override
			public Seller mapRow(ResultSet rs) throws SQLException {
				return new Seller(rs.getString("SELLER_ID"), rs.getString("SHOP_URL"), rs.getString("SHOP_PHONE"),
						rs.getString("BANK"), rs.getString("BANK_ACCOUNT"));
			}
		};
	}
	
	public static int numberOfSellers() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select count(SELLER_ID) from SELLER";
		System.out.println(jdbcTemplate.update(sql));
		return jdbcTemplate.update(sql);
	}
}
