
package com.djs.test.spring_sample.greeting;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.MessageSource;

public class GreetingServiceImpl implements GreetingService, BeanPostProcessor, BeanNameAware
{
	private final Logger log = Logger.getLogger( GreetingServiceImpl.class );

	private String greeting;
	private MessageSource messageSource;

	public GreetingServiceImpl()
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Enter..." );
		}
	}

	public GreetingServiceImpl( String greeting )
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Enter..." );
		}

		this.greeting = greeting;
	}

	public String getGreeting()
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Enter..." );
		}

		return greeting;
	}

	public void setGreeting( String greeting )
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Enter..." );
		}

		this.greeting = greeting;
	}

	public MessageSource getMessageSource()
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Enter..." );
		}

		return messageSource;
	}

	public void setMessageSource( MessageSource messageSource )
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Enter..." );
		}

		this.messageSource = messageSource;
	}

	@Override
	public void sayGreeting()
	{
		if (log.isInfoEnabled())
		{
			log.info( "Greeting = " + greeting );

			{
				String message = messageSource.getMessage( "welcome.message", null, "Welcome", null );

				log.info( "Welcome = " + message );
			}

			{
				String message = messageSource.getMessage( "welcome.message", null, "Welcome", Locale.SIMPLIFIED_CHINESE );

				log.info( "Welcome (CHS) = " + message );
			}

			{
				String message = messageSource.getMessage( "argument.required1", new Object []
				{ "greeting" }, "Required1", null );

				log.info( "Required message 1 = " + message );
			}

			{
				String message = messageSource.getMessage( "argument.required2", new Object []
				{ "greeting" }, "Required2", null );

				log.info( "Required message 2 = " + message );
			}
		}
	}

	@Override
	public Object postProcessBeforeInitialization( Object bean, String beanName ) throws BeansException
	{
		if (log.isInfoEnabled())
		{
			log.info( "Bean name = " + beanName );
			log.info( "Bean = " + bean );
		}

		return bean;
	}

	@Override
	public Object postProcessAfterInitialization( Object bean, String beanName ) throws BeansException
	{
		if (log.isInfoEnabled())
		{
			log.info( "Bean name = " + beanName );
			log.info( "Bean = " + bean );
		}

		return bean;
	}

	@Override
	public void setBeanName( String beanName )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Bean name = " + beanName );
		}
	}
}
