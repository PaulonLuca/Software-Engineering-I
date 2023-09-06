import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**Ogni istanza di sublist si basa su una lista di riferimento che risulta essere il backing
 * storage della sottolista. Quindi ogni modifica che viene eseguita sulla sottolista si riflette sulla lista
 * originale.*/
public class Sublist<E> implements List<E>
{
	/**Storage della sottolista*/
	List<E> lista;	
	/**Indice di partenza della sottolista riferito allo storage.*/
	int fromIndex;	
	/**Indice di arrivo della sottolista riferito allo storage, tale indice non è compreso nel range.*/
	int toIndex;//non compreso
	
	/**Costruttore con e paramteri: crea una sottolista il cui storage è la lista passata
	 * ed avente come limiti inferiore e superiore i due indici passati.
	 * @param original lista utilizzata come storage
	 * @param from indice di partenza riferito allo storage
	 * @param to indice di arrivo riferito allo storage*/
	public Sublist(List<E> original, int from, int to)
	{
		lista=original;
		fromIndex=from;
		toIndex=to;
	}
	
	/**Inserisce l'elemento specificato nella posizione specificata nella sottolista. Fa uno
	 * shift verso destra degli elementi che erano presenti a partire da quella posizione.
	 * Per l'inserimento si sfrutta il metodo add() della lista di riferimento.Si incrementa toIndex
	 * ovvero la vista diventa più ampia.
	 * @param e elemento da inserire
	 * @throws NullPointerException se l'elemento passato è un riferimento null,
	 * @throws IndexOutOfBoundsException se il metodo insertElementAt() di vector 
	 * lancia l'eccezione ArrayIndexOutOfBoundsException*/
	@Override
	public void add(int index, E element) 
	{
		int listIndex=index+fromIndex;
		if(listIndex<fromIndex || listIndex>=toIndex)
			throw new IndexOutOfBoundsException();
		
			lista.add(listIndex, element);	
			toIndex++;
	}

	/**Aggiunge l'elemento dopo l'ultimo elemento valido della sottolista.
	 * L'elemento viene aggiunto anche nella lista di riferimento. Si incrementa toIndex
	 * ovvero la vista diventa più ampia.
	 * @param e elemento da aggiungere
	 * @return true se l'inserimento nella sottolista va sempre a buon fine
	 * @throws NullPointerException se l'elemento passato è un riferimento null
	 * (viene lanciata dalla lista di riferimento)*/
	@Override
	public boolean add(E e) 
	{
		lista.add(toIndex++,e);
		return true;
	}

	/**Aggiunge tutti gli elementi della collezione nella sottolista partendo dall'indice
	 * specificato come paramtero. Si aggiorna la posizione di toIndex incrementandola
	 * di 1 per ogni elemento inserito. Gli elementi vengono aggiunti anche nella lista di riferimento.
	 * Si incrementa toIndex ovvero la vista diventa più ampia ad ogni elemento inserito.
	 * @param index indice da cui inizia l'inserimento degli elementi
	 * @param c collezione contenente gli elementi da inserire
	 * @return true se l'indice appartiene all'intervallo (listIndex&lt;fromIndex|| listIndex&gt;=toIndex)
	 * e quindi tutti gli elementi vegono inseriti.*/
	@Override
	public boolean addAll(int index, Collection<? extends E> c) 
	{
		if(c==null)
			throw new NullPointerException();
		
		int listIndex=fromIndex+index;
		if(listIndex>=fromIndex && listIndex<=toIndex)
			{				
				Iterator<?> iter=c.iterator();
				while(iter.hasNext())
				{
					lista.add(listIndex,(E)iter.next());
					toIndex++;
				}					
				return true;
			}
		return false;
	}

	/**Aggiunge tutti gli elementi della collezione nella sottolista partendo dalla
	 * posizione di toIndex, ad ogni inserimento si aggiorna incrementa di 1 toIndex.
	 * Gli elementi vengono aggiunti anche nella lista di riferimento. Si incrementa toIndex
	 * ovvero la vista diventa più ampia al ogni elemento aggiunto.
	 * @param c collezione contenente gli elementi da inserire
	 * @return true perchè vengono inseriti tutti gli elementi
	 * @throws NullPointeException se la collezione passata è un riferimento null*/
	@Override
	public boolean addAll(Collection<? extends E> c) 
	{
		if(c==null)
			throw new NullPointerException();
		Iterator<?> iter=c.iterator();
		while(iter.hasNext())
		{
			lista.add(toIndex++,(E)iter.next());
		}					
		return true;
	}

	/**Cancella il contenuto della sottlista che risulterà quindi vuota. Vengono rimossi
	 * anche tutti gli elementi della lista che risultano compresi tra fromIndex (incluso)
	 * e toIndex (escluso). Al termine fromIndex coincide con toIndex.*/
	@Override
	public void clear() 
	{
		int size=toIndex-fromIndex;
		for(int i=0;i<size;i++)
			lista.remove(fromIndex);
		fromIndex=toIndex;
	}

	/**Ritorna valore true se la sottolista contiene l'elemento passato come parametro.
	 * Non sono ammessi elementi nulli. Si utilizza il metodo indexOf() della sottolista,
	 * se l'indice corrispondente all'elemento è diverso da -1 allora l'elemento è presente nella
	 * sottolista, 
	 * @param o elemento di cui bisogna verificare la presenza nella sottolista
	 * @return true se l'elemento è presente nella lista, quindi se l'indice appartiene al range
	 * di indici validi (index&gt;fromIndex && index&lt;toIndex)
	 * @throws NullPointerException se l'elemento passato è un riferimento null*/
	@Override
	public boolean contains(Object o)
	{
		if(indexOf(o)==-1)
			return false;
		return true;
	}

	/**Ritorna true se la sottolista contiene tutti valori contenuti nella collezione passata 
	 * come parametro. Non sono ammesse collezioni nulle. Si itera sugli elementi della
	 *  collezione passata come paramtero verificando tramite il metodo contains() della sottolista
	 * se l'elemento è contenuto. Se almeno un elemento non risulta presente il risultato è false.
	 * @param c collezione i cui elementi devono essere totalmente contenuti nella sottolista 
	 * @return true se tutti gli elementi della collezione sono contenuti nella lista corrente
	 * @throws NullPointerException se viene passata una collezione il cui riferimento è null*/
	@Override
	public boolean containsAll(Collection<?> c) 
	{
		Iterator<?> iter=c.iterator();
		boolean contained=true;
		while(iter.hasNext() && contained)
			contained=contains(iter.next());
		return contained;				
	}

	/**Ritorna l'elemento nella posizione specificata. Si utilizza il metodo get() della lista di riferimento.
	 * @param index indice dell'elemento da ritornare
	 * @return elemento nella posizione specificate
	 * @throws IndexOutOfBoundsException se l'indice è fuori dal range (listIndex&lt;fromIndex|| listIndex&gt;=toIndex)*/
	@Override
	public E get(int index) 
	{
		int listIndex=index+fromIndex;
		if(listIndex<fromIndex || listIndex>=toIndex)
			throw new IndexOutOfBoundsException();
		return lista.get(listIndex);
	}

	/**Ritorna l'indice della prima occorrenza, all'interno della sottolista, dell'elemento
	 *  specificato come parametro oppure -1 se l'elemento non è presente. Si utilizza
	 *  il metodo indexOf() della lista di riferimento. In particolare se l'elemento è contenuto
	 *  nella lista ed ha un indice compreso nel range (index&gt;fromIndex && index&lt;toIndex) allora
	 *  lo si considera un indice valido e viene ritornato.
	 *  @param o elemento da cercare
	 *  @return indice della prima occorrenza dell'elemento specificato nella sottolista
	 *  @throws NullPointerException se l'elemento specificato è un riferimento null
	 *  (viene lanciata dalla lista di riferimento)*/
	@Override
	public int indexOf(Object o) 
	{
		int index=lista.indexOf(o);
		//indice non appartenente alla vista
		if(index<fromIndex || index>=toIndex)
			return -1;
		return index-fromIndex;//indice relativo
	}

	/**Ritorna true se la sublist risulta vuota. Si verifica che la dimensione della sublist sia 0.
	 * @return true se la dimensione della lista è 0.*/
	@Override
	public boolean isEmpty() 
	{	return size()==0;	}

	/**Ritorna un iterator sugli elementi della sottolista corrente. In realtà si ritorna
	 * un list iterator di cui però si urilizza l'interfaccia costitutita dai soli metodi
	 * hasNext() e next(). Nell'iteratore si utilizza il riferimento alla sottolista.
	 * @return list iterator sugli elementi della sottolista*/
	@Override
	public Iterator<E> iterator() 
	{ return new MySublistIterator(this);	}

	/**Ritorna l'indice dell'ultima occorrenza all'interno della sottolista dell'elemento
	 *  specificato come parametro oppure -1 se l'elemento non è presente. Si utilizza
	 *  il metodo lastIndexOf() del vector di riferimento per eseguire la ricerca.
	 *  @param o elemento da cercare
	 *  @return indice dell'ultima occorrenza dell'elemento specificato nella  sottolista
	 *  @throws NullPointerException se l'elemento specificato è un riferimento null
	 *  (viene lanciata dalla lista di riferimento)*/
	@Override
	public int lastIndexOf(Object o) 
	{
		int index=lista.lastIndexOf(o);
		//indice non appartenente alla vista
		if(index<fromIndex || index>=toIndex)
			return -1;
		return index-fromIndex;//indice relativo
	}

	/**Ritorna un list iterator sugli elementi della sottolista corrente. Si è implementato 
	 * un iteratore lazy. Nell'iteratore si utilizza il riferimento alla sottolista.
	 * @return list iterator sugli elementi della sottolista*/
	@Override
	public ListIterator<E> listIterator() 
	{	return new MySublistIterator(this);}

	/**Ritorna un list iterator sugli elementi della lista corrente. L'iterazione
	 * parte dall'elemento in posizione index. Nell'iteratore si utilizza il riferimento alla sottolista.
	 * @param index indice da cui far partire il cursore dell'iteratore
	 * @return ListIterator sugli elementi della lista
	 * @throws IndexOutOfBoundsException si l'index appartiene al range (listIndex&lt;fromIndex|| listIndex&gt;=toIndex) */
	@Override
	public ListIterator<E> listIterator(int index) 
	{
		int listIndex=fromIndex+index;
		if(listIndex<fromIndex|| listIndex>=toIndex)
			throw new IndexOutOfBoundsException();
		return new MySublistIterator(this,listIndex);
	}

	/**Rimuove l'elemento dalla sottolista corrente nella posizione specificata dall'indice.
	 * A seguito della rimozione gli elementi della lista subiscono uno shift verso sinistra
	 * di una posizione. Si utilizza il metodo remove() della lista di riferimento. Ad ogni rimozione
	 * è necessario decrementare toIndex per restringere la vista poichè la lista di riferimento
	 * subisce una compattazione.
	 * @param index indice dell'elemento da rimuovere
	 * @return l'elemento che è stato rimosso che si trovava precedentemente in quella posizione
	 * @throws IndexOutOfBoundsException se l'indice appartiene al range (listIndex&lt;fromIndex|| listIndex&gt;=toIndex)*/
	@Override
	public E remove(int index) 
	{
		int listIndex=index+fromIndex;
		if(listIndex<fromIndex|| listIndex>=toIndex)
			throw new IndexOutOfBoundsException();
		toIndex--;//restingimento della vista della sottolista
		return lista.remove(listIndex);
	}

	/**Rimuove una singola istanza dell'elemento passato come parmetro se è presente.
	 * Si utilizza il metodo contains() della sottolista per verificare se l'elemento è 
	 * presente nel range di indici validi. Se presente viene rimossa la prima occorrenza trovata. 
	 * Ad ongi rimozione	è necessario decrementare toIndex per restringere la vista 
	 * poichè la lista di riferimento subisce una compattazione.
	 * @param o elemento da rimuovere
	 * @return true se l'elemento era presente e se è stato rimosso con successo
	 * @throws NullPointerException se l'elemento passato è un riferimento null
	 * (lanciata dal metodo remove() della lista di riferimento)*/
	@Override
	public boolean remove(Object o) 
	{
		boolean toRemove=contains(o);
		if(toRemove)
		{
			lista.remove(o);
			toIndex--;//restingimento della vista della sottolista
		}		
		return toRemove;
	}

	/**Rimuove tutti gli elementi della sottolista di riferimento che sono presenti anche
	 * nella collezione che è stata passata come parametro. Dopo la chiamata 
	 * la sottolista corrente non conterrà elementi comuni con quella che è stata passata
	 * come parametro. Si itera sugli elementi della collezione passata, se è presente
	 * una corrispondenza tra un elemento della sottolista corrente e la seconda collezione
	 * allora si utilizza il metodo remove() della sottolista per rimuovere l'elemento.
	 * @param c collezione contenente gli elementi che devono essere rimossi
	 * @return true se la sottolista è cambiata dopo l'invocazione del metodo, ovvero se
	 * la dimensione della sottolista risulta diminuita di una unità
	 * @throws NullPointerException se viene passata una collezione il cui riferimento è null*/
	@Override
	public boolean removeAll(Collection<?> c) 
	{
		if(c==null)
			throw new NullPointerException();
		Iterator<?> iter =c.iterator();
		int oldSize=size();
		while(iter.hasNext())
			remove(iter.next());
		if(oldSize>size())
			return true;
		return false;
	}

	/**Mantiene nella sottolista corrente solo gli elementi che sono contenuti anche nella
	 * collezione che viene passata come parametro. Rimuove dalla sottolista corrente
	 * tutti gli elementi che non sono contenuti nella collezione passata come parametro.
	 * Si itera sulla collezione passata, si verifica se l'elemento analizzato non è contenuto
	 * nella collezione. In tal caso si rimuove dalla sottolista.
	 * @param c collezione i cui elementi devono essere mentenuti nella sottolista corrente
	 * @return true se la dimensione della sottolista è cambiata
	 * @throws NullPointerException se viene passata una collezione il cui riferimento è null*/
	@Override
	public boolean retainAll(Collection<?> c) 
	{
		if(c==null)
			throw new NullPointerException();
		Iterator<?> iter =c.iterator();
		int oldSize=size();
		for(int i=0;i<size();i++)//ad ogni rimozione toIndex puù diminuire poichè si restringe la vista 
		{
			//se la collezione NON contiene l'elemento della sottolista allora tale
			//elemento deve essere rimosso dalla sottolista
			E elem=get(i);
			if(!c.contains(elem))
				remove(i--);	//ad ogni rimozione di deve decrementare l'indice perchè gli elementi
								//subiscono uno shift verso sinistra, quindi alcuni potrebbero non
								//essere analizzati
		}
		if(oldSize!=size())
			return true;
		return false;
	}

	/**Rimpiazza l'elemento della sottolista nella posizione indicata con l'elemento che viene
	 * specificato come parametro. Si utilizza il metodo set() della lista di riferimento.
	 * @param index indice dell'elemento da rimpiazzare
	 * element elemento che deve essere memorizzato nella posizione specificata
	 * @return l'elemento che precedentemente si trovava nella posizione index
	 * @throws NullPointerException se l'elemento specificato è un riferimento null (viene lanciata dalla lista di riferimento)
	 * @throws IndexOutOfBoundsException se l'indice appartiene al range (listIndex&lt;fromIndex|| listIndex&gt;=toIndex)*/
	@Override
	public E set(int index, E element) 
	{
		int listIndex=index+fromIndex;
		if(listIndex<fromIndex|| listIndex>=toIndex)
			throw new IndexOutOfBoundsException();
		return lista.set(listIndex, element);
		
	}
	
	/**Ritorna il numero di elementi presenti all'interno della sottolista come
	 * differenza tra fromIndex e toIndex.
	 * @return dimensione della sottolista*/
	@Override
	public int size()
	{	return toIndex-fromIndex; }

	/**Ordina gli elementi della sottolista secondo l'ordine imposto dal comparator.
	 * Si ordina attraverso Bubble sort solo la porzione di lista che è compresa tra i gli indici fromIndex, toIndex
	 * @param c condizione di ordinamento.*/
	@Override
	public void sort(Comparator<? super E> c) 
	{	
		//Bubble sort che ordina solo la porzione di lista selezionata
		int size=size();
		for(int i = 0; i < size; i++) 
	    {
	        for(int j = 0; j < size-1; j++) {
	        //Se l' elemento j e maggiore del successivo allora si scambiano
	            if(c.compare((E)get(j),(E)get(j+1))>0 )
	            {
	                E tmp = (E) get(j);
	                set(j,get(j+1));
	                set(j+1,tmp);
	            }
	        }
	    }	
	}

	/**Ritorna una vista della porzione della lista tra gli indici specificati, fromIndex
	 * incluso e toIndex escluso. La lista ritornata si appoggia sulla sottolista corrente,
	 * quindi cambiamenti non stutturali si vanno a riflettere sulla sottolista corrente
	 * e vice-versa.
	 * @param fromIndex indice di partenza (incluso) della sottolista
	 * @param toIndex indice superiore (escluso) della sottolista
	 * @return una sottolista contenente i soli elementi specificati
	 * @throws IndexOutOfBoundsException se l'indice appartiene al range (fromIndex&lt;0 || toIndex&gt;this.size() || fromIndex&gt;toIndex)*/
	@Override
	public List<E> subList(int fromIndex, int toIndex) 
	{	
		if(fromIndex<0 || toIndex>size() || fromIndex>toIndex)
			throw new IndexOutOfBoundsException();
		
		return new Sublist<E>(this,fromIndex,toIndex);	
	}

	/**Ritorna un array contenente tutti gli elementi che sono contenuti nella sottolista
	 * corrente. Gli elementi sono ordinati nell'array secondo l'ordine posizionale che
	 * è presente nella lista di riferimento. 
	 * @return array contenente tutti gli elementi della lista*/
	@Override
	public Object[] toArray() 
	{
		int size=toIndex-fromIndex;
		Object[] array=new Object[size];
		for(int i=0;i<size;i++)
			array[i]=lista.get(fromIndex+i);
		return array;
	}
	
	/**Classe che implementa un iteratore per la sottolista.*/
	private class MySublistIterator implements ListIterator<E>
	{
		/**Riferimento alla sottolista su cui si sta iterando.*/
		List<E> list;
		/**Posizione corrente nella sottolista.*/
		int cursor;
		/**Indice dell'ultimo elemento referenziato.*/
		int lastIndex; 
		
		/**Costruttore con un paramtero: crea un iteratore sulla lista passata come parametro.
		 * @param sublist sottolista su cui si crea l'iteratore*/
		public MySublistIterator(List<E> sublist)
		{	
			cursor=0;
			list=sublist;
		}
		
		/**Costruttore con 2 parametri: crea un iteratore il cui cursore parte dall'indice specificato.
		 * @param sublist sottolista su cui si crea l'iteratore
		 * @param start posizione da cui far pertire l'iterazione*/
		public MySublistIterator(List<E> sublist,int start)
		{	
			cursor=start;
			list=sublist;
		}
		
		/**Verifica se è presente l'elemento successivo.
		 * @return true se è presente l'elemento successivo*/
		public boolean hasNext() 
		{	return cursor<list.size();	}
		
		/**Ritorna il valore successivo a quello corrente.
		 * @return elemento successivo nell'iterazione
		 * @throws NoSuchElementException se viene richiesto un elemento oltre l'ultimo*/
		public E next() 
		{
			if(!hasNext())
				throw new NoSuchElementException();

			//ritorna l'elemento successivo ma tiene salvata l'ultima posizione richiesta
			lastIndex=cursor;
			E elem=list.get(cursor++);
			return elem;
		}

		/**Inserisce l'elemento immediatamente prima dell'elemento che sarebbe tornato da next()
		*quindi si inserisce in posizone cursor e tale elmento diventa il prossimo next.
		*Al termine dell'inserimento la lista diventa più ampia di una unità.*/
		public void add(E arg) 
		{	list.add(cursor,arg);}

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
			E elem=(E)list.get(--cursor);
			return elem;
		}

		/**Ritorna l'indice precedente a quello corrente.
		 * @return indice precedente alla posizione corrente*/
		public int previousIndex() 
		{	return cursor-1;	}

		/**Rimuove l'ultimo elemento che è stato tornato da previous() o da next().*/
		public void remove() 
		{	list.remove(lastIndex);	}

		/**Modifica l'ultimo valore ritornato da next() o da previous().
		 * @param arg nuovo elemento che sostuis*/
		public void set(E arg) 
		{	
			if(lastIndex==list.size()) //se dopo un'iterazione si è rimosso l'ultimo elemento last index
				lastIndex--;	   //avrebbe un valore non valido, non appartennente alla sottolista 
								   //va quindi aggiornato
			list.set(lastIndex, arg); 
		}		
	}
	
	
	//-----------------------------------------Metodi da non implementare----------------------------------------------
	/**Ritorna un array contenenete tutti gli elementi della lista corrente, il
	 * runtime type dell'array ritornato è quello corrispondente all'array passato come
	 * parametro.
	 * @throws UnsupportedOperationException poichè in J2ME non è possibile definire il tipo
	 * a runtime in quanto manca la libreria che permette di utilizzare reflection*/
	@Override
	public <T> T[] toArray(T[] a) 
	{throw new UnsupportedOperationException();}

	/**Returns an immutable list containing zero elements.
	 * @throws UnsupportedOperationException*/
	@Override
	public <E> List<E> of() 
	{throw new UnsupportedOperationException();}

	/**Returns an immutable list containing one element.
	 * @throws UnsupportedOperationException*/
	@Override
	public <E> List<E> of(E e1) 
	{throw new UnsupportedOperationException();}

	/**Returns an immutable list containing an arbitrary number of elements.
	 * @throws UnsupportedOperationException*/
	@Override
	public <E> List<E> of(E... elements) 
	{throw new UnsupportedOperationException();}

	/**Returns an immutable list containing two elements.
	  * @throws UnsupportedOperationException*/
	@Override
	public <E> List<E> of(E e1, E e2)
	{throw new UnsupportedOperationException();}

	/**Returns an immutable list containing three elements.
	  * @throws UnsupportedOperationException*/
	@Override
	public <E> List<E> of(E e1, E e2, E e3) 
	{throw new UnsupportedOperationException();}

	/**Returns an immutable list containing four elements.
	  * @throws UnsupportedOperationException*/
	@Override
	public <E> List<E> of(E e1, E e2, E e3, E e4) 
	{throw new UnsupportedOperationException();}

	/**Returns an immutable list containing five elements.
	  * @throws UnsupportedOperationException*/
	@Override
	public <E> List<E> of(E e1, E e2, E e3, E e4, E e5)
	{throw new UnsupportedOperationException();}

	/**Returns an immutable list containing six elements.
	  * @throws UnsupportedOperationException*/
	@Override
	public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6) 
	{throw new UnsupportedOperationException();}

	/**Returns an immutable list containing seven elements.
	  * @throws UnsupportedOperationException*/
	@Override
	public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) 
	{throw new UnsupportedOperationException();}

	/**Returns an immutable list containing eight elements.
	  * @throws UnsupportedOperationException*/
	@Override
	public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) 
	{throw new UnsupportedOperationException();}

	/**Returns an immutable list containing nine elements.
	  * @throws UnsupportedOperationException*/
	@Override
	public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) 
	{throw new UnsupportedOperationException();}

	/**Returns an immutable list containing ten elements.
	  * @throws UnsupportedOperationException*/
	@Override
	public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) 
	{throw new UnsupportedOperationException();}

	/**Replaces each element of this list with the result of applying the operator to that element.
	  * @throws UnsupportedOperationException*/
	@Override
	public void replaceAll(UnaryOperator<E> operator)
	{throw new UnsupportedOperationException();}

	/**Creates a Spliterator over the elements in this list.
	 * @throws UnsupportedOperationException*/
	@Override
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
