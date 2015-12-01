package com.treehouse.spacecraft.server;

import com.treehouse.spacecraft.core.data.entity.DefaultMoveableEntity;
import com.treehouse.spacecraft.core.data.entity.MoveableEntity;
import com.treehouse.spacecraft.network.InputCommand;

public class User {

	private String name;
	private MoveableEntity e;
	private long lastPing;

	public User(String user) {
		this.name = user;
		this.e = new DefaultMoveableEntity(200, 200, 256, 256);
		e.setName(user);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public MoveableEntity getEntity(){
		return this.e;
	}
	
	public void updateEntity(InputCommand ic){
		e.setRotation(ic.getRotation());
		if(ic.isIncreaseVelocity()){
			float x =(float) Math.sin(Math.toRadians(e.getRotation()));
			float y = (float) Math.cos(Math.toRadians(e.getRotation()));		
			e.setX(e.getX() - x * e.getSpeed()*3);
			e.setY(e.getY() - y * -e.getSpeed()*3);

		}
		lastPing = System.currentTimeMillis();
	}
	
	public boolean isConnected(){
		return ((System.currentTimeMillis() - lastPing) < 1000);
	}

}
