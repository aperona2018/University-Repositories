package aux;

import java.util.Comparator;
import java.util.PriorityQueue;


public class ColaPrioridadCadenas {
    public static void main(String[] args) {
        Comparator<String> comparator = new aux.ComparadorDeCadenas();
        PriorityQueue<String> queue = new PriorityQueue<String> (10, comparator);
        System.out.println("Añadimos 1");
        queue.add("pequeña");
        System.out.println(queue);
        System.out.println("Añadimos 2");
        queue.add("cadena muy muy larga");
        System.out.println(queue);
        System.out.println("Añadimos 3");
        queue.add("cadena media");
        System.out.println("FINAL " + queue);
 
        
        System.out.println(queue);
        while (queue.size() != 0)
          System.out.println(queue.remove());
    }
}
