
package com.djs.learn.spring_sample.knight;

import org.apache.log4j.Logger;

public class KnightOfTheRoundTable implements Knight
{
	private final Logger log = Logger.getLogger( KnightOfTheRoundTable.class );

	private final String name;
	private Quest quest;

	public KnightOfTheRoundTable( String name )
	{
		if (log.isTraceEnabled())
		{
			log.trace( "New Knight of the round table = " + name );
		}

		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setQuest( Quest quest )
	{
		if (log.isInfoEnabled())
		{
			log.info( "Assign a quest = " + quest );
		}

		this.quest = quest;
	}

	public Object embarkOnQuest() throws QuestFailedException
	{
		if (log.isInfoEnabled())
		{
			log.info( "Embark on quest." );
		}

		Object target = quest.embark();

		if (log.isInfoEnabled())
		{
			log.info( "Got " + target + "." );
		}

		return target;
	}

	@Override
	public String toString()
	{
		return "Knight of the round table " + name;
	}
}
