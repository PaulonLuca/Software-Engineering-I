import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Iterator;

public class TestSetObjectAdapter 
{
	SetObjectAdapter<Object> adapter=new SetObjectAdapter<Object>();
	
	/**Si inizializza il set con i nomi di 10 colori*/
	@Before
	public void setUp()
	{
		adapter.add("Giallo"); adapter.add("Rosso"); adapter.add("Arancione"); adapter.add("Viola");
		adapter.add("Verde"); adapter.add("Nero"); adapter.add("Marrone"); adapter.add("Grigio"); 
		adapter.add("Blu"); adapter.add("Bianco"); 
	}
	
	@Test
	public void testSize()
	{
		System.out.println("Inside method testSize()");
		int expSize=10;
		assertEquals(expSize,adapter.size());
	}
	
	//testa anche clear
	@Test
	public void testIsEmptyTrue()
	{
		System.out.println("Inside method testIsEmptyTrue()");
		adapter.clear();
		assertTrue(adapter.isEmpty());
	}
	
	@Test
	public void testIsEmptyFalse()
	{
		System.out.println("Inside method testIsEmptyFalse()");
		assertFalse(adapter.isEmpty());
	}
	
	@Test
	public void testAdd()
	{
		System.out.println("Inside method testAdd()");
		assertTrue(adapter.add("Azzurro"));
		assertTrue(adapter.add("Lilla"));
		assertTrue(adapter.add("Magenta"));
		int expSize=13;
		assertEquals(expSize,adapter.size());
	}
	
	@Test
	public void testAddDublicates()
	{
		System.out.println("Inside method testAddDublicates()");
		assertFalse(adapter.add("Giallo"));
		assertFalse(adapter.add("Viola"));
		assertFalse(adapter.add("Nero"));
		int expSize=10;
		assertEquals(expSize,adapter.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddNullException()
	{
		System.out.println("Inside method testAddNullException()");
		adapter.add(null);
	}
	
	@Test
	public void testAddAllTrue()
	{
		System.out.println("Inside method testAddAllTrue()");
		Collection<String> c=new ListObjectAdapter<String>();
		c.add("Verde");
		c.add("Azzurro");
		c.add("Argento");
		c.add("Blu");
		assertTrue(adapter.addAll(c));
		int expSize=12;
		assertEquals(expSize,adapter.size());
	}
	
	@Test
	public void testAddAllFalse()
	{
		System.out.println("Inside method testAddAllFalse()");
		Collection<String> c=new ListObjectAdapter<String>();
		c.add("Verde");
		c.add("Nero");
		c.add("Rosso");
		c.add("Blu");
		assertFalse(adapter.addAll(c));
		int expSize=10;
		assertEquals(expSize,adapter.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAllNullException()
	{
		System.out.println("Inside method testAddAllNullException()");
		adapter.addAll(null);
	}
	
	@Test
	public void testContainsTrue()
	{
		System.out.println("Inside method testContainsTrue()");
		assertTrue(adapter.contains("Giallo"));
		assertTrue(adapter.contains("Nero"));
		assertTrue(adapter.contains("Arancione"));
	}
	
	@Test
	public void testContainsFalse()
	{
		System.out.println("Inside method testContainsFalse()");
		assertFalse(adapter.contains("Lilla"));
		assertFalse(adapter.contains("Magenta"));
		assertFalse(adapter.contains("Azzurro"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testContainsNullException()
	{
		System.out.println("Inside method testContainsNullException()");
		adapter.contains(null);
	}
	
	@Test
	public void testContainsAllTrue()
	{
		System.out.println("Inside method testContainsAllTrue()");
		Collection<String> c=new ListObjectAdapter<String>();
		c.add("Verde");
		c.add("Viola");
		c.add("Bianco");
		c.add("Blu");
		assertTrue(adapter.containsAll(c));
	}
	
	//si testano sia elementi contenuti sia non contenuti
	@Test
	public void testContainsAllFalse()
	{
		System.out.println("Inside method testContainsAllFalse()");
		Collection<String> c=new CollectionObjectAdapter<String>();
		c.add("Verde");
		c.add("Grigio");
		c.add("Lilla");
		c.add("Magenta");
		assertFalse(adapter.containsAll(c));
	}
	
	@Test(expected=NullPointerException.class)
	public void testContainsAllNullException()
	{
		System.out.println("Inside method testContainsAllNullException()");
		adapter.containsAll(null);
	}
	
	@Test
	public void testRemoveTrue()
	{
		System.out.println("Inside method testContainsAllFalse()");
		String toRemove="Viola";
		assertTrue(adapter.remove(toRemove));
		toRemove="Rosso";
		assertTrue(adapter.remove(toRemove));
		toRemove="Grigio";
		assertTrue(adapter.remove(toRemove));
		int expSize=7;
		assertEquals(expSize,adapter.size());
	}
	
	@Test
	public void testRemoveFalse()
	{
		System.out.println("Inside method testRemoveFalse()");
		String toRemove="Lilla";
		assertFalse(adapter.remove(toRemove));
		toRemove="Magenta";
		assertFalse(adapter.remove(toRemove));
		toRemove="Argento";
		assertFalse(adapter.remove(toRemove));
		int expSize=10;
		assertEquals(expSize,adapter.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testRemoveNullException()
	{
		System.out.println("Inside method testRemoveNullException()");
		adapter.remove(null);
	}
	
	@Test
	public void testRemoveAllTrue()
	{
		System.out.println("Inside method testRemoveAllTrue()");
		Collection<String> c=new SetObjectAdapter<String>();
		c.add("Verde");
		c.add("Viola");
		c.add("Bianco");
		c.add("Lilla");
		assertTrue(adapter.removeAll(c));
		int expSize=7;
		assertEquals(expSize,adapter.size());
	}
	
	@Test
	public void testRemoveAllFalse()
	{
		System.out.println("Inside method testRemoveAllFalse()");
		Collection<String> c=new ListObjectAdapter<String>();
		c.add("Magenta");
		c.add("Lilla");
		c.add("Argento");
		assertFalse(adapter.removeAll(c));
		int expSize=10;
		assertEquals(expSize,adapter.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testRemoveAllNullException()
	{
		System.out.println("Inside method testRemoveAllNullException()");
		adapter.removeAll(null);
	}
	
	@Test
	public void testRetainAllTrue()
	{
		System.out.println("Inside method testRetainAllTrue()");
		Collection<String> c=new SetObjectAdapter<String>();
		c.add("Verde");
		c.add("Viola");
		c.add("Bianco");
		c.add("Blu");
		c.add("Lilla");
		assertTrue(adapter.retainAll(c));
		int expSize=4;
		assertEquals(expSize,adapter.size());
	}
	
	@Test
	public void testRetainAllFalse()
	{
		System.out.println("Inside method testRetainAllFalse()");
		Collection<Object> c=adapter;		
		assertFalse(adapter.retainAll(c));
		int expSize=10;
		assertEquals(expSize,adapter.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testRetainsAllNullException()
	{
		System.out.println("Inside method testRetainsAllNullException()");
		adapter.retainAll(null);
	}
	
	//a differenza del metodo toArray di list l'array ritornato non ha alcun riferimento con il set
	//di riferimento. Quindi ogni modifica al set non riperquote sull'array e ogni modifica all'array
	//non si ripequote sul set
	@Test
	public void testToArray()
	{
		Object[] array=adapter.toArray();
		Collection<String> sameSet=new SetObjectAdapter<String>();
		for(int i=0;i<array.length;i++)
			sameSet.add((String)array[i]);
		assertTrue(adapter.containsAll(sameSet));	
	}
	
	//gli elementi ritornati dell'iteratore devono essere ordinati nello stesso modo in cui vengono inseriti
	//nell'array dal metodo toArray()
	@Test
	public void testIterator()
	{
		Object[] array=adapter.toArray();
		Iterator<Object> iter=adapter.iterator();
		int i=0;
		while(iter.hasNext())
			assertEquals(iter.next(),array[i++]);
	}
	
	
	/**Test equals.
	 * Si testa che due set sono uguali se entrambi i set contengono gli stessi elementi.*/
	@Test
	public void testEqualsTrue()
	{
		System.out.println("Inside method testEqualsTrue()");		
		Set<Object> sameAdp=new SetObjectAdapter<Object>();
		sameAdp.add("Giallo"); sameAdp.add("Rosso"); sameAdp.add("Arancione"); sameAdp.add("Viola");
		sameAdp.add("Verde"); sameAdp.add("Nero"); sameAdp.add("Marrone"); sameAdp.add("Grigio"); 
		sameAdp.add("Blu"); sameAdp.add("Bianco"); 
		assertTrue(adapter.equals(sameAdp));
		assertEquals(sameAdp.hashCode(),adapter.hashCode());
	}
	
	@Test
	public void testEqualsFalse()
	{
		System.out.println("Inside method testEqualsFalse()");		
		Set<Object> diffAdp=new SetObjectAdapter<Object>();
		diffAdp.add("Giallo"); diffAdp.add("Rosso"); diffAdp.add("Arancione"); diffAdp.add("Viola");
		diffAdp.add("Verde"); diffAdp.add("Nero"); //stessi elementi la dimensione differente
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
