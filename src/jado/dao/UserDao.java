package jado.dao;

import jado.model.Customer;
import jado.model.Seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

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
		try {
			return jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
	}

	public void updateDeleteUser(final String userId) {
		String sql = "update USER set ENABLED=2 where ID = ?";
		jdbcTemplate.update(sql, userId);
	}

	public void removeSeller(final String userId) {
		String sql = "delete from SELLER where ID = ?";
		jdbcTemplate.update(sql, userId);
	}
	
	public void updateUserRole(String userId) {
		String role = typeOfMailAuthStatus(userId);
		String sql = "update USER_ROLE set ROLE=? WHERE USER_ID=?";
		jdbcTemplate.update(sql, role, userId);
	}

	public String typeOfMailAuthStatus(String userId) {
		if (selectSellerById(userId) != null) {
			return "ROLE_SELLER";
		}
		return "ROLE_CUSTOMER";
	}

	public void insertDefaultRole(Customer customer) {
		String sql = "insert into USER_ROLE (USER_ID) values(?)";
		jdbcTemplate.update(sql, customer.getUserId());
	}

	public void updateUserEmailValidateStatus(String userEmail) {
		String sql ="update USER set EMAIL_VALIDATE_STATUS='T' where ID=?";
		jdbcTemplate.update(sql, userEmail);
	}
}
