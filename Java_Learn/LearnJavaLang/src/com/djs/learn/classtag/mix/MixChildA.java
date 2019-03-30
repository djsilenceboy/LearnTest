
package com.djs.learn.classtag.mix;

public class MixChildA extends MixParentA implements InterfaceParentV8
{
	// Both template and interface has getName() method.
	// The rule is: template first.
	// Template's getName() will be called.
}
