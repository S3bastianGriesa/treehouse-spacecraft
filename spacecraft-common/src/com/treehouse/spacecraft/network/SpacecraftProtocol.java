package com.treehouse.spacecraft.network;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.treehouse.spacecraft.core.data.entity.MoveableEntity;

public interface SpacecraftProtocol extends Remote{
	
	public MoveableEntity updatePosition(InputCommand ic, String user) throws RemoteException; 
	
	public List<MoveableEntity> getEntities(String user) throws RemoteException;
	

}
