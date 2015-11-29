package com.treehouse.spacecraft.network;

import java.io.Serializable;

public class InputCommand implements Serializable {

	private static final long serialVersionUID = 6786102974214499106L;
	private boolean increaseVelocity;
	private float rotation;

	public boolean isIncreaseVelocity() {
		return increaseVelocity;
	}

	public void setIncreaseVelocity(boolean increaseVelocity) {
		this.increaseVelocity = increaseVelocity;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

}
