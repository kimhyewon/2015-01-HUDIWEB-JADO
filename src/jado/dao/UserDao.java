package jado.dao;

import jado.model.Customer;
import jado.model.Seller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	/*
	 * jdbcTemplate은 bean으로 등록되었으므로 DI받아 사용합니다.
	 * 글쎄요.. JdbcDaoSupport를 상속받을 필요는 없을 것 같습니다.
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*
	 * import org.springframework.core.io.Resource; spring-beans 의존성 필요	
	 */
	@Value("classpath:sql/initDbSchema.sql")
	private Resource dbSchema;
	
	@Value("classpath:sql/insertTestSet.sql")
	private Resource testSet;

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScripts(dbSchema, testSet);
		DatabasePopulatorUtils.execute(populator, jdbcTemplate.getDataSource());

	}

	public void d(Customer customer) {
		String sql = "insert into USER values(?, ?, ?, ?, ? ,now(), null, 'F')";
		jdbcTemplate.update(sql, customer.getUserId(), customer.getPassword(), customer.getName(), customer.getPhone(), customer.getAddress());
	}

	public void insert(Seller seller) {
		String sql = "insert into SELLER values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, seller.getUrl(), seller.getUserId(), seller.getBank(), seller.getBankAccount());
	}

	public void updateCustomer(Customer customer) {
		String sql = "update USER set PHONE = ?, ADDRESS = ? where ID = ?";
		jdbcTemplate.update(sql, customer.getPhone(), customer.getAddress(), customer.getUserId());
	}

	public void updateSeller(Seller seller) {
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

		RowMapper<Seller> rm = new RowMapper<Seller>() {
			@Override
			public Seller mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Seller(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		};
		
		try {
			return jdbcTemplate.queryForObject(sql, rm, userId);
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
		return jdbcTemplate.queryForObject(sql, Integer.class);
		// RowMapper<Integer> rm = rs -> rs.getInt("count");
		// return jdbcTemplate.executeQuery(sql, rm);
	}

	public void removeCustomer(String userId) {
		String sql = "delete from USER where ID = ?";
		jdbcTemplate.update(sql);
	}

	public void removeSeller(String userId) {
		String sql = "delete from SELLER where ID = ?";
		jdbcTemplate.update(sql, userId);
	}

	public void updateMailAuthStatus() {
		String sql = "update USER set EMAIL_VALIDATE_STATUS = ?";
		jdbcTemplate.update(sql, "T");
	}

	public Customer selectCustomerById(String userId) {
		return selectUserById(userId);
	}

	public void insert(Customer customer) {
		String sql = "insert into USER values (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, customer.getUserId(), customer.getPassword(), customer.getName(), customer.getPhone(), customer.getAddress(), new Date(), null, "F");
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
