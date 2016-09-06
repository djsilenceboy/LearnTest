
package com.djs.learn.objdetails;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Common ToString helper.
 * <p>
 * The class uses Java reflect function. Sometimes, user should synchronize target object while using method of this class.
 * <p>
 * $Revision: 2832 $<br>
 * $Date: 2013-09-19 11:48:24 +0800 (Thu, 19 Sep 2013) $<br>
 * $Author: dujiang $
 * <p>
 * Update log: (date / author : comments)
 * <ul>
 * <li>2011-11-23 / Du Jiang : Creation
 * <li>2012-07-10 / Du Jiang : Limit generated string length
 * </ul>
 */
public class CommonToStringHelper
{
	/**
	 * Maximum total string length.
	 * <p>
	 * For string longer than maximum, it will be truncated and appended with postfix "...#om_xxx#}".<br>
	 * Valid value [10, 2000].<br>
	 * Default value is 1000.
	 */
	protected static int MAXIMUM_TOTAL_STRING_LENGTH = 1000;
	/**
	 * Maximum array item string length.
	 * <p>
	 * For each array item string, if longer than maximum, it will be truncated and appended with postfix "...#om_xxx#}".<br>
	 * Valid value [10, 1000], and should be less than MAXIMUM_TOTAL_STRING_LENGTH.<br>
	 * Default value is 400.
	 */
	protected static int MAXIMUM_ARRAY_ITEM_STRING_LENGTH = 400;

	/**
	 * Object to String: field separator.
	 * <p>
	 * Default value is ", ".
	 */
	protected static final String OBJECT_TO_STRING_FIELD_SEPARATOR = ", ";
	/**
	 * Object to String: null holder.
	 * <p>
	 * When object is null, how to represent it.<br>
	 * Default value is "#nul#".
	 */
	protected static final String OBJECT_TO_STRING_NULL_HOLDER = "#nul#";
	/**
	 * Object to String: internal class reference.
	 * <p>
	 * When object is repeated, has been output, how to represent it.<br>
	 * Default value is "#ref#".
	 */
	protected static final String OBJECT_TO_STRING_INTERNAL_CLASS_REFERENCE = "#ref#";
	/**
	 * Object to String: date display format.
	 * <p>
	 * Internally used by "java.text.SimpleDateFormat".<br>
	 * Default value is "yyyy-MM-dd HH:mm:ss.SSS z".
	 */
	protected static final String OBJECT_TO_STRING_DATE_DISPLAY_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS z";
	/**
	 * Object to String: number display format.
	 * <p>
	 * Internally used by "java.text.MessageFormat".<br>
	 * Default value is "#.#####".
	 */
	protected static final String OBJECT_TO_STRING_NUMBER_DISPLAY_FORMAT = "#.#####";
	/**
	 * Object to String: exclude holder.
	 * <p>
	 * When object is excluded, how to represent it.<br>
	 * Default value is "#exc#".
	 */
	protected static final String OBJECT_TO_STRING_EXCLUDE_HOLDER = "#exc#";
	/**
	 * Object to String: not-include holder.
	 * <p>
	 * When object is not included, how to represent it.<br>
	 * Default value is "#nin#".
	 */
	protected static final String OBJECT_TO_STRING_NOT_INCLUDE_HOLDER = "#nin#";
	/**
	 * Ignored fields for throwable.
	 * <p>
	 * A set of field name to be ignored while toString for Throwable/Exception object.<br>
	 * Default set is {"backtrace", "noBackTrace", "serialVersionUID", "stackTrace", "stackTraceHolder"}.
	 */
	protected static SortedSet<String> IGNORED_FIELDS_FOR_THROWABLE = null;

	static {
		IGNORED_FIELDS_FOR_THROWABLE = Collections.synchronizedSortedSet(new TreeSet<String>());
		IGNORED_FIELDS_FOR_THROWABLE.add("backtrace".toLowerCase());
		IGNORED_FIELDS_FOR_THROWABLE.add("noBackTrace".toLowerCase());
		IGNORED_FIELDS_FOR_THROWABLE.add("serialVersionUID".toLowerCase());
		IGNORED_FIELDS_FOR_THROWABLE.add("stackTrace".toLowerCase());
		IGNORED_FIELDS_FOR_THROWABLE.add("stackTraceHolder".toLowerCase());
	}

	/**
	 * Get maximum total string length.
	 *
	 * @return int
	 */
	public static int getMaximumTotalStringLength(){
		return MAXIMUM_TOTAL_STRING_LENGTH;
	}

	/**
	 * Set maximum total string length.
	 *
	 * @param maximumTotalStringLength
	 *        int. valid value [10, 2000].
	 */
	public static void setMaximumTotalStringLength(int maximumTotalStringLength){
		if (maximumTotalStringLength < 10) {
			maximumTotalStringLength = 10;
		} else if (maximumTotalStringLength > 2000) {
			maximumTotalStringLength = 2000;
		}

		MAXIMUM_TOTAL_STRING_LENGTH = maximumTotalStringLength;
	}

	/**
	 * Get maximum array item string length.
	 *
	 * @return int
	 */
	public static int getMaximumArrayItemStringLength(){
		return MAXIMUM_ARRAY_ITEM_STRING_LENGTH;
	}

	/**
	 * Set maximum array item string length.
	 *
	 * @param maximumArrayItemStringLength
	 *        int. valid value [10, 1000].
	 */
	public static void setMaximumArrayItemStringLength(int maximumArrayItemStringLength){
		if (maximumArrayItemStringLength < 10) {
			maximumArrayItemStringLength = 10;
		} else if (maximumArrayItemStringLength > 1000) {
			maximumArrayItemStringLength = 1000;
		}

		MAXIMUM_ARRAY_ITEM_STRING_LENGTH = maximumArrayItemStringLength;
	}

	/**
	 * Get ignored fields Set for Throwable.
	 *
	 * @return Set<String> - Synchronized TreeSet.
	 */
	public static Set<String> getIgnoredFieldsSetForThrowable(){
		return IGNORED_FIELDS_FOR_THROWABLE;
	}

	/**
	 * Get ignored fields array for Throwable.
	 *
	 * @return String[]
	 */
	public static String[] getIgnoredFieldsArrayForThrowable(){
		return getIgnoredFieldsSetForThrowable().toArray(new String[0]);
	}

	/**
	 * Get level array.
	 *
	 * @param maxLevel
	 *        int, should be >= 0. Set to < 0 means unlimited level.
	 * @return int [] - null if maxLevel < 0.
	 */
	protected static int[] getLevels(int maxLevel){
		int[] levels = null;

		if (maxLevel >= 0) {
			levels = new int[]{maxLevel, 0};
		}

		return levels;
	}

	/**
	 * Convert Object toString finally.
	 *
	 * @param obj
	 * @param placeHoders
	 *        String []: [0] fieldSeparator [1] nullHolder, [2] internalClassReference, [3] dateDisplayFormat, [4] numberDisplayFormat, [5] excludeHolder, [6]
	 *        notIncludeHolder.<br>
	 *        Each string can be null; String [] can be null.<br>
	 *        Default value: [0] fieldSeparator = "," [1] nullHolder = "#nul#", [2] internalClassReference = "#ref#", [3] dateDisplayFormat =
	 *        "yyyy-MM-dd HH:mm:ss.SSS z", [4] numberDisplayFormat = "#.#####", [5] excludeHolder = "#exc#", [6] notIncludeHolder = "#nin#".<br>
	 *        internalClassReference flag is not used in this method.
	 * @return String - Not null and not empty.
	 */
	protected static String objectToFinalString(Object obj, String[] placeHoders){
		StringBuilder temp = new StringBuilder();

		if (obj == null) {
			temp.append("<");
			temp.append((placeHoders == null) ? OBJECT_TO_STRING_NULL_HOLDER : (placeHoders[1] == null) ? OBJECT_TO_STRING_NULL_HOLDER : placeHoders[1]);
			temp.append(">");
		}
		// If Date or Calendar.
		else if ((obj instanceof Date) || (obj instanceof Calendar)) {
			String dateFormat =
			                    (placeHoders == null) ? OBJECT_TO_STRING_DATE_DISPLAY_FORMAT : (placeHoders[3] == null) ? OBJECT_TO_STRING_DATE_DISPLAY_FORMAT
			                                                                                                           : placeHoders[3];

			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

			if (obj instanceof Date) {
				Date tempObj = (Date)obj;

				temp.append(sdf.format(tempObj));
			} else {
				Calendar tempObj = (Calendar)obj;

				temp.append(sdf.format(tempObj.getTime()));
			}
		}
		// If Number.
		else if (obj instanceof Number) {
			String numberFormat =
			                      (placeHoders == null) ? OBJECT_TO_STRING_NUMBER_DISPLAY_FORMAT
			                                           : (placeHoders[4] == null) ? OBJECT_TO_STRING_NUMBER_DISPLAY_FORMAT : placeHoders[4];

			temp.append(MessageFormat.format("{0,number," + numberFormat + "}", obj));
		} else {
			temp.append(obj);
		}

		return temp.toString();
	}

	/**
	 * Convert Array Object deep toString.
	 *
	 * @param obj
	 *        Must be an array Object.
	 * @return String - Can be null or empty.
	 * @throws Exception
	 * @see CommonToStringHelper#objectDeepToString
	 */
	protected static String objectArrayDeepToString(Object obj, int[] levels, boolean[] flags, String[] placeHoders, Set<String> excludedFields,
	                                                Set<String> includedFields, int maximumTotalStringLength, int maximumArrayItemStringLength,
	                                                Set<Object> objHistory) throws Exception{
		StringBuilder temp = new StringBuilder();
		Class<?> objClass = obj.getClass();

		// Primitive array.
		if (objClass == boolean[].class) {
			temp.append(Arrays.toString((boolean[])obj));
		} else if (objClass == byte[].class) {
			temp.append(Arrays.toString((byte[])obj));
		} else if (objClass == char[].class) {
			temp.append(Arrays.toString((char[])obj));
		} else if (objClass == double[].class) {
			temp.append(Arrays.toString((double[])obj));
		} else if (objClass == float[].class) {
			temp.append(Arrays.toString((float[])obj));
		} else if (objClass == int[].class) {
			temp.append(Arrays.toString((int[])obj));
		} else if (objClass == long[].class) {
			temp.append(Arrays.toString((long[])obj));
		} else if (objClass == short[].class) {
			temp.append(Arrays.toString((short[])obj));
		}
		// Class array.
		else {
			Object[] objs = (Object[])obj;

			temp.append("[");

			if (objs.length > 0) {
				for (Object item : objs) {
					int[] localLevels = null;
					if (levels != null) {
						localLevels = levels.clone();
					}

					temp.append(objectDeepToString(item, localLevels, flags, placeHoders, excludedFields, includedFields, maximumTotalStringLength,
					                               maximumArrayItemStringLength, objHistory));
					temp.append((placeHoders == null) ? OBJECT_TO_STRING_FIELD_SEPARATOR : (placeHoders[0] == null) ? OBJECT_TO_STRING_FIELD_SEPARATOR
					                                                                                               : placeHoders[0]);
				}

				if (temp.charAt(temp.length() - 1) == ' ') {
					temp.setLength(temp.length() - 2);
				}

				if (temp.length() > maximumArrayItemStringLength) {
					int omitted = temp.length() - maximumArrayItemStringLength;

					temp.setLength(maximumArrayItemStringLength);
					temp.append("...#om_");
					temp.append(omitted);
					temp.append("#");
				}
			}

			temp.append("]");
		}

		return temp.toString();
	}

	/**
	 * Convert Object deep toString.
	 *
	 * @param obj
	 * @param levels
	 *        int []: [0] max level, [1] current level.<br>
	 *        int [] can be null, means unlimited level.<br>
	 *        Max level should be >= 0. Set to < 0 means unlimited level.<br>
	 *        Current level is internal managed, pass in 0 for the first time.
	 * @param flags
	 *        boolean []: [0] haveClassName, [1] havePackageName, [2] haveArrayName, [3] haveFieldName, [4] havePrimitiveName, [5] allowFinalField, [6]
	 *        allowStaticField, [7] ignoreThrowableField, [8] showExcludeField, [9] showNotIncludeField.
	 * @param placeHoders
	 *        String []: [0] fieldSeparator [1] nullHolder, [2] internalClassReference, [3] dateDisplayFormat, [4] numberDisplayFormat, [5] excludeHolder, [6]
	 *        notIncludeHolder.<br>
	 *        Each string can be null; String [] can be null.<br>
	 *        Default value: [0] fieldSeparator = "," [1] nullHolder = "#nul#", [2] internalClassReference = "#ref#", [3] dateDisplayFormat =
	 *        "yyyy-MM-dd HH:mm:ss.SSS z", [4] numberDisplayFormat = "#.#####", [5] excludeHolder = "#exc#", [6] notIncludeHolder = "#nin#".
	 * @param excludedFields
	 *        String Set, all items must be in lower-case.<br>
	 *        Specify which fields should be excluded.<br>
	 *        Can be null or empty, means no fields excluded, then further check includedFields.
	 * @param includedFields
	 *        String Set, all items must be in lower-case.<br>
	 *        Specify which fields should be included, if not be excluded by excludedFields.<br>
	 *        Can be null or empty, means all fields included, if not be excluded by excludedFields.
	 * @param maximumTotalStringLength
	 *        int. For string longer than maximum, it will be truncated and appended with postfix "...#om_xxx#}".<br>
	 *        Suggested value [10, 2000].
	 * @param maximumArrayItemStringLength
	 *        int. For each array item string, if longer than maximum, it will be truncated and appended with postfix "...#om_xxx#}".<br>
	 *        Suggested value [10, 1000], and should be less than maximumTotalStringLength.
	 * @param objHistory
	 *        Object Set, internal managed, can be null or empty. First level caller should pass in null.
	 * @return String - Can be null or empty.
	 * @throws Exception
	 * @see CommonToStringHelper#objectArrayDeepToString
	 */
	public static String objectDeepToString(Object obj, int[] levels, boolean[] flags, String[] placeHoders, Set<String> excludedFields,
	                                        Set<String> includedFields, int maximumTotalStringLength, int maximumArrayItemStringLength, Set<Object> objHistory)
	                                                                                                                                                           throws Exception{
		final int MAX_LEVEL_MARK = -1;

		// If no enough levels.
		if ((levels != null) && (levels.length < 2)) {
			throw new Exception("No enough levels.");
		}

		// If no enough flags.
		if ((flags == null) || (flags.length < 10)) {
			throw new Exception("No enough flags.");
		}

		// If no enough place holder.
		if ((placeHoders != null) && (placeHoders.length < 7)) {
			throw new Exception("No enough place holder.");
		}

		StringBuilder temp = new StringBuilder();

		// If reach max level.
		if ((levels != null) && (levels[0] > MAX_LEVEL_MARK) && (levels[1] > levels[0])) {
			temp.append(objectToFinalString(obj, placeHoders));
		}
		// If not reach max level, or unlimited level.
		else {
			// If obj is null.
			if (obj == null) {
				temp.append(objectToFinalString(obj, placeHoders));
			} else {
				Class<?> theClass = obj.getClass();

				// Special ignore fields for Throwable.
				if ((obj instanceof Throwable) && flags[7]) {
					if (excludedFields == null) {
						excludedFields = new TreeSet<String>();
					}

					excludedFields.addAll(getIgnoredFieldsSetForThrowable());
				}

				// If this obj has already been checked in history.
				if ((objHistory != null) && objHistory.contains(obj)) {
					temp.append("<");
					temp.append((placeHoders == null) ? OBJECT_TO_STRING_INTERNAL_CLASS_REFERENCE
					                                 : (placeHoders[2] == null) ? OBJECT_TO_STRING_INTERNAL_CLASS_REFERENCE : placeHoders[2]);
					temp.append(">");
				} else {
					if (objHistory == null) {
						objHistory = new HashSet<Object>();
					}

					// If Array or Collection (List/Set, etc).
					if (theClass.isArray() || (obj instanceof Collection)) {
						// Add into history.
						objHistory.add(obj);

						// If add Array name.
						if (flags[0] && flags[2]) {
							temp.append(CommonJavaHelper.getClassName(obj, flags[1]));
						}

						Object tempObj = theClass.isArray() ? obj : ((Collection<?>)obj).toArray();

						int[] localLevels = null;
						if (levels != null) {
							localLevels = levels.clone();

							if (localLevels[0] != MAX_LEVEL_MARK) {
								localLevels[1]++;
							}
						}

						temp.append(objectArrayDeepToString(tempObj, localLevels, flags, placeHoders, excludedFields, includedFields, maximumTotalStringLength,
						                                    maximumArrayItemStringLength, objHistory));
					}
					// Put this "else-if" just before "else".
					// Note that, Array/Collection can also be "final" and/or "static".
					// Check "$" in static class name to exclude "static class" type, because its name looks like "outClassName$innerClassName".
					// Do not use flag[5] and flag[6] here.
					else if ((obj instanceof Date) || (obj instanceof Calendar) || (obj instanceof Number) || theClass.isEnum() || theClass.isPrimitive()
					        || Modifier.isFinal(theClass.getModifiers())
					        || (Modifier.isStatic(theClass.getModifiers()) && (theClass.getName().indexOf("$") < 0))) {
						// Not add into history.

						// If add name.
						if (flags[0] && flags[4]) {
							temp.append(CommonJavaHelper.getClassName(obj, flags[1]));
							temp.append("(");
						}

						temp.append(objectToFinalString(obj, placeHoders));

						if (flags[0] && flags[4]) {
							temp.append(")");
						}
					} else {
						// Add into history.
						objHistory.add(obj);

						if (flags[0]) {
							temp.append(CommonJavaHelper.getClassName(obj, flags[1]));
						}

						temp.append("{");

						ArrayList<Field> fields = null;
						fields = CommonJavaHelper.getAllFieldsInClass(fields, theClass);

						if ((fields != null) && (fields.size() > 0)) {
							int[] localLevels = null;
							if (levels != null) {
								localLevels = levels.clone();

								if (localLevels[0] != MAX_LEVEL_MARK) {
									localLevels[1]++;
								}
							}

							for (Field item : fields) {
								item.setAccessible(true);

								String tempName = item.getName().toLowerCase();

								// Check if not excluded.
								if ((excludedFields == null) || !excludedFields.contains(tempName)) {
									// Check if included.
									if ((includedFields == null) || includedFields.contains(tempName)) {
										// If not allow final/static, but it is final/static, then not check.
										if ((!flags[5] && Modifier.isFinal(item.getModifiers())) || (!flags[6] && Modifier.isStatic(item.getModifiers()))) {
											continue;
										}

										if (flags[3]) {
											temp.append(item.getName());
											temp.append("=");
										}

										temp.append(objectDeepToString(item.get(obj), localLevels, flags, placeHoders, excludedFields, includedFields,
										                               maximumTotalStringLength, maximumArrayItemStringLength, objHistory));
										temp.append((placeHoders == null) ? OBJECT_TO_STRING_FIELD_SEPARATOR
										                                 : (placeHoders[0] == null) ? OBJECT_TO_STRING_FIELD_SEPARATOR : placeHoders[0]);
									}
									// If show not-include field.
									else if (flags[9]) {
										// If not allow final/static, but it is final/static, then not check.
										if ((!flags[5] && Modifier.isFinal(item.getModifiers())) || (!flags[6] && Modifier.isStatic(item.getModifiers()))) {
											continue;
										}

										if (flags[3]) {
											temp.append(item.getName());
											temp.append("=<");
											temp.append((placeHoders == null) ? OBJECT_TO_STRING_NOT_INCLUDE_HOLDER
											                                 : (placeHoders[6] == null) ? OBJECT_TO_STRING_NOT_INCLUDE_HOLDER : placeHoders[6]);
											temp.append(">");
											temp.append((placeHoders == null) ? OBJECT_TO_STRING_FIELD_SEPARATOR
											                                 : (placeHoders[0] == null) ? OBJECT_TO_STRING_FIELD_SEPARATOR : placeHoders[0]);
										}
									}
								}
								// If show exclude field.
								else if (flags[8]) {
									// If not allow final/static, but it is final/static, then not check.
									if ((!flags[5] && Modifier.isFinal(item.getModifiers())) || (!flags[6] && Modifier.isStatic(item.getModifiers()))) {
										continue;
									}

									if (flags[3]) {
										temp.append(item.getName());
										temp.append("=<");
										temp.append((placeHoders == null) ? OBJECT_TO_STRING_EXCLUDE_HOLDER
										                                 : (placeHoders[5] == null) ? OBJECT_TO_STRING_EXCLUDE_HOLDER : placeHoders[5]);
										temp.append(">");
										temp.append((placeHoders == null) ? OBJECT_TO_STRING_FIELD_SEPARATOR
										                                 : (placeHoders[0] == null) ? OBJECT_TO_STRING_FIELD_SEPARATOR : placeHoders[0]);
									}
								}
							}

							if (temp.charAt(temp.length() - 1) == ' ') {
								temp.setLength(temp.length() - 2);
							}
						}

						temp.append("}");
					}
				}
			}
		}

		return temp.toString();
	}

	/**
	 * Convert Object deep toString (quick template).
	 *
	 * @param maximumTotalStringLength
	 *        int. For string longer than maximum, it will be truncated and appended with postfix "...#om_xxx#}".<br>
	 *        Suggested value [10, 2000].<br>
	 *        "0" means using default value.<br>
	 *        "< 0" means using unlimited value.
	 * @param maximumArrayItemStringLength
	 *        int. For each array item string, if longer than maximum, it will be truncated and appended with postfix "...#om_xxx#}".<br>
	 *        Suggested value [10, 1000], and should be less than maximumTotalStringLength.<br>
	 *        "0" means using default value.<br>
	 *        "< 0" means using unlimited value.
	 * @return String - Can be null or empty.
	 * @see CommonToStringHelper#objectDeepToString
	 */
	protected static String objectDeepToString_QuickTempalte(Object obj, int[] levels, boolean[] flags, String[] placeHoders, String[] excludedFields,
	                                                         String[] includedFields, int maximumTotalStringLength, int maximumArrayItemStringLength){
		String result = null;

		try {
			if (maximumTotalStringLength < 0) {
				maximumTotalStringLength = Integer.MAX_VALUE;
			} else if (maximumTotalStringLength == 0) {
				maximumTotalStringLength = MAXIMUM_TOTAL_STRING_LENGTH;
			}

			if (maximumArrayItemStringLength < 0) {
				maximumArrayItemStringLength = Integer.MAX_VALUE;
			} else if (maximumArrayItemStringLength == 0) {
				maximumArrayItemStringLength = MAXIMUM_ARRAY_ITEM_STRING_LENGTH;
			}

			Set<String> tempExcludedFields = null;

			if ((excludedFields != null) && (excludedFields.length > 0)) {
				tempExcludedFields = new TreeSet<String>();

				for (String item : excludedFields) {
					if (item != null) {
						item = item.trim();

						if (!item.isEmpty()) {
							tempExcludedFields.add(item.toLowerCase());
						}
					}
				}
			}

			Set<String> tempIncludedFields = null;

			if ((includedFields != null) && (includedFields.length > 0)) {
				tempIncludedFields = new TreeSet<String>();

				for (String item : includedFields) {
					if (item != null) {
						item = item.trim();

						if (!item.isEmpty()) {
							tempIncludedFields.add(item.toLowerCase());
						}
					}
				}
			}

			// There would be Exception.
			result =
			         objectDeepToString(obj, levels, flags, placeHoders, tempExcludedFields, tempIncludedFields, maximumTotalStringLength,
			                            maximumArrayItemStringLength, null);
		} catch (Exception e) {
			result = "Unexpected error (" + e + ") while process (" + obj + ").";
		}

		if (result.length() > maximumTotalStringLength) {
			StringBuilder temp = new StringBuilder();

			temp.append(result.substring(0, maximumTotalStringLength));
			temp.append("...#om_");
			temp.append(result.length() - maximumTotalStringLength);
			temp.append("#");
			if (result.charAt(result.length() - 1) == '}') {
				temp.append("}");
			}

			result = temp.toString();
		}

		return result;
	}

	/**
	 * Convert Object deep toString (value only format).
	 * <p>
	 * flags = { false, false, false, false, false, false, false, false }
	 *
	 * @param maxLevel
	 *        int, should be >= 0. Set to < 0 means unlimited level.
	 * @return String - Can be null or empty.
	 * @see CommonToStringHelper#objectDeepToString
	 * @see CommonToStringHelper#objectDeepToString_QuickTempalte
	 */
	public static String objectDeepToString_0_ValueOnly(Object obj, int maxLevel, String[] placeHoders, String[] excludedFields, String[] includedFields,
	                                                    int maximumTotalStringLength, int maximumArrayItemStringLength){
		boolean[] flags = new boolean[]{false, false, false, false, false, false, false, false, false, false};

		return objectDeepToString_QuickTempalte(obj, getLevels(maxLevel), flags, placeHoders, excludedFields, includedFields, maximumTotalStringLength,
		                                        maximumArrayItemStringLength);
	}

	/**
	 * Convert Object deep toString (value only format).
	 *
	 * @see CommonToStringHelper#objectDeepToString_0_ValueOnly
	 */
	public static String objectDeepToString_0_ValueOnly(Object obj, String[] placeHoders, String[] excludedFields, String[] includedFields,
	                                                    int maximumTotalStringLength, int maximumArrayItemStringLength){
		return objectDeepToString_0_ValueOnly(obj, -1, placeHoders, excludedFields, includedFields, maximumTotalStringLength, maximumArrayItemStringLength);
	}

	/**
	 * Convert Object deep toString (simple format).
	 * <p>
	 * flags = { true, false, false, false, false, false, false, false }
	 *
	 * @param maxLevel
	 *        int, should be >= 0. Set to < 0 means unlimited level.
	 * @return String - Can be null or empty.
	 * @see CommonToStringHelper#objectDeepToString
	 * @see CommonToStringHelper#objectDeepToString_QuickTempalte
	 */
	public static String objectDeepToString_1_Simple(Object obj, int maxLevel, String[] placeHoders, String[] excludedFields, String[] includedFields,
	                                                 int maximumTotalStringLength, int maximumArrayItemStringLength){
		boolean[] flags = new boolean[]{true, false, false, false, false, false, false, false, false, false};

		return objectDeepToString_QuickTempalte(obj, getLevels(maxLevel), flags, placeHoders, excludedFields, includedFields, maximumTotalStringLength,
		                                        maximumArrayItemStringLength);
	}

	/**
	 * Convert Object deep toString (simple format).
	 *
	 * @see CommonToStringHelper#objectDeepToString_1_Simple
	 */
	public static String objectDeepToString_1_Simple(Object obj, String[] placeHoders, String[] excludedFields, String[] includedFields,
	                                                 int maximumTotalStringLength, int maximumArrayItemStringLength){
		return objectDeepToString_1_Simple(obj, -1, placeHoders, excludedFields, includedFields, maximumTotalStringLength, maximumArrayItemStringLength);
	}

	/**
	 * Convert Object deep toString (readable format).
	 * <p>
	 * flags = { true, false, false, true, false, false, false, false }
	 *
	 * @param maxLevel
	 *        int, should be >= 0. Set to < 0 means unlimited level.
	 * @return String - Can be null or empty.
	 * @see CommonToStringHelper#objectDeepToString
	 * @see CommonToStringHelper#objectDeepToString_QuickTempalte
	 */
	public static String objectDeepToString_2_Readable(Object obj, int maxLevel, String[] placeHoders, String[] excludedFields, String[] includedFields,
	                                                   int maximumTotalStringLength, int maximumArrayItemStringLength){
		boolean[] flags = new boolean[]{true, false, false, true, false, false, false, false, false, false};

		return objectDeepToString_QuickTempalte(obj, getLevels(maxLevel), flags, placeHoders, excludedFields, includedFields, maximumTotalStringLength,
		                                        maximumArrayItemStringLength);
	}

	/**
	 * Convert Object deep toString (readable format).
	 *
	 * @see CommonToStringHelper#objectDeepToString_2_Readable
	 */
	public static String objectDeepToString_2_Readable(Object obj, String[] placeHoders, String[] excludedFields, String[] includedFields,
	                                                   int maximumTotalStringLength, int maximumArrayItemStringLength){
		return objectDeepToString_2_Readable(obj, -1, placeHoders, excludedFields, includedFields, maximumTotalStringLength, maximumArrayItemStringLength);
	}

	/**
	 * Convert Object deep toString (readable more format).
	 * <p>
	 * flags = { true, false, true, true, false, false, false, false }
	 *
	 * @param maxLevel
	 *        int, should be >= 0. Set to < 0 means unlimited level.
	 * @return String - Can be null or empty.
	 * @see CommonToStringHelper#objectDeepToString
	 * @see CommonToStringHelper#objectDeepToString_QuickTempalte
	 */
	public static String objectDeepToString_3_ReadMore(Object obj, int maxLevel, String[] placeHoders, String[] excludedFields, String[] includedFields,
	                                                   int maximumTotalStringLength, int maximumArrayItemStringLength){
		boolean[] flags = new boolean[]{true, false, true, true, false, false, false, false, true, true};

		return objectDeepToString_QuickTempalte(obj, getLevels(maxLevel), flags, placeHoders, excludedFields, includedFields, maximumTotalStringLength,
		                                        maximumArrayItemStringLength);
	}

	/**
	 * Convert Object deep toString (readable more format).
	 *
	 * @see CommonToStringHelper#objectDeepToString_3_ReadMore
	 */
	public static String objectDeepToString_3_ReadMore(Object obj, String[] placeHoders, String[] excludedFields, String[] includedFields,
	                                                   int maximumTotalStringLength, int maximumArrayItemStringLength){
		return objectDeepToString_3_ReadMore(obj, -1, placeHoders, excludedFields, includedFields, maximumTotalStringLength, maximumArrayItemStringLength);
	}

	/**
	 * Convert Object deep toString (verbose format).
	 * <p>
	 * flags = { true, true, true, true, false, true, true, false }
	 *
	 * @param maxLevel
	 *        int, should be >= 0. Set to < 0 means unlimited level.
	 * @return String - Can be null or empty.
	 * @see CommonToStringHelper#objectDeepToString
	 * @see CommonToStringHelper#objectDeepToString_QuickTempalte
	 */
	public static String objectDeepToString_4_Verbose(Object obj, int maxLevel, String[] placeHoders, String[] excludedFields, String[] includedFields,
	                                                  int maximumTotalStringLength, int maximumArrayItemStringLength){
		boolean[] flags = new boolean[]{true, true, true, true, false, true, true, false, true, true};

		return objectDeepToString_QuickTempalte(obj, getLevels(maxLevel), flags, placeHoders, excludedFields, includedFields, maximumTotalStringLength,
		                                        maximumArrayItemStringLength);
	}

	/**
	 * Convert Object deep toString (verbose format).
	 *
	 * @see CommonToStringHelper#objectDeepToString_4_Verbose
	 */
	public static String objectDeepToString_4_Verbose(Object obj, String[] placeHoders, String[] excludedFields, String[] includedFields,
	                                                  int maximumTotalStringLength, int maximumArrayItemStringLength){
		return objectDeepToString_4_Verbose(obj, -1, placeHoders, excludedFields, includedFields, maximumTotalStringLength, maximumArrayItemStringLength);
	}

	/**
	 * Convert Object deep toString (trivial format).
	 * <p>
	 * flags = { true, true, true, true, true, true, true, false }
	 *
	 * @param maxLevel
	 *        int, should be >= 0. Set to < 0 means unlimited level.
	 * @return String - Can be null or empty.
	 * @see CommonToStringHelper#objectDeepToString
	 * @see CommonToStringHelper#objectDeepToString_QuickTempalte
	 */
	public static String objectDeepToString_5_Trivial(Object obj, int maxLevel, String[] placeHoders, String[] excludedFields, String[] includedFields,
	                                                  int maximumTotalStringLength, int maximumArrayItemStringLength){
		boolean[] flags = new boolean[]{true, true, true, true, true, true, true, false, true, true};

		return objectDeepToString_QuickTempalte(obj, getLevels(maxLevel), flags, placeHoders, excludedFields, includedFields, maximumTotalStringLength,
		                                        maximumArrayItemStringLength);
	}

	/**
	 * Convert Object deep toString (trivial format).
	 *
	 * @see CommonToStringHelper#objectDeepToString_5_Trivial
	 */
	public static String objectDeepToString_5_Trivial(Object obj, String[] placeHoders, String[] excludedFields, String[] includedFields,
	                                                  int maximumTotalStringLength, int maximumArrayItemStringLength){
		return objectDeepToString_5_Trivial(obj, -1, placeHoders, excludedFields, includedFields, maximumTotalStringLength, maximumArrayItemStringLength);
	}

	/**
	 * Convert Throwable deep toString (readable format).
	 * <p>
	 * flags = { true, true, false, true, false, false, false, true }
	 *
	 * @param t
	 *        Throwable
	 * @param maxLevel
	 *        int, should be >= 0. Set to < 0 means unlimited level.
	 * @return String - Can be null or empty.
	 * @see CommonToStringHelper#objectDeepToString
	 * @see CommonToStringHelper#objectDeepToString_QuickTempalte
	 */
	public static String objectDeepToString_Throwable_Readable(Throwable t, int maxLevel, String[] placeHoders, int maximumTotalStringLength){
		boolean[] flags = new boolean[]{true, true, false, true, false, false, false, true, false, false};

		return objectDeepToString_QuickTempalte(t, getLevels(maxLevel), flags, placeHoders, null, null, maximumTotalStringLength, 0);
	}

	/**
	 * Convert Throwable deep toString (readable format).
	 *
	 * @see CommonToStringHelper#objectDeepToString_Throwable_Readable
	 */
	public static String objectDeepToString_Throwable_Readable(Throwable t, String[] placeHoders, int maximumTotalStringLength){
		return objectDeepToString_Throwable_Readable(t, -1, placeHoders, maximumTotalStringLength);
	}
}
