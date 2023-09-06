public abstract class Pezzo 
{
	int x;
	int y;
	protected double attacco;
	protected double difesa;
	
	protected Pezzo(int X, int Y, double a, double d)
	{
		x=X;
		y=Y;
		attacco=a;
		difesa=d;
	}
	
	//metodi get, per realizzare incapsulamento
	public int getX()
	{	return x; }
	
	public int getY()
	{	return y; }	
	
	public double getAttacco()
	{	return attacco; }
	
	public double getDifesa()
	{	return difesa; }
	
}
