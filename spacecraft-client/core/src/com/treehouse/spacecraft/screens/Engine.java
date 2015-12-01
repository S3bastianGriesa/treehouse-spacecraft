package com.treehouse.spacecraft.screens;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.treehouse.framework.engine.TreehouseGameEngine;
import com.treehouse.spacecraft.input.MenuListener;

public class Engine extends TreehouseGameEngine implements MenuListener{
	
	private Properties config;
	
	public Engine(){
		config = new Properties();
		try {
			config.load(new FileInputStream(new File("properties//config.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void create() {
		super.create();
		Gdx.input.setInputProcessor(this.getInputMultiplexer());
		goMenu();
	}
	
	public String getUser(){
		return config.getProperty("user");
	}
	
	public void setUser(String user){
		config.setProperty("user", user);
		saveConfig();
	}
	
	private void saveConfig(){
		try {
			config.store(new FileOutputStream(new File("properties//config.properties")), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getPort(){
		return Integer.valueOf(config.getProperty("port"));
	}
	
	public void setPort(int port){
		config.setProperty("port", port +"");
		saveConfig();
	}
	
	public String getIP(){
		return config.getProperty("ip");
	}
	
	public void setIP(String ip){
		config.setProperty("ip", ip);
		saveConfig();
	}

	@Override
	public void goMenu() {
		MenuScreen screen = new MenuScreen(this);
		this.setScreen(screen);
	}
	

}
