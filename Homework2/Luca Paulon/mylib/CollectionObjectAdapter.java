import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

/**Adapter dell'adaptee Collection. Si implementa l'interfaccia taget Collection facendo uso di un vector.
 * Nella collezione non sono ammessi valori nulli. L'implementazione del seguente adapter è molto
 * simile a quella dell'adapter per la lista, ma nonostante le similituidi si è comunque deciso
 * si procedere nello sviluppo dell'adapter e dei corrispondenti test poichè il testo richiedeva
 * l'implementazione di 4 Adapter. Sono collection adapter anche ListObjectAdapter e SetObjectAdapter,
 * poichè implementano tutti l'intefaccia Collection, l'unica differenza è che il set non ammette elementi suplicati.*/
public class CollectionObjectAdapter<E> implements Collection<E>
{
	/**Storage della collezione*/
	Vector vector; 
	
	/**Costruttore con un parametro: inizializza una collezione basata su un vector la cui dimensione è quella passata
	 * come paramtero.
	 * @param size dimensione della collezione*/
	public CollectionObjectAdapter(int size)
	{	vector=new Vector(size);	}
	
	/**Costruttore di default: inizializza una collezione basata su un vector vuoto.*/
	public CollectionObjectAdapter()
	{	vector=new Vector();	}

	/**Si assicura che la collezione corrente contenga l'elemento specificato come parametro.
	 * Per l'inserimento si sfrutta il metodo addElement() di vector.
	 * @param e elemento da inserire
	 * @return true se l'elemento è stato inserito
	 * @throws NullPointerException se l'elemento passato è un riferimento null*/
	@Override
	public boolean add(E e) 
	{
		if(e==null)
			throw new NullPointerException();
		vector.addElement(e);
		return true;
	}

	/**Aggiunge tutti gli elementi della collezione specificata come paramtero alla collezione corrente.
	 * Viene richiesto un iteratore alla collezione passata come parametro. Si itera su tutti
	 * gli elementi della collezione inserendo un elemento per volta nel vector attraverso il metodo addElement().
	 * L'operazione è andata a buon fine se la dimensione risulta incrementata almeno di una unità.
	 * @param c collezione contenente gli elementi che devono essere aggiunti
	 * @return true se la collezione risulta cambiata, ovvero se la dimensione è aumentata
	 * @throws NullPointerException se viene passata una collezione il cui riferimento è null
	 * (non si testa che la collezione passata contenga elementi nulli poichè per scelte implementative
	 * questi non possono esservi inseriti)*/
	@Override
	public boolean addAll(Collection<? extends E> c) 
	{
		if(c==null)
			throw new NullPointerException();
		
		Iterator<?> iter=c.iterator();
		int oldSize=vector.size();
		while(iter.hasNext())
		{
			try
			{
				E elem=(E)iter.next();
				vector.addElement(elem);
			}
			catch(ClassCastException cce)
			{
				//se il tipo non è compatibile catturo eccezione e non lo inserisco
			}			
		}
		return oldSize<vector.size();
	}

	/**Rimuove tutti gli elementi dalla collezione corrente. Si richiama il metodo clear()
	 * sul vector di riferimento.*/
	@Override
	public void clear() 
	{	vector.clear();	}

	/**Ritorna valore true se la collezione contiene l'elemento passato come parametro.
	 * Non sono ammessi elementi nulli. Si utilizza il metodo contains() del vector di riferimento.
	 * @param o elemento da inserire nella collezione
	 * @return true se l'elemento è stato inserito con successo
	 * @throws NullPointerException se l'elemento passato è un riferimento null*/
	@Override
	public boolean contains(Object o) 
	{
		if(o==null)
			throw new NullPointerException();	
		return vector.contains(o);
	}

	/**Ritorna true se la collezione corrente contiene tutti valori contenuti nella collezione
	 * che viene passata come parametro. Non sono ammesse collezioni nulle. Si itera sugli
	 * elementi della collezione passata come parametro verificando tramite il metodo contains() di vector
	 * se l'elemento è contenuto. Se almeno un elemento non risulta presente il risultato è false.
	 * @param c collezione i cui elementi devono essere totalmente contenuti nella collezione corrente
	 * @return true se tutti gli elementi della collezione sono contenuti nella collezione corrente
	 * @throws NullPointerException se viene passata una collezione il cui riferimento è null*/
	@Override
	public boolean containsAll(Collection<?> c) 
	{
		if(c==null)
			throw new NullPointerException();
		
		//crezione di un iteratore specializzato con il tipo della collezione che viene passata come parametro
		Iterator<?> iter=c.iterator();
		boolean contains=true;
		//non appena un elemento non è presente termina il ciclo
		while(iter.hasNext() && contains)
		{			
			E elem=(E)iter.next();
			contains=vector.contains(elem);						
		}		
		return contains;
	}

	/**Ritorna true se la collezione risulta vuota. Si utilizza il metodo size() di vector
	 * per verificare che la dimensione della collezione sia 0.*/
	@Override
	public boolean isEmpty() 
	{	return vector.size()==0;	}

	/**Ritorna un iteratore collegato agli elementi della collezione. Gli elementi sono
	 * ritornati dall'iteratore seguendo l'ordine posizionale che è presenta nel vector di riferimento.
	 * Si è implementato un iteratore lazy.
	 * @return iteratore sugli elementi della collezione*/
	@Override
	public Iterator<E> iterator() 
	{	return new LazyCollectionIterator();	}

	/**Rimuove una singola istanza dell'elemento passato come parmetro se è presente.
	 * Si utilizza il metodo removeElement() di vector per effettuare la rimozione.
	 * Viene rimossa la prima occorrenza trovata. Non sono ammessi elementi nulli.
	 * @param o elemento da rimuovere
	 * @return true se l'elemento era presente e se è stato rimosso con successo
	 * @throws NullPointerException se l'elemento passato è un riferimento null*/
	@Override
	public boolean remove(Object o) 
	{
		if(o==null)
			throw new NullPointerException();		
		return vector.removeElement(o);
	}

	/**Rimuove tutti gli elementi della collezione di riferimento che sono presenti anche
	 * nella collezione che è stata passata come parametro. Dopo la chiamata 
	 * la collezione corrente non conterrà elementi comuni con quella che è stata passata
	 * come parametro. Si itera sugli elementi della collezione passata, se è presente
	 * una corrispondenza tra un elemento della collezione corrente e la seconda collezione
	 * allora si utilizza il metodo removeElement() di vector per rimuovere l'elemento.
	 * @param c collezione contenente gli elementi che devono essere rimossi
	 * @return true se la collezione è cambiata dopo l'invocazione del metodo, ovvero se
	 * la dimensione della collezione risulta diminuita di una unità
	 * @throws NullPointerException se viene passata una collezione il cui riferimento è null*/
	@Override
	public boolean removeAll(Collection<?> c) 
	{
		if(c==null)
			throw new NullPointerException();
		
		int oldSize=vector.size();
		Iterator<?> iter=c.iterator();
		//potrebbe lanciare class castException
		while(iter.hasNext())
		{			
			E elem=(E)iter.next();
			if(vector.contains(elem))
				vector.removeElement(elem);
		}		
		return vector.size()<oldSize;
	}

	/**Mantiene nella collezione corrente solo gli elementi che sono contenuti anche nella
	 * collezione che viene passata come parametro. Rimuove dalla collezione corrente
	 * tutti gli elementi che non sono contenuti nella collezione passata come parametro.
	 * Si itera sulla collezione passata come parametro, si crea un nuovo vector di appoggio
	 * ad ogni corrispondenza tra un elemento della collezione corrente e quello della collezione
	 * passata come parametro si aggiunge l'elemento nel nuovo vector tramite il metodo addElement().
	 * @param c collezione i cui elementi devono essere mentenuti nella collezione corrente
	 * @return true se la dimensione della collezione è cambiata
	 * @throws NullPointerException se viene passata una collezione il cui riferimento è null*/
	@Override
	public boolean retainAll(Collection<?> c) 
	{
		if(c==null)
			throw new NullPointerException();
		
		Vector newVector=new Vector(vector.size());	//nuovo storage	
		int oldSize=vector.size();
		Iterator<?> iter=c.iterator();
		
		while(iter.hasNext())
		{			
			E elem=(E)iter.next();
			if(vector.contains(elem))//se il vecchio storage contiene l'elemento esaminato nella collezione
				newVector.addElement(elem);	//si aggiunge l'elemento al nuovo storage
		}	
		vector=newVector;//si sostituisce il vecchio storage con quello nuovo
		
		return vector.size()!=oldSize;
	}

	/**Ritorna il numero di elementi nella collezione corrente. Si utilizza il metodo size()
	 * del vector di riferimento.
	 * @return il numero di elementi nella collezione*/
	@Override
	public int size() 
	{	return vector.size();	}

	/**Ritorna un array contenente tutti gli elementi che sono contenuti nella collezione
	 * corrente. Gli elementi sono ordinati nell'array secondo l'ordine posizionale che
	 * è presente nel vector. 
	 * Si utilizza il metodo copyOf() del vector di riferimento per copiare gli elementi della
	 * collezione corrente in un array avente la dimenzione della collezione.
	 * @return array contenente tutti gli elementi della collezione*/
	@Override
	public Object[] toArray() 
	{
		Object[] array=new Object[vector.size()];
		vector.copyInto(array);
		return array;
	}

	/**Ritorna un array contenenete tutti gli elementi della collezione corrente, il
	 * runtime type dell'array ritornato è quello corrispondente all'array passato come
	 * parametro.
	 * @throws UnsupportedOperationException poichè in J2ME non è possibile definire il tipo
	 * a runtime in quanto manca la libreria che permette di utilizzare reflection*/
	@Override
	public <T> T[] toArray(T[] a) 
	{	throw new UnsupportedOperationException();	}
	
	/**La collezione corrente la si considera uguale ad un'altra collezione se e solo se 
	 * contengono gli stessi elementi ed hanno la stessa dimensione.
	 * @param o collezione da confrontare con la lista corrente
	 * @throws ClassCastException nel caso in cui l'oggetto passato non rappresenti una collezione*/
	public boolean equals(Object o)
	{	
		Collection<?> c=(Collection<?>)o;
		return  size()==c.size() && containsAll(c) ;	
	}
	
	/**L'hashcode del set è la somma degli hashcode degli elementi che sono contenuti.
	 * @return hashcode del set*/
	public int hashCode()
	{	
		int sum=0;
		for(int i=0;i<vector.size();i++)
			sum+=vector.get(i).hashCode();
		return sum;
	}
	
	/**Returns a possibly parallel Stream with this collection as its source.
	 * @throws UnsupportedOperationException */
	@Override
	public Stream<E> parallelStream()
	{throw new UnsupportedOperationException();}
	
	/**Removes all of the elements of this collection that satisfy the given predicate.
	 * @throws UnsupportedOperationException */
	@Override
	public boolean removeIf(Predicate<? super E> filter)
	{throw new UnsupportedOperationException();}
		
	/**Creates a Spliterator over the elements in this collection.
	* @throws UnsupportedOperationException */
	@Override
	public Spliterator<E> spliterator()
	{throw new UnsupportedOperationException();}
	
	/**Returns a sequential Stream with this collection as its source.
	 *@throws UnsupportedOperationException */
	@Override
	public Stream<E> stream()
	{throw new UnsupportedOperationException();}
	
	/**Implementa un itaratore lazy che è collegato al vector della collezione
	 * di riferimento*/
	private class LazyCollectionIterator implements Iterator<E>
	{
		/**Posizione corrente dell'iteratore.*/
		int cursor;
		
		/**Costruttore di default: inizializza a 0 la posizione del cursore.*/
		public LazyCollectionIterator()
		{	cursor=0;	}
		
		/**Verifica se è presente l'elemento successivo.
		 * @return true se è presente l'elemento successivo*/
		public boolean hasNext() 
		{	return cursor<vector.size();	}

		/**Ritorna il valore successivo a quello corrente.
		 * @return elemento successivo nell'iterazione*/
		public E next() 
		{
			if(!hasNext())
				throw new NoSuchElementException();
			return (E)vector.elementAt(cursor++);
		}		
	}	
}
