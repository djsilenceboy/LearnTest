
package com.djs.learn.spring_sample.logging;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import com.djs.learn.spring_sample.db.ItemLog;
import com.djs.learn.spring_sample.db.ItemLogDao;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import com.googlecode.ehcache.annotations.TriggersRemove;

@Repository(value = "itemLogDao")
public class ItemLogDaoHibernateImplA implements ItemLogDao
{
	private final Logger log = Logger.getLogger( ItemLogDaoHibernateImplA.class );

	@Autowired
	private JpaTemplate jpaTemplate;

	public void setJpaTemplate( JpaTemplate jpaTemplate )
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Set " + jpaTemplate.getClass().getName() );
		}

		this.jpaTemplate = jpaTemplate;
	}

	@Override
	// @TriggersRemove(cacheName = "localCache", removeAll = true)
	@TriggersRemove(cacheName = "localCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
	public void save( ItemLog itemLog )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Save: " + itemLog );
		}

		jpaTemplate.persist( itemLog );
	}
}
