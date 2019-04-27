package common;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserInt extends Remote {
	
	public void register(String username, String password)throws RemoteException, IOException;
	public Boolean login(String username, String password)throws RemoteException, IOException;

}
