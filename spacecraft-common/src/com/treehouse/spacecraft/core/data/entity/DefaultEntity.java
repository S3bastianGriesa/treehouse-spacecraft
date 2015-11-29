package com.treehouse.spacecraft.core.data.entity;

public class DefaultEntity extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4270207197248094915L;

	public DefaultEntity(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	@Override
	public void update(long delta) {
		
	}

}
