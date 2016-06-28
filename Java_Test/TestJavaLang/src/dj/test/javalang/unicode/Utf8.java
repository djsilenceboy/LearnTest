
package dj.test.javalang.unicode;

import java.util.Arrays;

public class Utf8
{
	public void test(){
		try {
			String szOriginal1 = "你好_abc";
			String szOriginal2 = "\u4F60\u597D_abc";

			System.out.println("Original1 (length) = (" + szOriginal1.length() + ") " + szOriginal1);
			System.out.println("Original2 (length) = (" + szOriginal2.length() + ") " + szOriginal2);

			System.out.println("----------------------------------------");

			char[] chay1_1 = new char[szOriginal1.length()];
			szOriginal1.getChars(0, szOriginal1.length(), chay1_1, 0);

			System.out.println();
			System.out.println("Original1 - > chars (length) = (" + chay1_1.length + ") " + Arrays.toString(chay1_1));
			for (int i = 0; i < chay1_1.length; i++) {
				System.out.println("              [" + i + "] = " + chay1_1[i] + " <0x" + Integer.toHexString(chay1_1[i]).toUpperCase() + ">");
			}

			System.out.println("----------------------------------------");

			byte[] byay1_1 = szOriginal1.getBytes();
			byte[] byay1_2 = szOriginal1.getBytes("UTF-8");
			byte[] byay1_3 = szOriginal1.getBytes("UTF-16");
			String szByteBack1_2_1 = new String(byay1_2, "UTF-8");
			String szByteBack1_2_2 = new String(byay1_2, "UTF-16");
			String szByteBack1_3_1 = new String(byay1_3, "UTF-8");
			String szByteBack1_3_2 = new String(byay1_3, "UTF-16");

			System.out.println();
			System.out.println("Original1 -> bytes (length) = (" + byay1_1.length + ") " + Arrays.toString(byay1_1));
			System.out.println("Original1 -> bytes (UTF-8)  (length) = (" + byay1_2.length + ") " + Arrays.toString(byay1_2));
			System.out.println("Original1 -> bytes (UTF-16) (length) = (" + byay1_3.length + ") " + Arrays.toString(byay1_3));
			System.out.println("Original1 -> bytes (UTF-8)  (length) = (" + byay1_2.length + ") [" + UnicodeTest.bytesToHexString(byay1_2) + "]");
			System.out.println("Original1 -> bytes (UTF-16) (length) = (" + byay1_3.length + ") [" + UnicodeTest.bytesToHexString(byay1_3) + "]");
			System.out.println("Original1 -> bytes (UTF-8)  -> string (UTF-8)  (length) = (" + szByteBack1_2_1.length() + ") " + szByteBack1_2_1);
			System.out.println("Original1 -> bytes (UTF-8)  -> string (UTF-16) (length) = (" + szByteBack1_2_2.length() + ") " + szByteBack1_2_2);
			System.out.println("Original1 -> bytes (UTF-16) -> string (UTF-8)  (length) = (" + szByteBack1_3_1.length() + ") " + szByteBack1_3_1);
			System.out.println("Original1 -> bytes (UTF-16) -> string (UTF-16) (length) = (" + szByteBack1_3_2.length() + ") " + szByteBack1_3_2);

			System.out.println("----------------------------------------");

			System.out.println();
			System.out.println("====== System property ======== ");
			System.getProperties().list(System.out);
		} catch (Exception e) {
			System.err.println("Exception =" + e);
		}

		/*
		 * Original1 (length) = (6) 你好_abc
		 * Original2 (length) = (6) 你好_abc
		 * ----------------------------------------
		 * Original1 - > chars (length) = (6) [�?, �?, _, a, b, c]
		 * [0] = �? <0x4F60>
		 * [1] = �? <0x597D>
		 * [2] = _ <0x5F>
		 * [3] = a <0x61>
		 * [4] = b <0x62>
		 * [5] = c <0x63>
		 * ----------------------------------------
		 * Original1 -> bytes (length) = (14) [-2, -1, 79, 96, 89, 125, 0, 95, 0, 97, 0, 98, 0, 99]
		 * Original1 -> bytes (UTF-8) (length) = (10) [-28, -67, -96, -27, -91, -67, 95, 97, 98, 99]
		 * Original1 -> bytes (UTF-16) (length) = (14) [-2, -1, 79, 96, 89, 125, 0, 95, 0, 97, 0, 98, 0, 99]
		 * Original1 -> bytes (UTF-8) (length) = (10) [0xE4, 0xBD, 0xA0, 0xE5, 0xA5, 0xBD, 0x5F, 0x61, 0x62, 0x63]
		 * Original1 -> bytes (UTF-16) (length) = (14) [0xFE, 0xFF, 0x4F, 0x60, 0x59, 0x7D, 0x00, 0x5F, 0x00, 0x61, 0x00, 0x62, 0x00, 0x63]
		 * Original1 -> bytes (UTF-8) -> string (UTF-8) (length) = (6) 你好_abc
		 * Original1 -> bytes (UTF-8) -> string (UTF-16) (length) = (5) ꃥꖽ彡�?
		 * Original1 -> bytes (UTF-16) -> string (UTF-8) (length) = (14) ��O`Y}_a b c
		 * Original1 -> bytes (UTF-16) -> string (UTF-16) (length) = (6) 你好_abc
		 */
	}
}
