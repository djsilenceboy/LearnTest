
package com.djs.learn.interview.mars_rover;

/**
 * Grid map.
 */
public class GridMap
{
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;

	public GridMap(int minX, int minY, int maxX, int maxY){
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public int getMinX(){
		return minX;
	}

	public void setMinX(int minX){
		this.minX = minX;
	}

	public int getMinY(){
		return minY;
	}

	public void setMinY(int minY){
		this.minY = minY;
	}

	public int getMaxX(){
		return maxX;
	}

	public void setMaxX(int maxX){
		this.maxX = maxX;
	}

	public int getMaxY(){
		return maxY;
	}

	public void setMaxY(int maxY){
		this.maxY = maxY;
	}

	@Override
	public String toString(){
		return "GridMap [minX=" + minX + ", minY=" + minY + ", maxX=" + maxX + ", maxY=" + maxY + "]";
	}
}
