package alumnos;

/**
 * @author aperona
 *
 */
public class Nuevo extends Alumno{


	private final int PRECIO_NUEVO = 60;
	private final int PRIORIDAD_NUEVO = 2;
	
	public Nuevo(String nombre, String apellidos, String titulacion, int expediente) {
		super(nombre, apellidos, titulacion, expediente);
		this.precio = this.PRECIO_NUEVO;
		this.prioridad = this.PRIORIDAD_NUEVO;
	}
	

	public static void main(String[] args) {
		Nuevo n = new Nuevo("Prueba", "Test", "Test tit", 1);
		System.out.println(n.getClass());
	}

}
