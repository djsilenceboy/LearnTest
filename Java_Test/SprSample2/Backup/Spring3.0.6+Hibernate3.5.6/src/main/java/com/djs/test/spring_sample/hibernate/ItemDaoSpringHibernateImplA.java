
package com.djs.learn.spring_sample.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.djs.learn.spring_sample.db.Item;
import com.djs.learn.spring_sample.db.ItemDao;

/*
 This class use spring-modules-cache. But it is discontinued.
 
 <!-- SpringModules framework is dead -->
		<dependency>
			<groupId>org.springmodules</groupId>
			<artifactId>spring-modules-cache</artifactId>
			<version>0.8</version>
			<exclusions>
				<exclusion>
					<artifactId>gigaspaces-ce</artifactId>
					<groupId>gigaspaces</groupId>
				</exclusion>
				<exclusion>
					<groupId>jini</groupId>
					<artifactId>jsk-lib</artifactId>
				</exclusion>
				<exclusion>
					<groupId>jini</groupId>
					<artifactId>jsk-platform</artifactId>
				</exclusion>
				<exclusion>
					<groupId>jini</groupId>
					<artifactId>mahalo</artifactId>
				</exclusion>
				<exclusion>
					<groupId>jini</groupId>
					<artifactId>reggie</artifactId>
				</exclusion>
				<exclusion>
					<groupId>jini</groupId>
					<artifactId>start</artifactId>
				</exclusion>
				<exclusion>
					<groupId>jini</groupId>
					<artifactId>boot</artifactId>
				</exclusion>
				<exclusion>
					<groupId>jini</groupId>
					<artifactId>webster</artifactId>
				</exclusion>
				<exclusion>
					<groupId>jboss</groupId>
					<artifactId>jboss-cache</artifactId>
				</exclusion>
				<exclusion>
					<groupId>jboss</groupId>
					<artifactId>jboss-common</artifactId>
				</exclusion>
				<exclusion>
					<groupId>jboss</groupId>
					<artifactId>jboss-jmx</artifactId>
				</exclusion>
				<exclusion>
					<groupId>jboss</groupId>
					<artifactId>jboss-minimal</artifactId>
				</exclusion>
				<exclusion>
					<groupId>jboss</groupId>
					<artifactId>jboss-system</artifactId>
				</exclusion>
				<exclusion>
					<groupId>jcs</groupId>
					<artifactId>jcs</artifactId>
				</exclusion>
				<exclusion>
					<groupId>xpp3</groupId>
					<artifactId>xpp3_min</artifactId>
				</exclusion>
				<exclusion>
					<groupId>ehcache</groupId>
					<artifactId>ehcache</artifactId>
				</exclusion>
				<exclusion>
					<groupId>org.springframework</groupId>
					<artifactId>spring</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:ehcache="http://www.springmodules.org/schema/ehcache"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
						http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
						http://www.springmodules.org/schema/ehcache
						http://www.springmodules.org/schema/cache/springmodules-ehcache.xsd">

	<!-- This is to enable Ehcache for itemDao2. -->
	<ehcache:config configLocation="classpath:db_ehcache.xml" />

	<!-- This method is not recommended, because SpringModules framework is 
		dead. -->
	<ehcache:annotations>
		<ehcache:caching id="localCacheModel" cacheName="localCache" />
		<ehcache:flushing id="localFlushModel" cacheNames="localCache"
			when="before" />
	</ehcache:annotations>

	<bean id="itemDao2"
		class="com.djs.learn.spring_sample.hibernate.ItemDaoSpringHibernateImplA">
		<property name="sessionFactory" ref="sessionFactory" />
	</bean>

</beans>
 */
public class ItemDaoSpringHibernateImplA extends HibernateDaoSupport implements ItemDao
{
	private final Logger log = Logger.getLogger( ItemDaoSpringHibernateImplA.class );

	@Override
	// @CacheFlush(modelId = "localFlushModel")
	public void save( Item item )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Save/Update: " + item );
		}

		// This saveOrUpdate() will do an update action first. If not exit, then do an insert action.
		// getHibernateTemplate().saveOrUpdate( item );
		getHibernateTemplate().save( item );
	}

	@Override
	// @Cacheable(modelId = "localCacheModel")
	public List<Item> query( String itemName )
	{
		// Here must include Item's full package name.
		final String sql = "FROM " + Item.class.getName() + " WHERE ITEM_NAME = ?";

		if (log.isInfoEnabled())
		{
			log.info( "Query: " + itemName );
		}

		List items = getHibernateTemplate().find( sql, new Object []
		{ itemName } );

		if (log.isInfoEnabled())
		{
			log.info( "Result: " + items );
		}

		return items.size() > 0 ? (List<Item>)items : null;
	}
}
