
package com.djs.learn.interview.mars_rover;

import java.util.Map;
import java.util.TreeMap;

/**
 * Mars rover controller.
 */
public class MarsRoverController
{
	private MarsRover rover;
	private GridMap gridMap;
	private Obstacles obstacles;

	// Find direction by index.
	private Map<Integer, Direction> directions = new TreeMap<Integer, Direction>();
	// Find command by symbol.
	private Map<Character, CommandType> commandTypes = new TreeMap<Character, CommandType>();

	/**
	 * Constructor.
	 *
	 * @param MarsRover
	 *        rover
	 * @param GridMap
	 *        gridMap
	 * @param Obstacles
	 *        obstacles
	 */
	public MarsRoverController(MarsRover rover, GridMap gridMap, Obstacles obstacles){
		this.rover = rover;
		this.gridMap = gridMap;
		this.obstacles = obstacles;

		directions.put(Direction.N.getIndex(), Direction.N);
		directions.put(Direction.E.getIndex(), Direction.E);
		directions.put(Direction.S.getIndex(), Direction.S);
		directions.put(Direction.W.getIndex(), Direction.W);

		commandTypes.put(CommandType.F.getSymbol(), CommandType.F);
		commandTypes.put(CommandType.B.getSymbol(), CommandType.B);
		commandTypes.put(CommandType.R.getSymbol(), CommandType.R);
		commandTypes.put(CommandType.L.getSymbol(), CommandType.L);
	}

	/**
	 * Check range.
	 * <p>
	 * Reset value if cross min/max range.
	 *
	 * @param int
	 *        currentValue
	 * @param int
	 *        min
	 * @param int
	 *        max
	 * @return int
	 *         New value.
	 */
	private int checkRange(int currentValue, int min, int max){
		if (currentValue < min) {
			currentValue = max;
		} else if (currentValue > max) {
			currentValue = min;
		}

		return currentValue;
	}

	/**
	 * Move.
	 *
	 * @param Character
	 *        command
	 */
	public void move(Character command){
		CommandType commandType = commandTypes.get(command);

		switch (commandType){
			case F:
			case B:
				if ((rover.getDirection() == Direction.E) || (rover.getDirection() == Direction.W)) {
					int newX = checkRange(rover.getX() + rover.getDirection().getFactor() * commandType.getDelta(), gridMap.getMinX(), gridMap.getMaxX());

					rover.setX(newX);
				} else { // Direction.N and Direction.S
					int newY = checkRange(rover.getY() + rover.getDirection().getFactor() * commandType.getDelta(), gridMap.getMinY(), gridMap.getMaxY());

					rover.setY(newY);
				}
			break;
			case R:
			case L:
				int newDirectionIndex = checkRange(rover.getDirection().getIndex() + commandType.getDelta(), Direction.N.getIndex(), Direction.W.getIndex());

				rover.setDirection(directions.get(newDirectionIndex));
			break;
		}
	}

	/**
	 * Detect obstacle.
	 * <p>
	 * Not move yet.
	 *
	 * @param Character
	 *        command
	 * @return boolean
	 *         true: find obstacle
	 *         false: not find obstacle
	 */
	public boolean detectObstacle(Character command){
		boolean find = false;
		CommandType commandType = commandTypes.get(command);

		// Only check "F" and "B" actions.
		if ((commandType == CommandType.F) || (commandType == CommandType.B)) {
			int newX = rover.getX();
			int newY = rover.getY();

			// Get new position.
			if ((rover.getDirection() == Direction.E) || (rover.getDirection() == Direction.W)) {
				newX = checkRange(rover.getX() + rover.getDirection().getFactor() * commandType.getDelta(), gridMap.getMinX(), gridMap.getMaxX());
			} else { // Direction.N and Direction.S
				newY = checkRange(rover.getY() + rover.getDirection().getFactor() * commandType.getDelta(), gridMap.getMinY(), gridMap.getMaxY());
			}

			// Compare with obstacle positions.
			for (Integer[] position : obstacles.getPositions()) {
				if ((newX == position[0]) && (newY == position[1])) {
					find = true;
					break;
				}
			}
		}

		return find;
	}
}
