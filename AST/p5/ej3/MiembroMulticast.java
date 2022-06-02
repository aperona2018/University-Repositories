import java.net.*;
import java.util.*;
import java.io.*;

public class MiembroMulticast {
	public static void main(String args[]) {
		// args[0] es la direccion del grupo
		Scanner sc = new Scanner(System.in);
		boolean nombreValido = false;
		boolean salir = false;
		String nombre = "";
		Calendar calendario = new GregorianCalendar();

		byte[] bufer = new byte[1000];
		String linea;

		try {
			InetAddress grupo = InetAddress.getByName(args[0]);
			// InetAddress grupo = InetAddress.getByName("224.0.0.3");
			MulticastSocket socket = new MulticastSocket(6789);

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

			// Mientras el usuario no quiera salir, puede seguir escribiendo en el chat
			while (!salir) {
				System.out.print("-> ");
				String mensaje = sc.nextLine();
				if (!mensaje.equals("salir")) {
					int hora = calendario.get(Calendar.HOUR_OF_DAY);
					int minutos = calendario.get(Calendar.MINUTE);
					// El mensaje se compone del nombre del usuario, la hora a la que ha mandado el mensaje y el respectivo mensaje
					String msg = " - " + nombre + " (" + hora + ":" + minutos + ") : " + mensaje;
					byte[] m = msg.getBytes();
					DatagramPacket mensajeSalida = new DatagramPacket(m, m.length, grupo, 6789);
					socket.send(mensajeSalida);

					DatagramPacket mensajeEntrada = new DatagramPacket(bufer, bufer.length);
					socket.receive(mensajeEntrada);
					linea = new String(mensajeEntrada.getData(), 0, mensajeEntrada.getLength());
					System.out.println(linea);

				} else {
					salir = true;
					System.out.println("Saliendo...");
					sc.close();
					socket.leaveGroup(grupo);
					socket.close();
				}
			}
		} catch (SocketException e) {
			System.out.println("Socket:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		}
	}
}
