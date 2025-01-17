package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A Adaptable PriorityQueue based on an heap. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author
 */

public class HeapPQ<K,V> implements AdaptablePriorityQueue<K,V> {
	protected static class AdaptablePQEntry<K,V> extends PQEntry<K,V>{
		private int index;
		public AdaptablePQEntry(K key, V value, int j) {
			super(key,value);
			index = j;
		}
		
		public int getIndex() {
			return index;
		}
	
		public void setIndex(int j) {
			index = j;
		}
	}
	private Comparator<K> comparator;
	private ArrayList<Entry<K,V>> heap = new ArrayList<>();
	
	public HeapPQ() {
		comparator = new DefaultComparator<K>();
	}
	
	public HeapPQ(Comparator<K> c) {
		comparator = c;
	}
	
	/**
	 * The entry should be bubbled up to its appropriate position 
	 * @param int move the entry at index j higher if necessary, to restore the heap property
	 */
	
	//parent given a node
	private int parent(int i) {
		return (i-1)/2;
	}
	
	//left child given a node
	private int left(int i) {
		return 2*i+1;
	}
	
	//right child given a node
	private int right(int i) {
		return 2*i+2;
	}
	
	//expression to find left 
	private boolean hasLeft(int i) {
		return left(i) < heap.size();
	}
	
	//expression to find whether there is a right
	private boolean hasRight(int i) {
		return right(i) < heap.size();
	}
	
	//swaps two elements
	private void swap(int i, int j) {
		Entry<K,V> foo = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, foo);
		((AdaptablePQEntry<K,V>)heap.get(i)).setIndex(i);
		((AdaptablePQEntry<K,V>)heap.get(j)).setIndex(j);
	}
	
	//sorts from bottom to top
	public void upheap(int j){
		while(j >0) {
			int p = parent(j);
			if(comparator.compare(heap.get(j).getKey(), heap.get(p).getKey()) >= 0)
				break;
			swap(j,p);
			j=p;
		}
	}
	
	/**
	 * The entry should be bubbled down to its appropriate position 
	 * @param int move the entry at index j lower if necessary, to restore the heap property
	 */
	
	//sorts from top to bottom
	public void downheap(int j){
		while(hasLeft(j)) {
			int leftIndex = left(j);
			int smallIndex = leftIndex;
			if(hasRight(j)) {
				int rightIndex = right(j);
				if(comparator.compare(heap.get(leftIndex).getKey(), heap.get(rightIndex).getKey()) >= 0)
					smallIndex = rightIndex;
			}
			if(comparator.compare(heap.get(smallIndex).getKey(), heap.get(j).getKey()) >= 0)
				break;
			swap(j,smallIndex);
			j=smallIndex;
		}
		
	}
	@Override
	public int size() {
		return heap.size();
	}
	@Override
	public boolean isEmpty() {
		return heap.size() == 0;
	}
	//inserts and sorts using uphead
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		Entry<K,V> e = new AdaptablePQEntry<>(key, value, heap.size());
		heap.add(heap.size(), e);
		upheap(heap.size()-1);
		return e;
	}
	//checks to see if the type of node is valid
	public AdaptablePQEntry<K,V> validate(Entry<K,V> e) throws IllegalArgumentException{
		if(!(e instanceof AdaptablePQEntry))
			throw new IllegalArgumentException("Invalid entry");
		AdaptablePQEntry<K,V> valid = (AdaptablePQEntry<K,V>) e;
		int j = valid.getIndex();
		if(j >= heap.size() || comparator.compare(heap.get(j).getKey(), valid.getKey()) > 0)
			throw new IllegalArgumentException("Invalid entry");
		return valid;
	}
	
	//determines whether an uphead or a downheap is needed during an insert
	private void bubble(int j) {
		if(j>0 && comparator.compare(heap.get(j).getKey(), heap.get(parent(j)).getKey())< 0)
			upheap(j);
		else
			downheap(j);
	}

	@Override
	public Entry<K, V> min() {
		if(heap.isEmpty())
			return null;
		return heap.get(0);
	}
	@Override
	public Entry<K, V> removeMin() {
		if(heap.isEmpty())
			return null;
		Entry<K,V> e = heap.get(0);
		swap(0, heap.size()-1);
		heap.remove(heap.size()-1);
		downheap(0);
		return e;
	}
	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		AdaptablePQEntry<K,V> e = validate(entry);
		int j = e.getIndex();
		if(j==heap.size()-1) {
			heap.remove(heap.size()-1);
		}else {
			swap(j, heap.size()-1);
			heap.remove(heap.size()-1);
			bubble(j);
		}
		
	}
	@Override
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
		AdaptablePQEntry<K,V> e = validate(entry);
		e.setKey(key);
		bubble(e.getIndex());
	}
	@Override
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
		AdaptablePQEntry<K,V> e = validate(entry);
		e.setValue(value);
		
	}
	
}
