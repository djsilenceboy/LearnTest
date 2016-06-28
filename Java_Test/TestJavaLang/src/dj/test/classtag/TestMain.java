
package dj.test.classtag;

import dj.test.classtag.abstracttag.AbstractParentA;
import dj.test.classtag.abstracttag.CompleteParentA;
import dj.test.classtag.abstracttag.CompleteParentB;
import dj.test.classtag.abstracttag.CompleteParentC;
import dj.test.classtag.finaltag.ParentD;
import dj.test.classtag.finaltag.ParentE;
import dj.test.classtag.inherited.ChildA;
import dj.test.classtag.inherited.ChildB;
import dj.test.classtag.inherited2.ChildB3;
import dj.test.classtag.interfacetag.ImplementChildA;
import dj.test.classtag.interfacetag.ImplementParentA;
import dj.test.classtag.interfacetag.InterfaceParentA;
import dj.test.classtag.nestedtag.OuterParentA;
import dj.test.classtag.nestedtag.OuterParentB;
import dj.test.classtag.nestedtag.OuterParentC;
import dj.test.classtag.nestedtag.OuterParentD;
import dj.test.classtag.nestedtag.WrapperParentA;
import dj.test.classtag.nestedtag.WrapperParentB;
import dj.test.classtag.overridetag.OverrideChildA;
import dj.test.classtag.overridetag.OverrideParentA;
import dj.test.classtag.statictag.StaticChildA;
import dj.test.classtag.statictag.StaticParentA;
import dj.test.classtag.statictag.StaticParentB;

public class TestMain
{
	public void testAbstractTag(){
		System.out.println("---------- Test: Abstract ----------");

		int i;

		try {
			CompleteParentA completeParentA = new CompleteParentA();
			completeParentA.getCountA();
			completeParentA.getCountB();

			completeParentA.printNameA();
			completeParentA.printNameB();
			completeParentA.printNameC();
			completeParentA.printNameD();
			completeParentA.printNameE();
			completeParentA.printNameF();
			completeParentA.printNameG();
			completeParentA.printNameH();

			i = CompleteParentA.count;
			System.out.println("i = " + i);

			System.out.println("--------------------");

			{
				AbstractParentA abstractParentA = completeParentA;

				abstractParentA.printNameA();
				abstractParentA.printNameB();
				abstractParentA.printNameC();
				abstractParentA.printNameD();
				abstractParentA.printNameG();
			}

			System.out.println("--------------------");

			{
				CompleteParentB completeParentB = new CompleteParentB();

				AbstractParentA abstractParentA = completeParentB;
				abstractParentA.getCountA();
				abstractParentA.getCountB();
			}

			System.out.println("--------------------");

			{
				CompleteParentC completeParentC = new CompleteParentC();

				completeParentC.test1();
			}
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testFinalTag(){
		System.out.println("---------- Test: Final ----------");

		try {
			ParentD parentD = new ParentD();

			System.out.println("--------------------");

			ParentD parentD2 = new ParentD();

			System.out.println("--------------------");

			ParentE parentE = new ParentE();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testInherited(){
		System.out.println("---------- Test: Inherited ----------");

		try {
			ChildA childA = new ChildA();

			System.out.println("--------------------");

			childA.ParentA();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("----------------------------------------");

		try {
			ChildB childB = new ChildB();

			System.out.println("--------------------");

			// Not in same package, cannot be visible.
			// childB.protLv1();
			// childB.protLv2();
			// childB.inpkgLv1();
			// childB.inpkgLv2();

			childB.test();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("----------------------------------------");

		try {
			ChildB2 childB2 = new ChildB2();

			System.out.println("--------------------");

			// Not in same package, cannot be visible.
			// childB2.protLv1();
			// childB2.inpkgLv1()

			childB2.protLv2();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("----------------------------------------");

		try {
			ChildB3 childB3 = new ChildB3();

			System.out.println("--------------------");

			// Not in same package, cannot be visible.
			// childB2.protLv1();
			// childB2.inpkgLv1()
			// childB3.protLv3();

			childB3.protLv2();

			System.out.println("--------------------");

			childB3.testProtPkg();

			System.out.println("--------------------");

			childB3.testInPkg();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("----------------------------------------");

		try {
			ChildC childC = new ChildC();

			System.out.println("--------------------");

			childC.protLv1();

			System.out.println("--------------------");

			childC.protLv2();

			System.out.println("--------------------");

			childC.inpkgLv1();

			System.out.println("--------------------");

			childC.inpkgLv2();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testInterfaceTag(){
		System.out.println("---------- Test: Interface ----------");

		int i;

		try {
			ImplementParentA implementParentA = new ImplementParentA();

			System.out.println("--------------------");

			i = implementParentA.getCountA();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			i = implementParentA.getCountB();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			i = ImplementParentA.count;
			System.out.println("i = " + i);

			System.out.println("--------------------");

			ImplementChildA implementChildA = new ImplementChildA();

			System.out.println("--------------------");

			i = implementChildA.getCountA();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			i = implementChildA.getCountB();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			i = ImplementChildA.count;
			System.out.println("i = " + i);

			System.out.println("--------------------");

			i = implementChildA.getAgeA();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			i = ImplementChildA.age;
			System.out.println("i = " + i);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testNestedTag(){
		System.out.println("---------- Test: Nested ----------");

		int i;

		try {
			OuterParentA outerParentA = new OuterParentA();

			System.out.println("--------------------");

			i = outerParentA.getCount();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			outerParentA.printCount();

			System.out.println("--------------------");

			outerParentA.testInnerClass();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("----------------------------------------");

		try {
			WrapperParentA wrapperParentA = new WrapperParentA();

			System.out.println("--------------------");

			i = wrapperParentA.getCount();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			wrapperParentA.printCount();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("----------------------------------------");

		try {
			OuterParentA.StaticInnerParentA staticInnerParentA = new OuterParentA.StaticInnerParentA();

			System.out.println("--------------------");

			i = staticInnerParentA.getCount();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			staticInnerParentA.printCount();

			System.out.println("--------------------");

			staticInnerParentA.testOuterClass();

			System.out.println("--------------------");

			OuterParentA.StaticInnerParentB staticInnerParentB = new WrapperParentB();

			System.out.println("--------------------");

			i = staticInnerParentB.getCount();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			staticInnerParentB.printCount();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("----------------------------------------");

		try {
			OuterParentB outerParentB = new OuterParentB();

			System.out.println("--------------------");

			i = outerParentB.getCount();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			outerParentB.printCount();

			System.out.println("--------------------");

			outerParentB.testInnerClass();;

			{
				System.out.println("--------------------");
				OuterParentB.InnerParentA innerParentA = outerParentB.new InnerParentA();

				System.out.println("--------------------");

				innerParentA.printCount(3);

				System.out.println("--------------------");

				innerParentA.testOuterClass();
			}

			System.out.println("--------------------");

			outerParentB.getCount();

			System.out.println("--------------------");

			{
				System.out.println("--------------------");
				OuterParentB.InnerParentA innerParentA = outerParentB.new InnerParentA();

				System.out.println("--------------------");

				innerParentA.printCount(4);

				System.out.println("--------------------");

				innerParentA.testOuterClass();
			}
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("--------------------");

		try {
			OuterParentC outerParentC = new OuterParentC();

			System.out.println("--------------------");

			outerParentC.printCount();

			System.out.println("--------------------");

			outerParentC.testLocalClass(3);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("--------------------");

		try {
			OuterParentD outerParentD = new OuterParentD();

			System.out.println("--------------------");

			outerParentD.testAnonymousClass(new InterfaceParentA() {
				@Override
				public int getCountA(){
					System.out.println("InterfaceParentA:getCountA");

					return 0;
				}

				@Override
				public int getCountB(){
					System.out.println("InterfaceParentA:getCountB");

					return 0;
				}
			});
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testOverrideTag(){
		System.out.println("---------- Test: Override ----------");

		try {
			OverrideChildA overrideChildA = new OverrideChildA();

			System.out.println("--------------------");

			overrideChildA.protLv1();

			System.out.println("--------------------");

			overrideChildA.inpkgLv1();

			System.out.println("--------------------");

			overrideChildA.testReturn();

			System.out.println("--------------------");

			OverrideParentA overrideParentA = overrideChildA;

			System.out.println("OverrideParentA.name = " + overrideParentA.name);
			System.out.println("OverrideChildA.name = " + overrideChildA.name);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public void testStaticTag(){
		System.out.println("---------- Test: Static ----------");

		int i;

		try {
			i = StaticParentA.getCountA();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			StaticParentA staticParentA = new StaticParentA();

			System.out.println("--------------------");

			i = StaticParentA.getCountA();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			i = staticParentA.getCountA();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			i = staticParentA.getCountB();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			i = staticParentA.getCountB();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			i = StaticParentA.getCountC();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			i = StaticParentA.getCountC();
			System.out.println("i = " + i);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("----------------------------------------");

		try {
			i = StaticChildA.getCountA();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			StaticChildA staticChildA = new StaticChildA();

			i = staticChildA.getCountB();
			System.out.println("i = " + i);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("----------------------------------------");

		try {
			i = StaticParentB.getCountA();
			System.out.println("i = " + i);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public static void main(String[] args){
		TestMain theClass = new TestMain();

		System.out.println("============================================================");

		theClass.testAbstractTag();

		System.out.println("============================================================");

		theClass.testFinalTag();

		System.out.println("============================================================");

		theClass.testInherited();

		System.out.println("============================================================");

		theClass.testInterfaceTag();

		System.out.println("============================================================");

		theClass.testNestedTag();

		System.out.println("============================================================");

		theClass.testOverrideTag();

		System.out.println("============================================================");

		theClass.testStaticTag();

		System.out.println("============================================================");
	}
}
