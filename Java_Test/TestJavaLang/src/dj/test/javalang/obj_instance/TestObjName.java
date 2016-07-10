
package dj.test.javalang.obj_instance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 * ==================================================
 * objInnerCls = TestObjectName$Cat, TestObjectName$Cat@c17164
 * objayInnerCls = [Ljava.lang.Object;, [Ljava.lang.Object;@1fb8ee3, [TestObjectName$Cat@61de33, TestObjectName$Cat@14318bb]
 * ==================================================
 * objOuterCls = Dog, Dog@10b30a7
 * objayOuterCls = [Ljava.lang.Object;, [Ljava.lang.Object;@1a758cb, [Dog@1b67f74, Dog@69b332]
 * ==================================================
 * iayVal = [I, [I@173a10f, [11, 11]
 * ==================================================
 * objPrimCls = java.lang.Integer, 11
 * objayPrimCls = [Ljava.lang.Object;, [Ljava.lang.Object;@530daa, [11, 11]
 * ==================================================
 * objMap = java.util.HashMap, {m1=yes, m2=no}
 * ==================================================
 * objSet = java.util.HashSet, [s2, s1]
 * ==================================================
 * objList = java.util.LinkedList, [l1, l2]
 * ==================================================
 * </pre>
 */
public class TestObjName
{
	public class Cat
	{
		long l = 2;
		String szTest = "cat";
	}

	public static void main(String[] args){
		TestObjName test = new TestObjName();

		System.out.println("==================================================");
		Object innerClass = test.new Cat();
		Object[] innerClasses = new Object[2];
		innerClasses[0] = test.new Cat();
		innerClasses[1] = test.new Cat();

		System.out.println("innerClass = " + innerClass.getClass().getName() + ", " + innerClass);
		System.out.println("innerClasses = " + innerClasses.getClass().getName() + ", " + innerClasses + ", " + java.util.Arrays.toString(innerClasses));

		System.out.println("==================================================");
		Object outerClass = new Dog();
		Object[] outerClasses = new Object[2];
		outerClasses[0] = new Dog();
		outerClasses[1] = new Dog();

		System.out.println("outerClass = " + outerClass.getClass().getName() + ", " + outerClass);
		System.out.println("outerClasses = " + outerClasses.getClass().getName() + ", " + outerClasses + ", " + java.util.Arrays.toString(outerClasses));

		System.out.println("==================================================");
		int value = 11;
		int[] values = new int[2];
		values[0] = value;
		values[1] = value;

		System.out.println("values = " + values.getClass().getName() + ", " + values + ", " + java.util.Arrays.toString(values));

		System.out.println("==================================================");
		Object primaryClass = value;
		Object[] primaryClasses = new Object[2];
		primaryClasses[0] = value;
		primaryClasses[1] = value;

		System.out.println("primaryClass = " + primaryClass.getClass().getName() + ", " + primaryClass);
		System.out
		        .println("primaryClasses = " + primaryClasses.getClass().getName() + ", " + primaryClasses + ", " + java.util.Arrays.toString(primaryClasses));

		System.out.println("==================================================");
		Map<String, String> map = new HashMap<String, String>();
		map.put("m1", "yes");
		map.put("m2", "no");
		Object mapObject = map;
		System.out.println("mapObject = " + mapObject.getClass().getName() + ", " + mapObject);

		System.out.println("==================================================");
		Set<String> set = new HashSet<String>();
		set.add("s1");
		set.add("s2");
		Object setObject = set;
		System.out.println("setObject = " + setObject.getClass().getName() + ", " + setObject);

		System.out.println("==================================================");
		List<String> list = new LinkedList<String>();
		list.add("l1");
		list.add("l2");
		Object listObjetc = list;
		System.out.println("listObjetc = " + listObjetc.getClass().getName() + ", " + listObjetc);

		System.out.println("==================================================");
	}
}
