import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Vector;

/**Adapter dell'adaptee List. Si adatta l'interfaccia specificata facendo uso di un vector.
 * Nella lista per scelta implementativa non possono essere inseriti valori nulli.*/
public class ListObjectAdapter <E> implements List<E>
{
	/**Storage della lista.*/
	Vector vector; 
	
	/**Costruttore con un parametro.
	 * Inizializza una lista basata su un vector la cui dimensione è quella passata
	 * come paramtero.
	 * @param size dimensione della lista*/
	public ListObjectAdapter(int size)
	{	vector=new Vector(size);	}
	
	/**Costruttore di default.
	 * Inizializza una lista basata su un vector vuoto.*/
	public ListObjectAdapter()
	{	vector=new Vector();	}
	
	/**Inserisce l'elemento specificato nella posizione specificata nella lista. Fa uno
	 * shift verso destra degli elementi che erano presenti a partire da quella posizione.
	 * Per l'inserimento si sfrutta il metodo insertElementAt() di vector.
	 * @param e elemento da inserire
	 * @param index indice in cui inserire l'elemento
	 * @throws NullPointerException se l'elemento passato è un riferimento null,
	 * @throws IndexOutOfBoundsException se il metodo insertElementAt() di vector 
	 * lancia l'eccezione ArrayIndexOutOfBoundsException*/
	@Override
	public void add(int index, E element) 
	{
		if(element==null)
			throw new NullPointerException();
		try
		{
			vector.insertElementAt(element, index);	
		}catch(ArrayIndexOutOfBoundsException aiobe)
		{
			IndexOutOfBoundsException iobe= new IndexOutOfBoundsException();
			iobe.initCause(aiobe);
			throw iobe;
		}	
	}

	/**Si assicura che la lista corrente contenga l'elemento specificato come parametro.
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

	/**Aggiunge tutti gli elementi della collezione passata come paramtero alla lista
	 * corrente partendo dall'inidice specificato. Tutti gli elementi presenti a partire
	 * da quell'inidice in poi subiscono uno shift a destra di una posizione.
	 * Viene richiesto un iteratore alla collezione passata come parametro. Si itera su tutti
	 * gli elementi della collezione inserendo un elemento per volta nel vector attraverso il metodo addElement().
	 * L'operazione è andata a buon fine se la dimensione risulta incrementata almeno di una unità.
	 * @param index indice in cui iniziare l'inserimento degli elementi
	 * @param c collezione contenente gli elementi che devono essere aggiunti
	 * @return true se la collezione risulta cambiata, ovvero se la dimensione è aumentata
	 * @throws NullPointerException se viene passata una collezione il cui riferimento è null
	 * (non si testa che la collezione passata contenga elementi nulli poichè per scelte implementative
	 * questi non possono esservi inseriti), IndexOutOfBoundsException se (index&lt;0|| index&gt;=vector.size())*/
	@Override
	public boolean addAll(int index, Collection<? extends E> c) 
	{
		if(index<0 || index>vector.size())
			throw new IndexOutOfBoundsException();
		
		if(c==null)
			throw new NullPointerException();
		
		Iterator<?> iter=c.iterator();
		int oldSize=vector.size();
		int i=0;
		while(iter.hasNext())
		{
			try
			{
				E elem=(E)iter.next();
				vector.insertElementAt(elem, index+i++);
			}
			catch(ClassCastException cce)
			{
				//se il tipo non è compatibile catturo eccezione e non lo inserisco
			}			
		}
		
		return oldSize<vector.size();
	}

	/**Aggiunge tutti gli elementi della collezione specificata come paramtero alla lista corrente.
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

	/**Rimuove tutti gli elementi dalla lista corrente. Si richiama il metodo clear()
	 * sul vector di riferimento.*/
	@Override
	public void clear() 
	{	vector.clear();	}

	/**Ritorna valore true se la lista contiene l'elemento passato come parametro.
	 * Non sono ammessi elementi nulli. Si utilizza il metodo contains() del vector di riferimento.
	 * @param o elemento di cui bisogna verificare la presenza nella lista
	 * @return true se l'elemento è contenuto nella lista
	 * @throws NullPointerException se l'elemento passato è un riferimento null*/
	@Override
	public boolean contains(Object o) 
	{
		if(o==null)
			throw new NullPointerException();
		return vector.contains(o);				
	}

	/**Ritorna true se la lista corrente contiene tutti valori contenuti nella collezione
	 * che viene passata come parametro. Non sono ammesse collezioni nulle. Si itera sugli
	 * elementi della collezione passata come paramtero verificando tramite il metodo contains() di vector
	 * se l'elemento è contenuto. Se almeno un elemento non risulta presente il risultato è false.
	 * @param c collezione i cui elementi devono essere totalmente contenuti nella lista corrente
	 * @return true se tutti gli elementi della collezione sono contenuti nella lista corrente
	 * @throws NullPointerException se viene passata una collezione il cui riferimento è null*/
	@Override
	public boolean containsAll(Collection<?> c) 
	{
		if(c==null)
			throw new NullPointerException();
		
		Iterator<?> iter=c.iterator();
		boolean contains=true;
		//potrebbe lanciare class castException
		while(iter.hasNext() && contains)
		{			
			E elem=(E)iter.next();
			contains=vector.contains(elem);						
		}		
		return contains;
	}

	/**Ritorna l'elemento nella posizione specificata. Si utilizza il metodo elementAt()
	 * del vector di riferimento.
	 * @param index indice dell'elemento da ritornare
	 * @return elemento nella posizione specificate
	 * @throws IndexOutOfBoundsException se l'indice è fuori dal range (index&lt;0 || index&gt;=vector.size())*/
	@Override
	public E get(int index) 
	{
		if(index<0 || index>=vector.size())
			throw new IndexOutOfBoundsException();
		return (E)vector.elementAt(index);
	}

	/**Ritorna l'indice della prima occorrenza, all'interno della lista, dell'elemento
	 *  specificato come parametro oppure -1 se l'elemento non è presente. Si utilizza
	 *  il metodo indexOf() del vector di riferimento per eseguire la ricerca.
	 *  @param o elemento da cercare
	 *  @return indice della prima occorrenza dell'elemento specificato nella lista
	 *  @throws NullPointerException se l'elemento specificato è un riferimento null*/
	@Override
	public int indexOf(Object o) 
	{
		if(o==null)
			throw new NullPointerException();
		return vector.indexOf(o);
	}

	/**Ritorna true se la lista risulta vuota. Si utilizza il metodo size() di vector
	 * per verificare che la dimensione della lista sia 0.
	 * @return true se la dimensione della lista è 0.*/
	@Override
	public boolean isEmpty() 
	{	return vector.size()==0;	}

	/**Ritorna un iteratore collegato agli elementi della lista. Gli elementi sono
	 * ritornati dall'iteratore seguendo l'ordine posizionale che è presenta nel vector di riferimento.
	 * Si è implementato un iteratore lazy.
	 * @return iteratore sugli elementi della lista*/
	@Override
	public Iterator<E> iterator() 
	{	return new LazyListIterator();	}

	/**Ritorna l'indice dell'ultima occorrenza all'interno della lista dell'elemento
	 *  specificato come parametro oppure -1 se l'elemento non è presente. Si utilizza
	 *  il metodo lastIndexOf() del vector di riferimento per eseguire la ricerca.
	 *  @param o elemento da cercare
	 *  @return indice dell'ultima occorrenza dell'elemento specificato nella lista
	 *  @throws NullPointerException se l'elemento specificato è un riferimento null*/
	@Override
	public int lastIndexOf(Object o) 
	{
		if(o==null)
			throw new NullPointerException();
		return vector.lastIndexOf(o);
	}

	/**Ritorna un list iterator sugli elementi della lista corrente. Si è implementato 
	 * un iteratore lazy.
	 * @return list iterator sugli elementi della lista*/
	@Override
	public ListIterator<E> listIterator() 
	{	return new MyListIterator(vector);	}

	/**Ritorna un list iterator sugli elementi della lista corrente. L'iterazione
	 * parte dall'elemento in posizione index.
	 * @param index indice da cui far partire il cursore dell'iteratore
	 * @return list iterator sugli elementi della lista
	 * @throws IndexOutOfBoundsException se l'index appartiene al range (index&lt;0 || index&gt;vector.size()) */
	@Override
	public ListIterator<E> listIterator(int index) 
	{
		if(index<0 || index>vector.size())
			throw new IndexOutOfBoundsException();
		
		//creazione di un list iterator il cui puntatore corrente parte dall'indice index
		//specificato come parametro
		return new MyListIterator(vector,index);
	}

	/**Rimuove l'elemento dalla lista corrente nella posizione specificata dall'indice.
	 * A seguito della rimozione gli elementi della lista subiscono uno shift verso sinistra
	 * di una posizione. Si utilizza il metodo removeElementAt() del vector di riferimento.
	 * @param index indice dell'elemento da rimuovere
	 * @return l'elemento che è stato rimosso che si trovava precedentemente in quella posizione
	 * @throws IndexOutOfBoundsException se il metodo removeElementAt() di vector lancia
	 * l'eccezione ArrayIndexOutOfBoundsException*/
	@Override
	public E remove(int index) 
	{
		try
		{
			E elem=(E)vector.elementAt(index);
			vector.removeElementAt(index);
			return elem;
		}
		catch(ArrayIndexOutOfBoundsException aiobe)
		{
			IndexOutOfBoundsException iobe= new IndexOutOfBoundsException();
			iobe.initCause(aiobe);
			throw iobe;
		}			
	}

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

	/**Rimuove tutti gli elementi della lista di riferimento che sono presenti anche
	 * nella collezione che è stata passata come parametro. Dopo la chiamata 
	 * la lista corrente non conterrà elementi comuni con quella che è stata passata
	 * come parametro. Si itera sugli elementi della collezione passata, se è presente
	 * una corrispondenza tra un elemento della lista corrente e la seconda collezione
	 * allora si utilizza il metodo removeElement() di vector per rimuovere l'elemento.
	 * @param c collezione contenente gli elementi che devono essere rimossi
	 * @return true se la lista è cambiata dopo l'invocazione del metodo, ovvero se
	 * la dimensione della lista risulta diminuita di una unità
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

	/**Mantiene nella lista corrente solo gli elementi che sono contenuti anche nella
	 * collezione che viene passata come parametro. Rimuove dalla lista corrente
	 * tutti gli elementi che non sono contenuti nella collezione passata come parametro.
	 * Si itera sulla collezione passata, si crea un nuovo vector di appoggio
	 * ad ogni corrispondenza tra un elemento della lista corrente e quello della collezione
	 * passata come parametro si aggiunge l'elemento nel nuovo vector tramite il metodo addElement().
	 * @param c collezione i cui elementi devono essere mentenuti nella lista corrente
	 * @return true se la dimensione della lista è cambiata
	 * @throws NullPointerException se viene passata una collezione il cui riferimento è null*/
	@Override
	public boolean retainAll(Collection<?> c) 
	{
		if(c==null)
			throw new NullPointerException();
		
		Vector newVector=new Vector(vector.size());		
		int oldSize=vector.size();
		Iterator<?> iter=c.iterator();
		
		while(iter.hasNext())
		{			
			E elem=(E)iter.next();
			if(vector.contains(elem))
				newVector.addElement(elem);
		}	
		vector=newVector;
		
		return vector.size()!=oldSize;
	}

	/**Rimpiazza l'elemento della lista nella posizione indicata con l'elemento che viene
	 * specificato come parametro. Si utilizzano i metodi elementAt() e setElementAt() 
	 * del vector di riferimento. Il primo per ottenere il riferimento all'elemento
	 * desiderato il secondo per modificare l'elemento alla posizione specificata.
	 * @param index indice dell'elemento da rimpiazzare
	 * @param element elemento che deve essere memorizzato nella posizione specificata
	 * @return l'elemento che precedentemente si trovava nella posizione index
	 * @throws NullPointerException se l'elemento specificato è un riferimento null
	 * @throws IndexOutOfBoundsException se il metodo setElementAt() di vector lancia
	 * l'eccezione ArrayIndexOutOfBoundsException*/
	@Override
	public E set(int index, E element) 
	{
		if(element==null)
			throw new NullPointerException();	
		try
		{
			E old=(E)vector.elementAt(index);
			vector.setElementAt(element, index);
			return old;
		}
		catch(ArrayIndexOutOfBoundsException aiobe)
		{
			IndexOutOfBoundsException iobe= new IndexOutOfBoundsException();
			iobe.initCause(aiobe);
			throw iobe;
		}	
	}

	/**Ritorna il numero di elementi nella lista corrente. Si utilizza il metodo size()
	 * del vector di riferimento.
	 * @return il numero di elementi nella lista*/
	@Override
	public int size() 
	{	return vector.size();	}

	/**Ritorna una vista della porzione della lista tra gli indici specificati, fromIndex
	 * incluso e toIndex escluso. La lista ritornata si appoggia sulla lista corrente,
	 * quindi cambiamenti non stutturali si vanno a riflettere sulla lista corrente
	 * e vice-versa. Si crea un nuovo oggetto Sublist che si basa sul ListObjectAdapter
	 * corrente per la memorizzazione degli elementi.
	 * @param fromIndex indice di partenza (incluso) della sottolista
	 * @param toIndex indice superiore (escluso) della sottolista
	 * @return una sottolista contenente i soli elementi specificati
	 * @throws IndexOutOfBoundsException se l'indice appartiene al range (fromIndex&lt;0 || toIndex&gt;vector.size() || fromIndex&gt;toIndex)*/
	@Override
	public List<E> subList(int fromIndex, int toIndex) 
	{	
		if(fromIndex<0 || toIndex>vector.size() || fromIndex>toIndex)
			throw new IndexOutOfBoundsException();
		
		return new Sublist<E>(this,fromIndex,toIndex);	
	}

	/**Ritorna un array contenente tutti gli elementi che sono contenuti nella lista
	 * corrente. Gli elementi sono ordinati nell'array secondo l'ordine posizionale che
	 * è presente nel vector. 
	 * Si utilizza il metodo copyOf() del vector di riferimento per copiare gli elementi della
	 * lista corrente in un array avente la dimenzione della lista.
	 * @return array contenente tutti gli elementi della lista*/
	@Override
	public Object[] toArray() 
	{
		Object[] array=new Object[vector.size()];
		vector.copyInto(array);
		return array;
	}
	
	/**Ordina gli elementi della lista secondo l'ordine imposto dal comparator.
	 * Si è implementato l'algoritmo Bubble Sort.
	 * @param c condizione di ordinamento.*/
	@Override
	public void sort(Comparator<? super E> c)
	{
		//if(c==null)
			//throw new NullPointerException();
		
		for(int i = 0; i < vector.size(); i++) 
	    {
	        for(int j = 0; j < vector.size()-1; j++) {
	        //Se l' elemento j e maggiore del successivo allora si scambiano
	            if(c.compare((E)vector.elementAt(j),(E)vector.elementAt(j+1))>0 )
	            {
	                E tmp = (E) vector.elementAt(j);
	                vector.setElementAt(vector.elementAt(j+1), j);
	                vector.setElementAt(tmp,j+1);
	            }
	        }
	    }
	}

	/**Due liste si considerano uguali se e solo se contengono gli stessi elementie e hanno la stessa dimensione.
	 * @param o oggetto da confrontare con la lista corrente
	 * @throws NullPointerException se la lista passata è un riferimento nullo
	 * @throws ClassCastException non caso in cui l'oggetto passato non rappresenti una lista*/
	public boolean equals(Object o)
	{	
		List<?> c=(List<?>)o;
		return size()==c.size() && containsAll(c);
	}
	
	/**L'hashcode della lista è la somma degli hashcode degli elementi che sono contenuti nella lista.
	 * @return hashcode della lista*/
	public int hashCode()
	{	
		int sum=0;
		for(int i=0;i<vector.size();i++)
			sum+=vector.get(i).hashCode();
		return sum;
	}
	
	/**Implementa un itaratore lazy che è collegato al vector della lista
	 * di riferimento*/
	private class LazyListIterator implements Iterator<E>
	{
		/**Posizione corrente dell'iteratore.*/
		int cursor;
		
		/**Costruttore di default: inizializza a 0 la posizione del cursore.*/
		public LazyListIterator()
		{	cursor=0;	}
		
		/**Verifica se è presente l'elemento successivo.
		 * @return true se è presente l'elemento successivo*/
		public boolean hasNext() 
		{	return cursor<vector.size();	}

		/**Ritorna il valore successivo a quello corrente.
		 * @return elemento successivo nell'iterazione
		 * @throws NoSuchElementException se viene richiesto un elemento oltre l'ultimo*/
		public E next() 
		{
			if(!hasNext())
				throw new NoSuchElementException();
			return (E)vector.elementAt(cursor++);
		}		
	}
	
	private class MyListIterator implements ListIterator<E>
	{
		/**Riferimento allo storage della lista su cui si sta iterando*/
		Vector list; 
		/**Posizione corrente dell'iteratore*/
		int cursor; 
		/**Indice corrispondente all'ultimo elemento referenziato*/
		int lastIndex; 
		
		/**Costruttore con un parametro: inizializza il cursore alla posizione iniziale 0, memorizza
		 * il riferimento allo storage della lista.
		 * @param v vector di riferimento su cui si basa l'iteratore*/
		public MyListIterator(Vector v)
		{	
			cursor=0;
			list=v;
		}
		
		/**Costruttore con due parametri: inizializza il cursore alla posizione iniziale definita dal
		 * parametro index, memorizza il riferimento allo storage della lista.
		 * @param v vector di riferimento su cui si basa l'iteratore
		 * @param index indice a cui inizializzare il cursore dell'iteratore*/
		public MyListIterator(Vector v, int index)
		{	
			cursor=index;
			list=v;
		}
		
		/**Verifica se è presente l'elemento successivo.
		 * @return true se è presente l'elemento successivo*/
		public boolean hasNext() 
		{	return cursor<vector.size();	}

		/**Ritorna il valore successivo a quello corrente.
		 * @return elemento successivo nell'iterazione
		 * @throws NoSuchElementException se viene richiesto un elemento oltre l'ultimo*/
		public E next() 
		{
			if(!hasNext())
				throw new NoSuchElementException();
			
			lastIndex=cursor;//si salva l'ultima posizione richiesta
			E elem=(E)list.elementAt(cursor++);
			return elem;
		}

		/**Inserisce l'elemento immediatamente prima dell'elemento che sarebbe tornato da next()
		*quindi si inserisce in posizone cursor e tale elemento diventa il prossimo next.*/
		public void add(E arg) 
		{	list.insertElementAt(arg, cursor);	}

		/**Verifica se è presente un elemento prima di quello corrente.
		 * @param true se è presente un elemento prima di quello corrente*/
		public boolean hasPrevious() 
		{ return cursor>0;	}

		/**Ritorna l'indice dell'elemento successivo a quello corrente.
		 * @return indice dell'elemento successivo a quello corrente*/
		public int nextIndex() 
		{	return cursor+1;	}

		/**Ritorna l'elemento precedente a quello corrente.
		 * @return elemento precedente e quello corrente
		 * @throwsn NoSuchElementException se viene richiesto un elemento prima del primo.*/
		public E previous() 
		{
			if(!hasPrevious())
				throw new NoSuchElementException();
				
			lastIndex=cursor;
			E elem=(E)vector.elementAt(--cursor);
			return elem;
		}

		/**Ritorna l'indice precedente a quello corrente.
		 * @return indice precedente alla posizione corrente*/
		public int previousIndex() 
		{	return cursor-1;	}

		/**Rimuove l'ultimo elemento che è stato tornato da previous() o da next().*/
		public void remove() 
		{	list.removeElementAt(lastIndex);	}

		/**Modifica l'ultimo valore ritornato da next() o da previous().
		 * @param arg - nuovo elemento che sostuis*/
		public void set(E arg) 
		{	list.setElementAt(arg, lastIndex); }		
	}
	
	//-------------------------------------Metodi da non implementare---------------------------------------------
	/**Ritorna un array contenenete tutti gli elementi della lista corrente, il
	 * runtime type dell'array ritornato è quello corrispondente all'array passato come
	 * parametro.
	 * @throws UnsupportedOperationException poichè in J2ME non è possibile definire il tipo
	 * a runtime in quanto manca la libreria che permette di utilizzare reflection*/
	@Override
	public <T> T[] toArray(T[] a) 
	{	throw new UnsupportedOperationException();	}
	
	/**Returns an immutable list containing zero elements.
	 * @throws UnsupportedOperationException*/
	public <E> List<E> of()
	{throw new UnsupportedOperationException();} 
	
	/**Returns an immutable list containing one element.
	 * @throws UnsupportedOperationException*/
	public <E> List<E> of(E e1)
	{throw new UnsupportedOperationException();}
	
	/**Returns an immutable list containing an arbitrary number of elements.
	 * @throws UnsupportedOperationException*/
	public <E> List<E> of(E... elements)
	{throw new UnsupportedOperationException();}
	
	/**Returns an immutable list containing two elements.
	  * @throws UnsupportedOperationException*/
	public <E> List<E> of(E e1, E e2)
	{throw new UnsupportedOperationException();}
	
	/**Returns an immutable list containing three elements.
	 * @throws UnsupportedOperationException*/
	public <E> List<E> of(E e1, E e2, E e3)
	{throw new UnsupportedOperationException();}
	
	/**Returns an immutable list containing four elements.
	 *  * @throws UnsupportedOperationException*/
	public <E> List<E> of(E e1, E e2, E e3, E e4)
	{throw new UnsupportedOperationException();}
	
	/**Returns an immutable list containing five elements.
	  * @throws UnsupportedOperationException*/
	public <E> List<E> of(E e1, E e2, E e3, E e4, E e5)
	{throw new UnsupportedOperationException();}
	
	/**Returns an immutable list containing six elements.
	 * @throws UnsupportedOperationException*/
	public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6)
	{throw new UnsupportedOperationException();}
	
	/**Returns an immutable list containing seven elements.
	  * @throws UnsupportedOperationException*/
	public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7)
	{throw new UnsupportedOperationException();}
	
	/**Returns an immutable list containing eight elements.
	 * @throws UnsupportedOperationException*/
	public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8)
	{throw new UnsupportedOperationException();}
	
	/**Returns an immutable list containing nine elements.
	 * @throws UnsupportedOperationException*/
	public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9)
	{throw new UnsupportedOperationException();}
	
	/**Returns an immutable list containing ten elements.
	  * @throws UnsupportedOperationException*/
	public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8,E e9, E e10)
	{throw new UnsupportedOperationException();}
	
	/**Replaces each element of this list with the result of applying the operator to that element.
	  * @throws UnsupportedOperationException*/
	public void replaceAll(UnaryOperator<E> operator)
	{throw new UnsupportedOperationException();}
	
	/**Creates a Spliterator over the elements in this list.
	 * @throws UnsupportedOperationException*/
	public Spliterator<E> spliterator()
	{throw new UnsupportedOperationException();}
	
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

	/**Returns a sequential Stream with this collection as its source.
	 *@throws UnsupportedOperationException */
	@Override
	public Stream<E> stream() 
	{throw new UnsupportedOperationException();}
}