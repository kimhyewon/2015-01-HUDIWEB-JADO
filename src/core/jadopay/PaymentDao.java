package core.jadopay;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDao {
	private static final Logger logger = LoggerFactory.getLogger(PaymentDao.class);
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
}
