
package com.djs.learn.spring_sample.expression;

public class ACompany
{
	private String name;
	private AEmployee boss;
	private AEmployee assistant;
	private int totalSalary;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public AEmployee getBoss()
	{
		return boss;
	}

	public void setBoss( AEmployee boss )
	{
		this.boss = boss;
	}

	public AEmployee getAssistant()
	{
		return assistant;
	}

	public void setAssistant( AEmployee assistant )
	{
		this.assistant = assistant;
	}

	public int getTotalSalary()
	{
		return totalSalary;
	}

	public void setTotalSalary( int totalSalary )
	{
		this.totalSalary = totalSalary;
	}
}
