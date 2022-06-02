import java.rmi.*;
import java.rmi.server.*;
public class ChatClient extends UnicastRemoteObject implements InterfazClient{
	public String name;
	public InterfazClient client=null;
	
	
	public ChatClient(String n) throws RemoteException {
		this.name=n;
	}
	
	public String getName() throws RemoteException {
		return this.name;
	}
	
	public void setClient(InterfazClient c){
		client=c;
	}
	
	public InterfazClient getClient(){
		return client;
	}

	public void send(String s) throws RemoteException{
		System.out.println(s);
	}
}
