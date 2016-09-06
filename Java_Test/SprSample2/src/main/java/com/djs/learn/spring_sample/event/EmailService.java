
package com.djs.learn.spring_sample.event;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class EmailService implements ApplicationEventPublisherAware
{
	private final Logger log = Logger.getLogger( EmailService.class );

	private List<String> blackList;
	private ApplicationEventPublisher publisher;

	public void setBlackList( List<String> blackList )
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Enter..." );
		}

		this.blackList = blackList;
	}

	@Override
	public void setApplicationEventPublisher( ApplicationEventPublisher publisher )
	{
		if (log.isTraceEnabled())
		{
			log.trace( "Enter..." );
		}

		this.publisher = publisher;
	}

	public void sendEmail( String address, String text )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Address = " + address );
			log.info( "Test = " + text );
		}

		if (blackList.contains( address ))
		{
			if (log.isInfoEnabled())
			{
				log.info( "Action = Black list" );
			}

			BlackListEvent event = new BlackListEvent( this, address, text );

			publisher.publishEvent( event );
		}
		else
		{
			if (log.isInfoEnabled())
			{
				log.info( "Action = Send email" );
			}
		}
	}
}
