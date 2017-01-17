
package com.djs.learn.mvc.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan("com.djs.learn.mvc")
public class RootApplicationContextConfig
{
	private static final Logger logger = Logger.getLogger(RootApplicationContextConfig.class);

	@Bean
	public DataSource dataSource(){
		logger.info(this.getClass().getName() + ":dataSource");
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL).addScript("db/sql/create-table.sql").addScript("db/sql/insert-data.sql").build();
		return db;
	}

	@Bean
	public NamedParameterJdbcTemplate getJdbcTemplate(){
		logger.info(this.getClass().getName() + ":getJdbcTemplate");
		return new NamedParameterJdbcTemplate(dataSource());
	}
}
