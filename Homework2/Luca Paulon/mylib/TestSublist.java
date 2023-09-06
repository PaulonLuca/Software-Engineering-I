import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.ListIterator;

public class TestSublist 
{
	List<Object> list=new ListObjectAdapter<Object>();
	List<Object> sublist;
	int fromIndex=5;
	int toIndex=15;
	
	@Before
	public void setUp()
	{
		int size=20;
		for(int i=0;i<size;i++)
			list.add(i);
		sublist=list.subList(fromIndex, toIndex);
	}
	
	@Test
	public void testSize()
	{
		System.out.println("Inside method testSize()");
		int expected=10;
		assertEquals(expected,sublist.size());
	}
	
	@Test
	public void testIsEmptyFalse()
	{	
		System.out.println("Inside method testIsEmptyFalse()");
		assertFalse(sublist.isEmpty());
	}
	
	@Test
	public void testClearIsEmptyTrue()
	{
		System.out.println("Inside method testClearIsEmptyTrue()");
		sublist.clear();
		assertTrue(sublist.isEmpty());
		int expSize=10;
		assertEquals(expSize,list.size());
	}
	
	@Test
	public void testAdd()
	{
		System.out.println("Inside method testAdd()");
		sublist.add(20);
		sublist.add(21);
		sublist.add(22);
		int expSub=13;
		int expList=23;
		assertEquals(expSub,sublist.size());
		assertEquals(expList,list.size());
	}
	
	@Test
	public void testAddAll()
	{
		System.out.println("Inside method testAddAll()");
		Collection<Object> c=new ListObjectAdapter<Object>();
		c.add(20);
		c.add(30);
		c.add(40);
		c.add(50);
		assertTrue(sublist.addAll(c));
		int expSub=14;
		int expList=24;
		assertEquals(expSub,sublist.size());
		assertEquals(expList,list.size());
		Iterator<Object> iter=sublist.listIterator();
	}
	
	@Test
	public void testGet()
	{
		System.out.println("Inside method testGet()");
		Integer exp7=new Integer(7);
		Integer exp14=new Integer(14);
		Integer exp10=new Integer(10);
		assertEquals(exp7,sublist.get(2));
		assertEquals(exp14,sublist.get(9));
		assertEquals(exp10,sublist.get(5));	
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetBounfException1()
	{
		System.out.println("Inside method testGetNullException1()");
		sublist.get(-1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetBoundException2()
	{	
		System.out.println("Inside method testGetBoundException2()");
		sublist.get(toIndex);
	}
	
	@Test
	public void testToArray()
	{
		System.out.println("Inside method testToArray()");
		Object[] array=sublist.toArray();	
		Iterator<Object> iter=sublist.listIterator();
		int i=0;
		while(iter.hasNext())
			assertEquals(iter.next(),array[i++]);
	}
	
	/**Test toArray() ShallowCopy
	 * Si crea una classe IntHolder per verificare che modificando un elemento dell'array
	 * che contiene gli elementi della sottolista vengono modificati anche gli elementi della
	 * lista e della sottolista stessa
	 * Pre-condition: creazione di una lista di IntHolder inizializzati con numeriinteri da 0 a 19,
	 * creazione si una sottolista contenente una vista degli elementi tra 5 e 14.
	 * Post-condizioni: l'elemento in posizione 0 dell'array che è quello in posizione 5 della lista
	 * e 0 della sottolista viene modificato e le modifiche risultano applicate anche a lista e sottolista.
	 * Test-cases: si verifica che l'elemento modificato nell'array sia modificato anche nella lista e sottolista
	 * Execution variables: lista di IntHolder, sublist di IntHolder, array di IntHolder
	 * Execution record: true se elemento modificato in posizione 0 nell'array risulta modificato anche
	 * in lista e sottolista.*/
	@Test
	public void testToArrayShallowCopy()
	{
		System.out.println("Inside method testToArrayShallowCopy()");
		List<IntHolder> listIntH=new ListObjectAdapter<IntHolder>();
		for(int i=0;i<list.size();i++)
			listIntH.add(new IntHolder(i));
		List<IntHolder>sublistIntH=listIntH.subList(5, 15);		
		Object[] array=sublistIntH.toArray();		

		IntHolder n=(IntHolder)array[0];
		n.setInteger(n.getInteger()*100);
		
		int expInt=500;
		assertEquals(expInt,listIntH.get(5).getInteger());
		assertEquals(expInt,sublistIntH.get(0).getInteger());
	}
	
	/**Test toArray() sructural modify
	 * Pre-condition: creazione di una lista di IntHolder inizializzati con numeriinteri da 0 a 19,
	 * creazione si una sottolista contenente una vista degli elementi tra 5 e 14.
	 *  Post-condition: il nuovo elemento in posizione 5 della lista è l'IntHolder contenente il valore 6, l'elemento in
	 *  posizione 0 dell'array è un IntHolder contenente l'intero 10
	 *  Test-cases: si testa che dopo la rimozione dell'elemento in posizione 5 della lista il corrispondente
	 *  elemento in posizione 0 nell'array sia rimasto invariato. Poi si testa che modificando l'elemento
	 *  in posizione 0 dell'array il corrispondenti elemento in posizone 5 della lista e della sottolista
	 *  siano rimasti invariati
	 *  Execution variables: lista di IntHolder, sublist di IntHolder, array di IntHolder
	 *  Execution records: true se modificando la struttura dell'array non si modifica la stuttura di lista
	 *  e sottolista; true se modificando la struttura della lista non si modifica la struttura dell'array */
	@Test
	public void testToArrayStructuralModify()
	{
		System.out.println("Inside method testToArrayStructuralModify()");
		List<IntHolder> listIntH=new ListObjectAdapter<IntHolder>();
		for(int i=0;i<list.size();i++)
			listIntH.add(new IntHolder(i));
		List<IntHolder>sublistIntH=listIntH.subList(5, 15);		
		Object[] array=sublistIntH.toArray();
		
		//modificando la struttura della lista l'array rimane invariato
		//eliminazione dell'elemento in posizione 5 della lista che corrsiponderebbe
		//all'elemento in posizone 0 dell'array
		listIntH.remove(5);
		int expValue=5;
		assertEquals(expValue,((IntHolder) array[0]).getInteger());
				
		//modificando la struttura dell'array la lista e la sottolista rimangono invariate
		//si modifica l'elemento in posizione 0 dell'array che corrisponde all'elemento in
		//posizione 5 della lista
		array[0]=new IntHolder(10);
		expValue=6; //per via dello shift
		assertEquals(expValue,listIntH.get(5).getInteger());	
		assertEquals(expValue,sublistIntH.get(0).getInteger());
	}

	/**Test indexOf()
	 * Pre-condition: sublist inizializzata con i valori interi da 5 a 14
	 * Post-condizione: sia la lista che la sottolista risultano invariate
	 * Test case: si testano le corrisondenze tra gli elementi e gli indici attesi.
	 * Elemento 5 in posizione 5, elemento 10 in posizione 10, elemento 14 in posizione 14,
	 * elemento 4 in posizione -1 (poichè appartiene alla lista ma non alla sottolista),
	 * elemento 15 in posizone -1 (poichè appartiene alla lista ma non alla sottolista)
	 * Execution variables: numeri 4,5,10,14,15
	 * Excecution records: true se gli elementi corrispondono agli indici previsti*/
	@Test
	public void testIndexOf()
	{
		System.out.println("Inside method testIndexOf()");
		int expInd5=0;
		int expInd10=5;
		int expInd14=9;
		assertEquals(expInd5,sublist.indexOf(5));
		assertEquals(expInd10,sublist.indexOf(10));
		assertEquals(expInd14,sublist.indexOf(14));
		assertEquals(-1,sublist.indexOf(4));//non appartenente alla sottolista
		assertEquals(-1,sublist.indexOf(15));//non appartenente alla sottolista
	}
	
	@Test
	public void testContainsTrue()
	{
		System.out.println("Inside method testContainsTrue()");
		int elem12=12;
		int elem13=13;
		int elem6=6;
		assertTrue(sublist.contains(elem6));
		assertTrue(sublist.contains(elem12));
		assertTrue(sublist.contains(elem13));
	}
	
	@Test
	public void testContainsFalse()
	{
		System.out.println("Inside method testContainsFalse()");
		int elem4=4;
		int elem15=15;
		int elem20=20;
		assertFalse(sublist.contains(elem4));
		assertFalse(sublist.contains(elem15));
		assertFalse(sublist.contains(elem20));
	}
	
	@Test(expected=NullPointerException.class)
	public void testindexOfNullException1()
	{	
		System.out.println("Inside method testindexOfNullException1()");
		sublist.indexOf(null);
	}
	
	@Test
	public void testContainsAllTrue()
	{
		System.out.println("Inside method testContainsAllTrue()");
		Collection<Object> c=new ListObjectAdapter<Object>();
		c.add(5);
		c.add(6);
		c.add(10);
		c.add(12);
		assertTrue(sublist.containsAll(c));
	}
	
	//falso se almeno un elemento non risulta contenuto
	@Test
	public void testContainsAllFalse()
	{
		System.out.println("Inside method testContainsAllFalse()");
		Collection<Object> c=new ListObjectAdapter<Object>();
		c.add(10);
		c.add(12);
		c.add(3);
		c.add(11);
		assertFalse(sublist.containsAll(c));
	}
	
	@Test
	public void testLastIndexOf()
	{
		System.out.println("Inside method testLastIndexOf()");
		sublist.add(5);
		sublist.add(5);
		sublist.add(10);
		sublist.add(10);
		int expPos5=11;
		int expPos10=13;
		assertEquals(expPos5,sublist.lastIndexOf(5));
		assertEquals(expPos10,sublist.lastIndexOf(10));
		assertEquals(-1,sublist.lastIndexOf(15));//non appartenente alla sottolista
	}
	
	@Test(expected=NullPointerException.class)
	public void testLastIndexOfNullException1()
	{	
		System.out.println("Inside method testLastIndexOfNullException1()");
		sublist.lastIndexOf(null);
	}
	
	@Test
	public void testSet()
	{
		System.out.println("Inside method testSet()");
		int old5=5;
		int old10=10;
		assertEquals(old5,sublist.set(0, 10));
		assertEquals(10,sublist.get(0));
		assertEquals(10,list.get(5));
		assertEquals(old10,sublist.set(5, 30));
		assertEquals(30,sublist.get(5));
		assertEquals(30,list.get(10));
	}
	
	@Test(expected=NullPointerException.class)
	public void testSetNullException()
	{	
		System.out.println("Inside method testSetNullException()");
		sublist.set(5,null);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSetBoundException()
	{
		System.out.println("Inside method testSetBoundException()");
		sublist.set(toIndex,10);
	}
	
	@Test
	public void testRemove()
	{
		System.out.println("Inside method testRemove()");
		Integer toRem10=new Integer(10);
		Integer toRem14=new Integer(14);
		Integer toRem=new Integer(1);
		assertTrue(sublist.remove(toRem10));
		assertTrue(sublist.remove(toRem14));
		assertFalse(sublist.remove(toRem14));//non appartenente alla sottolista
		int expSize=8;
		assertEquals(expSize,sublist.size());
		expSize=18;
		assertEquals(expSize,list.size());
	}
	
	@Test
	public void testRemoveIndex()
	{
		System.out.println("Inside method testRemoveIndex()");
		int exp10=10;
		int exp13=13;
		//attenzione all'ordine delle rimozioni perchè nella sublist avviene la compattazione automatica
		assertEquals(exp13,sublist.remove(8));
		assertEquals(exp10,sublist.remove(5));
		int expSize=8;
		assertEquals(expSize,sublist.size());
		expSize=18;
		assertEquals(expSize,list.size());
		
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveIndexBoundException1()
	{	
		System.out.println("Inside method testRemoveIndexBoundException1()");
		sublist.remove(toIndex);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveIndexBoundException2()
	{	
		System.out.println("Inside method testRemoveIndexBoundException2()");
		sublist.remove(-1);
	}
	
	@Test
	public void testRemoveAll()
	{
		System.out.println("Inside method testRemoveAll()");
		Collection<Integer> c=new ListObjectAdapter<Integer>();
		c.add(6);
		c.add(8);
		c.add(9);
		c.add(12);
		c.add(16);
		c.add(18);
		assertTrue(sublist.removeAll(c));
		int expSize=6;
		assertEquals(expSize,sublist.size());
		expSize=16;
		assertEquals(expSize,list.size());
	}

	@Test(expected=NullPointerException.class)
	public void testRemoveAllNullException()
	{	
		System.out.println("Inside method testRemoveAllNullException()");
		sublist.removeAll(null);
	}
	
	@Test
	public void testRetainAllTrue()
	{
		System.out.println("Inside method testRetainAllTrue()");
		Collection<Integer> c=new ListObjectAdapter<Integer>();
		c.add(5);
		c.add(6);
		c.add(7);
		c.add(10);
		c.add(12);
		c.add(13);
		int expSize=6;
		assertTrue(sublist.retainAll(c));
		assertEquals(expSize,sublist.size());
	}
	
	/**Si mantengono nella lista elementi di una collezione che però non sono contenuti nella sottolista*/
	@Test
	public void testRetainAllEmpty()
	{
		System.out.println("Inside method testRetainAllEmpty()");
		Collection<Integer> c=new ListObjectAdapter<Integer>();
		c.add(1);
		c.add(2);
		c.add(3);
		c.add(15);
		c.add(16);
		c.add(18);
		assertTrue(sublist.retainAll(c));
		int expSize=0;
		assertEquals(expSize,sublist.size());
	}
	
	@Test
	public void testRetainAllFalse()
	{
		System.out.println("Inside method testRetainAllFalse()");
		List<Object> c=list.subList(fromIndex, toIndex);		
		assertFalse(sublist.retainAll(c));
		int expSize=10;
		assertEquals(expSize,sublist.size());
	}
	
	@Test
	public void testListIterator()
	{
		System.out.println("Inside method testListIterator()");
		ListIterator<Object> iter=sublist.listIterator();
		
		//test hasNext() e next()
		//scorrimento a destra, cursore in posizione toIndex
		int i=fromIndex;
		while(iter.hasNext())
			assertEquals(i++,iter.next());
		
		//test nextIndex()
		//verifica che l'indice precedente sia toIndex-fromIndex-1
		int prevInd=toIndex-fromIndex-1;
		assertEquals(prevInd,iter.previousIndex());
		
		//test hasPrevious() e previous()
		//scorrimento a sinistra, cursore in posizione fromIndex
		i=14;
		while(iter.hasPrevious())
			assertEquals(i--,iter.previous());
		
		//test previousIndex()
		//verifica che l'indice successivo sia fromIndex+1
		int nextInd=1;
		assertEquals(nextInd,iter.nextIndex());
		
		//test add()
		//aggiunta di un elemento in testa alla sottolista
		iter.add(4);
		toIndex++;
		int expElem=4;
		assertEquals(expElem,sublist.get(0));
		int expSize=11;
		assertEquals(expSize,sublist.size());
		
		//scorrimento a destra dell'indice e si rimuove l'ultimo elemento ritornato da next()
		i=4;
		while(iter.hasNext())
			assertEquals(i++,iter.next());
		int expRemElem=13;
		iter.remove();
		toIndex--;
		assertEquals(expRemElem,sublist.get(sublist.size()-1));
		expSize=10;
		assertEquals(expSize,sublist.size());
		
		//test set()
		iter.set(100);
		expElem=100;
		assertEquals(expElem,sublist.get(sublist.size()-1));
	}
	
	/**Verifica che la porzione centrale della lista risulti ordinata*/
	@Test
	public void testSort()
	{
		System.out.println("Inside method testSort()");
		List<Integer> list=new ListObjectAdapter();
		list.add(6);
		list.add(3);
		list.add(5);
		list.add(1);
		list.add(2);
		list.add(4);
		List<Integer> sub=list.subList(1, 5);
		sub.sort(new IntegerComparator());
		List<Integer> orderedList=new ListObjectAdapter();
		orderedList.add(1);
		orderedList.add(2);
		orderedList.add(3);
		orderedList.add(5);		
		for(int i=1;i<sub.size()-1;i++)//esclusione del primo e dell'ultimo elemento
			assertEquals(orderedList.get(i),sub.get(i));		
	}
	
	/**Se durante l'iterazione si aggiunge un elemento dopo la posizione corrente la lista
	* su cui si itera viene aggiornata e vengono aggiornati gli indici fromIndex e toIndex. 
	* Quindi a seguito dell'inserimento nella sottolista o nella lista dopo
	* la posizione corrente a causa dello shift a destra degli elementi, il nuovo inserito
	* rientra nella vista della sottolista.
	* Lo stesso vale se invece l'inserimento viene fatto prima della posizione corrente.*/
	
	@Test
	public void testAddDuringIterationAfter()
	{
		System.out.println("Inside method testAddDuringIterationAfter()");
		Iterator<Object> iter=sublist.iterator();	
		//aggiunta di un elemento dopo la posizione corrente durante l'iterazione
		int i=0;
		while(iter.hasNext())
		{
			if(i==sublist.size()/2)
				sublist.add(sublist.size()-1,100);
			iter.next();
			i++;			
		}
		assertEquals(i,sublist.size());
	}
	
	@Test
	public void testAddDuringIterationBefore()
	{
		System.out.println("Inside method testAddDuringIterationBefore()");
		Iterator<Object> iter=sublist.iterator();	
		//aggiunta di un elemento dopo la posizione corrente durante l'iterazione
		int i=0;
		while(iter.hasNext())
		{
			if(i==sublist.size()/2)
				sublist.add(0,100);
			iter.next();
			i++;			
		}
		assertEquals(i,sublist.size());
	}
	
	/**Nel caso della rimozione invece, si rimuove l'elemento dalla sottolista quindi la lista
	 *di backing storage risulta aggiornata, si aggiorna il valore di toIndex nella sottolista.
	Come nel caso dell'inserimento la lista risulta aggiornata con la sottolista.*/
	
	@Test
	public void testRemoveDuringIterationAfter()
	{
		System.out.println("Inside method testRemoveDuringIterationAfter()");
		Iterator<Object> iter=sublist.iterator();	
		//aggiunta di un elemento dopo la posizione corrente durante l'iterazione
		int i=0;
		while(iter.hasNext())
		{
			if(i==sublist.size()/2)
				sublist.remove(sublist.size()-1);
			iter.next();
			i++;			
		}
		assertEquals(i,sublist.size());
	}
	
	@Test
	public void testRemoveDuringIterationBefore()
	{
		System.out.println("Inside method testRemoveDuringIterationBefore()");
		Iterator<Object> iter=sublist.iterator();	
		//aggiunta di un elemento dopo la posizione corrente durante l'iterazione
		int i=0;
		while(iter.hasNext())
		{
			if(i==sublist.size()/2)
				sublist.remove(0);
			iter.next();
			i++;			
		}
		assertEquals(i,sublist.size());
	}
}
