package com.treehouse.spacecraft.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.treehouse.spacecraft.network.SpacecraftProtocol;

public class Client {

	private SpacecraftProtocol server;
	private Registry registry;
	private int port;
	private String address;

	public void connect() throws RemoteException, NotBoundException {
		registry = LocateRegistry.getRegistry(address, port);
		server = (SpacecraftProtocol) (registry.lookup("rmiServer"));
	}

	public boolean isConnected() {
		return (server != null && registry != null);
	}

	public void disconnect() {
		registry = null;
		server = null;
	}

	public SpacecraftProtocol getServer() {
		return server;
	}

	public void setServer(SpacecraftProtocol server) {
		this.server = server;
	}

	public Registry getRegistry() {
		return registry;
	}

	public void setRegistry(Registry registry) {
		this.registry = registry;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
