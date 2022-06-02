import java.net.*;
import java.util.*;
import java.io.*;
import java.time.*;

public class Servidor2 {

	public static void main(String args[]) {
		try {

			DatagramSocket socketUDP = new DatagramSocket(6789);
			byte[] bufer = new byte[1000];

			while (true) {
				// Construimos el DatagramPacket para recibir peticiones
				DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);

				System.out.println("Esperando peticion...");

				// Leemos una peticion del DatagramSocket
				socketUDP.receive(peticion);

				System.out.print("Datagrama recibido del host: " + peticion.getAddress());
				System.out.println(" desde el puerto remoto: " + peticion.getPort());

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
			}

		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		}
	}

}
