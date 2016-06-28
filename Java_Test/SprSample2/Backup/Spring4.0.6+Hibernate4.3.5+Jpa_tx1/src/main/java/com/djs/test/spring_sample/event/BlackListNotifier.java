
package com.djs.test.spring_sample.event;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;

public class BlackListNotifier implements ApplicationListener<BlackListEvent>
{
	private final Logger log = Logger.getLogger( BlackListNotifier.class );

	private String notificationAddress;

	public void setNotificationAddress( String notificationAddress )
	{
		this.notificationAddress = notificationAddress;
	}

	@Override
	public void onApplicationEvent( BlackListEvent event )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Send event to " + notificationAddress );
		}
	}
}
