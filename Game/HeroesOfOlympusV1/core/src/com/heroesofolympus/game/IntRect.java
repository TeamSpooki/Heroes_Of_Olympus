package com.heroesofolympus.game;

public class IntRect {
	private float x;
	
	private float y;
	
	private float width;
	
	private float height;

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
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


	public IntRect(float f, float g, int width, int height) {
		super();
		this.x = f;
		this.y = g;
		this.setWidth(width);
		this.height = height;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}
}
