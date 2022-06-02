# P5-SocketsUDP - Ampliación de Sistemas Telemáticos
## Antonio Perona Martínez
## Ingeniería Telemática

### Especificaciones:

- Programa creado en javaSE-13
- Indentación: 4 espacios por tabulación

### Contenidos de la práctica:

La práctica está compuesta por 3 ejercicios, los cuales están separados cada uno en una carpeta.

#### Ejercicio 1:

Para este ejercicio hemos modificado el **Cliente** para que, en vez de mandar el mensaje al ejecutar el Cliente, el usuario mande tantos mensajes como el desee por la entrada estándar.

De esta forma, solo se pasará 1 argumento, el cual será la dirección del Servidor.

El código del main del cliente modificado es: 

```java
// Los argumentos proporcionan el nombre del servidor
	public static void main(String args[]) {
		boolean salir = false;

		try {
			DatagramSocket socketUDP = new DatagramSocket();
			InetAddress hostServidor = InetAddress.getByName(args[0]);
			int puertoServidor = 6789;

			Scanner sc = new Scanner(System.in);

			// Mientras el usuario no quiera salir, pedimos que escriba por teclado
			while (!salir) {
				System.out.print("Escribe lo que quieras mandar: (salir para salir del programa) ");
				String msg = sc.nextLine();

				// Si el mensaje es "salir", se termina la comunicación
				if (msg.equals("salir")) {
					System.out.println("Saliendo...");
					salir = true;
				} else {
					byte[] mensaje = msg.getBytes();

					// Construimos un datagrama para enviar el mensaje al servidor
					DatagramPacket peticion = new DatagramPacket(mensaje, msg.length(), hostServidor, puertoServidor);

					// Enviamos el datagrama
					socketUDP.send(peticion);

					// Construimos el DatagramPacket que contendra la respuesta
					byte[] bufer = new byte[1000];
					DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
					socketUDP.receive(respuesta);

					// Enviamos la respuesta del servidor a la salida estandar
					System.out.println("Respuesta: " + new String(respuesta.getData()));

				}
			}
			// Cerramos el socket
			socketUDP.close();
			sc.close();

		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		}
	}
```


### Ejercicio 2:

En este caso, utilizamos más o menos el mismo código del Cliente del ejercio 1, sin embargo, añadimos un timeout de 5000 ms (5 s) al recibir mensajes del servidor

```java
byte[] bufer = new byte[1000];
DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
socketUDP.setSoTimeout(5000); // Timeout de 5000 ms
socketUDP.receive(respuesta);
```

Además, en el servidor se maneja el mensaje enviado por el cliente. Si es "hora", este mandará la hora en su mensaje al cliente. 

La forma de implementación de esto es:

```java
// sacamos el contenido del mensaje
String contenido = new String(peticion.getData(), 0, peticion.getLength());
System.out.println("Contenido del mensaje del Datagrama: " + contenido);
DatagramPacket respuesta = null;
// si el contenido del mensaje es "hora" se le proporciona la hora y el dia
if (contenido.equals("hora")) {
	System.out.println("Proporcionando hora...");
	Calendar calendario = new GregorianCalendar();
	int hora = calendario.get(Calendar.HOUR_OF_DAY);
	int minutos = calendario.get(Calendar.MINUTE);
	int segundos = calendario.get(Calendar.SECOND);
	String hora_msg = "Son las " + hora + ":" + minutos + ":" + segundos + " del " + LocalDate.now();
	byte[] mensaje = hora_msg.getBytes();
	respuesta = new DatagramPacket(mensaje, hora_msg.length(), peticion.getAddress(),
				peticion.getPort());
} else {
	// Construimos el DatagramPacket para enviar la respuesta
	respuesta = new DatagramPacket(peticion.getData(), peticion.getLength(), peticion.getAddress(),
	peticion.getPort());
}
socketUDP.send(respuesta);
```

### Ejercicio 3:

Para este ejercicio, modificamos **MiembroMulticast** para que este actúe de la misma manera que un chat. 

De esta forma, a cada usuario se le pide un nombre para identificarlo y, después, cada usuario puede mandar los mensajes que él desee al grupo. Puesto que se une a este al tener un nombre válido.

```java
// se pide un nombre para el usuario que quiera escribir en el chat
while (!nombreValido)
	try {
		System.out.print("Introduzca su nombre: ");
		nombre = sc.nextLine();
		nombreValido = true;
	} catch (Exception e) {
		System.out.println("ERROR: Introduce un nombre válido");
		sc.next();
	}

// Se une al grupo
socket.joinGroup(grupo);
```

Una vez dentro del grupo, cada usuario escribe por teclado el mensaje que quiera mandar y recibe el de los demás.

Cabe destacar que el formato de los mensajes es:

**- nombre (hora): mensaje**


