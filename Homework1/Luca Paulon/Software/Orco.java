public class Orco extends Pezzo
{
	//si definisco due costanti per i valori di attacco e difesa di default
	private static final double defAtt=4;
	private static final double defDif=4;
	
	public Orco(int X, int Y)
	{
		super(X,Y,defAtt,defDif);
	}
	
	//nel caso in cui la temporalità sia giorno il metodo seguente decrementa in valori di attacco e difesa dell'orco
	void decrementaAttDifGiorno()
	{
		double att=attacco/2;
		double dif=difesa/2;
		attacco-=att;
		difesa-=dif;	
	}
	
	//nel caso in cui la temporalità sia notte il metodo seguente incrementa in valori di attacco e difesa dell'orco
	void incrementaAttDifNotte()
	{
		double att=attacco/2;
		double dif=difesa/2;
		attacco+=att;
		difesa+=dif;
	}
}
