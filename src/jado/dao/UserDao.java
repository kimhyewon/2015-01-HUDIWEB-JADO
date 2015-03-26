package jado.dao;

import jado.model.Customer;
import jado.model.Seller;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class UserDao {
	public static void insert(Customer customer) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into user values(?, ?, ?, ?, ? ,now(), null, 'F')";
		jdbcTemplate.executeUpdate(sql, customer.getUserId(), customer.getPassword(), customer.getName(),
				customer.getPhone(), customer.getAddress());
	}

	public static void insert(Seller seller) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into seller values (?, ?, ?, ?)";
		jdbcTemplate.executeUpdate(sql, seller.getUrl(), seller.getUserId(), seller.getBank(), seller.getBankAccount());
	}

	public static void updateCustomer(Customer customer) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "update USER set (PHONE, ADDRESS) values (?, ?) where USER_ID = ?";
		jdbcTemplate.executeUpdate(sql, customer.getPhone(), customer.getAddress(), customer.getUserId());
	}

	public static void updateSeller(Seller seller) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "update SELLER set (SHOP_URL, SHOP_PHONE, BANK, BANK_ACCOUNT) values (?, ?, ?, ?) where SELLER_ID = ?";
		jdbcTemplate.executeUpdate(sql);	////재우오빠 고쳐줘용~ 
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
		String sql = "select * from SELLER where SELLER_ID=?";
		RowMapper<Seller> rm = rs -> new Seller(sql, sql, sql, sql);	//재우오빠 고쳐줘용~ 
		return jdbcTemplate.executeQuery(sql, rm, userId);
	}

	public static int numberOfSellers() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select count(SELLER_ID) AS count from SELLER";
		RowMapper<Integer> rm = rs -> rs.getInt("count");
		return jdbcTemplate.executeQuery(sql, rm);
	}
	
	public void removeUser(Seller seller) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();	
		String sql = "delete from SELLER where USER_ID = ?";
		jdbcTemplate.executeUpdate(sql, seller.getUserId());		
	}
	
	public void removeUser(Customer customer) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();	
		String sql = "delete from USER where USER_ID = ?";
		jdbcTemplate.executeUpdate(sql, customer.getUserId());		
	}
}
