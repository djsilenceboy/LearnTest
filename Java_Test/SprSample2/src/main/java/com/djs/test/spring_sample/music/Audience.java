
package com.djs.test.spring_sample.music;

import org.apache.log4j.Logger;

public class Audience
{
	private final Logger log = Logger.getLogger( Audience.class );

	public Audience()
	{
		if (log.isDebugEnabled())
		{
			log.debug( "New " + this + "." );
		}
	}

	public void takeSeats()
	{
		if (log.isInfoEnabled())
		{
			log.info( this + " is taking their seats." );
		}
	}

	public void turnOffCellPhones()
	{
		if (log.isInfoEnabled())
		{
			log.info( this + " is turning off their cellphones" );
		}
	}

	public void applaud()
	{
		if (log.isInfoEnabled())
		{
			log.info( this + " is clapping." );
		}
	}

	public void demandRefund()
	{
		if (log.isInfoEnabled())
		{
			log.info( this + " want their money back!" );
		}
	}

	@Override
	public String toString()
	{
		return "audience";
	}
}
