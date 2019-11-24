package cs2321;

public class HeapPQSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K> {
	
	@TimeComplexity("O(nlogn)")
	public void sort(K[] array) {
		//Create a heap
		HeapPQ<K,K> pq = new HeapPQ<>();
		//Call the super method from the generic PQ
		super.sort(array, pq);
	}

}
