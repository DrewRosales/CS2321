package cs2321;

public class UnorderedPQSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K>  {

	@TimeComplexity("n^2")
	@Override
	public void sort(K[] array) {
		//Create an unordered PQ
		UnorderedPQ<K,K> pq = new UnorderedPQ<>();
		//Call the super method of sort from generic PQ
		super.sort(array, pq);
	}

}
