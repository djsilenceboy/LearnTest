
package com.djs.learn.scb;

/**
 * Direction.
 * <p>
 * North, East, South, West.
 */
public enum Direction
{
	N(0, 1), E(1, 1), S(2, -1), W(3, -1);

	int index;
	// Factor for multiply.
	int factor;

	private Direction(int index, int factor){
		this.index = index;
		this.factor = factor;
	}

	public int getIndex(){
		return index;
	}

	public int getFactor(){
		return factor;
	}
}
