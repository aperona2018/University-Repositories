import java.util.*;

public class RepartidorPizzas extends Thread {
	private Pizzas p;
	private int numero;
	private int maxSegsEspera = 10;

	public RepartidorPizzas(Pizzas pi, int pnumero) {
		p = pi;
		numero = pnumero;
		setName("RP" + numero);
		start();
	}

	/*
	 * Métodos a implementar: run(); // irá llamando a "repartirPizza" en intervalos
	 * de tiempo aleatorios hasta que no haya...
	 */
	
	public void run() {
		synchronized(this) { // Utilizamos synchronized para evitar condiciones de carrera
			while ((this.p.getNumPizzas() > 0) || (this.p.getCociendoAhora() > 0)) {
				System.out.println("Repartidor cociendo ahora: " + this.p.getCociendoAhora() + " restantes: " + this.p.getNumPizzas());
				// Cargamos las pizzas cociendo ahora en int cociendo.
				int cociendo = this.p.getCociendoAhora();
				if (cociendo > 0) {
					Random r = new Random();
					// Los segundos que puede esperar el repartidor: hasta 10 segundos
					int segsEspera = r.nextInt(this.maxSegsEspera);
					try {
						Thread.sleep(segsEspera*1000); // Duerme los segundos que determine el random
						int pizza = this.p.repartirPizza();
						if (pizza != -1) {
							pizza += segsEspera;
							
							int maxCoc = this.p.getMaxCoccion();
							if (pizza > maxCoc) pizza = maxCoc; // ponemos el tope de estado de pizza en 5 (MaxCoccion)
							System.out.println("El repartidor " + this.numero + " reparte una pizza (" + pizza + ")");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("Fin de repartidor " + this.numero);
		}
	}
}
