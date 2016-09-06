
package com.djs.learn.hbt_sample;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
	private final static Logger log = Logger.getLogger( HibernateUtil.class );

	private static SessionFactory sessionFactory;

	static
	{
		try
		{
			log.debug( "Before Configuration().configure().buildSessionFactory()." );

			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		catch (Throwable e)
		{
			log.error( e, e );

			throw new ExceptionInInitializerError( e );
		}
	}

	public static SessionFactory getSessionFactory()
	{
		// Alternatively, you could look up in JNDI here.
		return sessionFactory;
	}

	public static void shutdown()
	{
		log.debug( "Before SessionFactory.close()." );

		// Close caches and connection pools.
		getSessionFactory().close();
	}
}
