import java.util.*;

public class Pizzas {
	private int maxPizzHorno = 3; // número máximo de pizzas cociéndose a la vez
	private int maxPizzas = 100; // número máximo de pizzas en el establecimiento
	private int numPizzas = this.maxPizzas;
	private int maxCoccion = 5; // tiempo máximo de cocción de pizzas
	private int cociendoAhora = 0; // cuántas pizzas se están cociendo ahora mismo
	private Queue<Integer> colaPizzas;
	private Queue<Integer> colaCociendo;
	

	public Pizzas() { // Constructor de Pizzas
		this.colaPizzas = new LinkedList<Integer>();
		this.colaCociendo = new LinkedList<Integer>();
		for (int i = 0; i < maxPizzas; i++)
			this.colaPizzas.add(0); // ponemos el estado de las pizzas a "sin hornear"
	}


	public int getMaxPizzHorno() {
		return maxPizzHorno;
	}
	
	public int getMaxCoccion() {
		return maxCoccion;
	}

	public int getNumPizzas() {
		return numPizzas;
	}

	public int getCociendoAhora() {
		//return cociendoAhora;
		return this.colaCociendo.size();
	}

	public Queue<Integer> getColaPizzas() {
		return colaPizzas;
	}

	public Queue<Integer> getColaCociendo() {
		return colaCociendo;
	}
	
	/**
	 * Se comprueba que haya menos de 3 pizzas cociéndose y coge la siguiente pizza de 
	 * la colaPizzas. Si se puede retorna la pizza y si no se puede retorna -1
	 */
	
	public synchronized int cogerPizza() {
		try {
			if (this.cociendoAhora < 3){
				int pizza = this.colaPizzas.remove();
				this.colaCociendo.add(pizza);
				this.cociendoAhora++;
				this.numPizzas--;
				//System.out.println("Cociendo ahora: " + this.cociendoAhora);
				return pizza;
			} 
		} catch (Exception e) {
			return -1;
		}
		
		return -1;
	}
	
	
	/**
	 * Mira que haya pizzas cociéndose y si las hay, extrae la siguiente pizza de 
	 * colaCociendo
	 * 
	 */
	public synchronized int repartirPizza() {
		try {
			if (this.cociendoAhora > 0) {
				int pizza = this.colaCociendo.remove();
				this.cociendoAhora--;
				System.out.println("Cociendo ahora: " + this.cociendoAhora);
				return pizza;
			}
		} catch (Exception e) {
			return -1;
		}
		return -1;
	}


}
