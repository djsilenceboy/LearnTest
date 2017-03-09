
package com.djs.learn.interview;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

/**
 * From JPM.
 * Programming test: Mars Rover.
 * <p>
 * Requirement:<br>
 * Develop an API that moves a rover around on a grid.
 * <p>
 * Rules:<br>
 * You are given the initial starting point (x,y) of a rover and the direction (N,S,E,W) it is facing.
 * The rover receives a character array of commands.
 * Implement commands that move the rover forward/backward (F,B).
 * Implement commands that turn the rover left/right (L,R).
 * Implement wrapping from one edge of the grid to another. (planets are spheres after all)
 */
public class MarsRover
{
	public static class Rover
	{
		private int x;
		private int y;
		private int direction;

		public Rover(int x, int y, int direction){
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

		public int getDirection(){
			return direction;
		}

		public void setDirection(int direction){
			this.direction = direction;
		}
	}

	public static int checkRange(int currentValue, int min, int max){
		if (currentValue < min) {
			currentValue = max;
		} else if (currentValue > max) {
			currentValue = min;
		}

		return currentValue;
	}

	@Test
	public void test(){
		int gridXLength = 10;
		int gridYLength = 10;

		// Direction index = 0, 1, 2, 3 = "N", "E", "S", "W"
		// <Direction index, Factor>
		Map<Integer, Integer> directionFactors = new TreeMap<Integer, Integer>();
		directionFactors.put(0, 1);
		directionFactors.put(1, 1);
		directionFactors.put(2, -1);
		directionFactors.put(3, -1);

		// <Command symbol, Delta move>
		Map<Character, Integer> deltaMove = new TreeMap<Character, Integer>();
		deltaMove.put('f', 1);
		deltaMove.put('b', -1);
		deltaMove.put('r', 1);
		deltaMove.put('l', -1);

		Rover rover = new Rover(0, 0, 0);

		Character[] commands = new Character[]{'f', 'f', 'r', 'f', 'f'};

		for (Character command : commands) {
			switch (command){
				case 'f':
				case 'b':
					if ((rover.getDirection() == 1) || (rover.getDirection() == 3)) {
						int newX = checkRange(rover.getX() + directionFactors.get(rover.getDirection()) * deltaMove.get(command), 0, gridXLength - 1);

						rover.setX(newX);
					} else {
						int newY = checkRange(rover.getY() + directionFactors.get(rover.getDirection()) * deltaMove.get(command), 0, gridYLength - 1);

						rover.setY(newY);
					}
				break;
				case 'r':
				case 'l':
					int newDirction = checkRange(rover.getDirection() + deltaMove.get(command), 0, 3);

					rover.setDirection(newDirction);
				break;
			}
		}

		assertEquals(rover.getX(), 2);
		assertEquals(rover.getY(), 2);
		assertEquals(rover.getDirection(), 1);
	}
}
