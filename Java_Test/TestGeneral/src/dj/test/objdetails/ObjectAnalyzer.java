
package dj.test.objdetails;

/*
 * Project : ATOMIC
 * FileName : ObjectAnalyzer.java
 * Copyright Notice: 2008 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the license
 * agreement you entered into with SingTel.
 */

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Object analyzer. Does analysis of state of an object. Will recursively analyze state of composite objects to "nestLevels" level. Returns a string with state
 * report.
 */
public class ObjectAnalyzer
{
	// TODO: change
	static final String str = new String("\n>>--- STATE OF OBJECT TYPE --->>");

	// static final String format = new String(
	// "+------- FIELD name :{0}\t Type : {1}\t value : {2}\n" );
	// static final String strFormat = new String(
	// "+------- Type : {0}\t value : {1}\n" );
	static final String format = new String("+--- {0}: {1}\n");
	static final String strFormat = new String("+--- Value : {0}\n");
	static final String listHeaderFormat = new String("+--- {0}:");
	static final String listFormat = new String("{0}:{1} ");
	private static final String SINGTEL_NDP_PACKAGE = "com.singtel.ndp";
	private static final String SINGTEL_NDP_PACKAGE_2 = "ndp";
	private static final String SINGTEL_ATOMIC_PACKAGE = "com.atomic";
	private static final String OPTUS_PACKAGE = "au.com";

	/**
	 * Analyse an object and its composite object. Recursively analyzes state of composite objects up to "nestLevels" deep.
	 * 
	 * @param obj
	 *        Object to analyze
	 * @param indent
	 *        An indentation level for the report
	 * @param nestLevels
	 *        Number of levels of compostion to analyze
	 * @param bExpandList
	 *        To iterate and analyse the Items in the List
	 * @return String - A string state report of object and its composition
	 */
	@SuppressWarnings("unchecked")
	public static String analyze(Object obj, final String indent, final int nestLevels, boolean... bExpandList){
		StringBuffer buf = new StringBuffer(str);

		if (obj == null) {
			buf.append("State of Object for analyze is NULL. No more info available.\n");
		} else {
			if (obj instanceof java.lang.String) {
				buf.append("\n");
				buf.append(MessageFormat.format(strFormat, new Object[]{
				// obj.getClass( ).getName( ),
				                                obj}));
			} else {
				Field[] fields = getFieldsInObject(obj);

				if (null == fields) {
					return "";
				}

				buf.append(obj.getClass().getName()).append("\n");

				for (int i = 0; i < fields.length; i++) {
					// Skip over "final" members
					int modifier = fields[i].getModifiers();

					if (Modifier.isFinal(modifier) && Modifier.isStatic(modifier)) {
						continue;
					}

					try {
						fields[i].setAccessible(true);

						// Modified on 11/Jun/2010 for List and Calendar
						// Manipulation
						if ((fields[i].get(obj) instanceof List) && (fields[i].get(obj) != null)) {
							if ((bExpandList.length > 0) && (bExpandList[0])) {
								List<Object> objInList = (List<Object>)fields[i].get(obj);

								if (null != objInList) {
									buf.append(MessageFormat.format(listHeaderFormat, fields[i].getName()));

									for (Object objInLoop : objInList) {
										buf.append(analyzeList(objInLoop));
									}
								}
							} else {
								buf.append(constructAnalysedText(fields[i], obj, format));
							}
						} else {
							buf.append(constructAnalysedText(fields[i], obj, format));

							if (nestLevels > 1) // Go to another level
							{
								if ((fields[i].get(obj) != null) && !fields[i].getType().isPrimitive() && !fields[i].getType().isArray()
								        && isPackageAllowed(fields[i].getType().getPackage().getName())) {
									buf.append("|" + indent + "Composed of :");
									buf.append(analyze(fields[i].get(obj), indent + indent, nestLevels - 1));
								}
							}
						}
					} catch (Exception e) {
						buf.append("There was an exception doing analysis on object !!!!!!!! - " + e.getMessage() + " , " + e.toString());
						e.printStackTrace();
					}
				}
			}
		}

		return buf.toString();
	}

	/**
	 * Analyse all the Items Object
	 * 
	 * @param arrObj
	 * @return String
	 */
	private static String analyzeList(Object arrObj){
		Field[] fields = getFieldsInObject(arrObj);
		StringBuffer buf = new StringBuffer("\n");

		if (null == fields) {
			return "";
		}

		for (int i = 0; i < fields.length; i++) {
			// Skip over "final" members
			int modifier = fields[i].getModifiers();

			if (Modifier.isFinal(modifier) && Modifier.isStatic(modifier)) {
				continue;
			}

			fields[i].setAccessible(true);
			buf.append(constructAnalysedText(fields[i], arrObj, listFormat));
		}

		return buf.toString();
	}

	/**
	 * Create the Analysed Text based on the Field ,Object and Format
	 * 
	 * @param field
	 * @param obj
	 * @param format
	 * @return String
	 */
	private static String constructAnalysedText(Field field, Object obj, String format){
		StringBuffer buf = new StringBuffer();

		try {
			if (field.getType().isArray()) {
				if (field.get(obj) instanceof int[]) {
					// Special Handling for ilogTable[] in BigDecimal
					buf.append(MessageFormat.format(format, new Object[]{field.getName(),

					(field.get(obj) != null) ? (field.get(obj)) : null}));
				} else {
					buf.append(MessageFormat.format(format, new Object[]{field.getName(),

					(field.get(obj) != null) ? Arrays.deepToString((Object[])field.get(obj)) : null}));
				}
			} else if (field.get(obj) instanceof Calendar) {
				buf.append(MessageFormat.format(format, new Object[]{field.getName(),

				(field.get(obj) != null) ? ((Calendar)field.get(obj)).getTime().toString() : null}));
			} else {
				buf.append(MessageFormat.format(format, new Object[]{field.getName(),

				(field.get(obj) != null) ? field.get(obj).toString() : null}));
			}
		} catch (Exception e) {
			buf.append("There was an exception doing analysis on object !!!!!!!! - " + e.getMessage() + " , " + e.toString());
			e.printStackTrace();
		}

		return buf.toString();
	}

	/**
	 * Returns the Fields in the Object
	 * 
	 * @param object
	 *        Object
	 * @return Field []
	 */
	private static Field[] getFieldsInObject(Object object){
		List<Field> fieldList = getAllFields(new ArrayList<Field>(), object.getClass());

		Field[] fields = ((fieldList != null) && (fieldList.size() > 0)) ? fieldList.toArray(new Field[fieldList.size()]) : null;

		return fields;
	}

	/**
	 * Gets all the fields in the Class
	 * 
	 * @param fields
	 *        List<Field>
	 * @param type
	 *        Class<?>
	 * @return List<Field>
	 */
	public static List<Field> getAllFields(List<Field> fields, Class<?> type){
		// The method is added for getting all fields of the class including all
		// its superclass.
		for (Field field : type.getDeclaredFields()) {
			fields.add(field);
		}

		if (type.getSuperclass() != null) {
			fields = getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}

	/**
	 * Analyse an object and its composite object
	 * 
	 * @param obj
	 *        Object to analyze
	 * @return String - A string state report of object and its composition
	 */
	public static String analyze(Object obj){
		return analyze(obj, " ", 1);
	}

	private static boolean isPackageAllowed(String packageName){
		return (packageName.startsWith(SINGTEL_NDP_PACKAGE) || packageName.startsWith(SINGTEL_NDP_PACKAGE_2) || packageName.startsWith(SINGTEL_ATOMIC_PACKAGE) || packageName
		        .startsWith(OPTUS_PACKAGE));
	}
}
