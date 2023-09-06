
/**Classe di utilità per testare la shallow copy del metodo toArray() di List e di Collection.
 * Non è stato eseguito nessun controllo sui valori perchè nei test si passano i valori corretti.*/
public class IntHolder 
{
	/**Contiene il numero intero.*/
	int n; 
	
	public IntHolder(int num)
	{	n=num;	}
	
	/**Assegna al campo membro un valore intero passato come parametro.
	 * @param num valore da assegnare*/
	public void setInteger(int num)
	{n=num;	}
	
	/**Ritorna il valore intero corrispondente al campo membro.
	 * @return valore del campo membro*/
	public int getInteger()
	{return n;}
	
	/**Definisce il nuovo criterio di uguaglianza tra due IntHolder. Due InHolder sono uguali
	 * se e solo se i campi membro sono uguali.
	 * @param i oggetto con cui eseguire il confronto
	 * @return true se i campi membro dei due oggetti sono uguali*/
	public boolean equals(IntHolder i)
	{	return n==i.getInteger();	}
}
