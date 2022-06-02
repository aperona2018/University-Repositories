import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface InterfazServer extends Remote {
	/* Todos los métodos remotos deben estar aquí especificados.
	Todos ellos, además, levantan excepciones del tipo RemoteException. */
	public String getName () throws RemoteException;
	public void send (String s) throws RemoteException; 
}
