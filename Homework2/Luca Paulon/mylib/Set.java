import java.util.Iterator;

public interface Set<E> extends Collection<E>
{
	/**Adds the specified element to this set if it is not already present (optional operation).*/
	boolean add(E e);
	/**Adds all of the elements in the specified collection to this set if they're not already present (optional operation).*/
	boolean addAll(Collection<? extends E> c);
	/**Removes all of the elements from this set (optional operation).*/
	void clear();
	/**Returns true if this set contains the specified element.*/
	boolean contains(Object o);
	/**Returns true if this set contains all of the elements of the specified collection.*/
	boolean containsAll(Collection<?> c);
	/**Compares the specified object with this set for equality.*/
	boolean equals(Object o);
	/**Returns the hash code value for this set.*/
	int hashCode();
	/**Returns true if this set contains no elements.*/
	boolean isEmpty();
	/**Returns an iterator over the elements in this set.*/	
	Iterator<E> iterator();
	/**Removes the specified element from this set if it is present (optional operation).*/
	boolean remove(Object o);
	/**Removes from this set all of its elements that are contained in the specified collection (optional operation).*/
	boolean removeAll(Collection<?> c);
	/**Retains only the elements in this set that are contained in the specified collection (optional operation).*/
	boolean retainAll(Collection<?> c);
	/**Returns the number of elements in this set (its cardinality).*/
	int size();	
	/**Returns an array containing all of the elements in this set.*/
	Object[] toArray();
	
	
	//--------------------------------Metodi da non implementare--------------------------------------------
	/**Returns an array containing all of the elements in this set; the runtime type of the returned array is that of the*/
	<T> T[] toArray(T[] a);
	/**Creates a Spliterator over the elements in this set.*/
	Spliterator<E> spliterator();
	/**Returns an immutable set containing zero elements.*/
	<E> Set<E> of();
	/**Returns an immutable set containing one element.*/
	<E> Set<E> of(E e1);
	/**Returns an immutable set containing an arbitrary number of elements.*/
	<E> Set<E> of(E... elements);
	/**Returns an immutable set containing two elements.*/
	<E> Set<E> of(E e1, E e2);
	/**Returns an immutable set containing three elements.*/
	<E> Set<E> of(E e1, E e2, E e3);
	/**Returns an immutable set containing four elements.*/
	<E> Set<E> of(E e1, E e2, E e3, E e4);
	/**Returns an immutable set containing five elements.*/
	<E> Set<E> of(E e1, E e2, E e3, E e4, E e5);
	/**Returns an immutable set containing six elements.*/
	<E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6);
	/**Returns an immutable set containing seven elements.*/
	<E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7); 
	/**Returns an immutable set containing eight elements.*/
	<E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8);
	/**Returns an immutable set containing nine elements.*/
	<E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9);
	/**Returns an immutable set containing ten elements.*/
	<E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10);
}
