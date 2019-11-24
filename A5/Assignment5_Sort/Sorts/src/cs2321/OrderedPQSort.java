package cs2321;

public class OrderedPQSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K>  {
	@Override
	
	//Sort with an ordered PQ
	
	@TimeComplexity("O(n^2)")
	public void sort(K[] array) {
		OrderedPQ<K,K> pq = new OrderedPQ<>();
		//Calls the super method sort()
		super.sort(array, pq);
	}
}
