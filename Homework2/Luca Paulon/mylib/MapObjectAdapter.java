import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

/**Adapter dell'adaptee Map. Si implementa l'interfaccia target Map facendo uso di una hashtable.
 * Nella mappa non sono ammessi valori nulli nè come chiavi nè come valori.*/
public class MapObjectAdapter<K,V> implements Map<K,V>
{
	/**Storage della mappa*/
	Hashtable hashTable;
	
	/**Costruttore di default: crea una nuova hashtable che diventa lo storage della mappa*/
	public MapObjectAdapter()
	{ hashTable=new Hashtable();	}
	
	/**Rimuove tutte le coppie chiave valore dalla mappa.*/
	@Override
	public void clear()
	{hashTable.clear(); }

	/**Rtorna true se la mappa contiene una coppia chiave-valore la cui chiave è quella specificata.
	 * Si è utilizzato il metodo containsKey() di hashtable.
	 * @param key chiave di cui bisogna verificare la presenza nalla mappa
	 * @return true se la mappa contiene una coppia chiave-valore la cui chiave è quella pecificata
	 * @throws NullPointerException se la chiave specificata è un riferimento null*/
	@Override
	public boolean containsKey(Object key)
	{
		if(key==null)
			throw new NullPointerException();		
		return hashTable.containsKey(key);
	}

	/**Rtorna true se la mappa contiene una coppia chiave-valore il cui valore è quello specificato.
	 * Si è utilizzato il metodo contains() di hashtable.
	 * @param value valore di cui bisogna verificare la presenza nalla mappa
	 * @return true se la mappa contiene una coppia chiave-valore il cui valore è quello pecificato
	 * @throws NullPointerException se il valore specificata è un riferimento null*/
	@Override
	public boolean containsValue(Object value) 
	{
		if(value==null)
			throw new NullPointerException();		
		return hashTable.contains(value);
	}

	/**Ritorna un vista delle entry presenti all'interno della mappa attraverso un Set. Il Set utilizza
	 * la mappa come backing storage quindi ogni modifica apportata alla mappa viene vista anche
	 * nel set e vice-versa. Se durante un'iterazione vengono apportate delle modifiche alla mappa
	 * queste non vengono viste dall'iteratore. In particolare: in caso di aggiunta di un elemento questo
	 * viene ritornato se e solo se viene richiesto un nuovo iteratore al Set; in caso di rimozione il
	 * ragionamento è analogo, cioè un elemento non viene ritornato se e solo se viene richiesto 
	 * un nuovo iteratore che elabora i dati aggiornati.
	 * @return una vista basata su Set delle entry presenti nella mappa*/
	@Override
	public Set<Entry<K, V>> entrySet() 
	{	return new EntrySet<Map.Entry<K,V>>(hashTable);	}

	/**Ritorna il valore in corrispondenza del quale è mappata la chiave passata come parametro oppure 
	 * ritorna null se non è presente nessun mapping. Si è utilizzato il metodo get() di hashtable.
	 * @param key chiave associata al valore che deve essere ritornato
	 * @return il valore corrsipondente alla chiave passata se esiste una corrispondenza oppure
	 * null se non è presente nessuna corrispondenza nella mappa.
	 * @throws NullPointerException se la chiave specificata è un riferimento null*/
	@Override
	public V get(Object key) 
	{
		if(key==null)
			throw new NullPointerException();
		return (V)hashTable.get(key);
	}

	/**Ritorna il valore su cui è mappata la chiave specificata oppure il valore di default passato
	 * se non esiste alcun mapping per la chiave nalla mappa.
	 * @param key chiave associata al valore che deve essere ritornato
	 * @param defaultValue valore di default da ritornare nel caso la chiave non sia associata
	 * ad alcun valore
	 * @return il valore associato alla chiave specificata oppure il valore di default se 
	 * non esiste nessuna corrispondenza
	 * @throws NullPointerException se la chiave oppure il valore di default sono dei riferimenti null*/
	@Override
	public V getOrDefault(Object key, V defaultValue) 
	{
		if(key==null || defaultValue==null)
			throw new NullPointerException();
		
		if(hashTable.containsKey(key))
			return (V) hashTable.get(key);
		else
			return defaultValue;
	}

	/**Ritorna se la mappa non contiene alcuna coppia chiave valore.
	 * @return true se la mappa non contiene coppie chiave valore*/
	@Override
	public boolean isEmpty()
	{	return hashTable.size()==0; }

	/**Ritorna un vista delle chiavi presenti all'interno della mappa attraverso un Set. Il Set utilizza
	 * la mappa come backing storage quindi ogni modifica apportata alla mappa viene vista anche
	 * nel set e vice-versa. Se durante un'iterazione vengono apportate delle modifiche alla mappa
	 * queste non vengono viste dall'iteratore. In particolare: in caso di aggiunta di un elemento questo
	 * viene ritornato se e solo se viene richiesto un nuovo iteratore al Set; in caso di rimozione il
	 * ragionamento è analogo, cioè un elemento non viene ritornato se e solo se viene richiesto 
	 * un nuovo iteratore che elabora i dati aggiornati.
	 * @return una vista basata su Set delle chiavi presenti nella mappa*/
	@Override
	public Set<K> keySet() 
	{	return new KeySet(hashTable);	}

	/**Inserisce una coppia chiave valore nella mappa dove la chiave ed il valore sono quelli specificati.
	 * Se la mappa conteneva precedentemente un mapping corrispondente alla chiave passata allora si 
	 * aggiorna il valore corrispondente alla chiave con quello specificato. Si è utilizzato il metodo
	 * put() di hashtable.
	 * @param key chiave associata al valore specificato
	 * @param value valore associato alla chiave specificata
	 * @return il valore precedentemente associato alla chiave se è stato aggiornato oppure null se
	 * la coppia chiave valore non esisteva all'interno della mappa.
	 * @throws NullPointerException se o la chiave o il valore sono dei riferimenti null*/
	@Override
	public V put(K key, V value) 
	{
		if(key==null || value==null)
			throw new NullPointerException();
		return (V)hashTable.put(key, value);
	}

	/**Copia tutte le coppie chiave-valore presenti nella mappa specificata e le inserisce
	 * nella mappa corrente. Chiamare putAll() sulla mappa ha lo stesso effetto del'invocazione
	 * di put() per ogni elemento della mappa passata.
	 * @param m mappa contenente i valori da aggiungere
	 * @throws NullPointerException se la mappa passata è un riferimento null*/
	@Override
	public void putAll(Map<? extends K, ? extends V> m) 
	{
		if(m==null)
			throw new NullPointerException();
		
		Set<K> keySet=(Set<K>) m.keySet();
		Iterator<K> iter=keySet.iterator();
		while(iter.hasNext())
		{
			K key=iter.next();
			put(key,m.get(key));
		}
	}

	/**Se le chiave specificata non è ancora stata associata ad un valore nella mappa, la si inserisce
	 * associata al valore specificato. Si sono utilizzati i metodi get() e put di hashtable.
	 * @param key chiave da associare al valore spcificato
	 * @param value valore da associare alla chiave specificata
	 * @return il valore precedentemente associato alla chiave oppure null se non era precedentemente
	 * presente nessun mapping
	 * @throws NullPointerException se la chiave oppure il valore sono dei riferimenti null*/
	@Override
	public V putIfAbsent(K key, V value) 
	{
		if(key==null || value==null)
			throw new NullPointerException();
		
		V val=(V)hashTable.get(key);
		if(val==null)
			val=(V)hashTable.put(key,value);
		return val;
	}

	/**Rimuove la coppia chiave-valore associata alla chiave specificata. Si è utilizzato
	 * il metodo remove di hashtable. 
	 * @param key chiave associata alla coppia chiave valore da rimuovere
	 * @return il valore precedentemente associato alla chiave oppure null se non è presente 
	 * nessun mapping corrispondente alla chiave
	 * @throws NullPointerException se la chiave specificata è un riferimento null*/
	@Override
	public V remove(Object key) 
	{
		if(key==null)
			throw new NullPointerException();
		return (V)hashTable.remove(key);
	}

	/**Rimuove la entry specificata se e solo se esiste una entry all'interno della mappa avente chiave
	 * e valore corrispondenti alla chiave ed al valore specificati. Si sono utilizzati i metodi
	 * containsKey(),get() e remove() di hashtable.
	 * @param key chiave associata al valore specificato
	 * @param value valore associato alla chiave specificata
	 * @return true se l'entry è stata rimossa
	 * @throws NullPointerException se la chiave o il valore sono dei riferimenti null*/
	@Override
	public boolean remove(Object key, Object value) 
	{
		if(key==null || value==null)
			throw new NullPointerException();
		
		//se la tabella contiene la chiave e l'elemento corrispondente alla chiave è quello passato
		if(hashTable.containsKey(key) && value.equals(hashTable.get(key)))
		{
			hashTable.remove(key);
			return true;
		}
		else
			return false;			
	}

	/**Rimpiazza l'entry corrispondente alla chiave specificata con il valore specificato solo
	 * se esiste già un mapping per la chiave. Si è utilizzato il metodo put() di hashtable.
	 * @param key chiave corrispondente alla entry da sostutire
	 * @param value valore da associare alla chiave specificata
	 * @return il precedente valore associato alla chiave
	 * @throws NullPointerException se la chiave oppure il valore sono dei riferimenti null*/
	@Override
	public V replace(K key, V value) 
	{
		if(key==null || value==null)
			throw new NullPointerException();
		
		if(hashTable.containsKey(key))
			return (V)hashTable.put(key, value);
		else
			return null;
	}

	/**Rimpiazza l'entry corrispondente alla chiave specificata con il valore specificato solo
	 * se esiste già un mapping per la chiave. Si è utilizzato il metodo put() di hashtable.
	 * @param key chiave corrispondente alla entry da sostutire
	 * @param oldValue valore che ci si aspetta sia associato alla chiave
	 * @param newValue nuovo valore da associare alla chiave
	 * @return true se il rimpiazzamento è andato a buon fine
	 * @throws NullPointerException se la chiave oppure il vecchio valore  oppure il nuovo
	 * valore sono dei riferimenti null*/
	@Override
	public boolean replace(K key, V oldValue, V newValue)
	{
		if(key==null || oldValue==null || newValue==null)
			throw new NullPointerException();
		
		if(hashTable.containsKey(key) && oldValue.equals(hashTable.get(key)))
		{
			hashTable.put(key, newValue);
			return true;
		}
		else
			return false;		
	}

	/**Ritorna il numero di coppie chiave-valore mappate nella mappa.
	 * @return il numero di coppie chiave-valore presenti*/
	@Override
	public int size()
	{	return hashTable.size(); }
	
	/**Ritorna un vista degli elementi presenti all'interno della mappa attraverso un Set. Il Set utilizza
	 * la mappa come backing storage quindi ogni modifica apportata alla mappa viene vista anche
	 * nel set e vice-versa. Se durante un'iterazione vengono apportate delle modifiche alla mappa
	 * queste non vengono viste dall'iteratore. In particolare: in caso di aggiunta di un elemento questo
	 * viene ritornato se e solo se viene richiesto un nuovo iteratore al Set; in caso di rimozione il
	 * ragionamento è analogo, cioè un elemento non viene ritornato se e solo se viene richiesto 
	 * un nuovo iteratore che elabora i dati aggiornati.
	 * @return una vista basata su Set dei valori presenti nella mappa*/
	@Override
	public Collection<V> values() 
	{	return new Values<V>(hashTable);	}
	
	/**La mappa corrente la si considera uguale ad un'altra mappa se e solo se contengono 
	 * gli stessi elementi ed hanno la stessa dimensione.
	 * @param o oggetto da confrontare con la mappa corrente
	 * @throws NullPointerException se la mappa passata è un riferimento null
	 * @throws ClassCastException nel caso in cui l'oggetto passato non rappresenti una mappa*/
	public boolean equals(Object o)
	{	
		Map<K,V> m=(Map<K,V>)o;
		Set<Map.Entry<K, V>> eS1=entrySet();
		Set<Map.Entry<K, V>> eS2=m.entrySet();
		return m.size()==size() && eS1.containsAll(eS2);
	}
	
	/**L'hashcode della mappa è la somma degli hashcode delle entry che sono contenute nella mappa.
	 * @return hashcode del set*/
	public int hashCode()
	{	
		int sum=0;
		for(Enumeration e=hashTable.keys();e.hasMoreElements();)
		{
			K key=(K)e.nextElement();
			sum+=key.hashCode()+hashTable.get(key).hashCode();
		}
			
		return sum;
	}
	
	/**Classe che implementa l'interfaccia  Map.Entry<K, V>. Viene utilizzata per gestire
	 * le entry nel metodo entrySet().*/
	static public class SimpleEntry<K,V> implements Map.Entry<K, V>
	{
		K key;
		V value;
		
		/**Costruttore con due parametri: inizializza un'entry con la chiave ed il valore specificati.
		 * @param k chiave dell'entry
		 * @param v valore dell'entry*/
		public SimpleEntry(K k, V v)
		{
			key=k;
			value=v;
		}
		
		/**Ritorna la chiave associata all'entry.
		 * @return chiave associata all'entry*/
		@Override
		public K getKey() 
		{	return key;	}

		/**Ritorna il valore associato all'entry.
		 * @return valore associato all'entry*/
		@Override
		public V getValue() 
		{	return value;	}		
	}
	
	//-----------------------------Metodi da non implementare----------------------------------------
	/**Attempts to compute a mapping for the specified key and its current mapped value (or null if there is no current mapping).
	 * @throws UnsupportedOperationException*/
	@Override
	public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) 
	{	throw new UnsupportedOperationException();	}
	
	/**If the specified key is not already associated with a value (or is mapped to null), attempts to compute its value using the given mapping function and enters it into this map unless null.
	  * @throws UnsupportedOperationException*/
	@Override
	public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) 
	{	throw new UnsupportedOperationException();	}

	/**If the value for the specified key is present and non-null, attempts to compute a new mapping given the key and its current mapped value.
	 * @throws UnsupportedOperationException*/
	@Override
	public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) 
	{	throw new UnsupportedOperationException();	}

	 /**Returns an immutable Map.Entry containing the given key and value.
	  * @throws UnsupportedOperationException*/
	@Override
	public <K, V> Entry<K, V> entry(K k, V v) 
	{	throw new UnsupportedOperationException();	}

	/**Performs the given action for each entry in this map until all entries have been processed or the action throws an exception.
	 * @throws UnsupportedOperationException*/
	@Override
	public void forEach(BiConsumer<? super K, ? super V> action) 
	{	throw new UnsupportedOperationException();	}

	/**If the specified key is not already associated with a value or is associated with null, associates it with the given non-null value.
	 * @throws UnsupportedOperationException*/
	@Override
	public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable map containing zero mappings.
	 * @throws UnsupportedOperationException*/
	@Override
	public <K, V> Map<K, V> of() 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable map containing a single mapping.
	 * @throws UnsupportedOperationException*/
	@Override
	public <K, V> Map<K, V> of(K k1, V v1) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable map containing two mappings.
	 * @throws UnsupportedOperationException*/
	@Override
	public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable map containing three mappings.
	 * @throws UnsupportedOperationException*/
	@Override
	public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable map containing four mappings.
	 * @throws UnsupportedOperationException*/
	@Override
	public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable map containing five mappings.
	 * @throws UnsupportedOperationException*/
	@Override
	public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable map containing six mappings.
	 * @throws UnsupportedOperationException*/
	@Override
	public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable map containing seven mappings.
	 * @throws UnsupportedOperationException*/
	@Override
	public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable map containing eight mappings.
	 * @throws UnsupportedOperationException*/
	@Override
	public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8,V v8) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable map containing nine mappings.
	 * @throws UnsupportedOperationException*/
	@Override
	public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable map containing ten mappings.
	 * @throws UnsupportedOperationException*/
	@Override
	public <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable map containing keys and values extracted from the given entries.
	 * @throws UnsupportedOperationException*/
	@Override
	public <K, V> Map<K, V> ofEntries(Entry<? extends K, ? extends V>... entries) 
	{	throw new UnsupportedOperationException();	}

	/**Replaces each entry's value with the result of invoking the given function on that entry until all entries have been processed or the function throws an exception.
	 * @throws UnsupportedOperationException*/
	@Override
	public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) 
	{	throw new UnsupportedOperationException();	}
	
}
