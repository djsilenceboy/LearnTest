
package com.djs.learn.interview.mars_rover;

/**
 * Mars rover.
 */
public class MarsRover
{
	private int x;
	private int y;
	private Direction direction;

	public MarsRover(int x, int y, Direction direction){
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public int getX(){
		return x;
	}

	public void setX(int x){
		this.x = x;
	}

	public int getY(){
		return y;
	}

	public void setY(int y){
		this.y = y;
	}

	public Direction getDirection(){
		return direction;
	}

	public void setDirection(Direction direction){
		this.direction = direction;
	}

	@Override
	public String toString(){
		return "MarsRover [x=" + x + ", y=" + y + ", direction=" + direction + "]";
	}
}
