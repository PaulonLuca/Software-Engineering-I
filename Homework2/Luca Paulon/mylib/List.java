import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public interface List<E> extends Collection<E>
{
	/**Inserts the specified element at the specified position in this list (optional operation).*/
	void add(int index, E element);
	/**Appends the specified element to the end of this list (optional operation).*/
	boolean add(E e);
	/**Inserts all of the elements in the specified collection into this list at the specified position (optional operation).*/
	boolean addAll(int index, Collection<? extends E> c);
	/**Appends all of the elements in the specified collection to the end of this list, 
	*in the order that they are returned by the specifiedcollection's iterator (optional operation).*/
	boolean addAll(Collection<? extends E> c);
	/**Removes all of the elements from this list (optional operation).*/
	void clear();
	/**Returns true if this list contains the specified element.*/
	boolean contains(Object o);
	/**Returns true if this list contains all of the elements of the specified collection.*/
	boolean containsAll(Collection<?> c);
	/**Compares the specified object with this list for equality.*/
	boolean equals(Object o);
	/**Returns the element at the specified position in this list.*/
	E get(int index);
	/**Returns the hash code value for this list.*/
	int hashCode();
	/**Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.*/
	int indexOf(Object o);
	/**Returns true if this list contains no elements.*/
	boolean isEmpty();
	/**Returns an iterator over the elements in this list in proper sequence.*/
	Iterator<E> iterator();
	/**Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.*/
	int lastIndexOf(Object o);
	/**Returns a list iterator over the elements in this list (in proper sequence).*/
	ListIterator<E> listIterator();
	/**Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list.*/
	ListIterator<E> listIterator(int index);		
	/**Removes the element at the specified position in this list (optional operation).*/
	E remove(int index); 
	/**Removes the first occurrence of the specified element from this list, if it is present (optional operation).*/
	boolean remove(Object o);
	/**Removes from this list all of its elements that are contained in the specified collection (optional operation).*/
	boolean removeAll(Collection<?> c);	
	/**Retains only the elements in this list that are contained in the specified collection (optional operation).*/
	boolean retainAll(Collection<?> c);
	/**Replaces the element at the specified position in this list with the specified element (optional operation).*/
	E set(int index, E element);
	/**Returns the number of elements in this list.*/
	int size();
	/**Sorts this list according to the order induced by the specified Comparator.*/
	 void sort(Comparator<? super E> c);
	/**Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.*/
	List<E> subList(int fromIndex, int toIndex);
	/**Returns an array containing all of the elements in this list in proper sequence (from first to last element).*/
	Object[] toArray();
	
	
	
	//Metodi non supportati
	/**Returns an array containing all of the elements in this list in proper sequence (from first to last element); the runtime type of
	//the returned array is that of the specified array.*/
	<T> T[] toArray(T[] a);
	/**Returns an immutable list containing zero elements.*/
	<E> List<E> of();
	/** Returns an immutable list containing one element.*/
	<E> List<E> of(E e1);
	/**Returns an immutable list containing an arbitrary number of elements.*/
	<E> List<E> of(E... elements);
	/**Returns an immutable list containing two elements.*/
	<E> List<E> of(E e1, E e2);
	/**Returns an immutable list containing three elements.*/
	<E> List<E> of(E e1, E e2, E e3);	
	/**Returns an immutable list containing four elements.*/
	<E> List<E> of(E e1, E e2, E e3, E e4);
	/**Returns an immutable list containing five elements.*/
	<E> List<E> of(E e1, E e2, E e3, E e4, E e5);
	/**Returns an immutable list containing six elements.*/
	<E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6);
	/**Returns an immutable list containing seven elements.*/
	<E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7);
	/**Returns an immutable list containing eight elements.*/
	<E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8);
	/** Returns an immutable list containing nine elements.*/
	<E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9);
	/**Returns an immutable list containing ten elements.*/
	<E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8,E e9, E e10);
	/**Replaces each element of this list with the result of applying the operator to that element.*/
	void replaceAll(UnaryOperator<E> operator);
	/**Creates a Spliterator over the elements in this list.*/
	 Spliterator<E> spliterator();
}