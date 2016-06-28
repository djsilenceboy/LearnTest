
package com.djs.test.spring_sample.hibernate;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.djs.test.spring_sample.db.ItemLog;
import com.djs.test.spring_sample.db.ItemLogDao;

public class ItemLogDaoSpringHibernateImplA extends HibernateDaoSupport implements ItemLogDao
{
	private final Logger log = Logger.getLogger( ItemLogDaoSpringHibernateImplA.class );

	@Override
	public void save( ItemLog itemLog )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Save: " + itemLog );
		}

		getHibernateTemplate().save( itemLog );
	}
}
