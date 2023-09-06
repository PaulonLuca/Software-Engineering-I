import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Iterator;

public class TestKeySet 
{
	Map<Integer, String> mappa=new MapObjectAdapter<Integer,String>();
	Set<Integer> keySet;
	
	@Before
	public void setUp()
	{
		int size=10;
		for(int i=0;i<size;i++)
			mappa.put(i, "valore"+i);	
		keySet=mappa.keySet();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testAdd()
	{	
		System.out.println("Inside method testAddUnsupportedException()");
		keySet.add(0);		
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testAddAll()
	{	
		System.out.println("Inside method testAddAllUnsupportedException()");
		Collection<Integer> c=new ListObjectAdapter();
		c.add(1);
		c.add(2);
		c.add(3);
		keySet.addAll(c);
	}
	
	@Test
	public void testClearKeySetMappa()
	{
		System.out.println("Inside method testClearKeySetMappa()");
		keySet.clear();
		assertTrue(mappa.isEmpty());
		assertTrue(keySet.isEmpty());
	}
	
	@Test
	public void testClearMappaKeySet()
	{
		System.out.println("Inside method testClearMappaKeySet()");
		mappa.clear();
		assertTrue(mappa.isEmpty());
		assertTrue(keySet.isEmpty());
	}
	
	@Test
	public void testRemoveKeySet()
	{
		System.out.println("Inside method testRemoveKeySet()");
		assertTrue(keySet.remove(1));
		assertTrue(keySet.remove(2));
		assertTrue(keySet.remove(3));
		int expSize=7;
		assertEquals(expSize,keySet.size());
		assertEquals(expSize,mappa.size());
	}
	
	@Test
	public void testRemoveMappa()
	{
		System.out.println("Inside method testRemoveMappa()");
		mappa.remove(1);
		mappa.remove(2);
		mappa.remove(3);
		int expSize=7;
		assertEquals(expSize,keySet.size());
		assertEquals(expSize,mappa.size());
	}
	
	//aggiungendo nuove entry alla mappa le chiavi di queste sono visibili anche nel keySet
	@Test
	public void testModifyMap()
	{
		System.out.println("Inside method testModifyMap()");
		int toModify=100;
		mappa.put(toModify, "valore"+toModify);
		assertTrue(keySet.contains(toModify));
		toModify=200;
		mappa.put(toModify, "valore"+toModify);
		assertTrue(keySet.contains(toModify));
		toModify=300;
		mappa.put(toModify, "valore"+toModify);
		assertTrue(keySet.contains(toModify));
	}
	
	@Test
	public void testRemoveAllTrue()
	{
		System.out.println("Inside method testRemoveAllTrue()");
		Collection<Integer> c=new ListObjectAdapter();
		c.add(1);
		c.add(2);
		c.add(5);
		c.add(12);
		assertTrue(keySet.removeAll(c));
		int expSize=7;
		assertEquals(expSize,mappa.size());
		assertEquals(expSize,keySet.size());
	}
	
	@Test
	public void testRemoveAllFalse()
	{
		System.out.println("Inside method testRemoveAllFalse()");
		Collection<Integer> c=new ListObjectAdapter();
		c.add(10);
		c.add(20);
		c.add(30);
		c.add(40);
		assertFalse(keySet.removeAll(c));
		int expSize=10;
		assertEquals(expSize,mappa.size());
		assertEquals(expSize,keySet.size());
	}
		
	@Test
	public void testRetainAllTrue()
	{
		System.out.println("Inside method testRetainAllTrue()");
		Collection<Integer> c=new ListObjectAdapter();
		c.add(1);
		c.add(2);
		c.add(5);
		c.add(10);
		c.add(11);
		assertTrue(keySet.retainAll(c));
		int expSize=3;
		assertEquals(expSize,mappa.size());
		assertEquals(expSize,keySet.size());
	}
	
	@Test
	public void testRetainAllFalse()
	{
		System.out.println("Inside method testRetainAllFalse()");
		Collection<Integer> c=new ListObjectAdapter();
		int mapSize=mappa.size();
		for(int i=0;i<mapSize;i++)
			c.add(i);
		assertFalse(keySet.retainAll(c));
		int expSize=10;
		assertEquals(expSize,mappa.size());
		assertEquals(expSize,keySet.size());
	}
	
	/**l'iteratore della classe set non è lazy, opera su un array contenente le chiavi degli elementi
	*che erano presenti nella mappa prima che avvenisse l'inserimento dell'elemento nella mappa.
	*Quindi in caso di aggiunta di un elemento questo non verrà ritornato dall'iteratore a meno che
	*non se ne richieda uno nuovo.*/
	@Test
	public void testIteratorAdd()
	{
		System.out.println("Inside method testIteratorAdd()");
		//richiesta di un iteratore sulla vista delle chiavi con aggiunta di elemento sucessiva
		//alla richiesta dell'iteratore
		Iterator<Integer> iter =keySet.iterator();
		int i=0;
		while(iter.hasNext())
		{
			if(i==mappa.size()/2)
				mappa.put(100, "valore100");
			iter.next();
			i++;
		}
		assertEquals(i,mappa.size()-1);
		
		//modifica dell'elemento precedentemente inserito in posizione 100 e richiesta di iteratore 
		//sui dati aggiornati
		mappa.put(100, "newValore100");
		iter =keySet.iterator();	
		i=0;
		while(iter.hasNext())
		{			
			iter.next();
			i++;
		}		
		assertEquals(i,mappa.size());
	}
	
	/**Quando si rimuove un elemento durante l'iterazione, che questo si trovi prima o dopo
	 * la posizione corrente non ha importanza, ciò che importa è che l'iteratore non lo considera
	 * comuque perchè sta lavorando su una copia dei dati. Per fare in modo che vengano
	 * elaborati dati aggiornati è necessario richiedere un nuovo iteratore.*/
	@Test
	public void testIteratorRemove()
	{
		System.out.println("Inside method testIteratorRemove()");
		//richiesta di un iteratore sulla vista delle chiavi con aggiunta di elemento sucessiva
		//alla richiesta dell'iteratore
		Iterator<Integer> iter =keySet.iterator();
		int i=0;
		while(iter.hasNext())
		{
			if(i==mappa.size()/2)
				mappa.remove(0);
			iter.next();
			i++;
		}
		assertEquals(i,mappa.size()+1);//viene conteggiato dall'iteratore anche l'elemento che è stato rimosso
		
		iter =keySet.iterator();	
		i=0;
		while(iter.hasNext())
		{			
			iter.next();
			i++;
		}		
		assertEquals(i,mappa.size());//conteggia gli elementi dell'iteratore aggiornato
	}	

}
