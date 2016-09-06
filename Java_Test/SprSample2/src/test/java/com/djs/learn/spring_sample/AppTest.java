
package com.djs.learn.spring_sample;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import com.djs.learn.spring_sample.aop.AirMail;
import com.djs.learn.spring_sample.aop.DeliverInterface;
import com.djs.learn.spring_sample.db.Item;
import com.djs.learn.spring_sample.db.ItemDao;
import com.djs.learn.spring_sample.event.EmailService;
import com.djs.learn.spring_sample.expression.ACompany;
import com.djs.learn.spring_sample.expression.AEmployee;
import com.djs.learn.spring_sample.greeting.GreetingService;
import com.djs.learn.spring_sample.knightI.Knight;
import com.djs.learn.spring_sample.music.Performer;
import com.djs.learn.spring_sample.property.Company;
import com.djs.learn.spring_sample.property.Employee;
import com.djs.learn.spring_sample.property.PropertyResource;
import com.djs.learn.spring_sample.tx.TransactionalServiceA;
import com.djs.learn.spring_sample.tx.TransactionalServiceB;
import com.djs.learn.spring_sample.validation.Person;
import com.djs.learn.spring_sample.validation.PersonValidator;

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
	public void testAop()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "sample/test_aop.properties" );

			AirMail airMailA = (AirMail)appContext.getBean( "airMailA" );
			DeliverInterface deliver = (DeliverInterface)appContext.getBean( "deliver" );

			deliver.deliver();

			deliver.deliver( airMailA );
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	@Ignore
	@Test
	public void testEvent()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "sample/test_event.properties" );

			EmailService emailService = (EmailService)appContext.getBean( "emailService" );
			emailService.sendEmail( "yes@list.org", "Hello, yes!" );
			log.trace( "----------" );
			emailService.sendEmail( "black@list.org", "Hello, black!" );
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	@Ignore
	@Test
	public void testExpression()
	{
		log.trace( "Enter..." );

		try
		{
			ExpressionParser parser = new SpelExpressionParser();

			{
				Expression exp = parser.parseExpression( "'Hello World'.concat('!')" );
				String message = (String)exp.getValue();

				log.trace( "Message = " + message );
			}

			{
				Expression exp = parser.parseExpression( "'Hello World'.bytes.length" );
				int length = (Integer)exp.getValue();

				log.trace( "Message byte length = " + length );
			}

			{
				Expression exp = parser.parseExpression( "new String('hello world').toUpperCase()" );
				String message = exp.getValue( String.class );

				log.trace( "Message = " + message );
			}

			{
				Calendar c = Calendar.getInstance();

				Expression exp = parser.parseExpression( "time" );
				Date date = (Date)exp.getValue( c );

				log.trace( "Date = " + date );
			}

			loadPropertiesAndContext( "sample/test_expression.properties" );

			{
				AEmployee assistant = (AEmployee)appContext.getBean( "assistant" );

				log.trace( "Assistan.Name = " + assistant.getName() );
				log.trace( "Assistan.Age = " + assistant.getAge() );
				log.trace( "Assistan.Salary = " + assistant.getSalary() );

				AEmployee boss = (AEmployee)appContext.getBean( "boss" );

				log.trace( "Boss.Name = " + boss.getName() );
				log.trace( "Boss.Age = " + boss.getAge() );
				log.trace( "Boss.Salary = " + boss.getSalary() );

				ACompany company = (ACompany)appContext.getBean( "company" );

				log.trace( "Company.Name = " + company.getName() );
				log.trace( "Company.Boss = " + company.getBoss().getName() );
				log.trace( "Company.Assistant = " + company.getAssistant().getName() );
				log.trace( "Company.TotalSalary = " + company.getTotalSalary() );

			}
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
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

			// Got error: org.springframework.beans.factory.BeanIsNotAFactoryException: Bean named 'greetingService' must be of type [org.springframework.beans.factory.FactoryBean], but was actually of type [com.djs.test.spring_sample.greeting.GreetingServiceImpl]
			// log.trace( "GreetingService BeanFactory = " + appContext.getBean( "&greetingService" ) );
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

			// Knight knightB = (Knight)appContext.getBean( "knightB" );
			// knightB.embarkOnQuest();
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

			log.trace( "performerD1 (1) = " + performerD1 );
			log.trace( "performerD1 (2) = " + appContext.getBean( "performerD1" ) );

			Performer performerD2 = (Performer)appContext.getBean( "performerD2" );
			performerD2.perform();

			log.trace( "performerDS (1) = " + appContext.getBean( "performerDS" ) );
			log.trace( "performerDS (2) = " + appContext.getBean( "performerDS" ) );

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

	@Ignore
	@Test
	public void testProperty()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "sample/test_property.properties" );

			{
				PropertyResource propertyResource = (PropertyResource)appContext.getBean( "propertyResource" );

				log.trace( "ResourceText = " + propertyResource.getResourceText().getClass() );
				log.trace( "ResourceText = " + propertyResource.getResourceText() );
				log.trace( "ResourceText = " + propertyResource.getResourceText().getFile() );

				log.trace( "ResourceUrl = " + propertyResource.getResourceUrl().getClass() );
				log.trace( "ResourceUrl = " + propertyResource.getResourceUrl() );
				log.trace( "ResourceUrl = " + propertyResource.getResourceUrl().getURI() );
			}

			{
				BeanWrapper company = new BeanWrapperImpl( new Company() );
				// setting the company name..
				company.setPropertyValue( "name", "AA Company Inc." );
				log.trace( "Company name = " + ((Company)company.getWrappedInstance()).getName() );

				// ... can also be done like this:
				PropertyValue value = new PropertyValue( "name", "BB Company Inc." );
				company.setPropertyValue( value );
				log.trace( "Company name = " + ((Company)company.getWrappedInstance()).getName() );

				// ok, let's create the director and tie it to the company:
				BeanWrapper jim = new BeanWrapperImpl( new Employee() );
				jim.setPropertyValue( "name", "Jim Stravinsky" );
				jim.setPropertyValue( "salary", "1234" );
				log.trace( "Employee name = " + ((Employee)jim.getWrappedInstance()).getName() );

				company.setPropertyValue( "managingDirector", jim.getWrappedInstance() );
				log.trace( "Company Employee name = " + company.getPropertyValue( "managingDirector.name" ) );
				// retrieving the salary of the managingDirector through the company
				Float salary = (Float)company.getPropertyValue( "managingDirector.salary" );
				log.trace( "Company Employee salary = " + salary );
			}
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	@Ignore
	@Test
	public void testTx()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "sample/test_tx.properties" );

			TransactionalServiceA transactionalServiceA = (TransactionalServiceA)appContext.getBean( "transactionalServiceA" );
			transactionalServiceA.doOrder();
			transactionalServiceA.doAccount();

			TransactionalServiceB transactionalServiceB = (TransactionalServiceB)appContext.getBean( "transactionalServiceB" );
			transactionalServiceB.doOrder();
			transactionalServiceB.doAccount();
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	@Ignore
	@Test
	public void testValidator()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "sample/test_validation.properties" );

			Person person = (Person)appContext.getBean( "person" );
			PersonValidator personValidator = (PersonValidator)appContext.getBean( "personValidator" );

			BindException errors = new BindException( person, Person.class.getName() );
			ValidationUtils.invokeValidator( personValidator, person, errors );

			if (log.isInfoEnabled())
			{
				log.info( "Errors = " + errors );
			}
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}
}
