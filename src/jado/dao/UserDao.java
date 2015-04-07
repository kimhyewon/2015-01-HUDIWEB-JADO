package jado.dao;

import jado.model.Customer;
import jado.model.Seller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
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

	public void d(Customer customer) {
		String sql = "insert into USER values(?, ?, ?, ?, ? ,now(), null, 'F')";
		getJdbcTemplate().update(sql, customer.getUserId(), customer.getPassword(), customer.getName(), customer.getPhone(), customer.getAddress());
	}

	public void insert(Seller seller) {
		String sql = "insert into SELLER values (?, ?, ?, ?)";
		getJdbcTemplate().update(sql, seller.getUrl(), seller.getUserId(), seller.getBank(), seller.getBankAccount());
	}

	public void updateCustomer(Customer customer) {
		String sql = "update USER set PHONE = ?, ADDRESS = ? where ID = ?";
		getJdbcTemplate().update(sql, customer.getPhone(), customer.getAddress(), customer.getUserId());
	}

	public void updateSeller(Seller seller) {
		String sql = "update SELLER set BANK = ?, BANK_ACCOUNT = ? where ID = ?";
		getJdbcTemplate().update(sql, seller.getBank(), seller.getBankAccount(), seller.getUserId());
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

		RowMapper<Seller> rm = new RowMapper<Seller>() {
			@Override
			public Seller mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Seller(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		};
		
		try {
			return getJdbcTemplate().queryForObject(sql, rm, userId);
			// } catch ()
			// try {
			// return (Seller)jdbcTemplate.queryForObject(sql, new
			// BeanPropertyRowMapper<Seller>(Seller.class), userId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	// TODO 고쳐야 함
	public Integer numberOfSellers() {
		String sql = "select count(ID) AS count from SELLER";
		return getJdbcTemplate().queryForObject(sql, Integer.class);
		// RowMapper<Integer> rm = rs -> rs.getInt("count");
		// return jdbcTemplate.executeQuery(sql, rm);
	}

	public void removeCustomer(String userId) {
		String sql = "delete from USER where ID = ?";
		getJdbcTemplate().update(sql);
	}

	public void removeSeller(String userId) {
		String sql = "delete from SELLER where ID = ?";
		getJdbcTemplate().update(sql, userId);
	}

	public void updateMailAuthStatus() {
		String sql = "update USER set EMAIL_VALIDATE_STATUS = ?";
		getJdbcTemplate().update(sql, "T");
	}

	public Customer selectCustomerById(String userId) {
		return selectUserById(userId);
	}

	public void insert(Customer customer) {
		String sql = "insert into USER values (?, ?, ?, ?, ?, ?, ?, ?)";
		getJdbcTemplate().update(sql, customer.getUserId(), customer.getPassword(), customer.getName(), customer.getPhone(), customer.getAddress(), new Date(), null, "F");
	}

	public boolean IsPasswordCorrect(String userId, String password) {
		if (selectUserById(userId) != null)
			return selectUserById(userId).getPassword().equals(password);
		return false;
	}

	public boolean IsEmailValidated(String userId) {
		if (selectUserById(userId) != null)
			return selectUserById(userId).getEmailValidateStatus().equals("T");
		return false;
	}

	public boolean isExistSeller(String userId) {
		if (selectSellerById(userId) != null)
			return true;
		return false;
	}
}
