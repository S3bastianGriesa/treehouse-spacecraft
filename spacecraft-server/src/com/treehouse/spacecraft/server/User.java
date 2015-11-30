package com.treehouse.spacecraft.server;

import com.treehouse.spacecraft.core.data.entity.DefaultMoveableEntity;
import com.treehouse.spacecraft.core.data.entity.MoveableEntity;
import com.treehouse.spacecraft.network.InputCommand;

public class User {

	private String name;
	private MoveableEntity e;

	public User(String user) {
		this.name = user;
		this.e = new DefaultMoveableEntity(200, 200, 256, 256);
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
			e.setX(e.getX() - x * e.getSpeed());
			e.setY(e.getY() - y * -e.getSpeed());

		}
		
	}

}
