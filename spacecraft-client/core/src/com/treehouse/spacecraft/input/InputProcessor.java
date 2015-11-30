package com.treehouse.spacecraft.input;

import com.badlogic.gdx.Input.Keys;
import com.treehouse.framework.input.KeyboardInputProcessor;
import com.treehouse.spacecraft.network.InputCommand;

public class InputProcessor extends KeyboardInputProcessor{
	
	private InputCommand command;
	private MoveListener listener;
	
	public InputProcessor(MoveListener listener){
		this.listener = listener;
		command = new InputCommand();
		resetCommand();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(Keys.W == keycode){
			command.setIncreaseVelocity(true);
		}
		else if(Keys.A == keycode){
			listener.addRotation(1);
		}
		else if(Keys.D == keycode){
			listener.addRotation(-1);
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(Keys.W == keycode){
			command.setIncreaseVelocity(false);
		}
		else if(Keys.A == keycode || Keys.D == keycode){
			listener.addRotation(0);
		}

		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void resetCommand(){
//		command.setRotation(0);
//		command.setIncreaseVelocity(false);
	}
	
	public InputCommand getInputCommand(){
		return this.command;
	}

}
