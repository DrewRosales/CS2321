package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A PriorityQueue based on an ordered Doubly Linked List. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @Drew Rosales
 */

public class OrderedPQ<K,V> implements PriorityQueue<K,V> {
	private Comparator<K> comparator;
	DoublyLinkedList<Entry<K,V>> PQ = new DoublyLinkedList<>();
	
	public OrderedPQ() {
		comparator = new DefaultComparator<K>();
	}

	public OrderedPQ(Comparator<K> c) {
		comparator = c;
	}
	@TimeComplexity("O(1)")
	@Override
	public int size() {
		return PQ.size();
	}
	@TimeComplexity("O(1)")
	@Override
	public boolean isEmpty() {
		return PQ.size()==0;
	}
	@TimeComplexity("O(1)")
	protected int checkKey(K key) throws IllegalArgumentException{
		try {
			return (comparator.compare(key, key));
		}catch (ClassCastException e) {
			throw new IllegalArgumentException("Invalid Key: "+key);
		}
	}
	
	//iterates through the list and compares the elements to order the list and insert
	@TimeComplexity("O(n)")
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K,V> entry = new PQEntry<>(key, value);
		Position<Entry<K,V>> i = PQ.last();
		while(i != null && comparator.compare(entry.getKey(), i.getElement().getKey())< 0) {
			i = PQ.before(i);
		}
			if(i==null)
				PQ.addFirst(entry);
			else
				PQ.addAfter(i, entry);
			
			
		return entry;
	}
	
	//finds the min, first element in the list
	@TimeComplexity("O(1)")
	@Override
	public Entry<K, V> min() {
		if(!isEmpty()) 
			return PQ.first().getElement();
		return null;
	}
	
	//removes the first element in the list
	@TimeComplexity("O(1)")
	@Override
	public Entry<K, V> removeMin() {
		if(!isEmpty()) { 
			return PQ.remove(PQ.first());
		}
		return null;
	}

}
