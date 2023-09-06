public class Nano extends Pezzo
{
	//si definisco due costanti per i valori di attacco e difesa di default
	private static final double defAtt=2;
	private static final double defDif=5;
	
	public Nano(int X, int Y)
	{
		super(X,Y,defAtt,defDif);
	}
	
	public void modificaAttDifMontagna()
	{
		//difesa +0%
		attacco*=2; //attacco +100% (raddoppia)
	}
	
	
}
