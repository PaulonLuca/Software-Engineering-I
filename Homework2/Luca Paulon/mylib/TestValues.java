import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Iterator;

public class TestValues 
{
	Map<Integer, String> mappa=new MapObjectAdapter<Integer,String>();
	Collection<String> values;
	
	@Before
	public void setUp()
	{
		int size=10;
		for(int i=0;i<size;i++)
			mappa.put(i, "valore"+i);	
		values=mappa.values();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testAdd()
	{	
		System.out.println("Inside method testAddUnsupportedException()");
		values.add("valore0");		
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testAddAll()
	{	
		System.out.println("Inside method testAddAllUnsupportedException()");
		Collection<String> c=new ListObjectAdapter();
		c.add("valore1");
		c.add("valore2");
		c.add("valore3");
		values.addAll(c);
	}
	
	/**Cancellando tutti gli elementi dalla mappa si cancellano tutti gli elementi anche dalla
	 * collection di values.*/
	@Test
	public void testClearValuesMappa()
	{
		System.out.println("Inside method testClearValuesMappa()");
		values.clear();
		assertTrue(mappa.isEmpty());
		assertTrue(values.isEmpty());
	}
	
	/**Cancellando tutti gli elementi dalla collezione di values si cancellano tutti gli elementi anche mappa.*/
	@Test
	public void testClearMappaValues()
	{
		System.out.println("Inside method testClearMappaKeySet()");
		mappa.clear();
		assertTrue(mappa.isEmpty());
		assertTrue(values.isEmpty());
	}
	
	/**Rimuovendo dei valori dalla collezione di values gli stessi vengono rimossi anche dalla mappa*/
	@Test
	public void testRemoveValues()
	{
		System.out.println("Inside method testRemoveValues()");
		assertTrue(values.remove("valore1"));
		assertTrue(values.remove("valore2"));
		assertTrue(values.remove("valore3"));
		int expSize=7;
		assertEquals(expSize,values.size());
		assertEquals(expSize,mappa.size());
	}
	
	/**Rimuovendo dei valori dalla mappa gli stessi vengono rimossi anche dalla collezione di values*/
	@Test
	public void testRemoveMappa()
	{
		System.out.println("Inside method testRemoveMappa()");
		mappa.remove(1);
		mappa.remove(2);
		mappa.remove(3);
		int expSize=7;
		assertEquals(expSize,values.size());
		assertEquals(expSize,mappa.size());
	}
	
	/**aggiungendo nuove entry alla mappa i valori corrispondenti sono visibili anche nella collezione
	 * di values*/
	@Test
	public void testModifyMap()
	{
		System.out.println("Inside method testModifyMap()");
		int toModify=100;
		mappa.put(toModify, "valore"+toModify);
		assertTrue(values.contains("valore"+toModify));
		toModify=200;
		mappa.put(toModify, "valore"+toModify);
		assertTrue(values.contains("valore"+toModify));
		toModify=300;
		mappa.put(toModify, "valore"+toModify);
		assertTrue(values.contains("valore"+toModify));
		int expSize=13;
		assertEquals(expSize,values.size());
		assertEquals(expSize,mappa.size());
	}
	
	@Test
	public void testRemoveAllTrue()
	{
		System.out.println("Inside method testRemoveAllTrue()");
		Collection<String> c=new ListObjectAdapter();
		c.add("valore"+1);
		c.add("valore"+2);
		c.add("valore"+5);
		c.add("valore"+12);
		assertTrue(values.removeAll(c));
		int expSize=7;
		assertEquals(expSize,mappa.size());
		assertEquals(expSize,values.size());
	}
	
	@Test
	public void testRemoveAllFalse()
	{
		System.out.println("Inside method testRemoveAllFalse()");
		Collection<String> c=new ListObjectAdapter();
		c.add("valore"+10);
		c.add("valore"+20);
		c.add("valore"+30);
		c.add("valore"+40);
		assertFalse(values.removeAll(c));
		int expSize=10;
		assertEquals(expSize,mappa.size());
		assertEquals(expSize,values.size());
	}
	
	@Test
	public void testRetainAllTrue()
	{
		System.out.println("Inside method testRetainAllTrue()");
		Collection<String> c=new ListObjectAdapter();
		c.add("valore"+1);
		c.add("valore"+2);
		c.add("valore"+5);
		c.add("valore"+10);
		c.add("valore"+11);
		assertTrue(values.retainAll(c));
		int expSize=3;
		assertEquals(expSize,mappa.size());
		assertEquals(expSize,values.size());
	}
	
	@Test
	public void testRetainAllFalse()
	{
		System.out.println("Inside method testRetainAllFalse()");
		Collection<String> c=new ListObjectAdapter();
		int mapSize=mappa.size();
		for(int i=0;i<mapSize;i++)
			c.add("valore"+i);
		assertFalse(values.retainAll(c));
		int expSize=10;
		assertEquals(expSize,mappa.size());
		assertEquals(expSize,values.size());
	}
	
	/**l'iteratore della classe Values non è lazy, opera su un array contenente i valori degli elementi
	*che erano presenti nella mappa prima che avvenisse l'inserimento dell'elemento nella mappa.
	*Quindi in caso di aggiunta di un elemento questo non verrà ritornato dall'iteratore a meno che
	*non se ne richieda uno nuovo.*/
	@Test
	public void testIteratorAdd()
	{
		System.out.println("Inside method testIteratorAdd()");
		//richiesta di un iteratore sulla vista delle chiavi con aggiunta di elemento sucessiva
		//alla richiesta dell'iteratore
		Iterator<String> iter =values.iterator();
		int i=0;
		while(iter.hasNext())
		{
			if(i==mappa.size()/2)
				mappa.put(100, "valore100");
			iter.next();
			i++;
		}
		assertEquals(i,mappa.size()-1);//conteggia gli elementi dell'iteratore non aggiornato
		
		//modifica dell'elemento precedentemente inserito in posizione 100 e richiesta di iteratore 
		//sui dati aggiornati
		mappa.put(100, "newValore100");
		iter =values.iterator();	
		i=0;
		while(iter.hasNext())
		{			
			iter.next();
			i++;
		}		
		assertEquals(i,mappa.size());//conteggia gli elementi dell'iteratore aggiornato
	}
	
	/**Quando si rimuove un elemento durante l'iterazione, che questo si trovi prima o dopo
	 * la posizione corrente non ha importanza, ciò che importa è che l'iteratore lo considera
	 * comuque perchè sta lavorando su una copia dei dati. Per fare in modo che vengano
	 * elaborati dati aggiornati è necessario richiedere un nuovo iteratore.*/
	@Test
	public void testIteratorRemove()
	{
		System.out.println("Inside method testIteratorRemove()");
		//richiesta di un iteratore sulla vista delle chiavi con aggiunta di elemento sucessiva
		//alla richiesta dell'iteratore
		Iterator<String> iter =values.iterator();
		int i=0;
		while(iter.hasNext())
		{
			if(i==mappa.size()/2)
				mappa.remove(0);
			iter.next();
			i++;
		}
		assertEquals(i,mappa.size()+1);//viene conteggiato dall'iteratore anche l'elemento che è stato rimosso
		
		iter =values.iterator();	
		i=0;
		while(iter.hasNext())
		{			
			iter.next();
			i++;
		}		
		assertEquals(i,mappa.size());//conteggia gli elementi dell'iteratore aggiornato
	}	
}
