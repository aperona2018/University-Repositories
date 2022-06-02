package fechas;

/**
 * 
 * @author aperona2018
*/

/**
 * Clase Fecha que nos hemos creado para simbolizar
 * las fechas en las que los alumnos pedirán tutorías
 * a los profesores.
 *
 */

public class Fecha {
	
	private int hora;
	private int dia;
	private int mes;
	private int año;
	
	public Fecha(int hora, int dia, int mes, int año) {
		this.hora = hora;
		this.dia = dia;
		this.mes = mes;
		this.año = año;
	}
	 

	public int getHora() {
		return hora;
	}



	public int getDia() {
		return dia;
	}


	public int getMes() {
		return mes;
	}


	public int getAño() {
		return año;
	}

	
	/**
	 * Convierte a string la fecha en formato: "H - D/M/A"
	 */
	
	public String toString() {
		
		return (this.getHora() + " H - " + this.getDia() + "/" + this.getMes() +
				"/" + this.getAño());
		
	}
	
	public static void main(String[] args) {

	}

}
