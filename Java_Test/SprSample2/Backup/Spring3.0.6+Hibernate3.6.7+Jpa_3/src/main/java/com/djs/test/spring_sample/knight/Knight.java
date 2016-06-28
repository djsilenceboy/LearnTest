
package com.djs.test.spring_sample.knight;

public interface Knight
{
	public String getName();

	public Object embarkOnQuest() throws QuestFailedException;
}
