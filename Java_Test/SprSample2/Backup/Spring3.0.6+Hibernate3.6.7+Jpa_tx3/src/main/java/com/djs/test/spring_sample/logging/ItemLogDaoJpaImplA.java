
package com.djs.test.spring_sample.logging;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import com.djs.test.spring_sample.db.ItemLog;
import com.djs.test.spring_sample.db.ItemLogDao;

@Repository(value = "itemLogDao")
public class ItemLogDaoJpaImplA implements ItemLogDao
{
	private final Logger log = Logger.getLogger( ItemLogDaoJpaImplA.class );

	@Autowired
	private JpaTemplate jpaTemplate;

	@Override
	public void save( ItemLog itemLog )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Save: " + itemLog );
		}

		jpaTemplate.persist( itemLog );
	}
}
