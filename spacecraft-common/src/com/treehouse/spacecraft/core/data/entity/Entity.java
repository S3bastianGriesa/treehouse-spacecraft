package com.treehouse.spacecraft.core.data.entity;

import java.awt.Rectangle;

public interface Entity {
	public void update(long delta);

	public void setLocation(float x, float y);
	
	public void setID(long id);
	
	public long getID();
	
	public String getName();
	
	public void setName(String name);

	public void setX(float x);

	public void setY(float y);

	public void setWidth(float width);

	public void setHeight(float height);

	public float getX();

	public float getY();

	public float getHeight();

	public float getWidth();

	public boolean intersects(Entity other);

	public Rectangle getHitbox();
}
