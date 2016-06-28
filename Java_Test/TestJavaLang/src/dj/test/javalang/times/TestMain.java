
package dj.test.javalang.times;

public class TestMain
{
	public long lLoops = 100000;

	public boolean getTrue()
	{
		return true;
	}

	public boolean getFalse()
	{
		return false;
	}

	public void doNothing( String szLine )
	{
	}

	public long testNormal()
	{
		String szData1 = "ABCDEFG";
		String szData2 = "UVWXYZ";
		int iData = 111;
		long l;

		long lTime1, lTime2, lTimeDiff;

		lTime1 = System.currentTimeMillis();

		for (l = 0; l < lLoops; l++)
		{
			doNothing( szData1 + "," + iData + "," + szData2 );
		}

		lTime2 = System.currentTimeMillis();

		lTimeDiff = lTime2 - lTime1;

		return lTimeDiff;
	}

	public long testGetTrue()
	{
		String szData1 = "ABCDEFG";
		String szData2 = "UVWXYZ";
		int iData = 111;
		long l;

		long lTime1, lTime2, lTimeDiff;

		lTime1 = System.currentTimeMillis();

		for (l = 0; l < lLoops; l++)
		{
			if (getTrue())
			{
				doNothing( szData1 + "," + iData + "," + szData2 );
			}
		}

		lTime2 = System.currentTimeMillis();

		lTimeDiff = lTime2 - lTime1;

		return lTimeDiff;
	}

	public long testGetFalse()
	{
		String szData1 = "ABCDEFG";
		String szData2 = "UVWXYZ";
		int iData = 111;
		long l;

		long lTime1, lTime2, lTimeDiff;

		lTime1 = System.currentTimeMillis();

		for (l = 0; l < lLoops; l++)
		{
			if (getFalse())
			{
				doNothing( szData1 + "," + iData + "," + szData2 );
			}
		}

		lTime2 = System.currentTimeMillis();

		lTimeDiff = lTime2 - lTime1;

		return lTimeDiff;
	}

	/**
	 * @param args
	 */
	public static void main( String [] args )
	{
		TestMain as = new TestMain();

		long lTimeNormal, lTimeTrue, lTimeFalse, lTimeDiff;

		as.lLoops = 10000 * 500;

		System.out.println( "Loops = " + as.lLoops );

		lTimeNormal = as.testNormal();
		lTimeTrue = as.testGetTrue();
		lTimeFalse = as.testGetFalse();

		System.out.println( "Normal = " + lTimeNormal );
		System.out.println( "True   = " + lTimeTrue );
		System.out.println( "False  = " + lTimeFalse );

		lTimeDiff = lTimeNormal - lTimeFalse;
		System.out.println( "TimeNormal - TimeFalse  = " + lTimeDiff );

		lTimeDiff = lTimeTrue - lTimeFalse;
		System.out.println( "TimeTrue   - TimeFalse  = " + lTimeDiff );

		lTimeDiff = lTimeTrue - lTimeNormal;
		System.out.println( "TimeTrue   - TimeNormal = " + lTimeDiff );
	}
}
