package com.treehouse.spacecraft.server;

import java.rmi.RemoteException;

public class ServerBoot {

	public static void main(String args[]) throws RemoteException{
		Server serv = new Server();
		serv.setPort(4711);
		serv.start();
	}
	
}
