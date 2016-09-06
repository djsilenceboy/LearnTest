
package com.djs.learn.common;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class TestCommonReflection
{
	String name = "Tom";
	int age = 10;
	String type = "Cat";
	static String id = "home";

	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public boolean equals(Object obj){
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public static void main(String[] args){
		TestCommonReflection test = new TestCommonReflection();

		System.out.println("toString = " + test);

		TestCommonReflection test2 = new TestCommonReflection();

		System.out.println("Equal = " + test.equals(test2));
	}
}
