
package com.djs.learn.interview.mars_rover;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MarsRoverTest
{
	private MarsRover rover;
	private GridMap gridMap;
	private Obstacles obstacles;
	private MarsRoverController roverController;

	@Before
	public void setUp() throws Exception{
		rover = new MarsRover(0, 0, Direction.N);
		gridMap = new GridMap(0, 0, 9, 9);

		List<Integer[]> positions = new LinkedList<>();
		positions.add(new Integer[]{3, 1});
		obstacles = new Obstacles(positions);

		roverController = new MarsRoverController(rover, gridMap, obstacles);
	}

	@After
	public void tearDown() throws Exception{
	}

	@Test
	public void test_move_1(){
		Character[] commands = {'F', 'F', 'R', 'F', 'F'};

		System.out.println("Init = " + rover);
		for (Character command : commands) {
			roverController.move(command);
			System.out.println("Move = " + rover);
		}

		assertEquals(rover.getX(), 2);
		assertEquals(rover.getY(), 2);
		assertEquals(rover.getDirection(), Direction.E);
	}

	@Test
	public void test_move_2(){
		Character[] commands = {'F', 'F', 'L', 'F', 'F'};

		System.out.println("Init = " + rover);
		for (Character command : commands) {
			roverController.move(command);
			System.out.println("Move = " + rover);
		}

		assertEquals(rover.getX(), 8);
		assertEquals(rover.getY(), 2);
		assertEquals(rover.getDirection(), Direction.W);
	}

	@Test
	public void test_move_3(){
		Character[] commands = {'F', 'R', 'F', 'L', 'F', 'L', 'B'};

		System.out.println("Init = " + rover);
		for (Character command : commands) {
			roverController.move(command);
			System.out.println("Move = " + rover);
		}

		assertEquals(rover.getX(), 2);
		assertEquals(rover.getY(), 2);
		assertEquals(rover.getDirection(), Direction.W);
	}

	@Test
	public void test_detect_1(){
		Character[] commands = {'F', 'R', 'F', 'F', 'F', 'F'};

		System.out.println("Init = " + rover);
		for (Character command : commands) {
			if (roverController.detectObstacle(command)) {
				System.out.println("Detect Obstacle");
				break;
			}

			roverController.move(command);
			System.out.println("Move = " + rover);
		}

		assertEquals(rover.getX(), 2);
		assertEquals(rover.getY(), 1);
		assertEquals(rover.getDirection(), Direction.E);
	}

	@Test
	public void test_detect_2(){
		Character[] commands = {'R', 'F', 'F', 'F', 'L', 'F'};

		System.out.println("Init = " + rover);
		for (Character command : commands) {
			if (roverController.detectObstacle(command)) {
				System.out.println("Detect Obstacle");
				break;
			}

			roverController.move(command);
			System.out.println("Move = " + rover);
		}

		assertEquals(rover.getX(), 3);
		assertEquals(rover.getY(), 0);
		assertEquals(rover.getDirection(), Direction.N);
	}
}
