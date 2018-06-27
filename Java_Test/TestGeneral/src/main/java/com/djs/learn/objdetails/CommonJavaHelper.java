
package com.djs.learn.objdetails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.IllegalFormatConversionException;

/**
 * Common Java helper.
 * <p>
 * Update log: (date / author : comments)
 * <ul>
 * <li>2011-12-14 / Du Jiang : Creation
 * <li>2012-02-21 / Du Jiang : Add getThrowableStackTrace(), convertSerializableToBytes(), convertSerializableToBytes()
 * <li>2012-04-20 / Du Jiang : Add convertNumberToHexString()
 * </ul>
 */
public class CommonJavaHelper
{
	/**
	 * Get all fields in class include super classes.
	 *
	 * @param fields
	 *        ArrayList, can be null, then will create it internally if necessary.
	 * @param theClass
	 *        Class<?>
	 * @return ArrayList - Can be null, if input fields is null and no field available.
	 */
	public static ArrayList<Field> getAllFieldsInClass(ArrayList<Field> fields, Class<?> theClass){
		// Get local fields first.
		Field[] tempFields = theClass.getDeclaredFields();
		if ((tempFields != null) && (tempFields.length > 0)) {
			if (fields == null) {
				fields = new ArrayList<Field>();
			}

			for (Field item : tempFields) {
				fields.add(item);
			}
		}

		// Check super class.
		Class<?> superClass = theClass.getSuperclass();
		if ((superClass != null) && !superClass.isPrimitive() && !superClass.getName().equals("java.lang.Object")) {
			// Recursive checking.
			fields = getAllFieldsInClass(fields, superClass);
		}

		return fields;
	}

	/**
	 * Get class name.
	 *
	 * @param obj
	 * @param havePackageName
	 *        boolean, true = class name with package name.
	 * @return String
	 */
	public static String getClassName(Object obj, boolean havePackageName){
		Class<?> theClass = obj.getClass();
		String className = null;

		if (theClass.isArray()) {
			String tempName = theClass.getName();

			// If it is Class array. In format like "[..[Lpackage.name;".
			if (tempName.contains("[L") && havePackageName) {
				// Extract "package.name".
				className = tempName.substring(tempName.indexOf("[L") + 2, tempName.length() - 1);
			} else {
				// The simple name is in format "name[]".
				className = theClass.getSimpleName();
				// Remove "[]".
				className = className.substring(0, className.length() - 2);
			}
		} else {
			className = havePackageName ? theClass.getName() : theClass.getSimpleName();
		}

		return className;
	}

	/**
	 * Get StackTrace from Throwable.
	 *
	 * @param t
	 *        Throwable, not null.
	 * @return String - Muliple lines stack trace.
	 */
	public static String getThrowableStackTrace(Throwable t){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		t.printStackTrace(pw);

		pw.flush();
		sw.flush();

		return sw.toString();
	}

	/**
	 * Convert Serializable object to byte array.
	 *
	 * @param obj
	 *        Object implemented Serializable interface.
	 * @return byte [] - return null if obj is null.
	 * @throws IOException
	 */
	public static byte[] convertSerializableToBytes(Serializable obj) throws IOException{
		byte[] data = null;

		if (obj != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// There would be IOException.
			ObjectOutputStream oos = new ObjectOutputStream(baos);

			// There would be IOException.
			oos.writeObject(obj);
			// There would be IOException.
			oos.close();

			data = baos.toByteArray();
		}

		return data;
	}

	/**
	 * Convert byte array back to Serializable object.
	 * <p>
	 * byte array must have been converted from a Serializable object.
	 *
	 * @param data
	 *        byte [], it must have been converted from a Serializable object, otherwise there would be ClassNotFoundException.
	 * @return Object - return null if data is null.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Serializable convertBytesToSerializable(byte[] data) throws IOException, ClassNotFoundException{
		Serializable obj = null;

		if (data != null) {
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			// There would be IOException.
			ObjectInputStream ois = new ObjectInputStream(bais);

			// There would be IOException, ClassNotFoundException.
			obj = (Serializable)ois.readObject();
		}

		return obj;
	}

	/**
	 * Convert number to Hex string.
	 *
	 * @param maxLength
	 *        int. "<= 0" means keep original length.
	 * @param leftPaddingZero
	 *        boolean. true = left padding zeros; false = no padding.
	 * @param toUpperCase
	 *        boolean. true = to upper case; false = to lower case.
	 * @param number
	 *        Object. Should be byte, short, int, long, Byte, Short, Integer, Long and BigInteger.
	 * @return String - Hex string.
	 * @throws IllegalFormatConversionException
	 */
	public static String convertNumberToHexString(int maxLength, boolean leftPaddingZero, boolean toUpperCase, Object number){
		StringBuilder format = new StringBuilder();

		format.append("%");
		if (maxLength > 0) {
			format.append(leftPaddingZero ? "0" : "");
			format.append(maxLength);
		}
		format.append(toUpperCase ? "X" : "x");

		return String.format(format.toString(), number);
	}

	/**
	 * Check pattern (type 1).
	 * <p>
	 * Check if any sample matches any pattern.
	 *
	 * @param samples
	 *        String array of samples.<br>
	 *        It must not be null and at least have 1 element.
	 * @param patterns
	 *        String array of patterns.<br>
	 *        It must not be null and at least have 1 element.
	 * @param exactMatch
	 *        boolean. "true" means "exactly matching"; "false" means "start with".
	 * @param caseSensitive
	 *        boolean. "true" means case sensitive; "false" means case insensitive.
	 * @return int [] - int[2] means matched. [0] index of sample, [1] index of pattern.<br>
	 *         "null" means not matched.
	 */
	public static int[] checkPattern_Type1(String[] samples, String[] patterns, boolean exactMatch, boolean caseSensitive){
		int[] results = null; // Default is null.
		boolean found = false;
		int i, j;

		for (i = 0; i < samples.length; i++) {
			for (j = 0; j < patterns.length; j++) {
				if (exactMatch) {
					if (caseSensitive) {
						if (samples[i].equals(patterns[j])) {
							found = true;

							// Break j loop.
							break;
						}
					} else {
						if (samples[i].equalsIgnoreCase(patterns[j])) {
							found = true;

							// Break j loop.
							break;
						}
					}
				} else {
					if (caseSensitive) {
						if (samples[i].startsWith(patterns[j])) {
							found = true;

							// Break j loop.
							break;
						}
					} else {
						if (samples[i].toLowerCase().startsWith(patterns[j].toLowerCase())) {
							found = true;

							// Break j loop.
							break;
						}
					}
				}
			}

			// If found.
			if (found) {
				results = new int[]{i, j};

				// Break i loop.
				break;
			}
		}

		return results;
	}
}
