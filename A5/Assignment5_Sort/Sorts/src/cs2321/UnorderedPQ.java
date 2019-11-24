package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A PriorityQueue based on an Unordered Doubly Linked List. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author
 */

public class UnorderedPQ<K,V> implements PriorityQueue<K,V> {
	private Comparator<K> comparator;
	DoublyLinkedList<Entry<K,V>> PQ = new DoublyLinkedList<>();
	
	public UnorderedPQ() {
		comparator = new DefaultComparator<K>();
	}
	
	public UnorderedPQ(Comparator<K> c) {
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
		return PQ.size() == 0;
	}
	@TimeComplexity("O(1)")
	protected int checkKey(K key) throws IllegalArgumentException{
		try {
			return (comparator.compare(key, key));
		}catch (ClassCastException e) {
			throw new IllegalArgumentException("Invalid Key: "+key);
		}
	}
	
	//adds element to the last index
	@TimeComplexity("O(1)")
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K,V> entry = new PQEntry<>(key, value);
		PQ.addLast(entry);
		
		return entry;
	}
	
	//iterates through the list and compares to find the min
	@TimeComplexity("O(n)")
	public Position<Entry<K,V>> findMin(){
		Position<Entry<K, V>> min = PQ.first();
		
		for(Position<Entry<K,V>> i: PQ.positions()) {
			if(comparator.compare(i.getElement().getKey(), min.getElement().getKey()) < 0)
				min = i;
		}
		return min;
	}
	
	//gets the element based off the call on find min
	@TimeComplexity("O(n)")
	@Override
	public Entry<K, V> min() {
		if(!isEmpty()) 
			return findMin().getElement();
		return null;
	}
	
	//removes the min based off the call findMin
	@TimeComplexity("O(n)")
	@Override
	public Entry<K, V> removeMin() {
		if(!isEmpty()) { 
			return PQ.remove(findMin());
		}
		return null;
	}
	
	

}
