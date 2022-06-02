import java.net.*;
import java.io.*;
import java.util.*;

public class Cliente2 {

	// Los argumentos proporcionan el nombre del servidor
	public static void main(String args[]) {
		boolean salir = false;

		try {
			DatagramSocket socketUDP = new DatagramSocket();
			InetAddress hostServidor = InetAddress.getByName(args[0]);
			int puertoServidor = 6789;

			Scanner sc = new Scanner(System.in);

			// Mientras que el usuario no quiera salir, se siguen mandando mensajes
			while (!salir) {
				System.out.print(
						"Escribe lo que quieras mandar: (hora para conseguir la hora) (salir para salir del programa) ");
				String msg = sc.nextLine();

				// si el mensaje es "salir" se cierra la conexión
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
					socketUDP.setSoTimeout(5000); // Timeout de 5000 ms
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
			// si se ha excedido el timeout, se pinta el mensaje por pantalla
			System.out.println("IO: " + e.getMessage() + " -> Tiempo de espera agotado. Cortando conexión...");
		}
	}
}
