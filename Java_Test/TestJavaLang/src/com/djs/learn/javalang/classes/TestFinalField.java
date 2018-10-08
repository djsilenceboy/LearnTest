
package com.djs.learn.javalang.classes;

public class TestFinalField
{
	public static final String nameA = "nameA";
	public static final String nameB;
	// Static final must init when defined or in static init block.
	// public static final String nameC;

	static {
		// Final cannot init twice.
		// nameA = "nameA";
		nameB = "nameB";
	}

	public final String homeA = "homeA";
	public final String homeB;
	public final String homeC;
	// Instance final must init when defined or in instance init block or in constructor.
	// public final String homeD;

	{
		// Final cannot init twice.
		// homeA = "homeA";
		homeB = "homeB";
	}

	public TestFinalField(){
		homeC = "homeC";
	}

	public static void main(String[] args){
		System.out.println("============================================================");
	}
}
