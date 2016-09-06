
package com.djs.learn.spring_sample.knight;

import org.apache.log4j.Logger;

public class GoldenWoolQuest implements Quest
{
	private final Logger log = Logger.getLogger( GoldenWoolQuest.class );

	public GoldenWoolQuest()
	{
		if (log.isTraceEnabled())
		{
			log.trace( "New golden wool quest." );
		}
	}

	public Object embark() throws QuestFailedException
	{
		if (log.isInfoEnabled())
		{
			log.info( "Give golden wool." );
		}

		return new GoldenWool();
	}

	@Override
	public String toString()
	{
		return "Golden wool quest";
	}
}
