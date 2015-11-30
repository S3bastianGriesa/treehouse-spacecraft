package com.treehouse.spacecraft.screens;

import com.treehouse.framework.engine.TreehouseGameEngine;

public class Engine extends TreehouseGameEngine{
	

	@Override
	public void create() {
		super.create();
		TestScreen screen = new TestScreen(this);
		this.setScreen(screen);
	}
	

}
