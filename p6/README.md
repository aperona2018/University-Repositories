# AST - Práctica 6: P6-Pizzas
## Antonio Perona Martínez @aperona2018
## Ing. Telemática

### Especificaciones: 
* Versión de Java: JavaSE-13
* Indentación: 4 espacios por tabulación

### Clases utilizadas:

#### Pizzas:

Clase que va a representar las pizzas que vamos a meter en los hornos. Cada pizza será un entero con el valor de su estado **(1-5)**
En esta clase tendremos los siguientes atributos:

* **private int maxPizzHorno:** Número máximo de pizzas cociéndose, será de 3 pizzas
* **private int maxPizzas:** Número máximo de pizzas en el establecimiento, será de 100 pizzas
* **private int maxCoccion:** Tiempo máximo de cocción de pizzas, será de 5 "minutos", es decir, a partir de 5, la pizza saldrá quemada.
* **private int cociendoAhora:** Número de pizzas que están cociendo actualmente

Asimismo, dispondremos de dos colas **Queue**:

* **private Queue<Integer> colaPizzas:** Cola de pizzas que están a la espera de cocerse
* **private Queue<Integer> colaCociendo:** Cola de pizzas que están cociéndose --> Sacaremos las pizzas en orden.

Además, tendremos los siguientes métodos:

* **public synchronized int cogerPizza():** Comprueba que no se estén cociendo ya 3 pizzas y, si se puede, se extrae la siguiente pizza de **colaPizzas** y se añade a **colaCociendo**, además de retornar la pizza, si no se puede retorna -1.

* **public synchronized int repartirPizza():** Comprueba que haya pizzas cociendo y extrae la siguiente pizza de **colaCociendo** y la retorna.


#### HornoPizzas:

Clase que va a representar los hornos que van a cocer las pizzas. A cada horno se le va a pasar en su constructor las pizzas del establecimiento. Asimismo cada horno tiene su número.

Destaca el método **public void run():** en el cual se comprueba que haya pizzas en la cola de pizzas esperando por cocer y si no hay selecciona de manera aleatoria empleando **Random** un número de segundos entre 0 y 5 y duerme con **Thread.sleep()** durante los segundos seleccionados al azar. Una vez despierta, llama a **cogerPizza()**. Este proceso lo va a repetir hasta que no queden pizzas esperando por cocer en el establecimiento.


#### RepartidorPizzas:

Clase que va a representar los repartidores de pizzas del establecimiento. A cada repartidor se le va a pasar en su constructor las pizzas del establecimiento. Asimismo cada repartidor tiene su número.

Destaca el método **public void run():** en el cual se comprueba que haya pizzas esperando para ser cocidas o pizzas cociéndose en los hornos. Asimismo, si hay pizzas cociéndose en los hornos se selecionará un número de segundos de espera aleatoriamente mediante **Random** y el repartidor dormirá mediante **Thread.sleep()** durante dichos segundos. Tras despertar, llamará a **repartirPizza()**. Esto lo repetirá mientras haya pizzas esperando a entrar a los hornos o pizzas cociéndose.


#### Main:

Clase Main del programa, donde creamos la instancia **Pizzas** y donde creamos 5 instancias de **HornoPizzas** y 5 instancias de **RepartidorPizzas**, pasándoles como argumentos del constructor la instancia Pizzas y su respectivo número.

Asimismo, mientras los Threads de HornoPizzas y RepartidorPizzas ejecutan, dormiremos el Main usando **Thread.sleep()** una cantidad de 100000000 ms.


#### Condiciones de carrera:

Es notable que hemos utilizado **synchronized** en los dos métodos **run()** de los dos Threads. Esto lo hacemos para evitar condiciones de carrera, las cuales pueden llevar a errores en la correcta ejecución del programa. Asimismo también el uso de synchronized en los métodos **cogerPizza()** y **repartirPizza()** pueden ser redundantes. No obstante no perjudican de ninguna forma al programa.
