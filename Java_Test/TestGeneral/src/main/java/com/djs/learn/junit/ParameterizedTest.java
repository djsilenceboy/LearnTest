
package com.djs.learn.junit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedTest
{
	private static Logger log = Logger.getLogger(ParameterizedTest.class);

	private SimpleCalc tCls = null;
	private int a;
	private int b;
	private int expected;

	@Parameters
	public static Collection data(){
		return Arrays.asList(new Object[][]{{3, 4, 7}, {3, 4, 8}, {4, 4, 7}, {4, 4, 8},});
	}

	public ParameterizedTest(int a, int b, int expected){
		this.a = a;
		this.b = b;
		this.expected = expected;
	}

	@Before
	public void setUp(){
		tCls = new SimpleCalc();
	}

	// @Ignore("Ignored")
	@Test
	public void testDoSum1(){
		int r = tCls.doSum(a, b);

		log.debug(a + " + " + b + " = " + r);
		log.debug("assertEquals( " + r + ", " + expected + " )");

		assertEquals(r, expected);
	}
}
