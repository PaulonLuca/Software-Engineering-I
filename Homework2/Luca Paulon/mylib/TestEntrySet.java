import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestEntrySet 
{
	/**Essendo entrySet un set specializzato con delle entry quando riceve delle collezioni per poter
	 * eseguire delle elaborazioni queste devono contenere delle entry altrimenti viene lanciata
	 * l'eccezione ClassCastException*/
	
	Map<Integer, String> mappa=new MapObjectAdapter<Integer,String>();
	Set<Map.Entry<Integer,String>> entrySet;
	
	@Before
	public void setUp()
	{
		int size=10;
		for(int i=0;i<size;i++)
			mappa.put(i, "valore"+i);
		entrySet=mappa.entrySet();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testAddUnsupportedException()
	{	
		System.out.println("Inside method testAddUnsupportedException()");
		entrySet.add(new MapObjectAdapter.SimpleEntry<Integer,String>(0, "valore0"));		
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testAddAllUnsupportedException()
	{	
		System.out.println("Inside method testAddAllUnsupportedException()");
		Collection<Map.Entry<Integer, String>> c=new ListObjectAdapter<Map.Entry<Integer, String>>();
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(0, "valore0"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(1, "valore1"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(2, "valore2"));
		entrySet.addAll(c);
	}
	
	@Test
	public void testClearEntrySetMappa()
	{
		System.out.println("Inside method testClearEntrySetMappa()");
		entrySet.clear();
		assertTrue(mappa.isEmpty());
		assertTrue(entrySet.isEmpty());
	}
	
	@Test
	public void testClearMappaEntrySet()
	{
		System.out.println("Inside method testClearMappaEntrySet()");
		mappa.clear();
		assertTrue(mappa.isEmpty());
		assertTrue(entrySet.isEmpty());
	}
	
	@Test
	public void testRemoveEntrySetTrue()
	{
		//chiave e valore devono corrispondere a quelli nella mappa
		System.out.println("Inside method testRemoveEntrySet()");
		assertTrue(entrySet.remove(new MapObjectAdapter.SimpleEntry<Integer,String>(0, "valore0")));
		assertTrue(entrySet.remove(new MapObjectAdapter.SimpleEntry<Integer,String>(1, "valore1")));
		assertTrue(entrySet.remove(new MapObjectAdapter.SimpleEntry<Integer,String>(2, "valore2")));
		int expSize=7;
		assertEquals(expSize,entrySet.size());
		assertEquals(expSize,mappa.size());
	}
	
	@Test
	public void testRemoveKeySetFalse()
	{
		//basta che la chiave non sia nella mappa o che la chiave sia corretta ma il valore associato errato
		System.out.println("Inside method testRemoveEntrySetFalse()");
		assertFalse(entrySet.remove(new MapObjectAdapter.SimpleEntry<Integer,String>(0, "valore10")));
		assertFalse(entrySet.remove(new MapObjectAdapter.SimpleEntry<Integer,String>(1, "valore11")));
		assertFalse(entrySet.remove(new MapObjectAdapter.SimpleEntry<Integer,String>(10, "valore3")));
		int expSize=10;
		assertEquals(expSize,entrySet.size());
		assertEquals(expSize,mappa.size());
	}
	
	@Test(expected=ClassCastException.class)
	public void testRemoveKeySetClassCastEception()
	{
		System.out.println("Inside method testRemoveKeySetClassCastEception()");
		assertTrue(entrySet.remove(1));
		assertTrue(entrySet.remove(2));
	}
	
	@Test
	public void testRemoveMappa()
	{
		System.out.println("Inside method testRemoveMappa()");
		mappa.remove(1);
		mappa.remove(2);
		mappa.remove(3);
		int expSize=7;
		assertEquals(expSize,entrySet.size());
		assertEquals(expSize,mappa.size());
	}
	
	@Test
	public void testContainsTrue()
	{
		assertTrue(entrySet.contains(new MapObjectAdapter.SimpleEntry<Integer,String>(0, "valore0")));
		assertTrue(entrySet.contains(new MapObjectAdapter.SimpleEntry<Integer,String>(1, "valore1")));
		assertTrue(entrySet.contains(new MapObjectAdapter.SimpleEntry<Integer,String>(2, "valore2")));
	}
	
	@Test
	public void testContainsFalse()
	{
		//basta che la chiave non sia nella mappa o che la chiave sia corretta ma il valore associato errato
		assertFalse(entrySet.contains(new MapObjectAdapter.SimpleEntry<Integer,String>(10, "valore10")));
		assertFalse(entrySet.contains(new MapObjectAdapter.SimpleEntry<Integer,String>(1, "valore2")));
		assertFalse(entrySet.contains(new MapObjectAdapter.SimpleEntry<Integer,String>(9, "valore8")));
	}
	
	@Test
	public void testContainsAllTrue()
	{
		Collection<Map.Entry<Integer, String>> c=new ListObjectAdapter<Map.Entry<Integer, String>>();
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(5, "valore5"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(6, "valore6"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(7, "valore7"));
		assertTrue(entrySet.containsAll(c));
	}
	
	@Test
	public void testContainsAllFalse()
	{
		//basta che la chiave non sia nella mappa o che la chiave sia corretta ma il valore associato errato
		Collection<Map.Entry<Integer, String>> c=new SetObjectAdapter<Map.Entry<Integer, String>>();
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(10, "valore10"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(5, "valore6"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(4, "valore7"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(9, "valore0"));	
		assertFalse(entrySet.containsAll(c));
	}
		
	@Test
	public void testRemoveAllTrue()
	{
		System.out.println("Inside method testRemoveAllTrue()");
		Collection<Map.Entry<Integer, String>> c=new SetObjectAdapter<Map.Entry<Integer, String>>();
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(1, "valore10"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(5, "valore5"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(6, "valore6"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(9, "valore0"));	
		assertTrue(entrySet.removeAll(c));
		int expSize=8;
		assertEquals(expSize,mappa.size());
		assertEquals(expSize,entrySet.size());
	}
	
	@Test
	public void testRemoveAllFalse()
	{
		System.out.println("Inside method testRemoveAllFalse()");
		Collection<Map.Entry<Integer, String>> c=new SetObjectAdapter<Map.Entry<Integer, String>>();
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(10, "valore1"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(20, "valore2"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(30, "valore30"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(2, "valore20"));	
		assertFalse(entrySet.removeAll(c));
		int expSize=10;
		assertEquals(expSize,mappa.size());
		assertEquals(expSize,entrySet.size());
	}
	
	@Test
	public void testRetainAllTrue()
	{
		System.out.println("Inside method testRetainAllTrue()");
		Collection<Map.Entry<Integer, String>> c=new ListObjectAdapter<Map.Entry<Integer, String>>();
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(1, "valore1"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(2, "valore2"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(3, "valore3"));
		c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(4, "valore20"));
		assertTrue(entrySet.retainAll(c));
		int expSize=3;
		assertEquals(expSize,mappa.size());
		assertEquals(expSize,entrySet.size());
	}
	
	@Test
	public void testRetainAllFalse()
	{
		System.out.println("Inside method testRetainAllFalse()");
		Collection<Map.Entry<Integer, String>> c=new ListObjectAdapter<Map.Entry<Integer, String>>();
		for(int i=0;i<mappa.size();i++)
			c.add(new MapObjectAdapter.SimpleEntry<Integer,String>(i, "valore"+i));
		
		assertFalse(entrySet.retainAll(c));
		int expSize=10;
		assertEquals(expSize,entrySet.size());
	}
	
	/**Poichè EntrySet utilizza lo stesso iterator di set valgono le stesse osservazioni per gli inserimenti
	 * e per le rimozioni durante le iterazioni.*/
	
}
