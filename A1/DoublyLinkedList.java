package cs2321;
import java.util.Iterator;

import net.datastructures.Position;
import net.datastructures.PositionalList;


/*
 * Drew Rosales
 * CS2321
 */

public class DoublyLinkedList<E> implements PositionalList<E> {
	/*
	 * Inner class to allow for the creation of nodes 
	 */
	private static class Node<E> implements Position<E>{
		

		private Node<E> prev;
		private Node<E> next;
		private E data;
		
		/*
		 * Constructor to allow for pointers to other nodes and data for a specific node
		 */
		public Node(Node<E> prev, Node<E> next, E data) {
			this.prev = prev;
			this.next = next;
			this.data = data;
		}
		
		public Node<E> getPrev() {
			return prev;
		}


		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}


		public Node<E> getNext() {
			return next;
		}


		public void setNext(Node<E> next) {
			this.next = next;
		}
		
		/*
		 * Set a specific element to another value
		 */
		public void setElement(E e) {
			data = e;
		}
		
		
		@Override
		public E getElement() throws IllegalStateException {
			if(next == null) {
				throw new IllegalStateException("Position does not exist");
			}
			return data;
		}
		
	}
	
	private Node<E> header; //create a node for the header of the list
	private Node<E> tail; //create a node for the tail of the list
	private int size = 0;
	
	/*
	 * Constructor for a doubly linked list to make the header and tail nodes
	 */
	public DoublyLinkedList() {
		header = new Node<E> (null, null, null);
		tail = new Node<E> (header, null, null);
		header.setNext(tail);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}
	/*
	 * Checks to see if the node is the header or the tail, if not returns the passed node
	 */
	private Position<E> position(Node<E> node){
		if(node == header || node == tail) {
			return null;
		}
		
		return node;
	}
	/*
	 * returns the position of the first node
	 */
	@Override
	public Position<E> first() {
		return position(header.getNext());
	}

	/*
	 * returns the position of the last node
	 */
	@Override
	public Position<E> last() {
		return position(tail.getPrev());
	}
	
	/*
	 * Checks if the passed object is an instance of Node, if not there is an exception thrown
	 * If the next of the node is null then this is the end of the list.
	 * returns the node if no exceptions are thrown
	 */
	private Node<E> check(Position<E> pos) throws IllegalArgumentException{
		if(!(pos instanceof Node)) {
			throw new IllegalArgumentException("Invalid position");
		}
		Node<E> node = (Node<E>) pos;
		if(node.getNext() == null) {
			throw new IllegalArgumentException("Not in the list");
		}
		return node;
	}
	
	/*
	 * Checks to see if the node is valid, if so then return the position of the previous node
	 */
	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> n = check(p);
		return position(n.getPrev());
	}
	/*
	 * Checks to see if the node is valid, if so then return the position of the next node
	 */
	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> n = check(p);
		return position(n.getNext());
	}
	
	/*
	 * Adds a node by setting the pointers for previous, next, and updates the size.
	 * Returns the node
	 */
	public Position<E> add(Node<E> prev, Node<E> next, E e){
		Node<E> node = new Node(prev, next, e);
		prev.setNext(node);
		next.setPrev(node);
		size++;
		return node;
	}
	
	/*
	 * Helper method to add as the first element of the list
	 */
	@Override
	public Position<E> addFirst(E e) {
		return add(header, header.getNext(), e);
	}
	
	/*
	 * Helper method to add as the last element of the list
	 */
	@Override
	public Position<E> addLast(E e) {
		return add(tail.getPrev(), tail, e);
	}
	
	/*
	 * Helper method to add before a specific node
	 */
	@Override
	public Position<E> addBefore(Position<E> p, E e)
			throws IllegalArgumentException {
		Node<E> node = check(p);
		return add(node.getPrev(), node, e);
	}

	/*
	 * Helper method to add after a specific node
	 */
	@Override
	public Position<E> addAfter(Position<E> p, E e)
			throws IllegalArgumentException {
		Node<E> node = check(p);
		return add(node, node.getNext(), e);
	}
	/*
	 * Sets the value of a specific node
	 */
	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = check(p);
		E prevElement = node.getElement();
		node.setElement(e);
		return prevElement;
	}

	/*
	 * Removes a specific node, changes the pointers to the preceding and successing node, and decrement the size
	 */
	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = check(p);
		Node<E> prev = node.getPrev();
		Node<E> next = node.getNext();
		prev.setNext(next);
		next.setPrev(prev);
		size--;
		E element = node.getElement();
		node.setPrev(null);
		node.setNext(null);
		node.setElement(null);
		return element;
	}
	/*
	 * Iterator class for the position of the list
	 */
	public class PositionIterator implements Iterator<Position<E>>{
		private Position<E> cursor = first();
		private Position<E> last = null;
		
		@Override
		public boolean hasNext() {
			return cursor != null;
		}

		@Override
		public Position<E> next() {
			last = cursor;
			cursor = after(cursor);
			return last;
		}
		
	}

	private class PositionIterable implements Iterable<Position<E>>{

		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
	}
	public class ElementIterator implements Iterator<E>{
		Iterator<Position<E>> posIterator = new PositionIterator();
		

		@Override
		public boolean hasNext() {
			return posIterator.hasNext();
		}

		@Override
		public E next() {
			return posIterator.next().getElement();
		}
	}
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();
	}
	
	public E removeFirst() throws IllegalArgumentException {
		return remove(header.getNext());
	}
	
	public E removeLast() throws IllegalArgumentException {
		return remove(tail.getPrev());
	}

}