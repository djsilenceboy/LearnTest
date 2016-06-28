
package dj.test.jpmorgan;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class TestMain3a
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
		// Direction index = 0, 1, 2, 3 = "N", "E", "S", "W"
		int gridXLength = 100;
		int gridYLength = 100;
		Map<Character, Integer> delta = new TreeMap<Character, Integer>();
		delta.put('f', 1);
		delta.put('b', -1);
		delta.put('r', 1);
		delta.put('l', -1);

		Rover rover = new Rover(0, 0, 0);

		Character[] actions = new Character[]{'f', 'f', 'r', 'f', 'f'};

		for (Character action : actions) {
			switch (action){
				case 'f':
				case 'b':
					if ((rover.getDirection() == 1) || (rover.getDirection() == 3)) {
						int newX = checkRange(rover.getX() + delta.get(action), 0, gridXLength - 1);

						rover.setX(newX);
					} else {
						int newY = checkRange(rover.getY() + delta.get(action), 0, gridYLength - 1);

						rover.setY(newY);
					}
				break;
				case 'r':
				case 'l':
					int newDirction = checkRange(rover.getDirection() + delta.get(action), 0, 3);

					rover.setDirection(newDirction);
				break;
			}
		}

		assertEquals(rover.getX(), 2);
		assertEquals(rover.getY(), 2);
		assertEquals(rover.getDirection(), 1);
	}
}
