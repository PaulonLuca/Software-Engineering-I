import java.util.Iterator;

public interface Collection<E> 
{
	/**Ensures that this collection contains the specified element (optional operation).*/
	boolean add(E e);
	/**Adds all of the elements in the specified collection to this collection (optional operation).*/
	boolean addAll(Collection<? extends E> c);
	/**Removes all of the elements from this collection (optional operation).*/
	void clear();
	/**Returns true if this collection contains the specified element.*/
	boolean contains(Object o);
	/**Returns true if this collection contains all of the elements in the specified collection.*/
	boolean containsAll(Collection<?> c);
	/**Compares the specified object with this collection for equality.*/
	boolean equals(Object o);
	/**Returns the hash code value for this collection.*/
	int hashCode();
	/**Returns true if this collection contains no elements.*/
	boolean isEmpty();
	/**Returns an iterator over the elements in this collection.*/
	Iterator<E> iterator();	
	/**Removes a single instance of the specified element from this collection, if it is present (optional operation).*/
	boolean remove(Object o);
	/**Removes all of this collection's elements that are also contained in the specified collection (optional operation).*/
	boolean removeAll(Collection<?> c);
	/**Retains only the elements in this collection that are contained in the specified collection (optional operation).*/
	boolean retainAll(Collection<?> c);
	/**Returns the number of elements in this collection.*/
	int size(); 
	/**Returns an array containing all of the elements in this collection.*/
	Object[] toArray();
	/**Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.*/
	<T> T[] toArray(T[] a);
	
	/**Returns a possibly parallel Stream with this collection as its source.*/
	Stream<E> parallelStream();	
	/**Removes all of the elements of this collection that satisfy the given predicate.*/
	boolean removeIf(Predicate<? super E> filter);
	/**Creates a Spliterator over the elements in this collection.*/
	Spliterator<E> spliterator();
	/**Returns a sequential Stream with this collection as its source.*/
	Stream<E> stream();
}
