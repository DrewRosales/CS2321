package cs2321;

public class QuickSort<E extends Comparable<E>> implements Sorter<E> {
	//Sorts based on the start of the array and the end
	@TimeComplexity("O(n^2)")
	public void sort(E[] array) {
		int p = 0;
		int r = array.length - 1;
		quickSort(array, p, r);
	}
	//Helper method for sort
	public E[] quickSort(E[] array, int p, int r) {
		if(p<r) {
			int part = partition(array, p, r);
			quickSort(array, p, part-1);
			quickSort(array, part+1, r);
		}
		
		return array;
	}
	
	//Creates a split in the array
	@TimeComplexity("O(n^2)")
	public int partition(E[] array, int p, int r) {
		E entry = array[r];
		int i = p;
		int j = r - 1;
		
		while(i <= j) {
			while(i <= j && (array[i].compareTo(entry) <= 0)) {
				i++;
			}
			while(i <= j && array[j].compareTo(entry) >= 0) {
				j--;
			}
			
			if(i < j) {
				swap(array, i, j);
				i++;
				j--;
			}
			
		}
		
		swap(array, i, r);
		return i;
	}
	
	//Switch the element in index i and j
	@TimeComplexity("O(1)")
	public void swap(E[] arr, int i, int j) {
		E temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
