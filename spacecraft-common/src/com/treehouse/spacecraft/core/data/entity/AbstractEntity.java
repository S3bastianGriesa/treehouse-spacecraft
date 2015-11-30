package com.treehouse.spacecraft.core.data.entity;

import java.awt.Rectangle;
import java.io.Serializable;

public abstract class AbstractEntity implements Entity, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2126435635996441409L;
	protected float x, y, width, height;
	protected Rectangle hitbox;
	protected long id;
	protected String name;

	public AbstractEntity(float x, float y, float width, float height) {
		hitbox = new Rectangle();
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	
	@Override
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public String getName(){
		return this.name;
	}
	
	@Override
	public void setLocation(float x, float y) {
		setX(x);
		setY(y);
	}

	@Override
	public void setX(float x) {
		this.x = x;
		hitbox.x = (int) x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
		hitbox.y = (int) y;
	}

	@Override
	public void setWidth(float width) {
		this.width = width;
		hitbox.width = (int) width;
	}

	@Override
	public void setHeight(float height) {
		this.height = height;
		hitbox.height = (int) height;
	}

	@Override
	public float getX() {
		return this.x;
	}

	@Override
	public float getY() {
		return this.y;
	}

	@Override
	public float getHeight() {
		return this.height;
	}

	@Override
	public float getWidth() {
		return this.width;
	}

	@Override
	public Rectangle getHitbox() {
		return this.hitbox;
	}

	@Override
	public boolean intersects(Entity other) {
		return hitbox.intersects(other.getHitbox());
	}

	public boolean intersects(int x, int y) {
		return hitbox.contains(x, y);
	}
	
	@Override
	public long getID(){
		return this.id;
	}
	
	@Override
	public void setID(long id){
		this.id = id;
	}
}
