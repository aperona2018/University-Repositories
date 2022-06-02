

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		Pizzas p = new Pizzas(); // instanciamos las pizzas

		for (int i = 1; i <= 5; i++)
			new HornoPizzas(p, i); // bucle que crea 5 hilos de HornoPizzas, le pasamos las pizzas e i para saber
									// el número del hilo
		for (int i = 1; i <= 5; i++)
			new RepartidorPizzas(p, i); // bucle que crea 5 hilos de RepartidorPizzas, le pasamos las pizzas e i para
										// saber el número del hilo
		System.out.println("TRAZA: Comienzo de main");
		Thread.sleep(100000000);
		System.out.println("TRAZA: Fin de main");
	}
}
	