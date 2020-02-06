package com.mygdx.game;

public class Location {
	float x;
	float y;

	public boolean equals(Object obj) {
		
		if(!( obj instanceof Location))
		{
			return false;
		}
		Location other= (Location)obj;
		
		
		return other.getX()==x&&other.getY()==y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public Location(float x, float y) {
		
		this.x = x;
		this.y = y;
	}

	public void setY(float y) {
		this.y = y;
	}
	public void setLocation(float x,float y) {
		setX(x);
		setY(y);
	}
	//need to check
	public Location Transpose(float x, float y) {
		return new Location(this.x+x,this.y+y);
	}
}
