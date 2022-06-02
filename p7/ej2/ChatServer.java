import java.rmi.*;
import java.rmi.server.*;
public class ChatServer extends UnicastRemoteObject implements InterfazServer{
	public String name;
	public InterfazServer server=null;
	
	
	public ChatServer(String n) throws RemoteException {
		this.name=n;
	}
	
	public String getName() throws RemoteException {
		return this.name;
	}
	
	public void setServer(InterfazServer s){
		server=s;
	}
	
	public InterfazServer getServer(){
		return server;
	}

	public void send(String s) throws RemoteException{
		System.out.println(s);
	}
}
