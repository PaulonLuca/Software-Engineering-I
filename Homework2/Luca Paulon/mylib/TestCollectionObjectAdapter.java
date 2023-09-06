import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Iterator;

public class TestCollectionObjectAdapter 
{
	/**Costante che definisce la dimensione iniziale della collezione. Verrà usata come
	*limite superiore nell'esecuzione dei cicli for nei vari test. Si è introdotta per
	*evitare Magic Numbers.*/
	final int size=10;
	
	/**Adapter interno alla classe su cui verranno eseguiti tutti i test*/
	CollectionObjectAdapter<Object> adapter=new CollectionObjectAdapter<Object>(size);
	
	/**Startup.
	 * Prima di eseguire ogni test si inizializza la collezione inserendo al suo interno 10
	 * numeri interi appartenenti all'intevallo [0,9].*/
	@Before
	public void setUp()
	{
		for(int i=0;i<size;i++)
			adapter.add(i);
	}
	
	/**Test add() e size().
	 * Pre-condition: adapter inizializzato tramite startup.
	 * Post-condition: oltre ai 10 valori interi inseriti tramite il metodo di startup
	 * si inseriscono altri 10 valori interi multipli di 10 (0, 10, 20 ...90).
	 * Test cases: si testa che ad ogni inserimento il valore di ritorno del metodo add sia 
	 * true. Infine si testa che la dimensione della lista sia diventata doppia.
	 * Execution variables: 10 interi da 0 a 9, ulteriori 10 interi da 0 a 90, variabile
	 * che contiene la dimensione attesa, l'adapter.
	 * Execution records: true ad ogni invocazione di add, uguaglianza tra dimensione attesa
	 * ed dimensione della collezione.*/
	@Test
	public void testAdd()
	{
		System.out.println("Inside method testAdd()");
		for(int i=0;i<size;i++)
			assertTrue(adapter.add(i*10));
		int expected=size*2;
		assertEquals(expected,adapter.size());
	}
	
	/**Test add() NullPointerException.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: none.
	 * Test cases: si testa che l'eccezione lanciata a seguito dell'inserimento di un valore 
	 * nullo sia: NullPointerException.
	 * Execution variables: l'adapter.
	 * Execution records: none */
	@Test(expected=NullPointerException.class)
	public void testAddNullException()
	{
		System.out.println("Inside method testAddNullException()");
		adapter.add(null);
	}
	
	/**Test iterator().
	 * Pre-condition: adapter inizializzato tramite startup.
	 * Post-condition: none.
	 * Test cases: si testa che la sequenza di elementi ritornati dall'iteratore coincida
	 * con gli elementi nella collezione scanditi sequenzialmente in ordine di posizione.
	 * Execution variables: 10 interi da 0 a 9, l'adapter, l'iteratore.
	 * Execution records: true ad ogni iterazione del ciclo se l'elemento della collezione
	 * è lo stesso elemento dell'iteratore.*/
	@Test
	public void testIterator()
	{
		System.out.println("Inside method testIterator()");
		Iterator<Object> iter=adapter.iterator();
		int i=0;
		while(iter.hasNext())
			assertEquals(i++,iter.next());
	}
	
	/**Test addAll() return value: true.
	* Pre-condition: adapter inizializzato tramite startup.
	* Post-condition: oltre ai 10 interi inizialmente presenti nella lista si 
	* aggiungono tramite metodo addAll() 10 ulteriori numeri interi incrementali a 10 a 19.
	* Test cases: si verifica che il valore di ritorno sia true e che gli elementi inseriti
	* siano corettamente 20 numeri interi. Si fanno confronti diretti
	* tra i 20 numeri interi generati iterativamente attracerso un ciclo ed 
	* i valori ritornati dall'iteratore sulla collezione.
	* Execution variables: 20 numeri interi incrementali da 0 a 19, l'adapter iniziale,
	* una collezione contenente gli elementi da aggiungere.
	* Execution records: true ad ogni confronto tra valori coincidenti*/
	@Test
	public void testAddAll()
	{
		System.out.println("Inside method testAddAll()");
		Collection<Integer> adapterInt=new ListObjectAdapter<Integer>();
		for(int i=0;i<size;i++)
			adapterInt.add(i+size);			
		assertTrue(adapter.addAll(adapterInt));
		
		Iterator<Object> iter=adapter.iterator();
		for(int i=0;i<adapter.size();i++)
		{
			assertEquals(i,iter.next());
			//System.out.println(i+"  "+iter.next());
		}		
	}
	
	/**Test addAll() NullPointerException.
	 * Pre-condition: adapter inizializzato tramite startup.
	 * Post-condition: none.
	 * Test cases: si testa che l'eccezione lanciata a seguito dell'inserimento di
	 * una collezione nulla sia: NullPointerException.
	 * Execution variables: l'adapter.
	 * Execution records: none */
	@Test(expected=NullPointerException.class)
	public void testAddAllNullException()
	{
		System.out.println("Inside method testAddAllNullException()");		
		adapter.addAll(null);
	}
	
	/**Test clear().
	 * Pre-conditon: adapter inizializzato tramite startup.
	 * Post-condition: collezione vuota.
	 * Test-cases: si testa che la la dimensione della collezione sia 0.
	 * Execution variables: variabile con dimensione attesa, l'adapter.
	 * Execution records: true se la dimensione è nulla.*/
	@Test
	public void testClear()
	{
		System.out.println("Inside method testClear()");
		adapter.clear();	
		int expected=0;
		assertEquals(expected,adapter.size());
	}
	
	/**Test contains() return value: true.
	 * Pre-condition: adapter inizializzato tramite startup.
	 * Post-condition: none.
	 * Test cases: si verifica che la collezione contenga i numeri pari da 0 a 8.
	 * Execution variables: numeri pari da 0 a 18, l'adapter.
	 * Execution records: true se i valori sono contenuti nella collezione.*/
	@Test
	public void testContainsTrue()
	{
		System.out.println("Inside method testContainsTrue()");
		for(int i=0;i<size;i+=2)
			assertTrue(adapter.contains(i));
	
	}
	
	/**Test contains() return value: false.
	 * Pre-condition: adapter inizializzato tramite startup.
	 * Post-condition: none.
	 * Test cases: si verifica che la collezione non contenga i numeri pari da 10 a 
	 * 90 multipli di 10.
	 * Execution variables: numeri pari da 10 a 90 pari multipli di 10, l'adapter.
	 * Execution records: true se i valori non sono contenuti nella collezione.*/
	@Test
	public void testContainsFalse()
	{
		System.out.println("Inside method testContainsFalse()");
		for(int i=1;i<size;i++)
			assertFalse(adapter.contains(10*i));
	}
	
	/**Test contains() NullPointerException.
	 * Pre-condition: adapter inizializzato tramite startup.
	 * Post-condition: none.
	 * Test cases: si testa che l'eccezione lanciata a seguito della ricerca
	 * di un elemento nullo sia: NullPointerException.
	 * Execution variables: l'adapter.
	 * Execution records: none */
	@Test(expected=NullPointerException.class)
	public void testContainsNullException()
	{
		System.out.println("Inside method testContainsNullException()");
		adapter.contains(null);
	}
	
	 /**Test containsAll() return value: true.
	 * Pre-condition: adapter inizializzato tramite startup.
	 * Post-condition: none.
	 * Test cases: si verifica che la collezione contenga tutti gli elementi che
	 * sono contenuti nella seconda collezione.
	 * Execution variables: l'adapter, una collezione che contiene i numeri interi da 5 a 9.
	 * Execution records: true se i valori sono contenuti nella collezione.*/
	@Test
	public void testContainsAllTrue()
	{
		System.out.println("Inside method testContainsAllTrue()");
		Collection<Integer> adapterInt=new SetObjectAdapter<Integer>();
		for(int i=size/2;i<size;i++)
			adapterInt.add(i);
		assertTrue(adapter.containsAll(adapterInt));
	}
	
	/**Test containsAll() return value: false.
	 * Pre-condition: adapter inizializzato tramite startup.
	 * Post-condition: none.
	 * Test cases: si verifica che la collezione non contenga tutti gli elementi che
	 * sono contenuti nella seconda collezione.
	 * Execution variables: l'adapter, una collezione che contiene i numeri interi
	 * pari da 10 a 18 ed i numeri interi contenuti anche nella prima collezione 0,1,2.
	 * Execution records: false se i valori non sono contenuti nella collezione.*/
	@Test
	public void testContainsAllFalse()
	{
		System.out.println("Inside method testContainsAllFalse()");
		Collection<Integer> adapterInt=new SetObjectAdapter<Integer>();
		adapterInt.add(0);
		adapterInt.add(1);
		adapterInt.add(2);
		for(int i=size/2;i<size;i++)
			adapterInt.add(i*2);		
		assertFalse(adapter.containsAll(adapterInt));
		int expSize=10;
		assertEquals(expSize,adapter.size());
	}
	
	/**Test containsAll() NullPointerException.
	 * Pre-condition: adapter inizializzato tramite startup.
	 * Post-condition: none.
	 * Test cases: si testa che l'eccezione lanciata a seguito della ricerca
	 * in una collezione nulla sia: NullPointerException.
	 * Execution variables: l'adapter.
	 * Execution records: none */
	@Test(expected=NullPointerException.class)
	public void testContainsAllNullException()
	{
		System.out.println("Inside method testContainsAllNullException()");	
		adapter.containsAll(null);
	}
	
	/**Test toArray().
	 *  Pre-condition: adapter inizializzato tramite startup.
	 *  Post-condition: array con gli stessi elementi della collezione.
	 *  Test cases: si verifica che gli elementi della lista siano gli stessi
	 *  elementi dell'array confrontato gli elementi per posizione.
	 *  Execution variables: l'adapter, array.
	 *  Execution records: true ad ogni iterazione se gli elementi coincidono.*/
	@Test
	public void testToArray()
	{
		System.out.println("Inside method testToArray()");
		Object[] array=adapter.toArray();
		Iterator<Object> iter=adapter.iterator();
		int i=0;
		while(iter.hasNext())
			assertEquals(iter.next(),array[i++]);		
	}
	
	/**Test toArray() empty.
	 *  Pre-condition: adapter inizializzato tramite startup su cui si invoca poi il metodo clear().
	 *  Post-condition: array vuoto e collezione vuota.
	 *  Test cases: si verifica che la dimensione dell'array sia 0.
	 *  Execution variables: l'adapter, array.
	 *  Execution records: true se la dimensione dell'array è nulla.*/
	@Test
	public void testToArrayEmpty()
	{
		System.out.println("Inside method testToArrayEmpty()");
		adapter.clear();
		Object[] array=adapter.toArray();
		assertTrue(array.length==0);	
	}
	
	/**Test isEmpty() return value: true.
	 *  Pre-condition: adapter inizializzato tramite startup su cui si invoca poi il metodo clear().
	 *  Post-condition: collezione vuota.
	 *  Test cases: si verifica che la dimensione della collezione sia nulla.
	 *  Execution variables: l'adapter.
	 *  Execution records: true se la dimensione della collezione è nulla.*/
	@Test
	public void testIsEmptyTrue()
	{
		System.out.println("Inside method testIsEmptyTrue()");
		adapter.clear();
		assertTrue(adapter.isEmpty());
	}
	
	/**Test isEmpty() return value: false.
	 *  Pre-condition: adapter inizializzato tramite startup.
	 *  Post-condition: la collezione contiene ancora gli elementi con cui è stata inizializzata.
	 *  Test cases: si verifica che la dimensione della collezione non sia nulla.
	 *  Execution variables: l'adapter.
	 *  Execution records: false se la dimensione della collezione non è nulla.*/
	@Test
	public void testIsEmptyFalse()
	{
		System.out.println("Inside method testIsEmptyFalse()");
		assertFalse(adapter.isEmpty());
		int expSize=10;
		assertEquals(expSize,adapter.size());
	}
	
	/**Test remove() return value: true.
	 * Pre-condition: adapter inizializzato tramite startup.
	 * Post-condition: elemento rimosso dalla collezione.
	 * Test cases: si testa che il valore sia stato rimosso dalla collezione e che quindi
	 * la dimensione sia decrementata di 1.
	 * Execution variables: elemento da rimuovere (numero 6), l'adapter, vecchia dimensione.
	 * Execution records: true se l'elemento è stato rimosso e se la dimensione è diminuita.*/
	@Test
	public void testRemoveObjectTrue()
	{
		System.out.println("Inside method testRemoveObjectTrue()");
		int toRemove=6;
		int oldSize=adapter.size();
		assertTrue(adapter.remove(toRemove));
		assertTrue(oldSize>adapter.size());
	}
	
	/**Test remove() return value: false.
	 * Pre-condition: adapter inizializzato tramite startup.
	 * Post-condition: elemento non rimosso dalla collezione.
	 * Test cases: si testa che il valore non sia stato rimosso dalla collezione e che quindi
	 * la dimensione non sia decrementata di 1.
	 * Execution variables: elemento da rimuovere (numero 12), l'adapter, vecchia dimensione.
	 * Execution records: false se l'elemento non è stato rimosso e se la dimensione non è diminuita.*/
	@Test
	public void testRemoveObjectFalse()
	{
		System.out.println("Inside method testRemoveObjectFalse()");
		int toRemove=12;
		int oldSize=adapter.size();
		assertFalse(adapter.remove(toRemove));
		assertEquals(oldSize,adapter.size());
	}
	
	/**Test remove() NullPointerException.
	 * Pre-condition: adapter inizializzato tramite startup.
	 * Post-condition: none.
	 * Test cases: si testa che l'eccezione lanciata a seguito della rimozione
	 * di un elemento nullo sia: NullPointerException.
	 * Execution variables: l'adapter.
	 * Execution records: none */
	@Test(expected=NullPointerException.class)
	public void testRemoveObjectException()
	{
		System.out.println("Inside method testRemoveObjectException()");
		adapter.remove(null);
	}
	
	/**Test remove() empty.
	 * Pre-condition: adapter inizializzato tramite startup e su cui si invoca il metodo clear().
	 * Post-condition: elemento non rimosso dalla collezione.
	 * Test cases: si testa che il valore non sia stato rimosso dalla collezione poichè vuota.
	 * Execution variables: elemento da rimuovere (numero 3), l'adapter.
	 * Execution records: false se l'elemento non è stato rimosso.*/
	@Test
	public void testRemoveObjectEmpty()
	{
		System.out.println("Inside method testRemoveObjectEmpty()");
		adapter.clear();
		int toRemove=3;
		assertFalse(adapter.remove(toRemove));
	}
	
	/**Test removeAll() return value: true.
	 * Pre-condition: adapter inizializzato tramite startup.
	 * Post-condition: risultano rimossi dalla collezione tutti gli elementi
	 * che sono contenuti in quella passata come parametro.
	 * Test cases: si testa che la collezione rusulti modificata, cioè che siano stati rimossi
	 * tutti gli elementi della collezione passata come parametro e che siano rimasti solo
	 * gli elementi che effettivamente dovevano rimanere.
	 * Execution variables: collezione contenente gli elementi da rimuovere (numeri da
	 * 5 a 9), l'adapter.
	 * Execution records: true se gli elementi sono stati rimossi con successo.*/
	@Test
	public void testRemoveAllTrue()
	{
		System.out.println("Inside method testRemoveAllTrue()");
		CollectionObjectAdapter<Integer> adapterInt=new CollectionObjectAdapter<Integer>();
		for(int i=size/2;i<size;i++)
			adapterInt.add(i);
		assertTrue(adapter.removeAll(adapterInt));
		
		Iterator<Object> iter=adapter.iterator();
		int i=0;
		while(iter.hasNext())
			assertEquals(i++,iter.next());	
		int expSize=5;
		assertEquals(expSize,adapter.size());
	}
	
	/**Test removeAll() return value: false.
	 * Pre-condition: adapter inizializzato tramite startup.
	 * Post-condition: non risultano rimossi dalla collezione tutti gli elementi
	 * che sono contenuti in quella passata come parametro.
	 * Test cases: si testa che la collezione non rusulti modificata.
	 * Execution variables: collezione contenente gli elementi da rimuovere (numeri pari 
	 * da 10 a 18), l'adapter.
	 * Execution records: flase se gli elementi  non sono stati rimossi.*/
	@Test
	public void testRemoveAllFalse()
	{
		System.out.println("Inside method testContainsAllFalse()");
		CollectionObjectAdapter<Integer> adapterInt=new CollectionObjectAdapter<Integer>();
		for(int i=size/2;i<size;i++)
			adapterInt.add(i*2);
		assertFalse(adapter.removeAll(adapterInt));
		int expSize=10;
		assertEquals(expSize,adapter.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testRemoveAllNullException()
	{
		System.out.println("Inside method testRemoveAllNullException()");	
		assertFalse(adapter.removeAll(null));
	}
	
	/**Test equals() e hashcode().
	 * Pre-condition: adapter inizializzato tramite startup, si fa puntare la seconda collezione
	 * alla prima.
	 * Post-condition: none.
	 * Test cases: si verifica che due collezioni sono uguali se hanno lo stesso riferimento.
	 * Execution variables: due collezioni con lo stesso riferimento e quindi lo stesso 
	 * hashcode.
	 * Execution records: true se le collezioni hanno lo stesso indirizzo.
	 * Si testa che due oggetti sono uguali entrambi gli oggetti hanno lo stesso riferimento
	 * e quindi lo stesso hashcode.*/
	@Test
	public void testEquals()
	{
		System.out.println("Inside method testEquals()");		
		CollectionObjectAdapter<Object> sameAdp=adapter;
		assertTrue(adapter.equals(sameAdp));
		assertEquals(sameAdp.hashCode(),adapter.hashCode());
	}
	
	/**Test retainAll() return value: true.
	 * Pre-condition: adapter inizializzato tramite startup, seconda sollezione inizializzata
	 * con gli ultimi 5 elementi della prima collezione.
	 * Post-condition: la collezione manterrà al suo interno solo gli elementi che sono
	 * presenti nella collezione passata come parametro.
	 * Test cases: si verifica che gli elementi contenuti nella collezione siano soltanto
	 * quelli della seconda collezione. Si confrontano i valori della prima collezione
	 * con quelli della seconda attraverso un iteratore.
	 * Execution variables: l'adapter, collezione con gli elementi da mantenere, varibile
	 * che rappresenta un numero intero incrementale.
	 * Execution records: true se la dimensione della collezione risulta cambiata.*/
	@Test
	public void testRetainAllTrue()
	{
		System.out.println("Inside method testRetainAllTrue()");
		CollectionObjectAdapter<Integer> adapterInt=new CollectionObjectAdapter<Integer>();
		for(int i=size/2;i<size;i++)
			adapterInt.add(i);
		assertTrue(adapter.retainAll(adapterInt));
		
		Iterator<Object> iter=adapter.iterator();
		int i=5;
		while(iter.hasNext())
			assertEquals(i++,iter.next());
	}
	
	/**Test retainAll() return value: false.
	 * Pre-condition: adapter inizializzato tramite startup, seconda sollezione inizializzata
	 * con gli stessi elementi della prima collezione più un ulteriore elemento non presente nella prima.
	 * Post-condition: la collezione rimane invarita.
	 * Test cases: si verifica che la dimenzione della prima collezione non sia cambiata.
	 * Execution variables: l'adapter, collezione con gli elementi da mantenere.
	 * Execution records: false se la dimensione della collezione non risulta cambiata.*/
	@Test
	public void testRetainAllFalse()
	{
		System.out.println("Inside method testRetainAllFalse()");
		CollectionObjectAdapter<Integer> adapterInt=new CollectionObjectAdapter<Integer>();
		//si inizializza il secondo adapter con gli stessi elementi
		for(int i=0;i<size+1;i++)
			adapterInt.add(i);
		assertFalse(adapter.retainAll(adapterInt));
	}
	
	@Test(expected=NullPointerException.class)
	public void testRetainAllNullException()
	{
		System.out.println("Inside method testRetainAllNullException()");
		assertFalse(adapter.retainAll(null));
	}
	
	@Test
	public void testEqualsTrueCollection()
	{
		System.out.println("Inside method testEqualsTrueCollection()");		
		Collection<Object> sameAdp=new CollectionObjectAdapter<Object>();
		for(int i=0;i<size;i++)
			sameAdp.add(i);
		assertTrue(adapter.equals(sameAdp));
		assertEquals(sameAdp.hashCode(),adapter.hashCode());
	}
	
	@Test
	public void testEqualsTrueSet()
	{
		System.out.println("Inside method testEqualsTrueSet()");		
		SetObjectAdapter<Object> sameAdp=new SetObjectAdapter<Object>();
		for(int i=0;i<size;i++)
			sameAdp.add(i);
		assertTrue(adapter.equals(sameAdp));
		assertEquals(sameAdp.hashCode(),adapter.hashCode());
	}
	
	@Test
	public void testEqualsTrueList()
	{
		System.out.println("Inside method testEquals()");		
		Collection<Object> sameAdp=new ListObjectAdapter<Object>();
		for(int i=0;i<size;i++)
			sameAdp.add(i);
		assertTrue(adapter.equals(sameAdp));
		assertEquals(sameAdp.hashCode(),adapter.hashCode());
	}
	
	@Test
	public void testEqualsFalse()
	{
		System.out.println("Inside method testEqualsFalse()");		
		ListObjectAdapter<Object> diffAdp=new ListObjectAdapter<Object>();
		for(int i=0;i<size;i++)
			diffAdp.add("value"+i);
		assertFalse(adapter.equals(diffAdp));
		assertNotEquals(diffAdp.hashCode(),adapter.hashCode());
	}
	
	@Test
	public void testEqualsFalseSubset()
	{
		System.out.println("Inside method testEqualsFalseSubset()");		
		Collection<Object> diffAdp=new SetObjectAdapter<Object>();
		for(int i=size/2;i<size;i++)
			diffAdp.add(i);
		assertFalse(adapter.equals(diffAdp));
		assertNotEquals(diffAdp.hashCode(),adapter.hashCode());
	}
	
	@Test(expected=ClassCastException.class)
	public void testEqualsCastException()
	{
		System.out.println("Inside method testEqualsCastException()");		
		assertTrue(adapter.equals(new Integer(0)));
	}
	
	@Test(expected=NullPointerException.class)
	public void testEqualsNullException()
	{
		System.out.println("Inside method testEqualsNullException()");		
		assertTrue(adapter.equals(null));
	}
	
}
