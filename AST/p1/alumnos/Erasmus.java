package alumnos;

/**
 * @author aperona
 *
 */

public class Erasmus extends Alumno {

	
	private final int PRECIO_ERASMUS = 80;
	
	public Erasmus(String nombre, String apellidos, String titulacion, int expediente) {
		super(nombre, apellidos, titulacion, expediente);
		this.precio = this.PRECIO_ERASMUS;
	}

	
	public static void main(String[] args) {
		
	}

}
