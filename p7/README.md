# AST - Práctica 7: P7-RMI
## Antonio Perona Martínez @aperona2018
## Ing. Telemática 

### Especificaciones:

* Versión de Java: JavaSE-13
* Indentación: 4 espacios por tabulación

### Clases utilizadas sin variación en los dos ejercicios:

Las clases utilizadas que no han cambiado en ninguno de los dos ejercicios son las clases Chat y sus respectivas interfaces:

* **ChatClient** y **ChatServer**: Los cuales contienen los siguientes métodos:
 - public String getName()
 - public void setClient(InterfazClient c) // public void setServer(InterfazServer s): Establece la interfaz del chat respectivo
 - public InterfazClient getClient() // public InterfazServer getServer(): Retorna la interza del chat respectivo
 - public void send(String s): Pinta por la salida estándar el mensaje s
 
* **interzaClient** y **interfazServer**: Los cuales contienen la implementación de su chat correspondiente 

Ejemplo de implementación de código en la interfaz de chat del cliente:
```java
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface InterfazClient extends Remote {
	/* Todos los métodos remotos deben estar aquí especificados.
	Todos ellos, además, levantan excepciones del tipo RemoteException. */
	public String getName () throws RemoteException;
	public void send (String s) throws RemoteException;
}
```

### Ejercicio 1: Chat directo entre Servidor y Cliente

Creamos las dos clases Servidor y Cliente: **Server.java** y **Client.java**

La ejecución es sencilla, para ejecutar la parte del Servidor usaremos:

java -Djava.security.policy=permissions.policy Server

y la parte del cliente:

java -Djava.security.policy=permissions.policy Client

#### Server.java:

En el main de esta clase, pediremos un nombre para el servidor, una vez lo obtenemos, continuaremos:

```java
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
```

Con el nombre del server, crearemos un chat de cliente  y lo registraremos con **LocateRegistry.createRegistry (Registry.REGISTRY_PORT)** y lo ataremos con **Naming.rebind()**

Tras esto, esperaremos a que el cliente se conecte. Cuando se haya conectado, presionaremos cualquier tecla y lo enviaremos. Así ya podremos enviar los mensajes que queramos desde el server al client, puesto que tendremos una interfazServer con el mismo lookup que el cliente.


#### Client.java:

En esta clase, en su main, pediremos el nombre de usuario de la misma manera que en Server.java, tras ello crearemos un chat de cliente y una interfaz de cliente con el lookup del Server. De esta manera podremos mandar peticiones, en esta práctica en forma de strings pintados en pantalla al server.
Asimismo, crearemos un chatServer y lo registraremos en un puerto distinto al del chatClient, he decidido tener uno constante que será **8888**. De esta manera el cliente podrá recibir las peticiones del server, en este caso sus mensajes.

Una vez establecidas las interfaces, nos podremos comunicar con el server.

El código para mandar mensajes es idéntico en el server:
```java
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
```

### Ejercicio 1: Chat Broadcast entre Servidor y varios clientes:

En este ejercicio la única clase que he cambiado ha sido la de **Server.java**, las demás siguen intactas desde el ejercicio 1.

La única diferencia es que el cliente deberá ejecutarse de la siguiente forma:

java -Djava.security.policy=permissions.policy Client [puerto]

ya que no podemos repetir puerto entre clientes.


**IMPORTANTE: Hay que entablar comunicación con solo un chat al principio, una vez entablado el chat, se pueden unir los demás clientes**

#### Server.java:

La dinámica de esta clase es idéntica que en el ejercicio 1, salvo que esta vez tendremos una lista de interfazServer con los diversos clientes que se van conectando.

Asimismo, estaremos constantemente mirando el lookup de nombre "client" para ver si los clientes se van conectando y, si el cliente no está ya en la lista de clientes, se añadirá a esta. 

Muestra del código:

```java
boolean fin = false;
boolean clientYaEsta = false;
while (!fin){
	InterfazServer server = new ChatServer(nombre);
	InterfazServer s = (InterfazServer) Naming.lookup("client");
	System.out.println("Estás en un chat con: " + s.getName());
	for (InterfazServer sv : listaClientes){
		if (sv.equals(s)) 
			clientYaEsta = true;
		}
		if (!clientYaEsta) listaClientes.add(s);
				
		clientYaEsta = false;
				
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
```

Por último, es notable que por cada mensaje que envíe el cliente, se realiza un for de todos los clientes conectados para mandar el Broadcast.


### permisions.policy

Para que no nos dé error al ejecutar el programa, necesitaremos las siguientes líneas de código en un fichero permissions.policy:

```
grant {
  permission java.security.AllPermission;
};
```

