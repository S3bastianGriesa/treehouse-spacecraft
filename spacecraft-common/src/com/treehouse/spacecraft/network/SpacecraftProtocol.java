package com.treehouse.spacecraft.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.treehouse.spacecraft.core.data.entity.MoveableEntity;

public interface SpacecraftProtocol extends Remote{
	
	public MoveableEntity updatePosition(InputCommand ic) throws RemoteException; 

}
