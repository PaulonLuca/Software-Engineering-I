public class Elfo extends Pezzo
{
	private static final double defAtt=5;
	private static final double defDif=2;
	
	public Elfo(int X, int Y)
	{	
		super(X,Y,defAtt,defDif); //valuta se definirli come costanti
	} 
	
	public void modificaAttDifBosco()
	{
		//attacco +0%
		difesa*=2; //difesa +100% (raddoppia)
	}
}
