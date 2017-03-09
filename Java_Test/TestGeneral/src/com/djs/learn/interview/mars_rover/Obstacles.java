
package com.djs.learn.interview.mars_rover;

import java.util.LinkedList;
import java.util.List;

/**
 * Obstacle positions.
 * <p>
 * A list of Integer[2].
 * x = Integer[0], y = Integer[1].
 */
public class Obstacles
{
	List<Integer[]> positions = new LinkedList<>();

	public Obstacles(List<Integer[]> positions){
		this.positions = positions;
	}

	public List<Integer[]> getPositions(){
		return positions;
	}

	public void setPositions(List<Integer[]> positions){
		this.positions = positions;
	}
}
