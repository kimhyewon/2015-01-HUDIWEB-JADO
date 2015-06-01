package jado.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao {
	private static final Logger logger = LoggerFactory.getLogger(CartDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
}
