package com.treehouse.spacecraft.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.treehouse.framework.engine.TreehouseGameScreen;

public class MenuScreen extends TreehouseGameScreen {

	private static final float BUTTON_WIDTH = 300f;
	private static final float BUTTON_HEIGHT = 20f;
	private static final float BUTTON_SPACING = 5f;
	private Stage stage;
	private Texture background;
	private TextButton startButton, optionButton, endButton;
	private Engine engine;

	public MenuScreen(Engine engine) {
		super(engine);
		this.engine = engine;
	}

	@Override
	public void show() {
		getAssetManager().load("universe_planet_circle_star_65146_3840x2160.jpg", Texture.class);
		getAssetManager().finishLoading();
		background = getAssetManager().get("universe_planet_circle_star_65146_3840x2160.jpg");

	}

	@Override
	public void resize(int x, int y) {
		super.resize(x, y);
		float width = this.getCamera().viewportWidth;
		final float buttonX = (width - BUTTON_WIDTH) / 2;
		float currentY = this.getCamera().viewportHeight/6;
		stage = new Stage(new ScreenViewport());
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		startButton = new TextButton("Connect", skin);
		startButton.setX(buttonX);
		startButton.setY(currentY);
		final Dialog dialog = new Dialog("Options", skin);
		final TextField name = new TextField("Name: " + engine.getUser(), skin);
		final TextField ip = new TextField("IP: " + engine.getIP(), skin);
		final TextField port = new TextField("Port: " + engine.getPort(), skin);
		TextButton endOptions = new TextButton("Close", skin);
		endOptions.addListener(new ClickListener(){
			  @Override
		         public void clicked(InputEvent event, float x, float y){
				  engine.setUser(name.getText().substring(6));
				  engine.setIP(ip.getText().substring(4));
				  engine.setPort(Integer.valueOf(port.getText().substring(6)));
				  dialog.hide();
			  }
		});
		dialog.add(name);
		dialog.add(ip);
		dialog.add(port);
		
		dialog.add(endOptions);
		
		startButton.setWidth(BUTTON_WIDTH);
		startButton.setHeight(BUTTON_HEIGHT);
		stage.addActor(startButton);
		startButton.addListener(new ClickListener(){
			  @Override
		         public void clicked(InputEvent event, float x, float y){
				  engine.setScreen(new TestScreen(engine));
			  }
		});

		optionButton = new TextButton("Options", skin);
		optionButton.setX(buttonX);
		optionButton.setY(currentY -= BUTTON_HEIGHT + BUTTON_SPACING);
		optionButton.setWidth(BUTTON_WIDTH);
		optionButton.setHeight(BUTTON_HEIGHT);
		optionButton.addListener(new ClickListener(){
			  @Override
		         public void clicked(InputEvent event, float x, float y){
				  dialog.show(stage);
			  }
		});
		stage.addActor(optionButton);

		endButton = new TextButton("End", skin);
		endButton.setX(buttonX);
		endButton.setY(currentY -= BUTTON_HEIGHT + BUTTON_SPACING);
		endButton.setWidth(BUTTON_WIDTH);
		endButton.setHeight(BUTTON_HEIGHT);
		endButton.addListener(new ClickListener(){
			  @Override
		         public void clicked(InputEvent event, float x, float y){
				 	Gdx.app.exit();
			  }
		});
		stage.addActor(endButton);
		this.getInputMultiplexer().addProcessor(0, stage);
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
		// TODO Auto-generated method stub

	}

	@Override
	protected void draw(float delta) {
		getSpriteBatch().begin();
		getSpriteBatch().draw(background, getCamera().position.x - getCamera().viewportWidth / 2,
				getCamera().position.y - getCamera().viewportHeight / 2, getCamera().viewportWidth,
				getCamera().viewportHeight);
		getSpriteBatch().end();
		stage.act(delta);
		stage.draw();
	}

}
