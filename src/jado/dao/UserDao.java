package jado.dao;


import jado.model.Customer;
import jado.model.Seller;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.google.common.annotations.Beta;

import core.exception.UserNotFoundException;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class UserDao {
	
	@Autowired
	private DataSource datasource;
	
	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScripts(new ClassPathResource("sql/initDbSchema.sql"), new ClassPathResource("sql/insertTestSet.sql"));
		DatabasePopulatorUtils.execute(populator, datasource);
	}
	
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
				rs.getString("NAME"), rs.getString("PHONE"), rs.getString("ADDRESS"), rs.getString("IS_VALIDATED"));
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
		String sql = "select count(ID) AS count from SELLER";
		RowMapper<Integer> rm = rs -> rs.getInt("count");
		return jdbcTemplate.executeQuery(sql, rm);
	}
	
	public static void removeCustomer(String userId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();	
		String sql = "delete from USER where ID = ?";
		jdbcTemplate.executeUpdate(sql);		
	}

	public static void removeSeller(String userId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();	
		String sql = "delete from SELLER where ID = ?";
		jdbcTemplate.executeUpdate(sql,userId);		
	}
	
	public void updateMailAuthStatus() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "update USER set IS_VALIDATED = ?";
		jdbcTemplate.executeUpdate(sql, "T");
	}

	public static Customer selectCustomerById(String userId) throws UserNotFoundException {
		Customer user = selectUserById(userId);
		if(user == null) throw new UserNotFoundException("아이디가 존재하지 않습니다 다시 로그인 해주세요");
		return user;
	}
}
