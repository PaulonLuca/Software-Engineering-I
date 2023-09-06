import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestMapObjectAdapter 
{
	MapObjectAdapter<Integer, String> adapter =new MapObjectAdapter<Integer,String>();
	
	/**Startup.
	 * Prima di eseguire ogni test si inizializza la mappa inserendo al suo interno 10
	 * coppie chiave-valore. Le chiavi sono numeri numeri interi appartenenti
	 * all'intervallo [0,9], i valori invece sono stringhe denominate valorei dove 
	 * i è un numero intero appartenente all'intevallo [0,9].*/
	@Before
	public void setUp()
	{
		int size=10;
		for(int i=0;i<size;i++)
			adapter.put(i, "valore"+i);		
	}
	
	/**Test clear().
	 * Pre-conditon: adapter inizializzato tramite startup.
	 * Post-condition: mappa vuota.
	 * Test-cases: si testa che la dimensione della mappa sia 0.
	 * Execution variables: variabile con dimensione attesa, l'adapter.
	 * Execution records: true se la dimensione è nulla.*/
	@Test
	public void testClear()
	{
		System.out.println("Inside method testClear()");
		adapter.clear();
		int expected=0;
		assertEquals(0,adapter.size());
	}
	
	/**Test isEmpty() return value: true.
	 *  Pre-condition: adapter inizializzato tramite startup su cui si invoca poi il metodo clear().
	 *  Post-condition: mappa vuota.
	 *  Test cases: si verifica che la dimensione della mappa sia nulla.
	 *  Execution variables: l'adapter.
	 *  Execution records: true se la dimensione della mappa è nulla.*/
	@Test
	public void testIsEmptyTrue()
	{
		System.out.println("Inside method testIsEmptyTrue()");
		adapter.clear();
		assertTrue(adapter.isEmpty());
	}
	
	/**Test isEmpty() return value: false.
	 *  Pre-condition: adapter inizializzato tramite startup.
	 *  Post-condition: la mappa contiene ancora gli elementi con cui è stata inizializzata.
	 *  Test cases: si verifica che la dimensione della mappa non sia nulla.
	 *  Execution variables: l'adapter.
	 *  Execution records: false se la dimensione della mappa non è nulla.*/
	@Test
	public void testIsEmptyFalse()
	{
		System.out.println("Inside method testIsEmptyFalse()");
		assertFalse(adapter.isEmpty());
	}
	
	/**Test containsKey() return value: true.
	 *  Pre-condition: adapter inizializzato tramite startup.
	 *  Post-condition: la mappa contiene ancora gli elementi con cui è stata inizializzata.
	 *  Test cases: si verifica che le chiavi: 0, 3, 9 siano contenute nella mappa.
	 *  Execution variables: l'adapter.
	 *  Execution records: true se tutte e tre le chiavi risultano presenti nella mappa.*/
	@Test
	public void testContainsKeyTrue()
	{
		System.out.println("Inside method testContainsKeyTrue()");
		int expKey0=0;
		int expKey3=3;
		int expKey9=9;
		assertTrue(adapter.containsKey(expKey0));
		assertTrue(adapter.containsKey(expKey3));
		assertTrue(adapter.containsKey(expKey9));
	}
	
	/**Test containsKey() return value: false.
	 *  Pre-condition: adapter inizializzato tramite startup.
	 *  Post-condition: la mappa contiene ancora gli elementi con cui è stata inizializzata.
	 *  Test cases: si verifica che le chiavi: 10, 20, 30 non siano contenute nella mappa.
	 *  Execution variables: l'adapter.
	 *  Execution records: false se tutte e tre le chiavi non risultano presenti nella mappa.*/
	@Test
	public void testContainsKeyFalse()
	{
		System.out.println("Inside method testContainsKeyFalse()");
		int expKey10=10;
		int expKey20=20;
		int expKey30=30;
		assertFalse(adapter.containsKey(expKey10));
		assertFalse(adapter.containsKey(expKey20));
		assertFalse(adapter.containsKey(expKey30));
	}
	
	/**Test containsKey() NullPointerException.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che l'eccezione lanciata a seguito dell'invocazione del
	 * metodo containsKey() con una chiave di valore null sia: NullPointerException.
	 * Execution variables: l'adapter.
	 * Execution records: none */
	@Test(expected=NullPointerException.class)
	public void testContainsKeyException()
	{
		System.out.println("Inside method testContainsKeyException()");
		adapter.containsKey(null);
	}
	
	/**Test containsValue() return value: true.
	 *  Pre-condition: adapter inizializzato tramite startup.
	 *  Post-condition: la mappa contiene ancora gli elementi con cui è stata inizializzata.
	 *  Test cases: si verifica che i valori: valore5, valore7, valore2 siano contenute nella mappa.
	 *  Execution variables: l'adapter.
	 *  Execution records: true se tutti e tre i valori risultano presenti nella mappa.*/
	@Test
	public void testContainsValueTrue()
	{
		System.out.println("Inside method testContainsValueTrue()");
		String expValue5="valore5";
		String expValue7="valore7";
		String expValue2="valore2";
		assertTrue(adapter.containsValue(expValue5));
		assertTrue(adapter.containsValue(expValue7));
		assertTrue(adapter.containsValue(expValue2));
	}
	
	/**Test containsValue() return value: false.
	 *  Pre-condition: adapter inizializzato tramite startup.
	 *  Post-condition: la mappa contiene ancora gli elementi con cui è stata inizializzata.
	 *  Test cases: si verifica che i valori: valore50, valore60, valore70 non siano contenute nella mappa.
	 *  Execution variables: l'adapter.
	 *  Execution records: flase se tutti e tre i valori non risultano presenti nella mappa.*/
	@Test
	public void testContainsValueFalse()
	{
		System.out.println("Inside method testContainsValueFalse()");
		String expValue50="valore50";
		String expValue60="valore60";
		String expValue70="valore70";
		assertFalse(adapter.containsValue(expValue50));
		assertFalse(adapter.containsValue(expValue60));
		assertFalse(adapter.containsValue(expValue70));
	}
	
	/**Test containsValue() NullPointerException.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che l'eccezione lanciata a seguito dell'invocazione del
	 * metodo containsValue() con un valore null sia: NullPointerException.
	 * Execution variables: l'adapter.
	 * Execution records: none */
	@Test(expected=NullPointerException.class)
	public void testContainsValueNullException()
	{
		System.out.println("Inside method testContainsValueNullException()");
		adapter.containsValue(null);
	}
	
	/**Test get() return value: true.
	 *  Pre-condition: adapter inizializzato tramite startup.
	 *  Post-condition: la mappa contiene ancora gli elementi con cui è stata inizializzata.
	 *  Test cases: si verifica che i valori: valore1, valore2, null 
	 *  corrispondano alle chiavi: 1, 2, 11.
	 *  Execution variables: l'adapter.
	 *  Execution records: true se i tre valori corrispondono alle tre chiavi.*/
	@Test
	public void testGet()
	{
		System.out.println("Inside method testGet()");
		String expValue1="valore1";
		String expValue2="valore2";
		int keyNotIn=11;
		assertEquals(expValue1,adapter.get(1));
		assertEquals(expValue2,adapter.get(2));
		assertEquals(null,adapter.get(keyNotIn));
	}
	
	/**Test containsValue() NullPointerException.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che l'eccezione lanciata a seguito dell'invocazione del
	 * metodo get() con una chiave di valore null sia: NullPointerException.
	 * Execution variables: l'adapter.
	 * Execution records: none */
	@Test(expected=NullPointerException.class)
	public void testGetNullException()
	{
		System.out.println("Inside method testGetNullException()");
		adapter.get(null);
	}
	
	/**Test put() NullPointerException.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che l'eccezione lanciata a seguito dell'invocazione del
	 * metodo put() con una chiave di valore null o un valore null sia: NullPointerException.
	 * Si utilizzano 3 costrutti try/catch per catturare le 3 eccezioni.
	 * Execution variables: l'adapter.
	 * Execution records: test superato se viene lanciata l'eccezione nei tre casi. */
	@Test
	public void testPutKeyValueNullException()
	{
		System.out.println("Inside method testPutKeyValueNullException()");
		int numExce=0;
		try
		{
			adapter.put(null, "valore0");
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		try
		{
			adapter.put(0, null);
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		try
		{
			adapter.put(null, null);
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		assertTrue(numExce==3);	
	}
	
	@Test
	public void testPut()
	{
		System.out.println("Inside method testPut()");
		adapter.put(10, "valore10");
		adapter.put(11, "valore11");
		adapter.put(13, "valore12");
		int expSize=13;
		assertEquals(expSize,adapter.size());
	}
	
	/**Test getOrDefault() .
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che la chiave 9 corrisponda al valore9, poi si verifica che 
	 * passando una chiave non presente venga ritornato il parametro di defualt passato.
	 * Execution variables: l'adapter.
	 * Execution records: test superato se nel primo caso viene ritornato il valore corrispondente e nel secondo
	 * caso viene ritornato il valore di default. */	
	@Test
	public void testGetOrDefault()
	{
		System.out.println("Inside method testGetOrDefault()");
		//elemento presente
		String expValue="valore9";
		String elem=adapter.getOrDefault(9, "default");
		assertEquals(expValue,elem);
		
		//elemento non presente
		expValue="default";
		assertEquals(expValue,adapter.getOrDefault(11, "default"));
	}
	
	/**Test getOrDefault() NullPointerException.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che l'eccezione lanciata a seguito dell'invocazione del
	 * metodo getOrDefault() con una chiave di valore null o un valore null sia: NullPointerException.
	 * Si utilizzano 3 costrutti try/catch per catturare le 3 eccezioni.
	 * Execution variables: l'adapter.
	 * Execution records: test superato se viene lanciata l'eccezione nei tre casi. */
	@Test
	public void testGetOrDefaultNullException()
	{
		System.out.println("Inside method testGetOrDefaultNullException()");
		int numExce=0;
		try
		{
			adapter.getOrDefault(null, "default");
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		try
		{
			adapter.getOrDefault(0, null);
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		try
		{
			adapter.getOrDefault(null, null);
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		assertTrue(numExce==3);	
	}
	
	/**Test remove() elemento presente .
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: elemento in posizione 8 eliminato.
	 * Test cases: si testa che il valore corrispondente alla chiave 8 eliminata sia valore 8.
	 * Execution variables: l'adapter, chiave da rimuovere.
	 * Execution records: test superato se il valore rimosso corrisponde a quello atteso
	 * associato alla chiave. */
	@Test
	public void testRemoveTrue()
	{
		System.out.println("Inside method testRemoveTrue()");
		String expValue="valore8";
		assertEquals(expValue,adapter.remove(8));
		int expSize=9;
		assertEquals(expSize,adapter.size());
	}
	
	/**Test remove() elemento non presente .
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che il valore corrispondente alla chiave 12, che non è presente, sia
	 * il valore null.
	 * Execution variables: l'adapter, chiave da rimuovere.
	 * Execution records: test superato se il valore rimosso corrisponde ad un riferimento null. */
	@Test
	public void testRemoveFalse()
	{
		System.out.println("Inside method testRemoveFalse()");
		String expValue=null;
		assertEquals(expValue,adapter.remove(12));
		int expSize=10;
		assertEquals(expSize,adapter.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testRemoveNullException()
	{
		System.out.println("Inside method testRemoveNullException()");
		adapter.remove(null);
	}
	
	/**Test remove() entry, match esistente.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: si rimuove dalla mappa l'entri <1, valore1> poichè alla chiave 1 corrisponde
	 * il valore passato come parametro.
	 * Test cases: si testa che il valore corrispondente alla chiave 1 sia valore1.
	 * Execution variables: l'adapter, chiave da rimuovere, valore corrispondente alla chiave.
	 * Execution records: test superato se l'entry appartiene alla tabella. */
	@Test
	public void testRemoveEntry()
	{		
		System.out.println("Inside method testRemoveEntry()");
		int expSize=adapter.size()-1;
		assertTrue(adapter.remove(1, "valore1"));		
		assertEquals(expSize,adapter.size());
	}
	
	/**Test remove() entry, match non esistente.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che il valore corrispondente alla chiave 1 sia valore10.
	 * Execution variables: l'adapter, chiave da rimuovere, valore corrispondente alla chiave.
	 * Execution records: test superato se l'entry è costituita da una chiave, che non corrisponde,
	 * al valore, all'interno della mappa. */
	@Test
	public void testRemoveEntryNotMatch()
	{		
		System.out.println("Inside method testRemoveEntryNotMatch()");
		int expSize=adapter.size();
		assertFalse(adapter.remove(1, "valore10"));		
		assertEquals(expSize,adapter.size());
	}
	
	/**Test remove() NullPointerException.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che l'eccezione lanciata a seguito dell'invocazione del
	 * metodo put() con una chiave di valore null o un valore null sia: NullPointerException.
	 * Si utilizzano 3 costrutti try/catch per catturare le 3 eccezioni.
	 * Execution variables: l'adapter.
	 * Execution records: test superato se viene lanciata l'eccezione nei tre casi. */
	@Test
	public void testRemoveMatchNullException()
	{
		System.out.println("Inside method testRemoveMatchNullException()");
		int numExce=0;
		try
		{
			adapter.remove(null, "valore0");
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		try
		{
			adapter.remove(0, null);
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		try
		{
			adapter.remove(null, null);
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		assertTrue(numExce==3);	
	}
	
	/**Test replace() entry, match esistente.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: i valori corrispondenti alle chiavi 5,7,9 risultano modificati
	 * rispettivamente in nuovo5, nuovo7, nuovo9.
	 * Test cases: si testa che i vecchi valori che vengono ritornatoa a seguito del rimpiazzamento
	 * siano i vecchi valori corrispondenti alle chiavi.
	 * Execution variables: l'adapter, chiave da rimpiazzare, nuovo valore corrispondente alla chiave.
	 * Execution records: test superato se il rimpiazzamento va buon fine e vengono quindi 
	 * ritornati i vecchi valori associati alle chiavi. */
	@Test 
	public void testReplaceMatch()
	{
		System.out.println("Inside method testReplaceMatch()");
		String oldVal="valore5";
		assertEquals(oldVal,adapter.replace(5, "nuovo5"));
		assertEquals("nuovo5",adapter.get(5));
		oldVal="valore7";
		assertEquals(oldVal,adapter.replace(7, "nuovo7"));
		assertEquals("nuovo7",adapter.get(7));
		oldVal="valore9";
		assertEquals(oldVal,adapter.replace(9, "nuovo9"));
		assertEquals("nuovo9",adapter.get(9));
	}
	
	/**Test replace() entry, match non esistente.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che se si prova e rimpizzare una chiave non appartenente 
	 * alla mappa venga ritornato il valore null.
	 * Execution variables: l'adapter, chiave da rimpiazzare, nuovo valore corrispondente alla chiave.
	 * Execution records: test superato se il rimpiazzamento non va buon fine poichè le 
	 * chiavi non hanno corrispondenza nella mappa. */
	@Test 
	public void testReplaceNotMatch()
	{
		System.out.println("Inside method testReplaceNotMatch()");
		assertEquals(null,adapter.replace(10, "nuovo10"));
		assertEquals(null,adapter.replace(-1, "nuovo7"));
	}
	
	/**Test replace() NullPointerException.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che l'eccezione lanciata a seguito dell'invocazione del
	 * metodo replace() con una chiave di valore null o un valore null sia: NullPointerException.
	 * Si utilizzano 3 costrutti try/catch per catturare le 3 eccezioni.
	 * Execution variables: l'adapter.
	 * Execution records: test superato se viene lanciata l'eccezione nei tre casi. */
	@Test
	public void testReplaceMatchNullException()
	{
		System.out.println("Inside method testReplaceMatchNullException()");
		int numExce=0;
		try
		{
			adapter.remove(null, "valore0");
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		try
		{
			adapter.remove(0, null);
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		try
		{
			adapter.remove(null, null);
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		assertTrue(numExce==3);	
	}
	
	/**Test replace() entry con newValue, match esistente con oldValue.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: i valori corrispondenti alle chiavi 1,3,6 risultano modificati
	 * rispettivamente in new1, new3, new6.
	 * Test cases: si testa che i vecchi valori che vengono ritornati a seguito del rimpiazzamento
	 * siano i vecchi valori corrispondenti alle chiavi.
	 * Execution variables: l'adapter, chiave da rimpiazzare, nuovo valore corrispondente alla chiave,
	 * vecchio valore corrispondnete alla chiave.
	 * Execution records: test superato se il rimpiazzamento va a buon fine e vengono quindi 
	 * ritornati i nuovi valori associati alle chiavi. */
	@Test 
	public void testReplaceWithNewValueMatch()
	{
		System.out.println("Inside method testReplaceWithNewValueMatch()");
		String oldVal="valore3";
		assertTrue(adapter.replace(3,oldVal,"new3"));
		assertEquals("new3",adapter.get(3));
		
		oldVal="valore1";
		assertTrue(adapter.replace(1,oldVal,"new1"));
		assertEquals("new1",adapter.get(1));
		
		oldVal="valore6";
		assertTrue(adapter.replace(6,oldVal,"new6"));
		assertEquals("new6",adapter.get(6));
	}
	
	/**Test replace() entry con newValue, match non esistente con oldValue.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che i valori corrispondenti alle chiavi 1,3,6 rimangano inalterati poichè
	 * non è presente nessuna corrispondenza con gli oldValues passati come parameti.
	 * Execution variables: l'adapter, chiave da rimpiazzare, nuovo valore corrispondente alla chiave,
	 * vecchio valore corrispondnete alla chiave.
	 * Execution records: test superato se il rimpiazzamento non va a buon fine e quindi i valori
	 * associati alle chiavi rimangono inalterati. */
	@Test 
	public void testReplaceWithNewValueNotMatch()
	{
		System.out.println("Inside method testReplaceWithNewValueNotMatch()");
		String oldVal="valore8";
		assertFalse(adapter.replace(3,oldVal,"new3"));
		assertEquals("valore3",adapter.get(3));
		
		oldVal="valore9";
		assertFalse(adapter.replace(1,oldVal,"new1"));
		assertEquals("valore1",adapter.get(1));
		
		oldVal="valore2";
		assertFalse(adapter.replace(6,oldVal,"new6"));
		assertEquals("valore6",adapter.get(6));
	}
	
	/**Test replace() newValue NullPointerException.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che l'eccezione lanciata a seguito dell'invocazione del
	 * metodo replace() con una chiave di valore null o un oldValue null o un newValue null sia: NullPointerException.
	 * Si utilizzano 3 costrutti try/catch per catturare le 3 eccezioni.
	 * Non sono stati testate tutte lo possibili situazioni di lancio di eccezione.
	 * Execution variables: l'adapter.
	 * Execution records: test superato se viene lanciata l'eccezione nei tre casi. */
	@Test
	public void testReplaceWithNewValueNullException()
	{
		System.out.println("Inside method testReplaceWithNewValueNullException()");
		int numExce=0;
		try
		{
			adapter.replace(null, "valore0","new0");
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		try
		{
			adapter.replace(0, null,"new0");
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		try
		{
			adapter.replace(0, "valore0",null);
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		assertTrue(numExce==3);	
	}
	
	/**Test putIfAbsent(), valore non presente.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: vengono aggiunte nella mappa tre nuove entry: <10,new10>,
	 * <11,new11>, <12,new12>.
	 * Test cases: si testa che a seguito dell'inserimento di ognuna della 3 entry il
	 * valore ritornato sia null e che i valori corrispondenti alle chiavi siano new10, new11, new12.
	 * Execution variables: l'adapter, coppie chiave-valore non presenti da inserire.
	 * Execution records: test superato se gli inserimenti vanno a buon fine poichè le 
	 * chiavi non sono mappate su alcun valore. */
	@Test 
	public void testPutIfAbsentNotIn()
	{
		System.out.println("Inside method testPutIfAbsentNotIn()");
		String newVal="new10";
		assertEquals(null,adapter.putIfAbsent(10, newVal));
		assertEquals(newVal,adapter.get(10));
		
		newVal="new11";
		assertEquals(null,adapter.putIfAbsent(11, newVal));
		assertEquals(newVal,adapter.get(11));
		
		newVal="new12";
		assertEquals(null,adapter.putIfAbsent(12, newVal));
		assertEquals(newVal,adapter.get(12));
	}
		
	/**Test putIfAbsent(),valore presente.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che tentando d'inserire i nuovi valori in corrispondenza delle chiavi
	 * 0,1,2 questi non vengano inseriri poichè le chiavi hanno già un mapping.
	 * Execution variables: l'adapter, entry da inserire.
	 * Execution records: test superato se l'inserimento non va a buon fine poichè non è
	 * presente un'entry che abbia quella specifica chiave associata. */
	@Test 
	public void testPutIfAbsentIn()
	{
		System.out.println("Inside method testPutIfAbsentIn()");
		String oldVal="valore0";
		assertEquals(oldVal,adapter.putIfAbsent(0, "new0"));
		assertEquals("valore0",adapter.get(0));
		oldVal="valore1";
		assertEquals(oldVal,adapter.putIfAbsent(1, "new1"));
		assertEquals("valore1",adapter.get(1));
		oldVal="valore2";
		assertEquals(oldVal,adapter.putIfAbsent(2, "new2"));
		assertEquals("valore2",adapter.get(2));
		
	}
	
	/**Test putIfAbsent() newValue NullPointerException.
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: la mappa rimane inalterata.
	 * Test cases: si testa che l'eccezione lanciata a seguito dell'invocazione del
	 * metodo putIfAbsent() con una chiave di valore null o un valore null sia: NullPointerException.
	 * Si utilizzano 3 costrutti try/catch per catturare le 3 eccezioni.
	 * Non sono stati testate tutte lo possibili situazioni di lancio di eccezione.
	 * Execution variables: l'adapter.
	 * Execution records: test superato se viene lanciata l'eccezione nei tre casi. */
	@Test
	public void testPutIfAbsentNullException()
	{
		System.out.println("Inside method testReplaceWithNewValueNullException()");
		int numExce=0;
		try
		{
			adapter.replace(null, "valore0","new0");
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		try
		{
			adapter.replace(0, null,"new0");
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		try
		{
			adapter.replace(0, "valore0",null);
		}catch(NullPointerException npe)
		{	numExce++;	}
		
		assertTrue(numExce==3);	
	}
	
	/**Test putAll().
	 * Pre-condition: adapter inizializzato tremite startup.
	 * Post-condition: vengono aggiunti 4 coppie chiave-valore(<100,valore100>,<200,valore200>
	 * <300,valore300>,<400,valore400>), che prima non erano presenti.
	 * Test cases: si testa che dopo l'invocazione del metodo la dimensione della mappa
	 * sia aumentata di 4 unità.
	 * Execution variables: l'adapter, una mappa contenente le coppie chiave valore da aggiungere.
	 * Execution records: test superato se le coppie risultano aggiunte alla mappa. */
	@Test
	public void testPutAll()
	{
		System.out.println("Inside method testPutAll()");
		MapObjectAdapter<Integer, String> map =new MapObjectAdapter<Integer,String>();
		map.put(100, "valore100");
		map.put(200, "valore200");
		map.put(300, "valore300");
		map.put(400, "valore400");
		adapter.putAll(map);
		int expSize=14;
		assertEquals(expSize,adapter.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testPutAllNullException()
	{
		System.out.println("Inside method testPutAll()");
		adapter.putAll(null);
	}
	@Test
	public void testEqualsTrue()
	{
		System.out.println("Inside method testEqualsTrue()");		
		Map<Integer,String> sameMap=new MapObjectAdapter<Integer,String>();
		int size=10;
		for(int i=0;i<size;i++)
			sameMap.put(i, "valore"+i);	
		assertTrue(adapter.equals(sameMap));
		assertEquals(sameMap.hashCode(),adapter.hashCode());
	}
	
	@Test
	public void testEqualsFalse()
	{
		System.out.println("Inside method testEqualsFalse()");	
		Map<Integer,String> diffMap=new MapObjectAdapter<Integer,String>();
		int size=10;
		for(int i=0;i<size;i++)
			diffMap.put(i, "new"+i);	
		assertFalse(adapter.equals(diffMap));
		assertNotEquals(diffMap.hashCode(),adapter.hashCode());
	}
	
	@Test
	public void testEqualsFalseSubste()
	{
		System.out.println("Inside method testEqualsFalseSubste()");	
		Map<Integer,String> diffMap=new MapObjectAdapter<Integer,String>();
		int size=10;
		for(int i=0;i<=size/2;i++)
			diffMap.put(i, "valore"+i);	
		assertFalse(adapter.equals(diffMap));
		assertNotEquals(diffMap.hashCode(),adapter.hashCode());
	}
	
	@Test(expected=ClassCastException.class)
	public void testEqualsCastException()
	{
		System.out.println("Inside method testEqualsCastException()");		 
		assertTrue(adapter.equals(new ListObjectAdapter<Double>()));
	}
	
	@Test(expected=NullPointerException.class)
	public void testEqualsNullException()
	{
		System.out.println("Inside method testEqualsNullException()");		
		assertTrue(adapter.equals(null));
	}	
}
