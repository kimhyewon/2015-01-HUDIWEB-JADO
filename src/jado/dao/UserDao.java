package jado.dao;

import jado.model.Customer;
import jado.model.Seller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScripts(new ClassPathResource("sql/initDbSchema.sql"), new ClassPathResource("sql/insertTestSet.sql"));
		DatabasePopulatorUtils.execute(populator, dataSource);
	}
	
	public  void d(Customer customer) {
		String sql = "insert into USER values(?, ?, ?, ?, ? ,now(), null, 'F')";
		jdbcTemplate.update(sql, customer.getUserId(), customer.getPassword(), customer.getName(),
				customer.getPhone(), customer.getAddress());
	}

	public  void insert(Seller seller) {
		String sql = "insert into SELLER values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, seller.getUrl(), seller.getUserId(), seller.getBank(), seller.getBankAccount());
	}

	public  void updateCustomer(Customer customer) {
		String sql = "update USER set PHONE = ?, ADDRESS = ? where ID = ?";
		jdbcTemplate.update(sql, customer.getPhone(), customer.getAddress(), customer.getUserId());
	}

	public  void updateSeller(Seller seller) {
		String sql = "update SELLER set BANK = ?, BANK_ACCOUNT = ? where ID = ?";
		jdbcTemplate.update(sql, seller.getBank(), seller.getBankAccount(), seller.getUserId()); 
	}

	public Customer selectUserById(final String userId) {
		String sql = "select * from USER where ID=?";
		try {
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Customer>(Customer.class), userId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
//			return new Customer(rs.getString("ID"), rs.getString("PASSWORD"),
//					rs.getString("NAME"), rs.getString("PHONE"), rs.getString("ADDRESS"), rs.getString("IS_VALIDATED"));
	}

	public  Seller selectSellerById(final String userId) {
		String sql = "select * from SELLER where ID=?";
		try {
			return (Seller) jdbcTemplate.queryForObject(sql, new Object [] {userId}, Seller.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	// TODO 고쳐야 함
	public  Integer numberOfSellers() {
		String sql = "select count(ID) AS count from SELLER";
		return jdbcTemplate.queryForObject(sql, Integer.class);
//		RowMapper<Integer> rm = rs -> rs.getInt("count");
//		return jdbcTemplate.executeQuery(sql, rm);
	}
	
	public  void removeCustomer(String userId) {
		String sql = "delete from USER where ID = ?";
		jdbcTemplate.update(sql);		
	}

	public  void removeSeller(String userId) {
		String sql = "delete from SELLER where ID = ?";
		jdbcTemplate.update(sql,userId);		
	}
	
	public void updateMailAuthStatus() {
		String sql = "update USER set EMAIL_VALIDATE_STATUS = ?";
		jdbcTemplate.update(sql, "T");
	}

	public  Customer selectCustomerById(String userId) {
		 return selectUserById(userId);
	}

	public void insert(Customer customer) {
		String sql = "insert into USER values (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, customer.getUserId(), customer.getPassword(), customer.getName(), customer.getPhone(), customer.getAddress(), new Date(), null, "F");
	}

	public boolean IsPasswordCorrect(String userId, String password) {
		if(selectUserById(userId) != null)
			return selectUserById(userId).getPassword().equals(password);
		return false;
	}

	public boolean IsEmailValidated(String userId) {
		if(selectUserById(userId) != null)
			return selectUserById(userId).getEmailValidateStatus().equals("T");
		return false;
	}

	public boolean isExistSeller(String userId) {
		if(selectSellerById(userId) != null) 
			return true;
		return false;
	}
}
