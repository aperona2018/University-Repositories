package ej1_1upAST_aperona;

public class Reemplazador {

	
	public static void main(String[] args) {
		String palabra = "En la cabaña hay leña";
		String nuevaPalabra = "";
		for (char c : palabra.toCharArray()) {
			if (c == 'ñ') {			
				c = 'n';
				nuevaPalabra = nuevaPalabra + c + "i";	//lo hago de esta manera para poder cambiar
														// un char como 'ñ' por un string como 'ni'
			}
			else
				nuevaPalabra += c;
		}
		
		System.out.println(nuevaPalabra);

	}

}
