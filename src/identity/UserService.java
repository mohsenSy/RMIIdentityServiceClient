package identity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import common.UserInt;

public class UserService {
	
	private String[] urls;
	private UserInt user;
	
	public UserService() {
		urls = new String[2];
		urls[0] = "rmi://localhost/userService0";
		urls[1] = "rmi://localhost/userService1";
		user = null;
	}
	public UserService(String[] u) {
		urls = u;
		user = null;
	}
	
	private void connectUser() throws RemoteException {
		for (String url : urls) {
			try {
				user = (UserInt)Naming.lookup(url);	
			} catch (NotBoundException | MalformedURLException e) {
				user = null;
			}	
		}
	}
	
	public void register(String username, String password) throws IOException, ServerException {
		if (user == null) {
			try {
				connectUser();	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				user = null;
				throw new ServerException();
			}
		}
		user.register(username, password);
	}
	
	public Boolean login(String username, String password) throws IOException, ServerException {
		if (user == null) {
			try {
				connectUser();
			} catch (Exception e) {
				// TODO: handle exception
				user = null;
				throw new ServerException();
			}
		}
		return user.login(username, password);
	}

}
