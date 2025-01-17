package cs2321;

import net.datastructures.*;

/**
 * A class that performs the generic PQ Sort:
 *
 * @author CS2321 Instructor
 * @param <K>
 */
public class PQSort<K extends Comparable<K>> {
	
	/*
	 * PQSort - insert every element in array to PQ, 
	 * then call PQ's removeMin repeatedly, and overwrite the data in the array
	 */
	
	protected void sort(K[] kArray, PriorityQueue<K,K> pq) {
		//Place elements of the array into the PQ
		for(int i=0; i<kArray.length; i++) {
			pq.insert(kArray[i], null);
		}
		//Place the minimum of the PQ into the array
		for(int i=0; i<kArray.length; i++) {
			kArray[i] = pq.removeMin().getKey();
		}
	}

	

}
