package cs2321;

import java.util.Arrays;

public class MergeSort<E extends Comparable<E>> implements Sorter<E> {

	
	@TimeComplexity("nlogn")
	public void sort(E[] array) {
		int n = array.length;
		//Return if the size is 1
		if(n==1) {
			return;
		}
		//Midpoint of the array
		int mid = n/2;
		//Store each subdivision
		E[] left = Arrays.copyOfRange(array, 0, mid);
		E[] right = Arrays.copyOfRange(array, mid, n);
		//Recursively call on both ends
		sort(left);
		sort(right);
		//Merge the two halves
		merge(array, left, right);

	}
	
	//Combine two arrays in an ordered fashion
	@TimeComplexity("O(n)")
	public  void merge(E[] A, E[] A1, E[] A2) {
		int i = 0;
		int j = 0;
		
		while(i+j < A.length) {
			if(j==A2.length || (i<A1.length && A1[i].compareTo(A2[j]) < 0) ) {
				A[i+j] = A1[i];
				i++;
			}else {
				A[i+j] = A2[j];
				j++;
			}
		}
	}
}

