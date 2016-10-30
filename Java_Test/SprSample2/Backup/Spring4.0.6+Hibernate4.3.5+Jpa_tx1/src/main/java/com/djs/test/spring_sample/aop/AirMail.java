
package com.djs.learn.spring_sample.aop;

public class AirMail
{
	private String name;
	private String address;
	private long transactionId;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress( String address )
	{
		this.address = address;
	}

	public long getTransactionId()
	{
		return transactionId;
	}

	public void setTransactionId( long transactionId )
	{
		this.transactionId = transactionId;
	}

	@Override
	public String toString()
	{
		return "AirMail [name=" + name + ", address=" + address + ", transactionId=" + transactionId + "]";
	}
}
