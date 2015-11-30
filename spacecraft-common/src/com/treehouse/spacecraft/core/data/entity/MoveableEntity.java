package com.treehouse.spacecraft.core.data.entity;

import java.util.Vector;

public interface MoveableEntity extends Entity {
	public EntityType getType();

	public float getSpeed();
	
	public void setSpeed(float speed);
	
	public Vector<Float> getVelocity();
	
	public void setVelocity(Vector<Float> vel);
	
	public float getRotation();
	
	public void setRotation(float rotation);
}
