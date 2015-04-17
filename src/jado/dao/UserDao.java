package jado.dao;

import jado.model.Customer;
import jado.model.Seller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, jdbcTemplate.getDataSource());
	}

	public void insert(final Customer customer) {
		String sql = "insert into USER values(?, ?, ?, ?, ? ,now(), null, 'F', ?)";
		jdbcTemplate.update(sql, customer.getUserId(), customer.getPassword(), customer.getName(), customer.getPhone(), customer.getAddress(), customer.getUserStatus());
	}

	public void insert(final Seller seller) {
		String sql = "insert into SELLER values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, seller.getShopUrl(), seller.getUserId(), seller.getBank(), seller.getBankAccount());
	}

	public void updateCustomer(final Customer customer) {
		String sql = "update USER set PHONE = ?, ADDRESS = ? where ID = ?";
		jdbcTemplate.update(sql, customer.getPhone(), customer.getAddress(), customer.getUserId());
	}

	public void updateSeller(final Seller seller) {
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
	}

	public Seller selectSellerById(final String userId) {
		String sql = "select * from SELLER where ID=?";
		try {
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), userId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Integer numberOfSellers() {
		String sql = "select count(ID) AS count from SELLER";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public void removeCustomer(final String userId) {
		String sql = "delete from USER where ID = ?";
		jdbcTemplate.update(sql, userId);
	}

	public void removeSeller(final String userId) {
		String sql = "delete from SELLER where ID = ?";
		jdbcTemplate.update(sql, userId);
	}

	public void updateMailAuthStatus() {
		String sql = "update USER set EMAIL_VALIDATE_STATUS = ?";
		// TODO [우선순위 : 다소 높음] - sql where절이 필요할것 같아요! request from 경륜 to 태호 
		jdbcTemplate.update(sql, "T");
	}

	// TODO 이메소드 필요 없는것 같아요!
	public Customer selectCustomerById(String userId) {
		return selectUserById(userId);
	}

	public boolean IsPasswordCorrect(String userId, String password) {
		if (selectCustomerById(userId) != null)
			return selectCustomerById(userId).getPassword().equals(password);
		return false;
	}

	public boolean IsEmailValidated(String userId) {
		if (selectCustomerById(userId) != null)
			return selectCustomerById(userId).getEmailValidateStatus().equals("T");
		return false;
	}

	public boolean isExistSeller(String userId) {
		if (selectSellerById(userId) != null)
			return true;
		return false;
	}

	public void insertDefaultRole(Customer customer) {
		String sql = "insert into USER_ROLE values(?, null)";
		jdbcTemplate.update(sql, customer.getUserId());
	}
}
