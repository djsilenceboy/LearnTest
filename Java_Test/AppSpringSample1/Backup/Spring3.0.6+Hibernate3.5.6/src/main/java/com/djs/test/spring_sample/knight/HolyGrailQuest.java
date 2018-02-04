
package com.djs.learn.spring_sample.knight;

import org.apache.log4j.Logger;

public class HolyGrailQuest implements Quest
{
	private final Logger log = Logger.getLogger( HolyGrailQuest.class );

	public HolyGrailQuest()
	{
		if (log.isTraceEnabled())
		{
			log.trace( "New holy grail quest." );
		}
	}

	public Object embark() throws QuestFailedException
	{
		if (log.isInfoEnabled())
		{
			log.info( "Give a holy grail." );
		}

		return new HolyGrail();
	}

	@Override
	public String toString()
	{
		return "Holy grail quest";
	}
}
