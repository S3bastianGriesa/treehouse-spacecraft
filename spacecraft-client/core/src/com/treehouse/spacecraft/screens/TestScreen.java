package com.treehouse.spacecraft.screens;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.treehouse.framework.engine.TreehouseGameEngine;
import com.treehouse.framework.engine.TreehouseGameScreen;
import com.treehouse.spacecraft.client.Client;
import com.treehouse.spacecraft.core.data.entity.MoveableEntity;
import com.treehouse.spacecraft.input.InputProcessor;
import com.treehouse.spacecraft.input.MoveListener;
import com.treehouse.spacecraft.network.InputCommand;

public class TestScreen extends TreehouseGameScreen implements MoveListener{
	
	private Texture tex;
	private Sprite ship;
	private InputProcessor input;
	private Client client;
	private float addRotation;
	
	public TestScreen(TreehouseGameEngine engine) {
		super(engine);
	}

	@Override
	public void show() {
		client = new Client();
		client.setPort(4711);
		client.setAddress("192.168.0.11");
		Gdx.input.setInputProcessor(this.getInputMultiplexer());
		input = new InputProcessor(this);
		this.getInputMultiplexer().addProcessor(1, input);
		getAssetManager().load("spaceship.png", Texture.class);
		getAssetManager().finishLoading();
		tex = getAssetManager().get("spaceship.png");
		ship = new Sprite(tex, 256, 256);
		ship.setScale(0.25f);
		ship.setPosition(200, 200);
		try {
			client.connect();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void update(float delta) {
		this.ship.setRotation(this.ship.getRotation() + addRotation);
		if(this.ship.getRotation() >= 360f) this.ship.setRotation(0);
		if(client.isConnected()){
			try {
				InputCommand ic = input.getInputCommand();
				ic.setRotation(this.ship.getRotation());
				MoveableEntity e = client.getServer().updatePosition(ic);
				updateSprite(e);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			input.resetCommand();
		}
	}
	
	private void updateSprite(MoveableEntity e){
		ship.setPosition(e.getX(), e.getY());
		ship.setRotation(e.getRotation());
	}

	@Override
	protected void draw(float delta) {
		getSpriteBatch().begin();
		ship.draw(getSpriteBatch());
		getSpriteBatch().end();
	}

	@Override
	public void addRotation(float rot) {
		this.addRotation = rot;
	}

}
