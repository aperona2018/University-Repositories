import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;


@SuppressWarnings({"deprecation", "unused"})
public class Client{
	public static void main(String args[]){
		try{
			System.setSecurityManager(new RMISecurityManager());
			String nombre = null;
			Scanner sc = new Scanner(System.in);
			boolean nombreValido = false;
			while (!nombreValido){
				try{
					System.out.print("Introduce tu username: ");
					nombre = sc.nextLine();
					nombreValido = true;
				} catch (Exception e){
					System.out.println("ERROR: Introduce un nombre válido");
					sc.next();
				}
			}
			InterfazClient client = new ChatClient("client");
			InterfazClient c = (InterfazClient) Naming.lookup("server");
			System.out.println("Estás en un chat con: " + c.getName());
			
			ChatServer s = new ChatServer(nombre);
			// Creación de registro:
			int puerto = Integer.parseInt(args[0]);
			LocateRegistry.createRegistry (puerto); // puerto
	
	
			Naming.rebind("client", s); // nombre del stub
			System.out.println("Chat atado para escribir al cliente");
			
			boolean fin = false;
			while (!fin){
				System.out.print("Escribe lo que quieras decir (salir para salir): ");
				String msg = sc.nextLine();
				if (msg.equals("salir")){
					System.out.println("Saliendo...");
					fin = true;
				} else{
					msg = nombre + ": " + msg; 
					c.send(msg);
				}
			}

		
		} catch (Exception e){
			System.err.println("¡Excepción en Chat!");
      		e.printStackTrace();
		}
	
	}
}
