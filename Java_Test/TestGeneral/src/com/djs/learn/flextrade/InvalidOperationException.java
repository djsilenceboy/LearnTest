
package com.djs.learn.flextrade;

public class InvalidOperationException extends RuntimeException
{
	public InvalidOperationException(String reason){
		super(reason);
	}
}
