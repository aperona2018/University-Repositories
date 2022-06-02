package alumnos;

/**
 * @author aperona
 *
 */
public class Nuevo extends Alumno{


	private final int PRECIO_NUEVO = 60;
	
	public Nuevo(String nombre, String apellidos, String titulacion, int expediente) {
		super(nombre, apellidos, titulacion, expediente);
		this.precio = this.PRECIO_NUEVO;
	}
	

	public static void main(String[] args) {

	}

}
