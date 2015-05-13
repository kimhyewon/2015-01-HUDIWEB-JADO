package jado.dao;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class InitializeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
}
