import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**Adapter dell'adaptee Set. Si implementa l'interfaccia target Set facendo uso di una hashtable.
 * Nel set non sono ammessi valori duplicati e per scelta implementativa nemmeno i valori nulli.
 * Poichè la hashtable prevede sia una chiave che un valore, si utilizza la chiave sia come chiave
 * che come valore.*/
public class SetObjectAdapter<E> implements Set<E>
{
	/**Storage dell set.*/
	protected Hashtable hashTable;
	
	/**Costruttore di default: crea lo storage del set, ovvero una hashtable.*/
	public SetObjectAdapter()
	{	hashTable=new Hashtable();	}
	
	/**Aggiunge l'elemento specificato al set se non è già contenuto. Si utilizza il metodo put() di hashtable
	 * per eseguire l'inserimento, ed il metodo containsKey() per verificare se la chiave è presente.
	 * @param e elemento da aggiungere al set
	 * @return true se l'elemento non era ancora presente nel set
	 * @throws NullPointerException se l'elemento passato è un riferimento null */
	@Override
	public boolean add(E e) 
	{
		if(e==null)
			throw new NullPointerException();
		
		if(!hashTable.containsKey(e))
		{
			hashTable.put(e, e);
			return true;
		}
		return false;
	}

	/**Aggiunge tutti gli elementi della collezione specificata nel set se non sono già contenuti.
	 * Se la collezione passata è un set allora tale operazione rappresenta l'unione dei due insiemi.
	 * Si invoca per ogni elemento il metodo add() del set di riferimento.
	 * @param c collezione contenente gli elementi che devono essere aggiunti al set
	 * @return true se il set è cambiato dopo l'esecuzione di tale operazione, ovvero
	 * se la dimensione del set è aumentata almeno di una unità.
	 * @throws NullPointerException se la collezione passata è un riferimento null */
	@Override
	public boolean addAll(Collection<? extends E> c) 
	{
		if(c==null)
			throw new NullPointerException();
		Iterator<?> iter=c.iterator();
		int oldSize=size();
		
		while(iter.hasNext())
			add((E)iter.next()); //si delelga al metodo add la verifica che l'elemento non sia già presente
		return oldSize<size();
	}

	/**Rimuove tutti gli elementi dal set. Il set risulterà vuoto a seguito di questa chiamata.
	 * Si utilizza il metodo clear() di hashtable.*/
	@Override
	public void clear() 
	{	hashTable.clear();	}

	/**Ritorna true se il set contiene l'elemento specificato. Si utilizza il metodo contains() di hashtable.
	 * @param o elemento di cui bisogna verificare la presenza
	 * @return true se l'elemento è contenuto nel set
	 * @throws NullPointerException se l'elemento passato è un riferimento null*/
	@Override
	public boolean contains(Object o) 
	{
		if(o==null)
			throw new NullPointerException();
		return hashTable.containsKey(o);
	}

	/**Ritorna true se il set contiene tutti gli elementi della collezione specificata. Se la collezione
	 * specificata è un set allora tale operazione dice se il set passato è un sottinsieme di quello corrente.
	 *Si invoca ripetutamente il metodo containsKey() di hashtable.
	 * @param c collezione contenente gli elementi di cui bisogna verificare la presenza nel set
	 * @return true se tutti gli elementi della collezione sono contenuti anche nel set
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
			E elem=(E)iter.next();
			contains=hashTable.containsKey(elem);						
		}		
		return contains;
	}

	/**Ritorna true se il set no contiene elementi. Si verifica che la dimensione del set di riferimento sia nulla.
	 * @return true se non ci sono elementi nel set*/
	@Override
	public boolean isEmpty() 
	{	return size()==0;	}

	/**Ritorna un iteratore sugli elementi del set. Gli elementi sono ritornati secondo l'ordine
	 * imposto dall'enumerazione keys() della hashtable.
	 * @return ritorna un iteratore sugli elementi della lista*/
	@Override
	public Iterator<E> iterator() 
	{	return new SetIterator(this);	}

	/**Rimuove l'elemento specificato dal set se presente. Si utilizza il metodo remove() di hashtable.
	 * @param o oggetto da rimuovere dal set
	 * @return true se il set conteneva l'elemento specificato ed è stato rimosso con successo.
	 * @throws NullPointerException se l'elemento passato è un riferimento null.*/
	@Override
	public boolean remove(Object o) 
	{
		if(o==null)
			throw new NullPointerException();

		E elem=(E)hashTable.remove(o);
		if(elem==null)
			return false;
		return true;			
	}

	/**Rimuove dal set di riferimento tutti gli elementi che sono contenuti nella collezione specificata.
	 * Se l'operazione va a buon fine il risultato rappresenta la differanza tra i due set. Si utilizza
	 * ripetutamente il metodo remove() del set di riferimento per ogni elemento della collezione.
	 * @param c collezione contenente gli elementi che devono essere rimossi dal set
	 * @return true se a seguito dell'operazione il set risulta cambiato, ovvero se la dimensione
	 * è diminuita almeno di una unitò
	 * @throws NullPointerException se la collezione passata è un riferimento null*/
	@Override
	public boolean removeAll(Collection<?> c) 
	{
		if(c==null)
			throw new NullPointerException();
		
		Iterator<?> iter=c.iterator();
		int oldSize=size();
		
		while(iter.hasNext())			
			remove(iter.next());
		
		return oldSize>size();
	}

	/**Mantiene nel set solo gli elementi che sono contenuti anche nella collezione specificata. Quindi
	 * si rimuovono dal set corrente tutti gli elemento che non sono contenuti nella collezione specificata.
	 * @param c collezione contenente gli elementi da mantenere nel set
	 * @return true se a seguito dell'operazione il set risulta cambiato
	 * @throws NullPointerException se la collezione passata è un riferimento null*/
	@Override
	public boolean retainAll(Collection<?> c) 
	{
		if(c==null)
			throw new NullPointerException();
		
		Iterator<?> iter=c.iterator();
		int oldSize=hashTable.size();
		Hashtable oldHt=hashTable; //salva la vecchia hashtable
		hashTable=new Hashtable();	//crea una nuova hashtable di riferimento	
				
		while(iter.hasNext())
		{			
			E elem=(E)iter.next();
			if(oldHt.containsKey(elem))//se l'elemento era contenuto nella vecchia hashtable
				add(elem);			//allora si aggiunge alla nuova hashtable attraverso il
		}							//metodi di classe che controlla l'inserimento di duplicati
		
		return hashTable.size()!=oldSize;
	}

	/**Ritorna il numero di elementi presenti nel set.
	 * @return il numero di elementi contenuti nel set*/
	@Override
	public int size() 
	{	return hashTable.size();	}

	/**Ritorna un array contenente tutti gli elementi che sono contenuti nel set. Gli elementi
	 * vengono inseriti nell'array secondo l'ordine imposto dall'enumerazione keys() di hashtable.
	 * Nel set non viene mantenuto nessun riferimento all'array.
	 * @return array contenente gli elementi del set*/
	@Override
	public Object[] toArray() 
	{
		Object[] array=new Object[hashTable.size()];
		int i=0;
		for(Enumeration e=hashTable.keys();e.hasMoreElements();)
			array[i++]=e.nextElement();//chiavi e valori sono uguali in set, quindi si aggiungono le chiavi
		return array;
	}

	/**Classe che rapresenza un iteratore sugli elementi del set. Opera su una copia degli elementi
	 * del set ritornata dal metodo toArray().*/
	private class SetIterator implements Iterator<E>
	{
		/**Array contenente gli elementi del set.*/
		Object[] elements;
		/**Posizione corrente dell'iteratore.*/
		int cursor;
		
		/**Costruttore con un parametro: crea un iteratore sugli elementi del set che viene passato,
		 * posiziona il cursore il posizione 0.*/
		public SetIterator(Set<E> set)
		{
			cursor=0;
			elements=set.toArray();
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
		public E next() 
		{
			if(!hasNext())
				throw new NoSuchElementException();
			return (E)elements[cursor++];
		}	
	}
	
	/**Il set corrente lo si considera uguale ad un altro set se e solo se contengono 
	 * gli stessi elementi ed hanno la stessa dimensione.
	 * @param o oggetto da confrontare con il set corrente
	 * @throws NullPointerException se il set passato è un riferimento null
	 * @throws ClassCastException nel caso in cui l'oggetto passato non rappresenti un set*/
	public boolean equals(Object o)
	{	
		Set<?> c=(Set<?>)o;
		return size()==c.size() && containsAll(c);	
	}
	
	/**L'hashcode del set è la somma degli hashcode degli elementi che sono contenuti nel set.
	 * @return hashcode del set*/
	public int hashCode()
	{	
		int sum=0;
		for(Enumeration e=hashTable.keys();e.hasMoreElements();)
			sum+=e.nextElement().hashCode();
		return sum;
	}
	
	//------------------------------Metodi da non implementare---------------------------------------
	/**Returns an array containing all of the elements in this set; the runtime type of the returned array is that of the*/
	@Override
	public <T> T[] toArray(T[] a) 
	{	throw new UnsupportedOperationException();	}

	/**Creates a Spliterator over the elements in this set.*/
	@Override
	public Spliterator<E> spliterator() 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable set containing zero elements.*/
	@Override
	public <E> Set<E> of() 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable set containing one element.*/
	@Override
	public <E> Set<E> of(E e1) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable set containing an arbitrary number of elements.*/
	@Override
	public <E> Set<E> of(E... elements) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable set containing two elements.*/
	@Override
	public <E> Set<E> of(E e1, E e2) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable set containing three elements.*/
	@Override
	public <E> Set<E> of(E e1, E e2, E e3) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable set containing four elements.*/
	@Override
	public <E> Set<E> of(E e1, E e2, E e3, E e4) 
	{	throw new UnsupportedOperationException();	}
	
	/**Returns an immutable set containing five elements.*/
	@Override
	public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable set containing six elements.*/
	@Override
	public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable set containing seven elements.*/
	@Override
	public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7)
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable set containing eight elements.*/
	@Override
	public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8)
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable set containing nine elements.*/
	@Override
	public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) 
	{	throw new UnsupportedOperationException();	}

	/**Returns an immutable set containing ten elements.*/
	@Override
	public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10)
	{	throw new UnsupportedOperationException();	}
		
	/**Returns a possibly parallel Stream with this collection as its source.
	 * @throws UnsupportedOperationException */
	@Override
	public Stream<E> parallelStream() 
	{	throw new UnsupportedOperationException();	}

	/**Removes all of the elements of this collection that satisfy the given predicate.
	 * @throws UnsupportedOperationException */
	@Override
	public boolean removeIf(Predicate<? super E> filter)
	{	throw new UnsupportedOperationException();	}

	/**Returns a sequential Stream with this collection as its source.
	 *@throws UnsupportedOperationException */
	@Override
	public Stream<E> stream() 
	{	throw new UnsupportedOperationException();	}
}
