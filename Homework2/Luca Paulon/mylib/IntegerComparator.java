import java.util.Comparator;

/**Classe di utilità che contiene il criterio di confronto tra due numeri interi. Si utilizza
 * tale classe nel metodo sort della classe List e Sublist.*/
public class IntegerComparator implements Comparator<Integer>
{

	/**Due numeri interi sono uguali se e solo se la loro differenza fa 0. Il numero corrente è maggiore
	 * di quello passato se la loro differenza è maggiore di 0. Il numero corrente è minore di quello passato
	 * se la loro differenza è minore di 0.*/
	@Override
	public int compare(Integer arg0, Integer arg1) 
	{	return arg0-arg1;	}

}
