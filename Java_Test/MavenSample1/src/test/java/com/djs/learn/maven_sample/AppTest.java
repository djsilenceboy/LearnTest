
package com.djs.learn.maven_sample;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class AppTest
{
	@Test
	public void aTestApp1()
	{
		assertTrue( true );
	}

	@Ignore("Ignored")
	@Test
	public void aTestApp2()
	{
		assertTrue( false );
	}

	@Test
	public void aTestApp3()
	{
		assertFalse( false );
	}
}
