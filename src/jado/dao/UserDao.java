package jado.dao;

import jado.model.Customer;
import jado.model.Seller;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends JdbcDaoSupport {

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScripts(new ClassPathResource("sql/initDbSchema.sql"), new ClassPathResource("sql/insertTestSet.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
	}

	public void insert(Customer customer) {
		String sql = "insert into USER values (?, ?, ?, ?, ?, now(), null, 'F')";
		Object[] args = new Object[] { customer.getUserId(), customer.getPassword(), customer.getName(), customer.getPhone(), customer.getAddress() };
		getJdbcTemplate().update(sql, args);
	}

	public void insert(Seller seller) {
		String sql = "insert into SELLER values (?, ?, ?, ?)";
		Object[] args = new Object[] { seller.getUrl(), seller.getUserId(), seller.getBank(), seller.getBankAccount() };
		getJdbcTemplate().update(sql, args);
	}

	public void updateCustomer(Customer customer) {
		String sql = "update USER set PHONE = ?, ADDRESS = ? where ID = ?";
		Object[] args = new Object[] { customer.getPhone(), customer.getAddress(), customer.getUserId() };
		getJdbcTemplate().update(sql, args);
	}

	public void updateSeller(Seller seller) {
		String sql = "update SELLER set BANK = ?, BANK_ACCOUNT = ? where ID = ?";
		Object[] args = new Object[] { seller.getBank(), seller.getBankAccount(), seller.getUserId() };
		getJdbcTemplate().update(sql, args);
	}

	public Customer selectUserById(final String userId) {
		String sql = "select * from USER where ID=?";
		try {
			return getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<Customer>(Customer.class), userId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Seller selectSellerById(final String userId) {
		String sql = "select * from SELLER where ID=?";
		try {
			return getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), userId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Integer numberOfSellers() {
		String sql = "select count(*) from SELLER";
		return getJdbcTemplate().queryForObject(sql, Integer.class);
	}

	public void removeCustomer(String userId) {
		String sql = "delete from USER where ID = ?";
		getJdbcTemplate().update(sql, userId);
	}

	public void removeSeller(String userId) {
		String sql = "delete from SELLER where ID = ?";
		getJdbcTemplate().update(sql, userId);
	}

	public void updateMailAuthStatus() {
		String sql = "update USER set EMAIL_VALIDATE_STATUS = ?";
		// TODO @태호 sql where절이 필요할것 같아요!
		getJdbcTemplate().update(sql, "T");
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
}
