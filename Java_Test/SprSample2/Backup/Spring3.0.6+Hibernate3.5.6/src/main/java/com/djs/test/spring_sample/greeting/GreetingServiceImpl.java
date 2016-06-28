
package com.djs.test.spring_sample.greeting;

import org.apache.log4j.Logger;

public class GreetingServiceImpl implements GreetingService
{
	private final Logger log = Logger.getLogger( GreetingServiceImpl.class );

	private String greeting;

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

	public void sayGreeting()
	{
		if (log.isInfoEnabled())
		{
			log.info( "Greeting words: " + greeting );
		}
	}

	public void setGreeting( String greeting )
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Enter..." );
		}

		this.greeting = greeting;
	}
}
