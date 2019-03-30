
package com.djs.learn.classtag.statictag;

public class StaticParentCV7
{
	static {
		try {
			int i = 1 / 0;
		} catch (Exception e) {
			throw e;
		}
	}
}
