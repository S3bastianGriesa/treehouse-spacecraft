package com.treehouse.spacecraft.core.data.entity;

import java.io.Serializable;

public abstract class AbstractMoveableEntity extends AbstractEntity implements MoveableEntity, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4801582320588265632L;
	private float moveSpeed, rotation;


	public AbstractMoveableEntity(double x, double y, double width, double height) {
		super(x, y, width, height);
		moveSpeed = 2f;
	}

	@Override
	public void update(long delta) {

	}

	public abstract EntityType getType();

	@Override
	public float getSpeed() {
		return this.moveSpeed;
	}
	
	public void setSpeed(float speed){
		this.moveSpeed = speed;
	}
	
	public float getRotation(){
		return this.rotation;
	}
	
	public void setRotation(float rotation){
		this.rotation = rotation;
	}
}
