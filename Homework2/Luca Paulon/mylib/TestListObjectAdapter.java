import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.ListIterator;

public class TestListObjectAdapter
{
	final int size=10;
	ListObjectAdapter<Object> adapter=new ListObjectAdapter<Object>(size);
	
	/** Si verifica il funzionamento inserendo un numero di elementi nella posizione
	 * indide definita, si confronta poi che gli elementi nelle posizioni occupate
	 * siano quelli corretti.*/
	@Test
	public void testAddIndex()
	{
		System.out.println("Inside method testAddIndex()");
		for(int i=0;i<size;i++)
			adapter.add(i,"stringa"+i);
		for(int i=0;i<size;i++)
			assertTrue(adapter.get(i).equals("stringa"+i));
	}
	
	/**Si testa l'eccezione lanciata dal metodo addIndex se viene inserito un indice al di fuori
	 * della dimensione della lista*/
	@Test(expected=IndexOutOfBoundsException.class)
	public void testAddIndexOutOfBoundException()
	{
		System.out.println("Inside method testAddIndexOutOfBoundException()");
		adapter.add(100,"stringa100");
	}
	
	/**Si testa l'eccezione lanciata dal metodo addIndex se viene inserito un elemento
	 * nullo*/
	@Test(expected=NullPointerException.class)
	public void testAddIndexNullException()
	{
		System.out.println("Inside method testAddIndexNullException()");
		adapter.add(0,null);
	}

	/**Test add() e size().
	 * Pre-condition: adapter inizializzato con 3 valori stringa0, stringa1, stringa2.
	 * Post-condition: oltre ai 10 valori interi inseriti tramite il metodo di startup
	 * si inseriscono altri 10 valori interi multipli di 10 (0, 10, 20 ...90).
	 * Test cases: si testa che ad ogni inserimento il valore di ritorno del metodo add sia 
	 * true. Infine si testa che la dimensione della lista sia diventata doppia.
	 * Execution variables: 10 interi da 0 a 9, ulteriori 10 interi da 0 a 90, variabile
	 * che contiene la dimensione attesa, l'adapter.
	 * Execution records: true ad ogni invocazione di add, uguaglianza tra dimensione attesa
	 * ed dimensione della collezione.*/
	@Test
	public void testAddSize()
	{
		System.out.println("Inside method testAddSize()");
		adapter.add("stringa0");
		adapter.add("stringa1");
		adapter.add("stringa2");
		int expected=3;
		assertEquals(expected,adapter.size());
	}
	
	/**Si testa l'eccezione lanciata dal metodo add se viene inserito un elemento
	 * nullo*/
	@Test(expected=NullPointerException.class)
	public void testAddNullException()
	{
		System.out.println("Inside method testAddNullException()");
		adapter.add(null);
	}
	
	/**Si testa che dopo una sequenza di inserimenti e l'invocazione del metodo clear la 
	 * dimensione risulti nulla*/
	@Test
	public void testClear()
	{
		System.out.println("Inside method testClear()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		adapter.clear();	
		int expected=0;
		assertEquals(expected,adapter.size());
	}
	
	/**Si testa il lancio di eccezione NullPointerException quando si invoca il metodo
	 * indexOf() a cui si passa un riferimento nullo*/
	@Test(expected=NullPointerException.class)
	public void testIndexOfException()
	{
		System.out.println("Inside method testIndexOfException()");
		adapter.indexOf(null);
	}
	
	@Test
	public void testIndexOfIn()
	{
		System.out.println("Inside method testIndexOfIn()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		int expected=2;
		assertEquals(expected,adapter.indexOf("stringa2"));
	}
	
	@Test
	public void testIndexOfNotIn()
	{
		System.out.println("Inside method testIndexOfNotIn()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		int expected=-1;
		assertEquals(expected,adapter.indexOf("stringa100"));
	}
	
	@Test
	public void testIsEmptyTrue()
	{
		System.out.println("Inside method testIsEmptyTrue()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		adapter.clear();
		assertTrue(adapter.isEmpty());
	}
	
	@Test
	public void testIsEmptyFalse()
	{
		System.out.println("Inside method testIsEmptyFalse()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		assertFalse(adapter.isEmpty());
	}
	
	@Test
	public void testContainsTrue()
	{
		System.out.println("Inside method testContainsTrue()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		assertTrue(adapter.contains("stringa7"));
	}
	
	@Test
	public void testContainsFalse()
	{
		System.out.println("Inside method testContainsFalse()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		assertFalse(adapter.contains("stringa100"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testContainsNullException()
	{
		System.out.println("Inside method testContainsNullException()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		adapter.contains(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testLastIndexOfException()
	{
		System.out.println("Inside method testLastIndexOfException()");
		adapter.add("stringa0");
		adapter.add("stringa1");
		adapter.add("stringa2");
		adapter.add("stringa1");
		adapter.add("stringa2");
		adapter.lastIndexOf(null);
	}
	
	@Test
	public void testLastIndexOfIn()
	{
		System.out.println("Inside method testLastIndexOfIn()");
		adapter.add("stringa0");
		adapter.add("stringa1");
		adapter.add("stringa2");
		adapter.add("stringa1");
		adapter.add("stringa2");
		int expected=4;
		assertEquals(expected,adapter.lastIndexOf("stringa2"));
	}
	
	@Test
	public void testLastIndexOfNotIn()
	{
		System.out.println("Inside method testLastIndexOfNotIn()");
		adapter.add("stringa0");
		adapter.add("stringa1");
		adapter.add("stringa2");
		adapter.add("stringa1");
		adapter.add("stringa2");
		int expected=-1;
		assertEquals(expected,adapter.lastIndexOf("stringa100"));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveException()
	{
		System.out.println("Inside method testRemoveException()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		adapter.remove(-1);
	}
	
	@Test
	public void testRemove()
	{
		System.out.println("Inside method testRemove()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		String expected="stringa5";
		assertEquals(expected,adapter.remove(5));
		int expSize=size-1;
		assertEquals(expSize,adapter.size());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveEmpty()
	{
		System.out.println("Inside method testRemoveEmpty()");
		adapter.remove(5);
	}
	
	@Test
	public void testRemoveObjectTrue()
	{
		System.out.println("Inside method testRemoveObjectTrue()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		assertTrue(adapter.remove("stringa6"));
		int expSize=size-1;
		assertEquals(expSize,adapter.size());
	}
	
	@Test
	public void testRemoveObjectFalse()
	{
		System.out.println("Inside method testRemoveObjectFalse()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		assertFalse(adapter.remove("stringa20"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testRemoveObjectException()
	{
		System.out.println("Inside method testRemoveObjectException()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		adapter.remove(null);
	}
	
	@Test
	public void testSet()
	{
		System.out.println("Inside method testSet()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		String expected="stringa8";
		assertEquals(expected,adapter.set(8, "nuova stringa"));
		expected="nuova stringa";
		assertEquals(expected,adapter.get(8));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSetBoundException()
	{
		System.out.println("Inside method testSetBoundException()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		adapter.set(100, "nuova stringa");
	}
	
	@Test(expected=NullPointerException.class)
	public void testSetNullException()
	{
		System.out.println("Inside method testSetNullException()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		adapter.set(0, null);
	}
	
	@Test
	public void testToArray()
	{
		System.out.println("Inside method testToArray()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		Object[] array=adapter.toArray();
		for(int i=0;i<size;i++)
			assertEquals(adapter.get(i),array[i]);		
	}
	
	@Test
	public void testToArrayEmpty()
	{
		System.out.println("Inside method testToArrayEmpty()");
		Object[] array=adapter.toArray();
		assertTrue(array.length==0);	
	}
	
	@Test//i valori tornati dall'iteratore devono coincidere con quelli inseriti
	public void testIterator()
	{
		System.out.println("Inside method testIterator()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		Iterator<Object> iter=adapter.iterator();
		int i=0;
		while(iter.hasNext())
		{
			String str="stringa";
			assertEquals(str+i++,iter.next());
		}	
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSublistBoundExceptionNegativeFromIndex()
	{
		System.out.println("Inside method testSublistBoundExceptionNegativeFromIndex()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		List<Object> sublist=adapter.subList(-1, 2);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSublistBoundExceptionTooBigToIndex()//indice di arrivo troppo grande
	{
		System.out.println("Inside method testSublistBoundExceptionTooBigToIndex()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		List<Object> sublist=adapter.subList(0, adapter.size()+1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSublistBoundExceptionFromGraterThanTo()
	{
		System.out.println("Inside method testSublistBoundExceptionFromGraterThanTo()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		List<Object> sublist=adapter.subList(4, 1);
	}
	
	@Test
	public void testListIteratorHasNextHasPreviousNextPrevious()
	{
		System.out.println("Inside method testListIteratorHasNextHasPreviousNextPrevious()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		ListIterator<Object> iter=adapter.listIterator();
		
		//scorro l'indice completamente a destra
		scorriIndiceDestra(iter);		
		//scorro l'indice completamente a sinistra
		scorriIndiceSinistra(iter);		
		//scorro l'indice completamente a destra
		scorriIndiceDestra(iter);	      
	}
	
	/**Scorrimento dell'indice verso destra verificando la corrispondenza tra gli
	 * elementi ritornati dall'iteratore e quelli della struttura.*/
	private void scorriIndiceDestra(ListIterator<Object> iter)
	{
		int i=0;
		while(iter.hasNext())
		{
			String str="stringa";
			assertEquals(str+i++,iter.next());
		}
	}
	
	/**Scorrimento dell'indice verso sinistra verificando la corrispondenza tra gli
	 * elementi ritornati dall'iteratore e quelli della struttura.*/
	private void scorriIndiceSinistra(ListIterator<Object> iter)
	{
		int i=adapter.size()-1;
		while(iter.hasPrevious())
		{
			String str="stringa";
			assertEquals(str+i--,iter.previous());
		}
	}
	
	/**Si testa il metodo listIterator ed in particolare la funzionalità di inserimento
	 * e quella di rimozione. Nel primo caso si fa scorrere l'iteratore all'estremo desto
	 * si inserisce un nuovo elemento e si verifica la nuova dimensione. Si rimuove poi
	 * l'ultimo elemento ritornato da next, si verifica la dimensione.
	 *  Prima si scorrere con il cursore a sinistra si ripristina lo stato precedente, 
	 *  poi si inserisce un elemento in testa si verifica la dimensione 
	 *  ed infine lo si rimuove verificando nuovamente la dimensione.*/
	@Test
	public void testListIteratorAddRemove()
	{
		System.out.println("Inside method testListIteratorAddRemove()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		ListIterator<Object> iter=adapter.listIterator();
		
		//scorro l'indice completamente a destra ed inserisco un elemento e poi rimuovo
		//l'ultimo elemento tornato con next quindi quello che PRIMA della modifica è in posizione size-1
		scorriIndiceDestra(iter);
		int expected=adapter.size()+1;
		iter.add("stringa10");
		assertEquals(expected,adapter.size());
		expected--;
		iter.remove();
		assertEquals(expected,adapter.size());
		//ripristino lo stato precedente poichè con la remove ho tolto l'ultimo
		//elemento tornato da next ovvero stringa9 che ora è stringa10 per la compattazione
		adapter.set(adapter.size()-1, "stringa9");
	
		//scorro l'indice completamente a sinistra ed inserisco un elemento e poi lo rimuovo
		scorriIndiceSinistra(iter);
		expected++;
		iter.add("newStringa0");
		assertEquals(expected,adapter.size());
		expected--;
		iter.remove();
		assertEquals(expected,adapter.size());
	}
	
	/**Si testa il metodo listIterator, in partcolare si testano le funzionalità NextIndex
	 * previousIndex e set. Si fa scorrere l'iteratore fino alla posizione 5 della lista
	 * si verica che l'indice precedente sia 4 e che il successivo sia 6. Si setta poi
	 * l'elemento in posizione 5 con un nuovo valore e si verifica l'avvenuta modifica.*/
	@Test
	public void testListIteratorNextIndexPreviousIndexSet()
	{
		System.out.println("Inside method testListIteratorNextIndexPreviousIndexSet()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		ListIterator<Object> iter=adapter.listIterator();
		
		//si scorre l'indice a destra di i posizioni
		int i=5;		
		while(iter.hasNext() && i>0)
		{
			iter.next();
			i--;
		}			
		//verifico il valore dell'indice precedente e dell'indice successivo
		int expectedPrev=4;
		int expectedNext=6;
		assertEquals(expectedPrev,iter.previousIndex());
		assertEquals(expectedNext,iter.nextIndex());
		
		//modifica dell'elemento di indice 4
		i=5;
		String expected="modificata";
		//viene modificato l'ultimo elemento tornato dall'iteratore quindi quello in posizione i-1
		iter.set("modificata");
		assertEquals(expected,adapter.get(i-1));
		
	}
	
	/**Si testa la creazione di un list iterator il cui puntatore corrente è settato ad
	 * una posizione iniziale definita. Si verifica che l'elemento ritornato da previous
	 * e da next siano quelli previsti dopo aver inizializzato opportunamente l'adapter*/
	@Test
	public void testListIteratorIndex()
	{
		System.out.println("Inside method testListIteratorIndex()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		
		int start=4;
		Object expected="stringa4";
		ListIterator<Object> iter=adapter.listIterator(start);
		assertEquals(expected,iter.next());
		
		//appena si invoca previous viene tornato lo stesso valore che è stato
		//precedentemente ritornato da next
		expected="stringa4";
		assertEquals(expected,iter.previous());			      
	}
	
	/**Si testa il lancio di IndexOutOfBoundsException quando si crea un ListIterator
	 * il cui indice di partenza è inferiore 0*/
	@Test(expected=IndexOutOfBoundsException.class)
	public void testListIteratorIndexExceptionMin()
	{
		System.out.println("Inside method testListIteratorIndexExceptionMin()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		ListIterator<Object> iter=adapter.listIterator(-1);
	}
	
	/**Si testa il lancio di IndexOutOfBoundsException quando si crea un ListIterator
	 * il cui indice di partenza è superiore alla dimensione della lista*/
	@Test(expected=IndexOutOfBoundsException.class)
	public void testListIteratorIndexExceptionMax()
	{
		System.out.println("Inside method testListIteratorIndexExceptionMax()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		ListIterator<Object> iter=adapter.listIterator(adapter.size()+1);
	}
	
	@Test
	public void testEqualsTrue()
	{
		System.out.println("Inside method testEqualsTrue()");		
		Collection<Object> sameAdp=new ListObjectAdapter<Object>();
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		for(int i=0;i<size;i++)
			sameAdp.add("stringa"+i);
		assertTrue(adapter.equals(sameAdp));
		assertEquals(sameAdp.hashCode(),adapter.hashCode());
	}
	
	@Test
	public void testEqualsFalse()
	{
		System.out.println("Inside method testEqualsFalse()");		
		Collection<Object> diffAdp=new ListObjectAdapter<Object>();
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		for(int i=0;i<size;i++)
			diffAdp.add("value"+i);
		assertFalse(adapter.equals(diffAdp));
		assertNotEquals(diffAdp.hashCode(),adapter.hashCode());
	}
	
	@Test(expected=ClassCastException.class)
	public void testEqualsCastException()
	{
		System.out.println("Inside method testEqualsCastException()");		
		Collection<Object> sameAdp=new ListObjectAdapter<Object>();
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		for(int i=0;i<size;i++)
			sameAdp.add("stringa"+i);
		assertTrue(adapter.equals(new Integer(0)));
	}
	
	@Test(expected=NullPointerException.class)
	public void testEqualsNullException()
	{
		System.out.println("Inside method testEqualsNullException()");		
		assertTrue(adapter.equals(null));
	}
	
	/**Test remove all valore di ritorno true.
	 * Si rimuovono dalla lista i numeri da 5 a 9, che sono contenuti dentro una
	 * specifica collezione e si verifica che il metodo ritorni
	 * il valore true. Si testa poi che affettivamente i valori siano stati rimossi 
	 * confrontando che quelli rimasti fossero quelli che effettivamente dovevano rimanere.*/
	@Test
	public void testRemoveAllTrue()
	{
		System.out.println("Inside method testRemoveAllTrue()");
		for(int i=0;i<size;i++)
			adapter.add(i);
		CollectionObjectAdapter<Integer> adapterInt=new CollectionObjectAdapter<Integer>();
		int oldSize=size/2;
		for(int i=oldSize;i<size;i++)
			adapterInt.add(i);
		assertTrue(adapter.removeAll(adapterInt));
		
		Iterator<Object> iter=adapter.iterator();
		int i=0;
		while(iter.hasNext())
			assertEquals(i++,iter.next());		
	}
	
	/**Test remove all valore di ritorno false.
	 * Si testa che se si provano a rimuovere dei valori contenuti in una certa
	 * collezione e non presenti nella lista questi non vengono
	 * effettivamente rimossi ed il metodo ritorna il valore false. */
	@Test
	public void testRemoveAllFalse()
	{
		System.out.println("Inside method testContainsAllFalse()");
		for(int i=0;i<size;i++)
			adapter.add(i);
		CollectionObjectAdapter<Integer> adapterInt=new CollectionObjectAdapter<Integer>();
		for(int i=size/2;i<size;i++)
			adapterInt.add(i*2);
		assertFalse(adapter.removeAll(adapterInt));
		int expSize=size;
		assertEquals(expSize,adapter.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testRemoveAllNullException()
	{
		System.out.println("Inside method testRemoveAllNullException()");	
		assertFalse(adapter.removeAll(null));
	}
	
	@Test
	public void testRemoveAllDuplicates()
	{
		System.out.println("Inside method testRemoveAllDuplicates()");
		adapter.add(0);
		adapter.add(0);
		adapter.add(1);
		adapter.add(1);
		adapter.add(2);
		adapter.add(2);
		CollectionObjectAdapter<Integer> adapterInt=new CollectionObjectAdapter<Integer>();
		for(int i=0;i<size;i++)
			adapterInt.add(i);
		assertTrue(adapter.removeAll(adapterInt));
		int expSize=3;
		assertEquals(expSize,adapter.size());
	}
	
	
	/**Test contains all valore di ritorno true.
	 * Si verifica che la lista istanziata contenga tutti gli elementi di una collezione
	 * che contiene un sott'insieme degli elementi della lista iniziale*/
	@Test
	public void testContainsAllTrue()
	{
		System.out.println("Inside method testContainsAllTrue()");
		for(int i=0;i<size;i++)
			adapter.add(i);
		CollectionObjectAdapter<Integer> adapterInt=new CollectionObjectAdapter<Integer>();
		for(int i=size/2;i<size;i++)
			adapterInt.add(i);
		assertTrue(adapter.containsAll(adapterInt));
	}
	
	/**Test contains all valore di ritorno false.
	 * Si verifica che la lista istanziata NON contenga tutti gli elementi di una collezione
	 * che contiene elementi differenti da quelli presenti nella lista.*/
	@Test
	public void testContainsAllFalse()
	{
		System.out.println("Inside method testContainsAllFalse()");
		for(int i=0;i<size;i++)
			adapter.add(i);
		CollectionObjectAdapter<Integer> adapterInt=new CollectionObjectAdapter<Integer>();
		for(int i=size/2;i<size;i++)
			adapterInt.add(i*2);
		assertFalse(adapter.containsAll(adapterInt));
	}
	
	/**Test contains all NullPointerException.
	 * Si verifica che il metodo contains all lanci l'eccezione NullPointerException in caso di
	 * riferimento nullo*/
	@Test(expected=NullPointerException.class)
	public void testContainsAllNullException()
	{
		System.out.println("Inside method testContainsAllNullException()");	
		adapter.containsAll(null);
	}
	
	/**Test retail all valore di ritorno true.
	 * Si verifica che la collezione istanziata a seguito dell'invocazione del metodo 
	 * retainAll contenga solo un sott'insieme degli elementi che conteneva inizialmente.
	 * Ovvero solo quelli contenuti anche nella collezione passata come parametro.*/
	@Test
	public void testRetainAllTrue()
	{
		System.out.println("Inside method testRetainAllTrue()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		CollectionObjectAdapter<String> adapterStr=new CollectionObjectAdapter<String>();
		for(int i=size/2;i<size;i++)
			adapterStr.add("stringa"+i);
		assertTrue(adapter.retainAll(adapterStr));//si verifica che l'invocazione del metodo vada a buon fine
		
		//si verifica che gli elementi rimasti siano effetivamente quelli del sott'insieme
		Iterator<Object> iter=adapter.iterator();
		int i=5;
		while(iter.hasNext())
			assertEquals("stringa"+i++,iter.next());
	}
	
	/**Test contains all valore di ritorno false.
	 * Si verifica che la lista istanziata contenga gli stessi elementi con cui è stata
	 * inizializzata anche a seguito dell'invocazione del metodo retain all. Ciò si verifica
	 * poichè la collezione che dovrebbe rappresentare un sott'insieme degli elementi
	 * da mantenere in realtà contiene gli stessi elementi della collezione iniziale.*/
	@Test
	public void testRetainAllFalse()
	{
		System.out.println("Inside method testRetainAllFalse()");
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		CollectionObjectAdapter<String> adapterStr=new CollectionObjectAdapter<String>();
		//si inizializza il secondo adapter con gli stessi elementi
		for(int i=0;i<size;i++)
			adapterStr.add("stringa"+i);
		assertFalse(adapter.retainAll(adapterStr));
		int expSize=size;
		assertEquals(expSize,adapter.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testRetainAllNullException()
	{
		System.out.println("Inside method testRetainAllNullException()");	
		adapter.retainAll(null);
	}
	
	/**Test addAll() con valore di ritorno true.
	 * Si verifica che tutti gli elementi di una nuova collezione vengano inseriti nella
	* lista iniziale, verificando il valore di ritorno.
	* Pre-condizioni: adapter inizializzato con 10 stringhe denominate stringa-i dove
	* i è un valore intero incrementale da 0 a 9.
	* Post-condizioni: oltre alle 10 stringhe inizialmente presenti nella lista si 
	* aggiungono tramite metodo addAll 10 numeri interi incrementali a 10 a 19.
	* Test cases: si verifica che il valore di ritorno sia true e che gli elementi inseriti
	* siano corettamente le 10 stringhe ed i 10 numeri interi tramite dei confronti diretti
	* attraverso due cicli che in sequenza verificano la corrispondenza degli elementi*/
	@Test
	public void testAddAllTrue()
	{
		System.out.println("Inside method testAddAll()");
		CollectionObjectAdapter<Integer> adapterInt=new CollectionObjectAdapter<Integer>();
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		for(int i=0;i<size;i++)
			adapterInt.add(i+size);			
		assertTrue(adapter.addAll(adapterInt));
		
		for(int i=0;i<adapter.size()/2;i++)
			assertEquals("stringa"+i,adapter.get(i));
			
		for(int i=10;i<adapter.size();i++)		
			assertEquals(i,adapter.get(i));		
	}
		
	/**Test addAll() NullPointerException.
	* Si verifica se il metodo addAll riceve come paramtero una collezione nulla questo
	* lanci l'eccezione NullPointerException.*/
	@Test(expected=NullPointerException.class)
	public void testAddAllNullException()
	{
		System.out.println("Inside method testAddAllNullException()");		
		adapter.addAll(null);
	}
	
	/**Test addAllIndex() NullPointerException.
	* Si verifica se il metodo addAll con indice riceve come paramtero una collezione nulla questo
	* lanci l'eccezione NullPointerException.*/
	@Test(expected=NullPointerException.class)
	public void testAddAllIndexNullException()
	{
		System.out.println("Inside method testAddAllIndexNullException()");		
		adapter.addAll(0,null);
	}
	
	/**Test addAll Index() IndexOutOfBoundException.*/
	@Test(expected=IndexOutOfBoundsException.class)
	public void testAddAllIndexBoundException()
	{
		System.out.println("Inside method testAddAllIndexBoundException()");
		CollectionObjectAdapter<Integer> adapterInt=new CollectionObjectAdapter<Integer>();
		for(int i=0;i<size;i++)
			adapterInt.add(i);
		adapter.addAll(adapter.size()+1,null);
	}
	
	/**Test addAllIndex().
	 * Si verifica che il metodo addAll con indice inserisca effettivamente i valori
	 * della collezione nella lista.
	 * Pre-condizioni: adapter inizializzato con 10 stringhe denominate stringa-i dove
	* i è un valore intero incrementale da 0 a 9.
	* Post-condizioni: oltre alle 10 stringhe inizialmente presenti nella lista si 
	* aggiungono tramite metodo addAll 5 numeri interi incrementali a 10 a 14 a
	* partire dalla metà della lista.
	* Test cases: si verifica che il valore di ritorno sia true e che gli elementi inseriti
	* partendo dalla metà della lista e per le successive 5 posizioni siano effettivamente
	* i numeri interi da 10 a 14.
	* Execution variables: 10 stringhe denominate stringa-i, 5 numeri interi da 10 a 14.
	* */ 
	@Test
	public void testAddAllIndex()
	{
		System.out.println("Inside method testAddAllIndex()");
		CollectionObjectAdapter<Integer> adapterInt=new CollectionObjectAdapter<Integer>();
		for(int i=0;i<size;i++)
			adapter.add("stringa"+i);
		for(int i=0;i<size/2;i++)
			adapterInt.add(i+10);
		assertTrue(adapter.addAll(adapter.size()/2,adapterInt));
		
		int n=10;
		for(int i=size/2;i<size;i++)			
			assertEquals(n++,adapter.get(i));
					
	}
	
	@Test
	public void testSort()
	{
		List<Integer> list=new ListObjectAdapter();
		list.add(6);
		list.add(3);
		list.add(5);
		list.add(1);
		list.add(2);
		list.add(4);
		list.sort(new IntegerComparator());
		List<Integer> orderedList=new ListObjectAdapter();
		orderedList.add(1);
		orderedList.add(2);
		orderedList.add(3);
		orderedList.add(4);
		orderedList.add(5);
		orderedList.add(6);
		assertEquals(orderedList,list);
	}
}