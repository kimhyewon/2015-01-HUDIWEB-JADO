package jado.dao;

import jado.model.Customer;
import jado.model.Seller;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class UserDao {
	public static void insert(Customer customer) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into USER values(?, ?, ?, ?, ? ,now(), null, 'F')";
		jdbcTemplate.executeUpdate(sql, customer.getUserId(), customer.getPassword(), customer.getName(),
				customer.getPhone(), customer.getAddress());
	}

	public static void insert(Seller seller) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into SELLER values (?, ?, ?, ?)";
		jdbcTemplate.executeUpdate(sql, seller.getUrl(), seller.getUserId(), seller.getBank(), seller.getBankAccount());
	}

	public static void updateCustomer(Customer customer) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "update USER set PHONE = ?, ADDRESS = ? where ID = ?";
		jdbcTemplate.executeUpdate(sql, customer.getPhone(), customer.getAddress(), customer.getUserId());
	}

	public static void updateSeller(Seller seller) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "update SELLER set BANK = ?, BANK_ACCOUNT = ? where ID = ?";
		jdbcTemplate.executeUpdate(sql, seller.getBank(), seller.getBankAccount(), seller.getUserId()); 
	}

	public static Customer selectUserById(final String userId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select * from USER where ID=?";
		RowMapper<Customer> rm = rs -> new Customer(rs.getString("ID"), rs.getString("PASSWORD"),
				rs.getString("NAME"), rs.getString("PHONE"), rs.getString("ADDRESS"));
		return jdbcTemplate.executeQuery(sql, rm, userId);
	}

	public static Seller selectSellerById(final String userId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select * from SELLER where ID=?";
		RowMapper<Seller> rm = rs -> new Seller(rs.getString("ID"), rs.getString("SHOP_URL"),
				rs.getString("BANK"), rs.getString("BANK_ACCOUNT")); 
		return jdbcTemplate.executeQuery(sql, rm, userId);
	}

	public static int numberOfSellers() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select count(SELLER_ID) AS count from SELLER";
		RowMapper<Integer> rm = rs -> rs.getInt("count");
		return jdbcTemplate.executeQuery(sql, rm);
	}
	
	public void removeUser(Customer customer) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();	
		String sql = "delete from USER where USER_ID = ?";
		jdbcTemplate.executeUpdate(sql, customer.getUserId());		
	}

	public void removeUser(Seller seller) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();	
		String sql = "delete from SELLER where USER_ID = ?";
		jdbcTemplate.executeUpdate(sql, seller.getUserId());		
	}
}
