/**
 * 
 */
package cs2321;

import net.datastructures.Queue;


/*
 * Drew Rosales
 * CS2321
 */


public class CircularArrayQueue<E> implements Queue<E> {

	private E[] queue; //Defining the queue to a generic array
	private int pos = 0; //The front of the queue
	private int size = 0;
	public CircularArrayQueue(int queueSize) {
		queue = (E[]) new Object[queueSize]; //Instantiating the queue to a generic array with a specific queue size
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/*
	 * Adds an element to the front of the queue and the size of the queue is updated
	 */
	@Override
	public void enqueue(E e) {
		int index = (pos + size) % queue.length;
		queue[index] = e;
		size++;
	}

	/*
	 * Returns the first element of the queue
	 */
	@Override
	public E first() {
		return queue[pos];
	}

	/*
	 * removes the element at the front of the queue and decrements the size
	 */
	@Override
	public E dequeue() {
		if(isEmpty()) {
			return null;
		}
		E data = queue[pos];
		queue[pos] = null;
		pos = (pos+1) % queue.length;
		size--;
		return data;
	}
    
}
