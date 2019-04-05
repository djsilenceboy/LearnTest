
package com.djs.learn.ctc.utility;

import java.util.Random;

/**
 * ID generation helper.
 * <p>
 * Generate various IDs.
 * <p>
 */
public class IdGenerationHelper
{
	/**
	 * Generate 8 bits ID based on time.
	 * <p>
	 * The method uses lower 16 bits of current time (milliseconds) as seed A.<br>
	 * First shift higher 8 bits of seed A with lower 8 bits of seed A, and keep 15 (positive only) or 16 bits (positive and negative).<br>
	 * Then, generate another 8 bits seed B.<br>
	 * Last, XOR seed A and seed B to make an ID.
	 *
	 * @param positive
	 *        boolean. true = 15 bits (positive only); false = 16 bits (positive and negative);
	 * @return short
	 */
	public static byte generate8bIdByTime(boolean positive){
		// Use time as base. Only keep lower 8 bits.
		long seedA = (System.currentTimeMillis() & 0xFFL);
		// Shift higher 4 bits with lower 4 bits, and make it positive by option.
		byte shift = (byte)(((seedA & 0xF0L) >> 4) | ((seedA & (positive ? 0x7L : 0xFL)) << 4));
		// Generate a random positive integer, 4 bits.
		int seedB = (new Random()).nextInt(0x10);
		// XOR lower 4 bits.
		byte id = (byte)(shift ^ seedB);

		return id;
	}

	/**
	 * Generate 16 bits ID based on time.
	 * <p>
	 * The method uses lower 16 bits of current time (milliseconds) as seed A.<br>
	 * First shift higher 8 bits of seed A with lower 8 bits of seed A, and keep 15 (positive only) or 16 bits (positive and negative).<br>
	 * Then, generate another 8 bits seed B.<br>
	 * Last, XOR seed A and seed B to make an ID.
	 *
	 * @param positive
	 *        boolean. true = 15 bits (positive only); false = 16 bits (positive and negative);
	 * @return short
	 */
	public static short generate16bIdByTime(boolean positive){
		// Use time as base. Only keep lower 16 bits.
		long seedA = (System.currentTimeMillis() & 0xFFFFL);
		// Shift higher 8 bits with lower 8 bits, and make it positive by option.
		short shift = (short)(((seedA & 0xFF00L) >> 8) | ((seedA & (positive ? 0x7FL : 0xFFL)) << 8));
		// Generate a random positive integer, 8 bits.
		int seedB = (new Random()).nextInt(0x100);
		// XOR lower 8 bits.
		short id = (short)(shift ^ seedB);

		return id;
	}

	/**
	 * Generate 32 bits ID based on time.
	 * <p>
	 * The method uses lower 32 bits of current time (milliseconds) as seed A.<br>
	 * First shift higher 16 bits of seed A with lower 16 bits of seed A, and keep 31 (positive only) or 32 bits (positive and negative).<br>
	 * Then, generate another 16 bits seed B.<br>
	 * Last, XOR seed A and seed B to make an ID.
	 *
	 * @param positive
	 *        boolean. true = 31 bits (positive only); false = 32 bits (positive and negative);
	 * @return int
	 */
	public static int generate32bIdByTime(boolean positive){
		// Use time as base. Only keep lower 32 bits.
		long seedA = (System.currentTimeMillis() & 0xFFFFFFFFL);
		// Shift higher 16 bits with lower 16 bits, and make it positive by option.
		int shift = (int)(((seedA & 0xFFFF0000L) >> 16) | ((seedA & (positive ? 0x7FFFL : 0xFFFFL)) << 16));
		// Generate a random positive integer, 16 bits.
		int seedB = (new Random()).nextInt(0x10000);
		// XOR lower 16 bits.
		int id = shift ^ seedB;

		return id;
	}

	/**
	 * Generate 64 bits ID based on time.
	 * <p>
	 * The method uses lower 64 bits of current time (milliseconds) as seed A.<br>
	 * First shift higher 32 bits of seed A with lower 32 bits of seed A, and keep 63 (positive only) or 64 bits (positive and negative).<br>
	 * Then, generate another 31 bits (maximum) seed B.<br>
	 * Last, XOR seed A and seed B to make an ID.
	 *
	 * @param positive
	 *        boolean. true = 63 bits (positive only); false = 64 bits (positive and negative);
	 * @return long
	 */
	public static long generate64bIdByTime(boolean positive){
		// Use time as base.
		long seedA = System.currentTimeMillis();
		// Shift higher 32 bits with lower 32 bits, and make it positive by option.
		long shift = ((seedA & 0xFFFFFFFF00000000L) >> 32) | ((seedA & (positive ? 0x7FFFFFFFL : 0xFFFFFFFFL)) << 32);
		// Generate a random positive integer, 31 bits.
		int seedB = (new Random()).nextInt(Integer.MAX_VALUE);
		// XOR lower 31 bits.
		long id = shift ^ seedB;

		return id;
	}
}
