
package com.djs.learn.maven_sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class SimpleCalcTest
{
	private final static Logger log = LogManager.getLogger(SimpleCalcTest.class);

	private SimpleCalc testClass = null;

	@Before
	public void setUp(){
		testClass = new SimpleCalc();
	}

	@Test
	public void testDoSum1(){
		int r = testClass.doSum(3, 4);

		log.debug("3 + 4 = " + r);
		log.debug("assertEquals( " + r + ", 7 )");

		assertEquals(r, 7);
	}

	@Test
	public void testDoSum2(){
		int r = testClass.doSum(3, 4);

		log.debug("3 + 4 = " + r);
		log.debug("assertEquals( " + r + ", 8 )");

		assertNotEquals(r, 8);
	}
}
