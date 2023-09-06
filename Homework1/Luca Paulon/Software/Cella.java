public class Cella 
{
	private int x;	//coordinata X  della cella
	private int y;	//coordinata Y della cella
	private TipoCella luogo;
	private int count; //conteggia il numero di pezzi sulla cella
	private int countOrchi; //conteggia il numero di orchi sulla cella
	private int countElfi; //conteggiia il numero di elfi sulla cella
	private int countNani; //conteggia il numero di nani sulla cella
	
	public Cella(int X, int Y)
	{
		x=X;
		y=Y;
		count=0;
		countOrchi=0;
		countElfi=0;
		countNani=0;
		initCella();
	}
	
	public TipoCella getLuogo()
	{	return luogo; }
	
	public boolean isEmpty()
	{	return count==0; }
	
	public int getX()
	{	return x; }
	
	public int getY()
	{	return y; }
	
	private void initCella()
	{
		int n=(int)(Math.random()*3); //genera numero tra 0 e 2
		switch(n)
		{
			case 0: luogo=TipoCella.pianura;break;
			case 1: luogo=TipoCella.bosco;break;
			case 2: luogo=TipoCella.montagna;break;
		}
	}
	
	public void addPezzo(Pezzo p)
	{
		if(count+1>5)
			throw new MaximumCellSizeException();
		
		count++;
		
		if(p instanceof Orco)
			countOrchi++;
		else
			if(p instanceof Elfo)
				countElfi++;
			else
				if(p instanceof Nano)
					countNani++;
	}
	
	public int findMaxType()
	{
		if(countOrchi>countElfi)
		{
			if(countOrchi>countNani)
				return countOrchi;
			else
				return countNani;
		}
		else
		{
			if(countElfi>countNani)
				return countElfi;
			else
				return countNani;
		}
	}
	
	public boolean equals(Cella c)
	{
		return c.x==x && c.y==y;
	}		
}
