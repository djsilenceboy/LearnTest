
package com.djs.learn.interview.mars_rover;

/**
 * Command type.
 * <p>
 * Forward, Backward, Turn right, Turn left.
 */
public enum CommandType
{
	F('F', 1), B('B', -1), R('R', 1), L('L', -1);

	Character symbol;
	// Delta for one move.
	int delta;

	private CommandType(Character actionSymbol, int delta){
		this.symbol = actionSymbol;
		this.delta = delta;
	}

	public Character getSymbol(){
		return symbol;
	}

	public int getDelta(){
		return delta;
	}
}
