package com.mygdx.game;

/**
 * Location class representing a location in a grid
 */
public class Location {
	/**
	 * Coordinate x
	 */
	private float x;
	/**
	 * Coordinate y
	 */
	private float y;

	public Location(float x, float y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Returns true if location is equal, false otherwise
	 * @param obj
	 * @return boolean
	 */
	public boolean equals(Object obj) {
		if(!( obj instanceof Location)) {
			return false;
		}
		Location other= (Location)obj;
		return other.getX()==x&&other.getY()==y;
	}

	/**
	 * Returns x coordinate
	 * @return x
	 */
	public float getX() {
		return x;
	}

	/**
	 * Set x coordinate
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Return y coordinate
	 * @return y
	 */
	public float getY() {
		return y;
	}

	/**
	 * Set y coordinate
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Set new location
	 * @param x
	 * @param y
	 */
	public void setLocation(float x,float y) {
		setX(x);
		setY(y);
	}

	/**
	 * Get above location from this location
	 * @return Location
	 */
	public Location aboveLocation() {
		return new Location(getX(),getY()-64);
	}

	/**
	 * Get below location from this location
	 * @return Location
	 */
	public Location belowLocation() {
		return new Location(getX(),getY()+64);
	}

	/**
	 * Get left location from this location
	 * @return Location
	 */
	public Location leftLocation() {
		return new Location(getX()-64,getY());
	}

	/**
	 * Get right location from this location
	 * @return Location
	 */
	public Location rightLocation() {
		return new Location(getX()+64,getY());
	}
}
