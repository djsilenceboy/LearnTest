
package com.djs.learn.spring_sample;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.djs.learn.spring_sample.db.Item;
import com.djs.learn.spring_sample.db.ItemDao;
import com.djs.learn.spring_sample.greeting.GreetingService;
import com.djs.learn.spring_sample.knight.Knight;
import com.djs.learn.spring_sample.music.Performer;

public class AppTest
{
	private final Logger log = Logger.getLogger( AppTest.class );

	private ApplicationContext appContext = null;

	@Before
	public void setUp()
	{
	}

	private void loadPropertiesAndContext( String propFileName ) throws Exception
	{
		// This one doesn't have "classpath" prefix. Because it is loaded by Java.
		ConfigSettings cs = new ConfigSettings( "/spring_config/" + propFileName );
		Properties pp = cs.getProperties();
		Set<Object> keys = pp.keySet();

		for (Object item : keys)
		{
			String key = (String)item;
			String value = pp.getProperty( key );

			System.setProperty( key, value );

			if (log.isTraceEnabled())
			{
				log.trace( key + " = " + value );
			}
		}

		// Notes: AOP doesn't work while using XmlBeanFactory!
		appContext = new ClassPathXmlApplicationContext( System.getProperty( "file_path.context.entry_xml" ) );
	}

	@Ignore
	@Test
	public void testGreeting()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "sample/test_greeting.properties" );

			GreetingService greetingService = (GreetingService)appContext.getBean( "greetingService" );
			greetingService.sayGreeting();
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	@Ignore
	@Test
	public void testKnight()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "sample/test_knight.properties" );

			Knight knightA = (Knight)appContext.getBean( "knightA" );
			knightA.embarkOnQuest();

			Knight knightB = (Knight)appContext.getBean( "knightB" );
			knightB.embarkOnQuest();
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	@Ignore
	@Test
	public void testMusic()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "sample/test_music.properties" );

			Performer performerA = (Performer)appContext.getBean( "performerA" );
			performerA.perform();

			Performer performerB = (Performer)appContext.getBean( "performerB" );
			performerB.perform();

			Performer performerC = (Performer)appContext.getBean( "performerC" );
			performerC.perform();

			Performer performerD1 = (Performer)appContext.getBean( "performerD1" );
			performerD1.perform();

			Performer performerD2 = (Performer)appContext.getBean( "performerD2" );
			performerD2.perform();

			appContext.getBean( "stageA" );
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	// @Ignore
	@Test
	public void testDbJdbc()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "test_db/test_jdbc.properties" );

			int id = (int)System.currentTimeMillis();
			final String namePrefix = "Jdbc_";

			// 0 - 6
			// Recommended by order: 2(5), 1(4), 0(3).
			// If use stored procedure: 3.
			int from = 3;
			int to = 3;

			for (int i = from; i <= to; i++)
			{
				Item item = new Item();
				item.setSeqId( new Integer( id++ ) );
				item.setItemName( namePrefix + i );
				item.setDescription( "This is " + namePrefix + i + "." );

				ItemDao itemDao = (ItemDao)appContext.getBean( "itemDao" + i );
				itemDao.save( item );
				itemDao.query( namePrefix + i );
			}
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	@Ignore
	@Test
	public void testDbHibernate()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "test_db/test_hibernate.properties" );

			final String namePrefix = "Hibernate_";
			// 0 - 3
			// Recommended by order: 1, 0, 3.
			int from = 0;
			int to = 0;

			for (int i = from; i <= to; i++)
			{
				Item item = new Item();
				// Hibernate will auto generate ID from sequence.
				item.setItemName( namePrefix + i );
				item.setDescription( "This is " + namePrefix + i + "." );
				item.setAddTime( new Date() );

				ItemDao itemDao = (ItemDao)appContext.getBean( "itemDao" + i );
				itemDao.save( item );
				itemDao.query( namePrefix + i );
				// Repeat to test caching.
				// If caching working, this call will not be totally bypassed! Not be called at all!
				// So, there is no log from following method call.
				List<Item> items = itemDao.query( namePrefix + i );
				// But items is actually get the cached values. And there is log for following output!
				if (log.isInfoEnabled())
				{
					log.info( "Result: " + items );
				}

				// For Bean itemDao1, if not add "@Scope("prototype")", the instance itemDao1 and itemDao2 are same one.
				// If add "@Scope("prototype")", the instance itemDao1 and itemDao2 are different ones.
				ItemDao itemDao2 = (ItemDao)appContext.getBean( "itemDao" + i );
				itemDao2.query( namePrefix + i );
				// But items is actually get the cached values. And there is log for following output!
				if (log.isInfoEnabled())
				{
					log.info( "Result 2: " + items );
				}
			}
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	@Ignore
	@Test
	public void testDbJpa()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "test_db/test_jpa.properties" );

			final String namePrefix = "Jpa_";
			// 0 - 1
			int from = 0;
			int to = 0;

			for (int i = from; i <= to; i++)
			{
				Item item = new Item();
				// Hibernate will auto generate ID from sequence.
				item.setItemName( namePrefix + i );
				item.setDescription( "This is " + namePrefix + i + "." );
				item.setAddTime( new Date() );

				ItemDao itemDao = (ItemDao)appContext.getBean( "itemDao" + i );
				itemDao.save( item );
				itemDao.query( namePrefix + i );
				// Repeat to test caching.
				// If caching working, this call will not be totally bypassed! Not be called at all!
				// So, there is no log from following method call.
				List<Item> items = itemDao.query( namePrefix + i );
				// But items is actually get the cached values. And there is log for following output!
				if (log.isInfoEnabled())
				{
					log.info( "Result: " + items );
				}
			}
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}
}
