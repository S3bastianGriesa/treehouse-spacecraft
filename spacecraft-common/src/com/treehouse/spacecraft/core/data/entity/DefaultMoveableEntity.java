package com.treehouse.spacecraft.core.data.entity;

public class DefaultMoveableEntity extends AbstractMoveableEntity{

	private static final long serialVersionUID = -6890649368481619422L;

	public DefaultMoveableEntity(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	@Override
	public EntityType getType() {
		return EntityType.TEST;
	}

}
