
package com.djs.test.spring_sample.knight;

import com.djs.test.spring_sample.knightI.Talk;

public class Minstrel2
{
	public void singBefore( Talk talk )
	{
		talk.talkMyName();
	}

	public void singAfter( Talk talk )
	{
		talk.talkMyGreat();
	}
}
