
package com.djs.learn.junit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import javax.xml.crypto.NoSuchMechanismException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NormalTest
{
	private static Logger log = Logger.getLogger(NormalTest.class);

	private SimpleCalc calc = null;

	@Before
	public void setUp(){
		calc = new SimpleCalc();
	}

	// @Ignore("Ignored")
	@Test
	public void testDoSum1(){
		int r = calc.doSum(3, 4);

		log.debug("3 + 4 = " + r);
		log.debug("assertEquals( " + r + ", 7 )");

		assertEquals(r, 7);
	}

	// @Ignore("Ignored")
	@Test
	public void testDoSum2(){
		int r = calc.doSum(3, 4);

		log.debug("3 + 4 = " + r);
		log.debug("assertEquals( " + r + ", 8 )");

		assertEquals(r, 8);
	}

	// @Ignore("Ignored")
	@Test
	public void testDoSum3(){
		int r = calc.doSum(3, 4);

		log.debug("3 + 4 = " + r);
		log.debug("assertFalse( " + r + " == 7 )");

		assertFalse(r == 7);
	}

	// @Ignore("Ignored")
	@Test
	public void testDoSum4(){
		int r = calc.doSum(3, 4);

		log.debug("3 + 4 = " + r);
		log.debug("assert " + r + " == 7");

		assert r == 7 : "Yes, " + r + " == 7";
	}

	// @Ignore("Ignored")
	@Test
	public void testDoSum5(){
		int r = calc.doSum(3, 4);

		log.debug("3 + 4 = " + r);
		log.debug("assertThat( " + r + ", is( 7 )");

		assertNotEquals(r, 8);
	}

	// @Ignore("Ignored")
	@Test
	public void testDoSum6(){
		int r = calc.doSum(3, 4);

		log.debug("3 + 4 = " + r);
		log.debug("assertThat( " + r + ", is( 7 )");

		assertThat(r, is(7));
	}

	// @Ignore("Ignored")
	@Test
	public void testDoSum7(){
		int r = calc.doSum(3, 4);

		log.debug("3 + 4 = " + r);
		log.debug("assertThat( " + r + ", equalTo( 7 )");

		assertThat(r, equalTo(7));
	}

	// @Ignore("Ignored")
	@Test
	public void testDoMultiply1(){
		int r = calc.doMultiply(3, 4);

		log.debug("3 x 4 = " + r);
		log.debug("assertEquals( " + r + ", 12 )");

		assertEquals(r, 12);
	}

	// @Ignore("Ignored")
	@Test
	public void testDoMultiply2(){
		int r = calc.doMultiply(3, 4);

		log.debug("3 x 4 = " + r);
		log.debug("assertTrue( " + r + " == 12 )");

		assertTrue(r == 12);
	}

	// @Ignore("Ignored")
	@Test
	public void test1(){
		int[] a = {1, 2, 3};
		int[] b = {1, 2, 3};

		log.debug("assertArrayEquals( " + Arrays.toString(a) + ", " + Arrays.toString(b) + " )");

		assertArrayEquals(a, b);
	}

	// @Ignore("Ignored")
	@Test
	public void test2(){
		Integer a = new Integer(1);

		log.debug("assertNull( " + a + " )");

		assertNull(a);
	}

	// @Ignore("Ignored")
	@Test
	public void test3(){
		Integer a = null;

		log.debug("assertNull( " + a + " )");

		assertNull(a);
	}

	// @Ignore("Ignored")
	@Test
	public void test4(){
		Integer a = new Integer(1);
		Integer b = new Integer(1);

		log.debug("assertSame( " + a + ", " + b + " )");

		assertSame(a, b);
	}

	// @Ignore("Ignored")
	@Test
	public void test5(){
		Integer a = new Integer(1);
		Integer b = a;

		log.debug("assertSame( " + a + ", " + b + " )");

		assertSame(a, b);
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// @Ignore("Ignored")
	@Test
	public void testException1(){
		thrown.expect(NullPointerException.class);

		log.debug("thrown.expect(NullPointerException.class)");

		throw new NullPointerException();
	}

	// @Ignore("Ignored")
	@Test
	public void testException2(){
		thrown.expect(NullPointerException.class);

		log.debug("thrown.expect(NullPointerException.class)");

		throw new NoSuchMechanismException();
	}
}
