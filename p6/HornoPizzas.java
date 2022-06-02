import java.util.*;

public class HornoPizzas extends Thread {
	// Recordad: las pizzas se queman cuando pasan más de 5 minutos
	private Pizzas p;
	private int numero;
	private int maxSegsEspera = 5;

	public HornoPizzas(Pizzas pi, int pnumero) {
		p = pi;
		numero = pnumero;
		setName("HP" + numero);
		start();
	}

	/*
	 * Métodos a implementar: run(); // irá llamando a "cogerPizza", hasta que no
	 * haya; comprueba si se queman...
	 */
	
	
	public void run() {
		
		synchronized(this) { // Utilizamos synchronized para evitar condiciones de carrera
			while (this.p.getNumPizzas() > 0) {
				System.out.println("TRAZA cociendo ahora: " + this.p.getCociendoAhora() + " restantes: "+ this.p.getNumPizzas());
				// Cargamos las pizzas cociendo ahora en int cociendo.
				int cociendo = this.p.getCociendoAhora();
				if (cociendo < this.p.getMaxPizzHorno()) {
					Random random = new Random();
					int segsEspera = random.nextInt(this.maxSegsEspera); // El horno puede esperar hasta 5 segundos
					try {
						Thread.sleep(segsEspera*1000); // dormimos la cantidad establecida por random
						int p = this.p.cogerPizza();
						if (p != -1) {
							System.out.println("Horno " + this.numero + " coge una pizza (" + p + ")");
							System.out.println("Pizzas restantes: " + this.p.getNumPizzas());
						} else {
							System.out.println("NO hay pizzas cociendo");
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("Fin de horno " + this.numero);
		}
	}
	
}
