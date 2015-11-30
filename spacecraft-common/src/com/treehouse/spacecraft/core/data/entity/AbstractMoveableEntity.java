package com.treehouse.spacecraft.core.data.entity;

import java.io.Serializable;
import java.util.Vector;

public abstract class AbstractMoveableEntity extends AbstractEntity implements MoveableEntity, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4801582320588265632L;
	private float moveSpeed, rotation;
	private Vector<Float> velocity;

	public AbstractMoveableEntity(double x, double y, double width, double height) {
		super(x, y, width, height);
		moveSpeed = 2f;
	}

	@Override
	public void update(long delta) {

	}

	@Override
	public float getSpeed() {
		return this.moveSpeed;
	}

	@Override
	public void setSpeed(float speed) {
		this.moveSpeed = speed;
	}

	@Override
	public float getRotation() {
		return this.rotation;
	}

	@Override
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	@Override
	public Vector<Float> getVelocity() {
		return velocity;
	}

	@Override
	public void setVelocity(Vector<Float> velo) {
		this.velocity = velo;
	}
}
