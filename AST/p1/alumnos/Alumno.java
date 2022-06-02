package alumnos;


// Clase padre Alumno del paquete alumnos


/**
 * @author aperona
 */
public class Alumno {
	

	private String nombre;
	private String apellidos;
	protected int precio;  //La definimos como protected para acceder sin setter
	private String titulacion;
	private int expediente; 
	
	
	/**
	 * Definicion de constantes 
	 * OJO: Solo las usaremos para el metodo precio, ya que la otra forma que hemos usado es más elegante
	 */
	private final int PRECIO_ERASMUS = 80;
	private final int PRECIO_NUEVO = 60;
	private final int PRECIO_REPETIDOR = 70;
	
	
	public Alumno(String nombre, String apellidos, String titulacion, int expediente) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.titulacion = titulacion;
		this.expediente = expediente;
		this.precio = 0;
	}

	
	/**
	 * Getters & Setters de los atributos de la clase Alumno
	 * OJO: precio, expediente y las constantes solo tienen getter, pues ya están establecidos
	 */
	
	public int getPrecio() {
		return precio;
	}
	
	
	public int getExpediente() {
		return expediente;
	}

	
	public String getNombre() {
		return nombre;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String getApellidos() {
		return apellidos;
	}

	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	
	public String getTitulacion() {
		return titulacion;
	}


	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}
	
	
	/**
	 * 
	 * Getters de las constantes 
	 * OJO: Solo las usaremos para el metodo precio, ya que la otra forma que hemos usado es más elegante
	 */
	
	public int getPRECIO_ERASMUS() {
		return PRECIO_ERASMUS;
	}


	public int getPRECIO_NUEVO() {
		return PRECIO_NUEVO;
	}


	public int getPRECIO_REPETIDOR() {
		return PRECIO_REPETIDOR;
	}


	public int precio(int tipoalumno) {
		
		/*
		 * Metodo precio: Devuelve el precio de un tipo de alumno dado un int tipoalumno
		 * 
		 * ENTRADA:
		 * int tipoalumno: 0 para Nuevo, 1 para Repetidor y 2 para Erasmus
		 * 
		 * SALIDA:
		 * precio del tipo del alumno
		 * 
		 * OJO: No usaremos este método, ya que el getPrecio() de cada instancia creada nos resuelve el problema de una manera mas elegante.
		 *
		 */

		int precio = 0;

		switch (tipoalumno) {
			case 0:
				precio = this.getPRECIO_NUEVO();
				break;
			case 1:
				precio = this.getPRECIO_REPETIDOR();
				break;
			case 2:
				precio = this.getPRECIO_ERASMUS();
				break;
			default:
				return 0;
		}
		
		return precio;
	}
	
	
	public String infoToString() {
		/*
		 * Método infoToString: convierte en string los atributos de la clase
		 * ENTRADAS: Ninguna, retorna un string de los atributos de quien lo llama
		 * SALIDAS: Un string con los atributos de quien lo llama
		 */
		return (this.getNombre() + " " + this.getApellidos() + "\n" + "Titulacion: " + this.getTitulacion() + "\n" + 
		"Expediente: " + this.getExpediente() + "\n" + "Precio matricula: " + this.getPrecio() + " euros." + "\n");
		
	}
	
	
	public String toString() {
		/*
		 * Método toString: Cambia levemente respecto del infoToString, convierte 
		 * en string el nombre y los apellidos de quien lo llama
		 */
		return (this.getNombre() + " " + this.getApellidos());
	}
	
	
	public void cambiarTitulacion(String titulacion) {
		/*
		 * Método cambiarTitulacion: cambia la titulación del alumno que lo llama
		 * ENTRADAS: 
		 * String titulacion: titulación por la que cambiamos la titulación
		 * del alumno
		 * SALIDAS:
		 * Ninguna, cambia la titulación del alumno.
		 */
		this.setTitulacion(titulacion);
		
	}


	public static void main(String[] args) {

	}

}
