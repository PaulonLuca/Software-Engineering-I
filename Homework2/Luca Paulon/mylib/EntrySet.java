import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

/**EntrySet è un class adapter che serve per adattare un set per fare im modo che vengano visualizzate
 * le entry dell'hashtable attraverso l'interfaccia Map.Entry. Si è optato per un class adapter e non
 *  per un object adapter poichè molti metodi del SetObjectAdapter vengono utilizzati anche nel class adapter.
 * La vista delle entry utilizza come backing storage l'hashtable della mappa in modo tale che
 * ogni modifica apportata alla mappa sia visibile nell'hashtable e viceversa.
 * Per utilizzare in modo corretto l'adapter corrente, le collezioni dovrebbero contenere delle Map.Entry
 * ed i parametri singoli passati dovrebbero essere anch'essi dei tipo che implementano Map.Entry.*/
public class EntrySet<E> extends SetObjectAdapter<E>
{
	/**Costruttore con un parametro: si inizializza l'hashtable del set con il riferimento
	 * dell'hashtable della mappa su cui si è invocato il metodo entrySet().
	 * @param htMappa riferimento alla hashtable della mappa sui cui si è invocato il metodo entrySet()*/
	public EntrySet(Hashtable htMappa)
	{	hashTable=htMappa;	}
	
	/**Rimuove l'entry specificata dal set se presente.Prima di rimuovere bisogna verificare che sia
	 * la chiave che il valore dell'entry di riferimento abbiano un mapping nell'hashtable.
	 * @param o entry da rimuovere dal set
	 * @return true se il set conteneva l'entry specificata ed è stata rimossa con successo.
	 * @throws NullPointerException se l'entry passata è un riferimento null.*/
	@Override
	public boolean remove(Object o) 
	{
		if(o==null)
			throw new NullPointerException();

		Map.Entry<Object, Object> entry= (Map.Entry<Object, Object>)o;
		//chiave presente e valore corrispondente alla chiave corretto allora si rimuove
		if(contains(entry))
		{
			hashTable.remove(entry.getKey());
			return true;
		}
		return false;			
	}
	
	/**Ritorna true se il set contiene l'entry specificata. Si verifica prima che la chiave dell'entry
	 * passata sia presente nell'hashtable, se è presente si verifica che il valore dell'entry
	 * passata corrisponda con il valore corrispondente alla chiave. Solo in questo caso l'entry è contenuta.
	 * @param o entry di cui bisogna verificare la presenza
	 * @return true se l'entry è contenuta nel set
	 * @throws NullPointerException se l'entry passata è un riferimento null*/
	@Override
	public boolean contains(Object o) 
	{
		if(o==null)
			throw new NullPointerException();
		Map.Entry<Object, Object> entry= (Map.Entry<Object, Object>)o;
		//se la chiave è presente si guarda anche il valore associato, i tal caso se corretto true altrimenti false
		//altrimenti se la chiave non c'è allora si ritorna false
		if(hashTable.containsKey(entry.getKey()))
			return (hashTable.get(entry.getKey())).equals(entry.getValue());
		return false;
	}
	
	/**Ritorna true se il set contiene tutte le entry della collezione specificata. Se la collezione
	 * specificata è un set allora tale operazione dice se il set passato è un sottinsieme di quello corrente.
	 * Si è sovrascritto questo metodo poichè bisogna verificare nella mappa la presenza della chiave
	 * corrispondente all'entry specificata.
	 * @param c collezione contenente le entry di cui bisogna verificare la presenza nel set
	 * @return true se tutti le entry della collezione sono contenute anche nel set
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
			Map.Entry<Object, Object> entry= (Map.Entry<Object, Object>)iter.next();
			contains=contains(entry);							
		}		
		return contains;
	}
	
	/**Mantiene nel set solo le entry che sono contenute anche nella collezione specificata. Quindi
	 * si rimuovono dal set corrente tutte le coppie chiave valore che non sono contenute nella collezione specificata.
	 * Per poter rimuovere le entry non presenti si verifica se ogni entry della mappa, e quindi dell'entrySet,
	 * è contenuta nella collezione. In caso affermativo l'entry viene mantenuta altrimenti viene rimossa.
	 * @param c collezione contenente gli elementi da mantenere nel set
	 * @return true se a seguito dell'operazione il set risulta cambiato
	 * @throws NullPointerException se la collezione passata è un riferimento null*/
	@Override
	public boolean retainAll(Collection<?> c) 
	{
		if(c==null)
			throw new NullPointerException();
		
		Object[] array=toArray();//array con tutte le entry della mappa
		int oldSize=size();
		boolean contained=false;
		for(int i=0;i<oldSize;i++)//per ogni entry del set
		{
			Map.Entry<Object, Object> entry= (Map.Entry<Object, Object>)array[i]; //si ottiene la entry
			Iterator<?> iter=c.iterator();//si richiede un'iteratore alla collezione per ogni entry del set
			while(iter.hasNext() && !contained)//si verifica se l'entry è contenuta nalla collezione
			{
				Map.Entry<Object, Object> elemEntry= (Map.Entry<Object, Object>)iter.next();
				if(elemEntry.getKey().equals(entry.getKey()) && elemEntry.getValue().equals(entry.getValue()))
					contained=true;
			}
			if(!contained)//se non è contenuta si rimuove dalla mappa
				remove(entry);
			
			contained=false;
		}
		return size()!=oldSize;
	}
	
	/**Ritorna un array contenente tutte le entry che sono contenute nella mappa. Le Map.Entry
	 * vengono inserite nell'array secondo l'ordine imposto dall'enumerazione keys() di hashtable.
	 * Nel set non viene mantenuto nessun riferimento all'array. Si sovrascrive il metodo del
	 * SetObjectAdapter poichè bisogna elaborare sia le chiavi e sia i valori, nel SetObjectAdapter
	 * si elaboravano solo le chiavi.
	 * @return array contenente i valori del set*/
	@Override
	public Object[] toArray() 
	{
		Object[] array=new Object[hashTable.size()];
		int i=0;
		for(Enumeration e=hashTable.keys();e.hasMoreElements();)
		{
			Object key=e.nextElement();
			array[i++]=new MapObjectAdapter.SimpleEntry<Object, Object>(key,hashTable.get(key));
		}
			//chiavi e valori sono uguali in set, quindi si aggiungono le chiavi
		return array;
	}
			
	//--------------------------------------Metodi da non implementare--------------------------------------
	/**EntrySet non supporta l'aggiunta di un elemento, come previsto dalla documentazione del metodo entrySet() di Map.
	 * @throws UnsupportedOperationException*/
	@Override
	public boolean add(E e) 
	{	throw new UnsupportedOperationException();	}

	/**EntrySet non supporta l'aggiunta di elementi appartenenti ad una collezione, come previsto 
	 * dalla documentazione del metodo entrySet() di Map.
	 * @throws UnsupportedOperationException*/
	@Override
	public boolean addAll(Collection<? extends E> c) 
	{	throw new UnsupportedOperationException();	}
}