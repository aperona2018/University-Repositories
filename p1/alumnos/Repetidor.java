package alumnos;

/**
 * @author aperona
 *
 */

public class Repetidor extends Alumno{

	private final int PRECIO_REPETIDOR = 70;
	
	public Repetidor(String nombre, String apellidos, String titulacion, int expediente) {
		super(nombre, apellidos, titulacion, expediente);
		this.precio = this.PRECIO_REPETIDOR;
		
	}
	
	public static void main(String[] args) {
		
	}

}
