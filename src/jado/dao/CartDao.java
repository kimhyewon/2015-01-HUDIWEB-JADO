package jado.dao;

import java.util.List;

import jado.model.Cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao {
	private static final Logger logger = LoggerFactory.getLogger(CartDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert(final Cart cart) {
		String sql = "insert into CART VALUES(?, ?, ?, ?, default, default);";
		jdbcTemplate.update(sql, cart.getShopUrl(), cart.getCustomerId(), cart.getProductId(), cart.getAmount());
	}
	
	public List<Cart> selectAllForCart(String shopUrl, String customerId) {
		String sql = "select * from CART WHERE SHOP_URL=? and CUSTOMER_ID=? and IS_PAY=0";
		Object[] args = new Object[] { shopUrl, customerId };
		try {
			return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Cart>(Cart.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
}
