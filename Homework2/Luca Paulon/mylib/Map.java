
public interface Map<K,V> 
{
	public interface Entry<K, V> 
	{	
		K getKey();
		V getValue();
	}
	
	/**Removes all of the mappings from this map (optional operation).*/
	void clear(); 
	/**Returns true if this map contains a mapping for the specified key.*/
	boolean containsKey(Object key);
	/**Returns true if this map maps one or more keys to the specified value.*/
	boolean containsValue(Object value);
	/**Returns a Set view of the mappings contained in this map.*/
	Set<Map.Entry<K,V>> entrySet();
	/**Compares the specified object with this map for equality.*/
	boolean equals(Object o);
	/**Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.*/
	V get(Object key);
	/**Returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key.*/
	V getOrDefault(Object key, V defaultValue);
	/**Returns the hash code value for this map.*/
	int hashCode();
	/**Returns true if this map contains no key-value mappings.*/
	boolean isEmpty();
	/**Returns a Set view of the keys contained in this map.*/
	Set<K> keySet();
	/**Associates the specified value with the specified key in this map (optional operation).*/
	V put(K key, V value);
	/**Copies all of the mappings from the specified map to this map (optional operation).*/
	void putAll(Map<? extends K,? extends V> m);
	/**If the specified key is not already associated with a value (or is mapped to null) associates it with the given value and returns null, else returns the current value.*/
	V putIfAbsent(K key, V value);
	/**Removes the mapping for a key from this map if it is present (optional operation).*/
	V remove(Object key);
	/**Removes the entry for the specified key only if it is currently mapped to the specified value.*/
	boolean remove(Object key, Object value);
	/**Replaces the entry for the specified key only if it is currently mapped to some value.*/
	V replace(K key, V value);
	/**Replaces the entry for the specified key only if currently mapped to the specified value.*/
	boolean replace(K key, V oldValue, V newValue);
	/**Returns the number of key-value mappings in this map.*/
	int size();
	/**Returns a Collection view of the values contained in this map.*/
	Collection<V> values();
	
	
	
	//-----------------------------------Metodi da non implementare------------------------------------------
	/**Attempts to compute a mapping for the specified key and its current mapped value (or null if there is no current mapping).*/
	V compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction);
	 /**If the specified key is not already associated with a value (or is mapped to null), attempts to compute its value using the given mapping function and enters it into this map unless null.*/
	V computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction);
	/**If the value for the specified key is present and non-null, attempts to compute a new mapping given the key and its current mapped value.*/
	V computeIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction);
	 /**Returns an immutable Map.Entry containing the given key and value.*/
	<K,V> Map.Entry<K,V> entry(K k, V v);
	/**Performs the given action for each entry in this map until all entries have been processed or the action throws an exception.*/
	void forEach(BiConsumer<? super K,? super V> action);
	/**If the specified key is not already associated with a value or is associated with null, associates it with the given non-null value.*/
	V merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction);
	/**Returns an immutable map containing zero mappings.*/
	<K,V> Map<K,V> of();
	/**Returns an immutable map containing a single mapping.*/
	<K,V> Map<K,V> of(K k1, V v1);
	/**Returns an immutable map containing two mappings.*/
	<K,V> Map<K,V> of(K k1, V v1, K k2, V v2);
	/**Returns an immutable map containing three mappings.*/
	<K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3);
	/**Returns an immutable map containing four mappings.*/
	<K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4);
	/**Returns an immutable map containing five mappings.*/
	<K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5);
	/**Returns an immutable map containing six mappings.*/
	<K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6,V v6);
	/**Returns an immutable map containing seven mappings.*/
	<K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6,V v6, K k7, V v7);
	/**Returns an immutable map containing eight mappings.*/
	<K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6,V v6, K k7, V v7, K k8, V v8);
	/**Returns an immutable map containing nine mappings.*/	
	<K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6,V v6, K k7, V v7, K k8, V v8, K k9, V v9);
	/**Returns an immutable map containing ten mappings.*/
	<K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6,V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10);	
	/**Returns an immutable map containing keys and values extracted from the given entries.*/
	<K,V> Map<K,V> ofEntries(Map.Entry<? extends K,? extends V>... entries);
	/**Replaces each entry's value with the result of invoking the given function on that entry until all entries have been processed or the function throws an exception.*/
	void replaceAll(BiFunction<? super K,? super V,? extends V> function);
}
