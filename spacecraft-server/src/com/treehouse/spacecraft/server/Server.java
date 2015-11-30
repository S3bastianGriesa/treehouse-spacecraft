package com.treehouse.spacecraft.server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.treehouse.spacecraft.core.data.entity.MoveableEntity;
import com.treehouse.spacecraft.network.InputCommand;
import com.treehouse.spacecraft.network.SpacecraftProtocol;

public class Server extends UnicastRemoteObject implements SpacecraftProtocol {

	private static final long serialVersionUID = -8368134709255563426L;
	private int port;
	private Registry registry;
	private HashMap<String, User> users;

	protected Server() throws RemoteException {
		super();
		users = new HashMap<String, User>();
	}

	public void start() throws RemoteException {
		registry = LocateRegistry.createRegistry(port);
		registry.rebind("rmiServer", this);
		System.out.println("listening on: " + port);
	}

	public void shutDown() throws NotBoundException, RemoteException {
		registry.unbind("rmiServer");
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Registry getRegistry() {
		return registry;
	}

	public void setRegistry(Registry registry) {
		this.registry = registry;
	}

	@Override
	public MoveableEntity updatePosition(InputCommand ic, String user) throws RemoteException {
		if (user == null || user.isEmpty()) {
			try {
				user = getClientHost();
			} catch (ServerNotActiveException e) {
				e.printStackTrace();
			}
		}
		if (!users.containsKey(user)) {
			users.put(user, new User(user));
			System.out.println("User connected: " + user);
		}
		User u = users.get(user);
		u.updateEntity(ic);
		return u.getEntity();
	}

	@Override
	public List<MoveableEntity> getEntities(String user) throws RemoteException {
		if (user == null || user.isEmpty()) {
			try {
				user = getClientHost();
			} catch (ServerNotActiveException e1) {
				e1.printStackTrace();
			}
		}
		LinkedList<MoveableEntity> entities = new LinkedList<MoveableEntity>();
		for (Entry<String, User> e : users.entrySet()) {
			if (!e.getValue().getName().equals(user) && e.getValue().isConnected()) {
				entities.add(e.getValue().getEntity());
			}
		}
		return entities;
	}

}
