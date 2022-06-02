import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Server {

	public static void main(String args[]){
		try {
			ArrayList<InterfazServer> listaClientes = new ArrayList<InterfazServer>();
			// Carga dinámica de clases (ver descripción en Client.java)
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

			ChatClient c = new ChatClient(nombre);
			// Creación de registro:
			LocateRegistry.createRegistry (Registry.REGISTRY_PORT); // puerto
			
			
			Naming.rebind("server", c); // nombre del stub
			System.out.println("Chat atado para recibir del cliente ");
			
			System.out.print("Pulsa cualquier tecla cuando el cliente se haya conectado: ");
			sc.nextLine();
		
		
		
			boolean fin = false;
			boolean yaEsta = false;
			while (!fin){
				InterfazServer server = new ChatServer(nombre);
				InterfazServer s = (InterfazServer) Naming.lookup("client");
				System.out.println("Estás en un chat con: " + s.getName());
				for (InterfazServer sv : listaClientes){
					if (sv.equals(s)) 
						yaEsta = true;
				}
				if (!yaEsta) listaClientes.add(s);
				
				yaEsta = false;
				
				System.out.println("TRAZA: Lista de clientes: " + listaClientes);
				System.out.print("Escribe lo que quieras decir (salir para salir): ");
				String msg = sc.nextLine();
				if (msg.equals("salir")){
					System.out.println("Saliendo...");
					fin = true;
				} else{
					msg = nombre + ": " + msg; 
					for (InterfazServer serv : listaClientes){
						serv.send(msg);
					}
				}
			}
	
	} catch (Exception e) {
		System.err.println("¡Excepción Chat!");
		e.printStackTrace();
    }
  }
}
