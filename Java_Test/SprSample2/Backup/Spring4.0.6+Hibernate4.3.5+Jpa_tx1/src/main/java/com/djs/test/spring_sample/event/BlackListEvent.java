
package com.djs.test.spring_sample.event;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;

public class BlackListEvent extends ApplicationEvent
{
	private final Logger log = Logger.getLogger( BlackListEvent.class );

	private final String address;
	private final String text;

	public BlackListEvent( Object source, String address, String text )
	{
		super( source );

		if (log.isInfoEnabled())
		{
			log.info( "Address = " + address );
			log.info( "Text = " + text );
		}

		this.address = address;
		this.text = text;
	}
}
