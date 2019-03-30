
package com.djs.learn.classtag;

import com.djs.learn.classtag.abstracttag.AbstractParentA;
import com.djs.learn.classtag.abstracttag.CompleteParentA;
import com.djs.learn.classtag.abstracttag.CompleteParentB;
import com.djs.learn.classtag.abstracttag.CompleteParentC;
import com.djs.learn.classtag.finaltag.ParentD;
import com.djs.learn.classtag.finaltag.ParentE;
import com.djs.learn.classtag.inherited.ChildA;
import com.djs.learn.classtag.inherited.ChildB;
import com.djs.learn.classtag.inherited2.ChildB3;
import com.djs.learn.classtag.interfacetag.ImplementChildA;
import com.djs.learn.classtag.interfacetag.ImplementChildC;
import com.djs.learn.classtag.interfacetag.ImplementParentA;
import com.djs.learn.classtag.interfacetag.InterfaceParentA;
import com.djs.learn.classtag.mix.MixChildA;
import com.djs.learn.classtag.mix.MixCompleteParentA;
import com.djs.learn.classtag.nestedtag.OuterParentA;
import com.djs.learn.classtag.nestedtag.OuterParentB;
import com.djs.learn.classtag.nestedtag.OuterParentC;
import com.djs.learn.classtag.nestedtag.OuterParentD;
import com.djs.learn.classtag.nestedtag.OuterParentE;
import com.djs.learn.classtag.nestedtag.WrapperParentA;
import com.djs.learn.classtag.nestedtag.WrapperParentB;
import com.djs.learn.classtag.overridetag.OverrideChildA;
import com.djs.learn.classtag.overridetag.OverrideParentA;
import com.djs.learn.classtag.statictag.StaticChildA;
import com.djs.learn.classtag.statictag.StaticParentA;
import com.djs.learn.classtag.statictag.StaticParentB;

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

			System.out.println("--------------------");

			ImplementChildC implementChildC = new ImplementChildC();

			System.out.println("Count A = " + implementChildC.getCountA());
			System.out.println("Count B = " + implementChildC.getCountB());
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

		System.out.println("----------------------------------------");

		try {
			OuterParentC outerParentC = new OuterParentC();

			System.out.println("--------------------");

			outerParentC.printCount();

			System.out.println("--------------------");

			outerParentC.testLocalClass(3);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("----------------------------------------");

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

		System.out.println("----------------------------------------");

		try {
			OuterParentE.StaticInnerA staticInnerA = new OuterParentE.StaticInnerA();
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

	public void testMixTag(){
		System.out.println("---------- Test: Mix ----------");

		int i;

		try {
			MixChildA mixChildA = new MixChildA();
			System.out.println("Name = " + mixChildA.getName());
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		System.out.println("----------------------------------------");

		try {
			MixCompleteParentA mixCompleteParentA = new MixCompleteParentA();
			i = mixCompleteParentA.getCountA();
			System.out.println("i = " + i);

			System.out.println("--------------------");

			i = mixCompleteParentA.getCountB();
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

		theClass.testMixTag();

		System.out.println("============================================================");
	}
}
