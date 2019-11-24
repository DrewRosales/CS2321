package cs2321;


/*
 * Drew Rosales
 * CS2321
 */
import java.util.Iterator;

import net.datastructures.List;

public class ArrayList<E> implements List<E> {

	public static int capacity = 16;	//Initial size of the arraylist
	private E[] list;
	private int size = 0;
	
	/*
	 * Constructor for the array list with no parameters
	 */
	public ArrayList() {
		this(capacity);
	}
	
	/*
	 * Overloaded constructor for a specific size arraylist
	 */
	public ArrayList(int capacity) {
		list = (E[])new Object[capacity];
	}

	/*
	 * Returns the size of the arraylist
	 */
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	/*
	 * Checks to index that is within acceptable bounds,
	 * throws an IndexOutBoundsException if not within acceptable bounds.
	 * If an exception is not thrown, then the value is returned at the specified index
	 */
	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		if(i < 0 || i > size) {
			throw new IndexOutOfBoundsException("Illegal Index" +i);
		}
		else {
			return list[i];
		}
	}
	/*
	 * Checks to index that is within acceptable bounds,
	 * throws an IndexOutBoundsException if not within acceptable bounds.
	 * If an exception is not thrown, then a temporary variable, foo, stores the current value
	 * of the value at the specific index, and the arraylist is update with the passed value
	 * to set(...)
	 */
	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		if(i < 0 || i >= size) {
			throw new IndexOutOfBoundsException("Illegal Index" +i);
		} else {
			E foo = list[i];
			list[i] = e;
			return foo;
		}
	}

	/*
	 *Checks to index that is within acceptable bounds,
	 *throws an IndexOutBoundsException if not within acceptable bounds.
	 *If an exception is not thrown and the arraylist is at capacity, then
	 *the arraylist capacity is doubled using capacity();
	 *The elements of the arraylist is shifted one index specified
	 *After element is inserted, the size of arraylist is increment
	 */
	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		if(i < 0 || i >= size+1) {
			throw new IndexOutOfBoundsException("Illegal Index: "+i);
		}
		if(size==list.length) {
			capacity();
		}
		for(int j=size-1; j >=i; j--) {
			list[j+1] = list[j];
		}
		list[i]=e;
		size++;
	}
	
	/*
	 *Checks to index that is within acceptable bounds,
	 *throws an IndexOutBoundsException if not within acceptable bounds.
	 *Removes an element at a specific index
	 *Shifts the entire arraylist up one index
	 */
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		if(i < 0 || i >= size) {
			throw new IndexOutOfBoundsException("Illegal Index: "+i);
		}
		E foo = list[i];
		for(int j=i; j <size-1; j++) {
			list[j] = list[j+1];
		}
		list[size-1] = null;
		size--;
		
		
		return foo;
	}
	/*
	 * Inner class for the iterator for the list
	 */
	public class listIterator implements Iterator<E>{
		int cursor = 0;

		@Override
		public boolean hasNext() {
			return cursor <= size-1;
		}

		@Override
		public E next() {
			return list[cursor++];
		}
		
	}
	
	@Override
	public Iterator<E> iterator() {
		return new listIterator();
	}
	/*
	 * Wrapper method to add as the first element of the arraylist
	 */
	public void addFirst(E e)  {
		add(0, e);
	}
	
	/*
	 * Wrapper method to add to last element of the arraylist
	 */
	public void addLast(E e)  {
		add(size, e);
	}
	
	/*
	 * Wrapper method to remove the first element of the arraylist
	 */
	public E removeFirst() throws IndexOutOfBoundsException {
		return remove(0);
	}
	
	/*
	 * Wrapper method to remove the last element of the arraylist
	 */
	public E removeLast() throws IndexOutOfBoundsException {
		return remove(size-1);
	}
	
	/* Return the capacity of array, not the number of elements.
	 * Notes: The initial capacity is 16. When the array is full, the array should be doubled.
	 * Updates the capacity variable to the double the original size
	 * The data of the original arraylist is temporary stored in another arraylist with
	 * double the original capacity, dList. 
	 */
	public int capacity() {
		if(size == capacity) {
			E[] dList = (E[])new Object[capacity*2];
			capacity *= 2;
			
			for(int i=0; i<list.length; i++) {
				dList[i] = list[i];
			}
			
			list = (E[]) new Object[capacity];
			
			list = dList;
		}
		return capacity;
	}
	
}
