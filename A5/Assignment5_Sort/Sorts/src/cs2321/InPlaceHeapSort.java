package cs2321;

public class InPlaceHeapSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place heap sort
	 * @param array - Array to sort
	 */
	
	//Size of the heap
	int heapSize;
	
	@TimeComplexity("O(nlogn)")
	public void sort(K[] array) {
		int n= array.length;
		
		//Create a max heap based on the array
		buildMaxHeap(array);
		
		//Order the array
		for(int i = n-1; i >= 1; i-- ) {
			swap(array, 0, i);
			heapSize--;
			maxHeapify(array, 0);
		}

	}
	
	
	//create a max heap
	@TimeComplexity("O(nlogn")
	public void buildMaxHeap(K[] arr) {
		heapSize = arr.length;
		for(int i=(arr.length/2); i >= 0; i--) {
			maxHeapify(arr, i);
		} 
	}
	
	//order the heap
	@TimeComplexity("O(logn)")
	public void maxHeapify(K[] arr, int i) {
		int largest = i;
		int l = left(i);
		int r = right(i);
		
		if(l < heapSize && (arr[l].compareTo(arr[largest]) > 0))
			largest = l;
		if(r < heapSize && (arr[r].compareTo(arr[largest]) > 0))
			largest = r;
		
		if(largest != i) {
			swap(arr, i, largest);
			maxHeapify(arr,largest);
		}
	}
	
	//swap elements of the array
	@TimeComplexity("O(1)")
	public void swap(K[] arr, int i, int j) {
		K temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	@TimeComplexity("O(1)")
	public int left(int i) {
		return (2*i + 1);
	}
	
	@TimeComplexity("O(1)")
	public int right(int i) {
		return (2*i + 2);
	}
}
