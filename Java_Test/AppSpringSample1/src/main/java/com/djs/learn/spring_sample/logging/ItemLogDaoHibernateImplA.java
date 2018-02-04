
package com.djs.learn.spring_sample.logging;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.djs.learn.spring_sample.db.ItemLog;
import com.djs.learn.spring_sample.db.ItemLogDao;

public class ItemLogDaoHibernateImplA implements ItemLogDao
{
	private final Logger log = Logger.getLogger( ItemLogDaoHibernateImplA.class );

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void save( ItemLog itemLog )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Save: " + itemLog );
		}

		hibernateTemplate.save( itemLog );
	}
}
