package core.jadopay;

import jado.model.PaymentWithProduct;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, jdbcTemplate.getDataSource());
	}

	public Integer getPrice(int productId) {
		String sql = "select PRICE from PRODUCT where ID=?";
		try {
			return jdbcTemplate.queryForObject(sql, Integer.class, productId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void processPay(String userId, String shopUrl, String cardCompany, int price, int productId) {
		String sql = "insert into PAYMENT values(?, ?, ?, ?, ?, null)";
		jdbcTemplate.update(sql, shopUrl, userId, productId, cardCompany, price);
	}

	public String getProductName(int productId) {
		String sql = "select NAME from PRODUCT where ID=?";
		try {
			return jdbcTemplate.queryForObject(sql, String.class, productId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<PaymentWithProduct> selectAll(String customerId, String url) {
		String sql = "select PAYMENT.BANK, PAYMENT.PRICE as REAL_PRICE, PAYMENT.PAY_TIME, PRODUCT.* from PAYMENT "
				+ "inner join PRODUCT on PAYMENT.PRODUCT_ID = PRODUCT.ID WHERE PAYMENT.SHOP_URL=? and PAYMENT.CUSTOMER_ID=?";
		Object[] args = new Object[] { url , customerId};
		try {
			return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<PaymentWithProduct>(PaymentWithProduct.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<PaymentWithProduct> selectAll(String url) {
		String sql = "select PAYMENT.BANK, PAYMENT.PRICE as REAL_PRICE, PAYMENT.PAY_TIME, PRODUCT.* from PAYMENT "
				+ "inner join PRODUCT on PAYMENT.PRODUCT_ID = PRODUCT.ID WHERE PAYMENT.SHOP_URL=?";
		Object[] args = new Object[] { url };
		try {
			return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<PaymentWithProduct>(PaymentWithProduct.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
