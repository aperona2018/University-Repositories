package aux;

import java.util.Comparator;

public class ComparadorDeCadenas implements Comparator<String>
{
	
	
	
    @Override
    public int compare(String x, String y) { // suponiendo que ningún String es null
    	System.out.println("Comparamos " + x + " y " + y);
    	System.out.println("Longitud de " + x + " = " + x.length());
    	System.out.println("Longitud de " + y + " = " + y.length());
    	
    	
        if (x.length() < y.length())
          return -1;
        else if (x.length() > y.length())
          return 1;
        else return 0;
    }
}
