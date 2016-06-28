
package dj.test.javalang.basic;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Random;

public class TestMainDataType
{
	public void testShiftRandom(){
		System.out.println("Test: Shift Rnd");

		long time = System.currentTimeMillis();

		System.out.println("Time = " + time);
		System.out.println("Time (hex)  = " + Long.toHexString(time));

		long shift = ((time & 0xFFFFFFFF00000000L) >> 32) | ((time & 0xFFFFFFFFL) << 32);

		System.out.println("Shift = " + shift);
		System.out.println("Shift (hex) = " + Long.toHexString(shift));

		Random random = new Random();
		int rnd = random.nextInt(0x7FFFFFFF);

		System.out.println("Rnd = " + rnd);
		System.out.println("Rnd (hex)   = " + Integer.toHexString(rnd));

		long newShifted = shift | rnd;

		System.out.println("New = " + newShifted);
		System.out.println("New (hex)   = " + Long.toHexString(newShifted));
	}

	public void testArray(){
		System.out.println("Test: Array");

		long[] data = new long[0];

		System.out.println("Long array lenght = " + data.length);

		String[] values = " ".split(";", -1);

		System.out.println("String array lenght = " + values.length + ", " + Arrays.toString(values));

		int[][] data1 = {{11, 12, 13}, {21, 22}, {31, 32, 33, 34}};
		System.out.println("Array1 = " + Arrays.deepToString(data1));

		int[] data2 = {11, 12, 13};
		// Cannot be "-1".
		// System.out.println("Array2[-1] = " + data2[-1]);

		List<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("Ok");
		list.add("Good");
		// Cannot be "-1".
		// System.out.println("ArrayList[-1] = " + list.get(-1));

		System.out.println("List = " + list);
	}

	public void testString(){
		System.out.println("Test: String");

		int numA = 65535;
		int numB = -65535;

		System.out.println("int A (dec) = " + numA);
		System.out.println("int A (hex) = " + Integer.toHexString(numA).toUpperCase());
		System.out.println("int B (dec) = " + numB);
		System.out.println("int B (hex) = " + Integer.toHexString(numB).toUpperCase());
	}

	private String padString(String str, int size, char padChar, boolean padLeft){
		StringBuffer padded = new StringBuffer(str);

		while (padded.length() < size) {
			if (padLeft) {
				padded.insert(0, padChar);
			} else {
				padded.append(padChar);
			}
		}

		return padded.toString();
	}

	public void testDecimalFormat(){
		System.out.println("Test: Decimal format");

		int numA = 0xABCD;
		DecimalFormat df = new DecimalFormat("00000000");

		System.out.println("numA = " + numA);
		System.out.println("numA padding = " + df.format(numA));
		System.out.println("str1 (dec) padding = " + (new Formatter()).format("%08d", numA));
		System.out.println("str1 (hex) padding = " + (new Formatter()).format("%08X", numA));

		String s1 = Integer.toHexString(numA).toUpperCase();

		System.out.println("str (hex) padding left = " + padString(s1, 8, '0', true));
		System.out.println("str (hex) padding right = " + padString(s1, 8, '0', false));
	}

	public void testStringSplit(){
		System.out.println("Test: String split");

		String[] original = new String[]{"111;222;333", "111;", "111"};

		for (int i = 0; i < original.length; i++) {
			String[] temp = original[i].split(";");

			System.out.println("Split [" + i + "] = " + Arrays.toString(temp));
		}
	}

	public void testMix(){
		System.out.println("Test: Mix");

		DecimalFormat df = new DecimalFormat("0000000000");
		String[] hexList = {"00272E6A", "7FFFFFFF", "FFFFFFFF"};
		int i;

		for (String hex : hexList) {
			long hexToLong = Long.parseLong(hex, 16);
			// String decimal = df.format( Long.toString( hexToLong ) );
			String decimal = df.format(hexToLong);

			System.out.println(hex + " -> " + hexToLong + " -> " + decimal);
		}

		System.out.println("----------------------------------------");

		i = 254;
		String hex = String.format("%08X", i);

		System.out.println(i + " -> " + hex);

		System.out.println("----------------------------------------");

		long startTime = System.currentTimeMillis();
		System.out.println("StartTime = " + startTime);
		startTime = (startTime / 1000) * 1000;
		System.out.println("StartTime = " + startTime);

		System.out.println("----------------------------------------");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		// SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 30);

		System.out.println(sdf.format(cal.getTime()));

		SimpleDateFormat sdf2 = new SimpleDateFormat("####<MMM d, yyyy");

		System.out.println(sdf2.format(new Date()));

		SimpleDateFormat sdf3 = new SimpleDateFormat("dd MMM yyyy");

		System.out.println(sdf3.format(new Date()));

		System.out.println("----------------------------------------");

		final int lowestCount = 1;
		final int highestCount = 5;
		int counter = 1;
		for (int j = 0; j < 10; j++) {
			System.out.println(j + " -> " + String.format("%06d", counter++));

			if (counter > highestCount) {
				counter = lowestCount;
			}
		}
	}

	public static void main(String[] args){
		TestMainDataType test = new TestMainDataType();

		System.out.println("============================================================");
		test.testShiftRandom();

		System.out.println("============================================================");
		test.testArray();

		System.out.println("============================================================");
		test.testString();

		System.out.println("============================================================");
		test.testDecimalFormat();

		System.out.println("============================================================");
		test.testStringSplit();

		System.out.println("============================================================");
		test.testMix();

		System.out.println("============================================================");
	}
}
