package com.treehouse.spacecraft.input;

import com.badlogic.gdx.Input.Keys;
import com.treehouse.framework.input.KeyboardInputProcessor;

public class MenuProcessor  extends KeyboardInputProcessor{
	
	private MenuListener listener;
	
	public MenuProcessor(MenuListener menu){
		this.listener = menu;
	}
	
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.ESCAPE){
			listener.goMenu();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

}
