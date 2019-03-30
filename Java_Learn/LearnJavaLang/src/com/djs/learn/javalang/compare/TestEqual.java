
package com.djs.learn.javalang.compare;

import java.util.HashSet;

/**
 * "==" compare instance memory reference.
 * "equals()" calls "equals(Object obj)".
 * For "hashSet.add(Value)" and "hashSet.contains(Key)", the real hash address is actually not just "Value" but "Value.hashcode ^ (Value.hashcode >>> 16)".
 * "hashSet.add(Value)" find real hash address first, then compares "instance memory reference || equals(Object obj)" to check unique.
 * "hashSet.contains(Value)" compares real hash address first, then compares "instance memory reference || equals(Object obj)".
 * Always remember to override hashCode() if you override equals().
 *
 * <pre>
============================================================
Class name: Simple
------------------------------
[0]: [i=100, str=AA]: HashCode: 652724274
[1]: [i=100, str=AA]: HashCode: 990404242
[2]: [i=300, str=CC]: HashCode: 578347397
[3]: [i=400, str=DD]: HashCode: 773715057
------------------------------
[0] == [1]: false
[0] equals [1] : false
[2] == [3]: false
[2] equals [3]: false
--------------------------------------------------
HashSet: [[i=300, str=CC], [i=100, str=AA]]
------------------------------
<HashSet> contains([0]): true
<HashSet> contains(new [0]): false
<HashSet> contains([2]): true
<HashSet> contains(new [2]): false
<HashSet> contains([3]): false
<HashSet> contains(new [3]): false
============================================================
Class name: SimpleEqu
------------------------------
[0]: [i=100, str=AA]: HashCode: 229910155
[1]: [i=100, str=AA]: HashCode: 776894132
[2]: [i=300, str=CC]: HashCode: 559102764
[3]: [i=400, str=DD]: HashCode: 1599198923
------------------------------
[0] == [1]: false
<Equals> [i=100, str=AA] to [i=100, str=AA]: true
[0] equals [1] : true
[2] == [3]: false
<Equals> [i=300, str=CC] to [i=400, str=DD]: false
[2] equals [3]: false
--------------------------------------------------
HashSet: [[i=300, str=CC], [i=100, str=AA]]
------------------------------
<HashSet> contains([0]): true
<HashSet> contains(new [0]): false
<HashSet> contains([2]): true
<HashSet> contains(new [2]): false
<HashSet> contains([3]): false
<HashSet> contains(new [3]): false
============================================================
Class name: SimpleHash
------------------------------
<HashCode> [i=100, str=AA]: 2116
[0]: [i=100, str=AA]: HashCode: 2116
<HashCode> [i=100, str=AA]: 2116
[1]: [i=100, str=AA]: HashCode: 2116
<HashCode> [i=300, str=CC]: 2380
[2]: [i=300, str=CC]: HashCode: 2380
<HashCode> [i=400, str=DD]: 2320
[3]: [i=400, str=DD]: HashCode: 2320
------------------------------
[0] == [1]: false
[0] equals [1] : false
[2] == [3]: false
[2] equals [3]: false
--------------------------------------------------
<HashCode> [i=100, str=AA]: 2116
<HashCode> [i=300, str=CC]: 2380
HashSet: [[i=100, str=AA], [i=300, str=CC]]
------------------------------
<HashCode> [i=100, str=AA]: 2116
<HashSet> contains([0]): true
<HashCode> [i=100, str=AA]: 2116
<HashSet> contains(new [0]): false
<HashCode> [i=300, str=CC]: 2380
<HashSet> contains([2]): true
<HashCode> [i=300, str=CC]: 2380
<HashSet> contains(new [2]): false
<HashCode> [i=400, str=DD]: 2320
<HashSet> contains([3]): false
<HashCode> [i=400, str=DD]: 2320
<HashSet> contains(new [3]): false
============================================================
Class name: SimpleEquHash
------------------------------
<HashCode> [i=100, str=AA]: 2116
[0]: [i=100, str=AA]: HashCode: 2116
<HashCode> [i=100, str=AA]: 2116
[1]: [i=100, str=AA]: HashCode: 2116
<HashCode> [i=300, str=CC]: 2380
[2]: [i=300, str=CC]: HashCode: 2380
<HashCode> [i=400, str=DD]: 2320
[3]: [i=400, str=DD]: HashCode: 2320
------------------------------
[0] == [1]: false
<Equals> [i=100, str=AA] to [i=100, str=AA]: true
[0] equals [1] : true
[2] == [3]: false
<Equals> [i=300, str=CC] to [i=400, str=DD]: false
[2] equals [3]: false
--------------------------------------------------
<HashCode> [i=100, str=AA]: 2116
<HashCode> [i=300, str=CC]: 2380
HashSet: [[i=100, str=AA], [i=300, str=CC]]
------------------------------
<HashCode> [i=100, str=AA]: 2116
<HashSet> contains([0]): true
<HashCode> [i=100, str=AA]: 2116
<Equals> [i=100, str=AA] to [i=100, str=AA]: true
<HashSet> contains(new [0]): true
<HashCode> [i=300, str=CC]: 2380
<HashSet> contains([2]): true
<HashCode> [i=300, str=CC]: 2380
<Equals> [i=300, str=CC] to [i=300, str=CC]: true
<HashSet> contains(new [2]): true
<HashCode> [i=400, str=DD]: 2320
<HashSet> contains([3]): false
<HashCode> [i=400, str=DD]: 2320
<HashSet> contains(new [3]): false
============================================================
Class name: SimpleSameEqu
------------------------------
[0]: [i=100, str=AA]: HashCode: 1313575169
[1]: [i=100, str=AA]: HashCode: 1125974223
[2]: [i=300, str=CC]: HashCode: 622868597
[3]: [i=400, str=DD]: HashCode: 1717214301
------------------------------
[0] == [1]: false
<Equals> [i=100, str=AA] to [i=100, str=AA] : true
[0] equals [1] : true
[2] == [3]: false
<Equals> [i=300, str=CC] to [i=400, str=DD] : true
[2] equals [3]: true
--------------------------------------------------
HashSet: [[i=300, str=CC], [i=100, str=AA]]
------------------------------
<HashSet> contains([0]): true
<HashSet> contains(new [0]): false
<HashSet> contains([2]): true
<HashSet> contains(new [2]): false
<HashSet> contains([3]): false
<HashSet> contains(new [3]): false
============================================================
Class name: SimpleSameHash
------------------------------
<HashCode> [i=100, str=AA]: 123
[0]: [i=100, str=AA]: HashCode: 123
<HashCode> [i=100, str=AA]: 123
[1]: [i=100, str=AA]: HashCode: 123
<HashCode> [i=300, str=CC]: 123
[2]: [i=300, str=CC]: HashCode: 123
<HashCode> [i=400, str=DD]: 123
[3]: [i=400, str=DD]: HashCode: 123
------------------------------
[0] == [1]: false
[0] equals [1] : false
[2] == [3]: false
[2] equals [3]: false
--------------------------------------------------
<HashCode> [i=100, str=AA]: 123
<HashCode> [i=300, str=CC]: 123
HashSet: [[i=300, str=CC], [i=100, str=AA]]
------------------------------
<HashCode> [i=100, str=AA]: 123
<HashSet> contains([0]): true
<HashCode> [i=100, str=AA]: 123
<HashSet> contains(new [0]): false
<HashCode> [i=300, str=CC]: 123
<HashSet> contains([2]): true
<HashCode> [i=300, str=CC]: 123
<HashSet> contains(new [2]): false
<HashCode> [i=400, str=DD]: 123
<HashSet> contains([3]): false
<HashCode> [i=400, str=DD]: 123
<HashSet> contains(new [3]): false
============================================================
Class name: SimpleSameEquHash
------------------------------
<HashCode> [i=100, str=AA]: 123
[0]: [i=100, str=AA]: HashCode: 123
<HashCode> [i=100, str=AA]: 123
[1]: [i=100, str=AA]: HashCode: 123
<HashCode> [i=300, str=CC]: 123
[2]: [i=300, str=CC]: HashCode: 123
<HashCode> [i=400, str=DD]: 123
[3]: [i=400, str=DD]: HashCode: 123
------------------------------
[0] == [1]: false
<Equals> [i=100, str=AA] to [i=100, str=AA] : true
[0] equals [1] : true
[2] == [3]: false
<Equals> [i=300, str=CC] to [i=400, str=DD] : true
[2] equals [3]: true
--------------------------------------------------
<HashCode> [i=100, str=AA]: 123
<HashCode> [i=300, str=CC]: 123
<Equals> [i=300, str=CC] to [i=100, str=AA] : true
HashSet: [[i=100, str=AA]]
------------------------------
<HashCode> [i=100, str=AA]: 123
<HashSet> contains([0]): true
<HashCode> [i=100, str=AA]: 123
<Equals> [i=100, str=AA] to [i=100, str=AA] : true
<HashSet> contains(new [0]): true
<HashCode> [i=300, str=CC]: 123
<Equals> [i=300, str=CC] to [i=100, str=AA] : true
<HashSet> contains([2]): true
<HashCode> [i=300, str=CC]: 123
<Equals> [i=300, str=CC] to [i=100, str=AA] : true
<HashSet> contains(new [2]): true
<HashCode> [i=400, str=DD]: 123
<Equals> [i=400, str=DD] to [i=100, str=AA] : true
<HashSet> contains([3]): true
<HashCode> [i=400, str=DD]: 123
<Equals> [i=400, str=DD] to [i=100, str=AA] : true
<HashSet> contains(new [3]): true
============================================================
 * </pre>
 */
public class TestEqual
{
	static public class Simple implements Cloneable
	{
		public int i = 100;
		public String str = "AA";

		public Simple(){
		}

		public Simple(int i, String str){
			this.i = i;
			this.str = str;
		}

		@Override
		public Object clone() throws CloneNotSupportedException{
			return super.clone();
		}

		@Override
		public String toString(){
			return "[i=" + i + ", str=" + str + "]";
		}
	}

	static public class SimpleEqu extends Simple
	{
		public SimpleEqu(){
			super();
		}

		public SimpleEqu(int i, String str){
			super(i, str);
		}

		@Override
		public boolean equals(Object obj){
			boolean result = false;

			if (obj instanceof SimpleEqu) {
				SimpleEqu inst = (SimpleEqu)obj;

				result = (i == inst.i) && str.equals(inst.str);
			}

			System.out.println("<Equals> " + this + " to " + obj + ": " + result);

			return result;
		}
	}

	static public class SimpleHash extends Simple
	{
		public SimpleHash(){
			super();
		}

		public SimpleHash(int i, String str){
			super(i, str);
		}

		@Override
		public int hashCode(){
			int result = i ^ str.hashCode();
			System.out.println("<HashCode> " + this + ": " + result);

			return result;
		}
	}

	static public class SimpleEquHash extends SimpleEqu
	{
		public SimpleEquHash(){
			super();
		}

		public SimpleEquHash(int i, String str){
			super(i, str);
		}

		@Override
		public int hashCode(){
			int result = i ^ str.hashCode();
			System.out.println("<HashCode> " + this + ": " + result);

			return result;
		}
	}

	static public class SimpleSameEqu extends Simple
	{
		public SimpleSameEqu(){
			super();
		}

		public SimpleSameEqu(int i, String str){
			super(i, str);
		}

		@Override
		public boolean equals(Object obj){
			System.out.println("<Equals> " + this + " to " + obj + " : true");

			return true;
		}
	}

	static public class SimpleSameHash extends Simple
	{
		public SimpleSameHash(){
			super();
		}

		public SimpleSameHash(int i, String str){
			super(i, str);
		}

		@Override
		public int hashCode(){
			int result = 123;
			System.out.println("<HashCode> " + this + ": " + result);

			return result;
		}
	}

	static public class SimpleSameEquHash extends SimpleSameEqu
	{
		public SimpleSameEquHash(){
			super();
		}

		public SimpleSameEquHash(int i, String str){
			super(i, str);
		}

		@Override
		public int hashCode(){
			int result = 123;
			System.out.println("<HashCode> " + this + ": " + result);

			return result;
		}
	}

	private void checkObjs(Object[] objs) throws Exception{
		int i = 0;

		System.out.println("Class name: " + objs[0].getClass().getSimpleName());

		System.out.println("------------------------------");
		for (Object obj : objs) {
			System.out.println("[" + i++ + "]: " + obj + ": HashCode: " + obj.hashCode());
		}

		System.out.println("------------------------------");
		System.out.println("[0] == [1]: " + (objs[0] == objs[1]));
		System.out.println("[0] equals [1] : " + (objs[0].equals(objs[1])));
		System.out.println("[2] == [3]: " + (objs[2] == objs[3]));
		System.out.println("[2] equals [3]: " + (objs[2].equals(objs[3])));

		System.out.println("--------------------------------------------------");
		HashSet<Object> hashSet = new HashSet<Object>();

		hashSet.add(objs[0]);
		hashSet.add(objs[2]);

		System.out.println("HashSet: " + hashSet);

		System.out.println("------------------------------");
		System.out.println("<HashSet> contains([0]): " + hashSet.contains((objs[0])));
		System.out.println("<HashSet> contains(new [0]): " + hashSet.contains(((Simple)objs[0]).clone()));
		System.out.println("<HashSet> contains([2]): " + hashSet.contains((objs[2])));
		System.out.println("<HashSet> contains(new [2]): " + hashSet.contains(((Simple)objs[2]).clone()));
		System.out.println("<HashSet> contains([3]): " + hashSet.contains((objs[3])));
		System.out.println("<HashSet> contains(new [3]): " + hashSet.contains(((Simple)objs[3]).clone()));
	}

	public static void main(String[] args){
		TestEqual test = new TestEqual();

		try {
			System.out.println("============================================================");

			Simple[] simples = new Simple[]{new Simple(), new Simple(), new Simple(300, "CC"), new Simple(400, "DD")};
			test.checkObjs(simples);

			System.out.println("============================================================");

			SimpleEqu[] simpleEqus = new SimpleEqu[]{new SimpleEqu(), new SimpleEqu(), new SimpleEqu(300, "CC"), new SimpleEqu(400, "DD")};
			test.checkObjs(simpleEqus);

			System.out.println("============================================================");

			SimpleHash[] simpleHashs = new SimpleHash[]{new SimpleHash(), new SimpleHash(), new SimpleHash(300, "CC"), new SimpleHash(400, "DD")};
			test.checkObjs(simpleHashs);

			System.out.println("============================================================");

			SimpleEquHash[] simpleEquHashs = new SimpleEquHash[]{new SimpleEquHash(), new SimpleEquHash(), new SimpleEquHash(300, "CC"),
			                                                     new SimpleEquHash(400, "DD")};
			test.checkObjs(simpleEquHashs);

			System.out.println("============================================================");

			SimpleSameEqu[] simpleSameEqus = new SimpleSameEqu[]{new SimpleSameEqu(), new SimpleSameEqu(), new SimpleSameEqu(300, "CC"),
			                                                     new SimpleSameEqu(400, "DD")};
			test.checkObjs(simpleSameEqus);

			System.out.println("============================================================");

			SimpleSameHash[] simpleSameHashs = new SimpleSameHash[]{new SimpleSameHash(), new SimpleSameHash(), new SimpleSameHash(300, "CC"),
			                                                        new SimpleSameHash(400, "DD")};
			test.checkObjs(simpleSameHashs);

			System.out.println("============================================================");

			SimpleSameEquHash[] simpleSameEquHashs = new SimpleSameEquHash[]{new SimpleSameEquHash(), new SimpleSameEquHash(), new SimpleSameEquHash(300, "CC"),
			                                                                 new SimpleSameEquHash(400, "DD")};
			test.checkObjs(simpleSameEquHashs);

			System.out.println("============================================================");
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}
}
