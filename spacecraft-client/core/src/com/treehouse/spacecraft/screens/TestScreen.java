package com.treehouse.spacecraft.screens;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.treehouse.framework.engine.TreehouseGameScreen;
import com.treehouse.spacecraft.client.Client;
import com.treehouse.spacecraft.core.data.entity.Entity;
import com.treehouse.spacecraft.core.data.entity.MoveableEntity;
import com.treehouse.spacecraft.input.InputProcessor;
import com.treehouse.spacecraft.input.MenuProcessor;
import com.treehouse.spacecraft.input.MoveListener;
import com.treehouse.spacecraft.network.InputCommand;

public class TestScreen extends TreehouseGameScreen implements MoveListener{
	
	private Texture tex;
	private Texture background;
	private Sprite ship;
	private Entity lastEntity;
	private List<MoveableEntity> entities;
	private InputProcessor input;
	private Client client;
	private BitmapFont font;
	private float addRotation;
	private int fpscounter;
	private long lastFrame;
	private int fps;
	private long ping;
	private Engine engine;
	
	public TestScreen(Engine engine) {
		super(engine);
		this.engine = engine;
		entities = new LinkedList<MoveableEntity>();
	}
	
	private void getFPS() {
		fpscounter++;
		if ((System.currentTimeMillis() - lastFrame) >= 1000) {
			lastFrame = System.currentTimeMillis();
			fps = fpscounter;
			fpscounter = 0;
		}
	}
	
	@Override
	public void show() {
		font = new BitmapFont();
		client = new Client();
		client.setPort(engine.getPort());
		client.setAddress(engine.getIP());
		input = new InputProcessor(this);
		this.getInputMultiplexer().addProcessor(1, new MenuProcessor(engine));
		this.getInputMultiplexer().addProcessor(2, input);
		getAssetManager().load("spaceship.png", Texture.class);
		getAssetManager().load("spaceship_outline.png", Texture.class);
		getAssetManager().load("universe-6323.jpg", Texture.class);
		getAssetManager().finishLoading();
		tex = getAssetManager().get("spaceship_outline.png");
		background = getAssetManager().get("universe-6323.jpg");
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
		getFPS();
		this.ship.setRotation(this.ship.getRotation() + addRotation);
		if(this.ship.getRotation() >= 360f) this.ship.setRotation(0);
		if(client.isConnected()){
			try {
				InputCommand ic = input.getInputCommand();
				ic.setRotation(this.ship.getRotation());
				long start = System.currentTimeMillis();
				MoveableEntity e = client.getServer().updatePosition(ic, engine.getUser());
				updateSprite(e);
				this.entities = client.getServer().getEntities(engine.getUser());
				long end = System.currentTimeMillis();
				this.ping = end - start;
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			input.resetCommand();
		}
	}
	
	private void updateSprite(MoveableEntity e){
		this.getCamera().position.x = e.getX() + e.getWidth()/2;
		this.getCamera().position.y = e.getY() + e.getHeight()/2;
		ship.setPosition(e.getX(), e.getY());
		ship.setRotation(e.getRotation());
		lastEntity = e;
	}

	@Override
	protected void draw(float delta) {
		
		getSpriteBatch().begin();
		getSpriteBatch().draw(background,this.getCamera().position.x - this.getCamera().viewportWidth/2, this.getCamera().position.y - this.getCamera().viewportHeight/2);
		for(MoveableEntity e : entities){
			float deltaX = e.getX() - ship.getX();
			float deltaY = e.getY() - ship.getY();
			if(!(Math.abs(deltaX) > getCamera().viewportWidth/2 ) && !(Math.abs(deltaY) > getCamera().viewportHeight/2)){
			Sprite s = new Sprite(tex, 256, 256);
			s.setPosition(e.getX(), e.getY());
			s.setRotation(e.getRotation());
			s.setScale(0.25f);
			s.draw(getSpriteBatch());
			font.draw(getSpriteBatch(), e.getName(), e.getX() + e.getWidth()/2 - 20, e.getY() + e.getHeight()/2 + 40);
			}
			else{
				double distance =  Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
				double scaleX = Math.abs(deltaX/(getCamera().viewportWidth/2));
				double scaleY = Math.abs(deltaY/(getCamera().viewportHeight/2));
				double diff = Math.max(scaleX, scaleY);
				float x = (float) (deltaX/diff);
				float y = (float) (deltaY/diff);
				x = x*0.95f;
				y = y*0.95f;
				String dist = e.getName() + " " + Math.round(distance) + "m";
				font.draw(getSpriteBatch(), dist, ship.getX() + (ship.getWidth()/2) + x -dist.length()*Math.signum(x)*4 - dist.length()*4, ship.getY() + ship.getHeight()/2 + y);
			}
		}
		ship.draw(getSpriteBatch());
		font.draw(getSpriteBatch(), lastEntity.getName(), ship.getX() + ship.getWidth()/2 - 20, ship.getY() + ship.getHeight()/2 + 40);
		font.draw(getSpriteBatch(),fps + " fps", this.getCamera().position.x - this.getCamera().viewportWidth/2, this.getCamera().position.y + this.getCamera().viewportHeight/2);
		font.draw(getSpriteBatch(),ping + " ms", this.getCamera().position.x - this.getCamera().viewportWidth/2, this.getCamera().position.y + this.getCamera().viewportHeight/2 - 15);
		font.draw(getSpriteBatch(),Math.round(ship.getX()) + "," + Math.round(ship.getY()), this.getCamera().position.x - this.getCamera().viewportWidth/2, this.getCamera().position.y + this.getCamera().viewportHeight/2 - 30);
		getSpriteBatch().end();
	}

	@Override
	public void addRotation(float rot) {
		this.addRotation = rot;
	}

}
