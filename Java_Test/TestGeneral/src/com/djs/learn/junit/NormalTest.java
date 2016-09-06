
package com.djs.learn.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class NormalTest
{
	private static Logger log = Logger.getLogger(NormalTest.class);

	private SimpleCalc tCls = null;

	@Before
	public void setUp(){
		tCls = new SimpleCalc();
	}

	// @Ignore("Ignored")
	@Test
	public void testDoSum1(){
		int r = tCls.doSum(3, 4);

		log.debug("3 + 4 = " + r);
		log.debug("assertEquals( " + r + ", 7 )");

		assertEquals(r, 7);
	}

	// @Ignore("Ignored")
	@Test
	public void testDoSum2(){
		int r = tCls.doSum(3, 4);

		log.debug("3 + 4 = " + r);
		log.debug("assertEquals( " + r + ", 8 )");

		assertEquals(r, 8);
	}

	// @Ignore("Ignored")
	@Test
	public void testDoSum3(){
		int r = tCls.doSum(3, 4);

		log.debug("3 + 4 = " + r);
		log.debug("assertFalse( " + r + " == 7 )");

		assertFalse(r == 7);
	}

	// @Ignore("Ignored")
	@Test
	public void testDoSum4(){
		int r = tCls.doSum(3, 4);

		log.debug("3 + 4 = " + r);
		log.debug("assert " + r + " == 7");

		assert r == 7 : "Yes, " + r + " == 7";
	}

	// @Ignore("Ignored")
	@Test
	public void testDoSum5(){
		int r = tCls.doSum(3, 4);

		log.debug("3 + 4 = " + r);
		log.debug("assertThat( " + r + ", is( 7 )");

		// assertThat(r, is(7));
	}

	// @Ignore("Ignored")
	@Test
	public void testDoMultiply1(){
		int r = tCls.doMultiply(3, 4);

		log.debug("3 x 4 = " + r);
		log.debug("assertEquals( " + r + ", 12 )");

		assertEquals(r, 12);
	}

	// @Ignore("Ignored")
	@Test
	public void testDoMultiply2(){
		int r = tCls.doMultiply(3, 4);

		log.debug("3 x 4 = " + r);
		log.debug("assertTrue( " + r + " == 12 )");

		assertTrue(r == 12);
	}
}
