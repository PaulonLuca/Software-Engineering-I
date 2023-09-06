import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**Values è un class adapter che serve per adattare una collezione per fare im modo che vengano visualizzati
 * solo i valori di una tabella hash.
 * La vista dei valori utilizza come backing storage l'hashtable della mappa in modo tale che
 * ogni modifica apportata alla mappa sia visibile nell'hashtable e viceversa.
 * Si è estesa una collection poichè i valori potrebbero essere duplicati, cioè potrebbero esserci
 * chiavi distinte a cui sono associati valori uguali, quindi per considerare gli eventuali duplicati è stata fatta questa scelta.
 * Per utilizzare in modo corretto l'adapter corrente le collezioni dovrebbero contenere dei
 * valori ed i parametri singoli passati dovrebbero essere anch'essi dei valori.*/
public class Values<V> extends CollectionObjectAdapter<V>
{
	/**Costruttore con un parametro: si inizializza l'hashtable della collezione con il riferimento
	 * dell'hashtable della mappa su cui si è invocato il metodo values().
	 * @param htMappa riferimento alla hashtable della mappa sui cui si è invocato il metodo values()*/
	private Hashtable hashTable;
	public Values(Hashtable htMappa)
	{	hashTable=htMappa;	}
	
	/**Ritorna true se il set contiene il valore specificato. 
	 * @param o valore di cui bisogna verificare la presenza
	 * @return true se il valore è contenuto nel set
	 * @throws NullPointerException se il valore passato è un riferimento null*/
	@Override
	public boolean contains(Object o) 
	{
		if(o==null)
			throw new NullPointerException();
		return hashTable.contains(o);
	}
	
	/**Ritorna true se il set contiene tutti gli elementi della collezione specificata. 
	* In questo caso la collezione è da interpetarsi come una collezione di valori.
	 * @param c collezione contenente i valori di cui bisogna verificare la presenza nel set
	 * @return true se tutti i valori della collezione sono contenuti anche nel set
	 * @throws  NullPointerException se la collezione passata è un riferimento null*/
	@Override
	public boolean containsAll(Collection<?> c) 
	{
		if(c==null)
			throw new NullPointerException();
		
		Iterator<?> iter=c.iterator();
		boolean contains=true;
		
		while(iter.hasNext() && contains)
		{			
			V elem=(V)iter.next();
			contains=hashTable.contains(elem);						
		}		
		return contains;
	}
	
	/**Metodo di untilità che restituisce la chiave corrispondente ad un dato valore.
	 * @param value valore della mappa di cui si vuole ottenere la chiave
	 * @return chiave corrispondente al valore se presente o null se non presente*/
	private Object findKey(V value)
	{
		for(Enumeration e=hashTable.keys(); e.hasMoreElements();)
		{
			Object key=e.nextElement();
			if(hashTable.get(key).equals(value))
				return key;
		}
		return null;
	}
	
	/**Rimuove il valore specificato dalla hashtable se presente. Per rimuovere il mapping dalla tabella hash
	 * è prima necessario individuare la chiave ad esso corrispondente se è presente attraverso il metodo findKey().
	 * @param o oggetto da rimuovere dalla collezione di valori
	 * @return true se il set conteneva il valore specificato ed è stato rimosso con successo.
	 * @throws NullPointerException se il valore passato è un riferimento null.*/
	@Override
	public boolean remove(Object o) 
	{
		if(o==null)
			throw new NullPointerException();

		Object keyToRemove=findKey((V)o);//si ottiene la chiave corrispondente all'elemento
		if(keyToRemove!=null)	
			return hashTable.remove(keyToRemove)!=null;
		return false;
	}
	
	/**Rimuove dalla collezione di valori tutti i valori che sono contenuti nella collezione specificata.
	 *Si utilizza ripetutamente il metodo remove() della hashtable, prima però bisogna individuare le 
	 * chiavi corrispondenti ai valori.
	 * @param c collezione contenente gli elementi che devono essere rimossi dalla collezione di valori.
	 * @return true se a seguito dell'operazione la collezione risulta cambiata, ovvero se la dimensione
	 * è diminuita almeno di una unità
	 * @throws NullPointerException se la collezione passata è un riferimento null*/
	@Override
	public boolean removeAll(Collection<?> c) 
	{
		if(c==null)
			throw new NullPointerException();
		
		Iterator<?> iter=c.iterator();
		int oldSize=size();
		
		while(iter.hasNext())
		{
			Object keyToRemove=findKey((V)iter.next());
			if(keyToRemove!=null)
				hashTable.remove(keyToRemove);
		}		
		return oldSize>size();
	}
	
	/**Mantiene nella collezione di valori solo i valori che sono contenuti anche nella collezione specificata. Quindi
	 * si rimuovono dalla collezione corrente tutti gli elementi che non sono contenuti nella collezione specificata.
	 * Per poter rimuovere i valori non presenti si individuano prima le chiavi corrispondenti ai valori.
	 * @param c collezione contenente i valori da mantenere
	 * @return true se a seguito dell'operazione la collezione risulta cambiata
	 * @throws NullPointerException se la collezione passata è un riferimento null*/
	@Override
	public boolean retainAll(Collection<?> c) 
	{
		if(c==null)
			throw new NullPointerException();
		
		Object[] array=toArray();//array con tutti i valori della mappa
		int oldSize=size();
				
		for(int i=0;i<oldSize;i++)
		{
			V value=(V)array[i];//possibile class cast exception
			if(!c.contains(value))//se la collezione non conitiene tutti i valori si rimuove la entry dalla mappa
			{
				Object key=findKey(value);// si ricerca la chiave corrispondente al valore
				hashTable.remove(key);
			}			
		}
		return size()!=oldSize;
	}	
	
	/**Ritorna un array contenente tutti i valori che sono contenuti nella mappa. I valori
	 * vengono inseriti nell'array secondo l'ordine imposto dall'enumerazione keys() di hashtable.
	 * @return array contenente i valori della tabella hash*/
	@Override
	public Object[] toArray() 
	{
		Object[] array=new Object[hashTable.size()];
		int i=0;
		for(Enumeration e=hashTable.keys();e.hasMoreElements();)
			array[i++]=hashTable.get(e.nextElement());
		return array;
	}
	
	@Override
	public int size()
	{	return hashTable.size();}
	
	@Override
	public void clear()
	{ hashTable.clear();}
	
	@Override
	public Iterator<V> iterator()
	{ return new ValuesIterator(toArray());}
	
	
	private class ValuesIterator implements Iterator<V>
	{
		/**Array contenente gli elementi della tabella hash.*/
		Object[] elements;
		/**Posizione corrente dell'iteratore.*/
		int cursor;
		
		/**Costruttore con un parametro: crea un iteratore sugli elementi della tabella hash che viene passata,
		 * posiziona il cursore il posizione 0.*/
		public ValuesIterator(Object[] elem)
		{
			cursor=0;
			elements=elem;
		}
		
		/**Verifica se è presente l'elemento successivo.
		 * @return true se è presente l'elemento successivo*/
		@Override
		public boolean hasNext() 
		{	return cursor<elements.length;	}

		/**Ritorna il valore successivo a quello corrente.
		 * @return elemento successivo nell'iterazione
		 * @throws NoSuchElementException se viene richiesto un elemento oltre l'ultimo*/
		@Override
		public V next() 
		{
			if(!hasNext())
				throw new NoSuchElementException();
			return (V)elements[cursor++];
		}	
	}

	//--------------------------------------Metodi da non implementare---------------------------------------------------
	/**Values non supporta l'aggiunta di un elemento, come previsto dalla documentazione del metodo values() di Map.
	 * @throws UnsupportedOperationException*/
	@Override
	public boolean add(V e) 
	{	throw new UnsupportedOperationException();	}

	/**Values non supporta l'aggiunta di elementi appartenenti ad una collezione, come previsto 
	 * dalla documentazione del metodo values() di Map.
	 * @throws UnsupportedOperationException*/
	@Override
	public boolean addAll(Collection<? extends V> c) 
	{	throw new UnsupportedOperationException();	}
	
}
