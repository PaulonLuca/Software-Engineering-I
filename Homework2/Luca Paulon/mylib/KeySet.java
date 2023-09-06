import java.util.Hashtable;

/**KeySet è un class adapter che serve per adattare un set per fare im modo che vengano visualizzate
 * solo le chiavi di una tabella hash. Si è optato per un class adapter e non per un object adapter
 * poichè molti metodi del SetObjectAdapter vengono utilizzati anche nel class adapter.
 * La vista delle chiavi utilizza come backing storage l'hashtable della mappa in modo tale che
 * ogni modifica apportata alla mappa sia visibile nell'hashtable e viceversa.
 * Per utilizzare in modo corretto l'adapter corrente le collezioni dovrebbero contenere delle
 * chiavi ed i paramteri singoli passati dovrebbero essere anch'essi delle chiavi.*/
public class KeySet<K> extends SetObjectAdapter<K>
{
	/**Costruttore con un parametro: si inizializza l'hashtable del set con il riferimento
	 * dell'hashtable della mappa su cui si è invocato il metodo keySet().
	 * @param htMappa riferimento alla hashtable della mappa sui cui si è invocato il metodo keySet()*/
	public KeySet(Hashtable htMappa)
	{	hashTable=htMappa;	}
	
	/**Mantiene nel set solo gli elementi che sono contenuti anche nella collezione specificata. Quindi
	 * si rimuovono dal set corrente tutti gli elemento che non sono contenuti nella collezione specificata.
	 * Si è sovrascritto il metodo del SetObjectAdapter poichè si faceva puntare l'hashtable ad una nuova
	 * hashtable contenente le sole coppie chiave valore contenute anche nella collezione, ma tale
	 * approccio non è utilizzabile in una classe derivata. Quindi si verifica prima che la collezione
	 * non contiene la chiave analizzata e se così dovesse essere allora la chiave viene rimossa dalla mappa
	 * e quindi anche dal keySet.
	 * @param c collezione contenente gli elementi da mantenere nel keySet
	 * @return true se a seguito dell'operazione il keySet risulta cambiato
	 * @throws NullPointerException se la collezione passata è un riferimento null*/
	@Override
	public boolean retainAll(Collection<?> c) 
	{
		if(c==null)
			throw new NullPointerException();
		
		Object[] array=toArray();//array con tutte le chiavi della mappa
		int oldSize=size();
				
		for(int i=0;i<oldSize;i++)
		{
			K key=(K)array[i];//possibile class cast exception
			if(!c.contains(key))//se la collezione non conitiene la chiave si rimuove la entry dalla mappa
				hashTable.remove(key);
		}
		return size()!=oldSize;
	}	
	
	//--------------------------------------Metodi da non implementare---------------------------------------	
	/**KeySet non supporta l'aggiunta di un elemento, come previsto dalla documentazione del metodo keySet() di Map.
	 * @throws UnsupportedOperationException*/
	@Override
	public boolean add(K e) 
	{	throw new UnsupportedOperationException();	}

	/**KeySet non supporta l'aggiunta di elementi appartenenti ad una collezione, come previsto 
	 * dalla documentazione del metodo keySet() di Map.
	 * @throws UnsupportedOperationException*/
	@Override
	public boolean addAll(Collection<? extends K> c) 
	{	throw new UnsupportedOperationException();	}
}
