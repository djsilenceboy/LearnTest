
package com.djs.test.spring_sample.logging;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.djs.test.spring_sample.db.ItemLog;
import com.djs.test.spring_sample.db.ItemLogDao;

public class ItemLogDaoImplA implements ItemLogDao
{
	private final Logger log = Logger.getLogger( ItemLogDaoImplA.class );

	@Autowired
	private SimpleJdbcTemplate simpleJdbcTemplate;

	@Override
	public void save( ItemLog itemLog )
	{
		// SimpleJdbcTemplate works for both JdbcTemplate and NamedParameterJdbcTemplate.

		if (log.isInfoEnabled())
		{
			log.info( "Insert: " + itemLog );
		}
	}
}
