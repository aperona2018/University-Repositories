package manejoFicheros;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * @author aperona
 * 
 * Clase diseñada para manejar los ficheros pertinentes del programa.
 * Así como lo pide la práctica, usaremos tanto la librería io como nio.
 */

public class ManejadorFicheros {

	private final Charset CHARSET = StandardCharsets.UTF_8;
	
	public ManejadorFicheros() {
	}
	
	
	public Charset getCHARSET() {
		return CHARSET;
	}


	/**
	 * Escribe el string datos en el fichero. Se usa io
	 * @param fichero
	 * @param datos
	 * @throws IOException
	 */
	public void escribirFichero(String fichero, String datos) throws IOException{
		
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
								new FileOutputStream(fichero)));
		
		out.writeUTF(datos);
		out.close();
	}
	
	/*
	public String leerFichero(String fichero) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader(fichero));
		StringBuilder sb = new StringBuilder();
		String string = "";
		
		while((string = br.readLine())!= null) {
			sb.append(string);
			string = sb.toString();
			System.out.println("TRAZA: " + string);
		}
		
		br.close();
		
		
		return sb.toString();	
	}
	*/
	
	/*
	public String leerFichero(String fichero) throws IOException{
		File file = new java.io.File(fichero);
		String filePath = new java.io.File(fichero).getAbsolutePath();
		Scanner s = null;
		String string = "";
		
		s = new Scanner(file);
		
		System.out.println("EXISTE?: " + file.exists());
		System.out.println("hasNextLine?: " + s.hasNextLine());
		System.out.println("PATH: " + filePath);
		
		while (s.hasNextLine()) {
			String line = s.nextLine();
			System.out.println("Line: " + line);
			string += line;
		}
		
		s.close();
		
		return string;
	} */
	
	
	/**
	 * Se lee la información del fichero usando un Charset. 
	 * Se leen todos los bytes del fichero y se decodifican con el Charset definido. En este caso utf-8
	 * Se utiliza la libreria nio
	 * Además, se trata el fichero para empezar a leer a partir de un char definido '#'
	 * @param fichero
	 * @param encoding
	 * @return String con la información en el fichero
	 * @throws IOException
	 */
	public String leerFichero(String fichero, Charset encoding) throws IOException {
		String filePath = new java.io.File(fichero).getAbsolutePath();
		byte[] encoded = Files.readAllBytes(Paths.get(filePath));
		String str = new String(encoded, encoding);
		
		String str_aux = "";
		
		boolean comienzo = false;
		for (char c : str.toCharArray()) {
			if (c == '#') {
				comienzo = true;
				continue;
			}
			if (comienzo)
				str_aux += c;
		}
	
		return str_aux;
	}
	

	public static void main(String[] args) throws IOException{
		ManejadorFicheros f = new ManejadorFicheros();
		f.escribirFichero("test.txt", "\n#Esto es una prueba");
		System.out.println("Escribe fichero");
		String s = f.leerFichero("test.txt", f.CHARSET);
		System.out.println("lee fichero: " + s);
	}

}
