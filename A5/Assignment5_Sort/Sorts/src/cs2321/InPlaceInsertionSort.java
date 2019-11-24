package cs2321;

public class InPlaceInsertionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place insertion sort
	 * @param array - Array to sort
	 */
	
	@TimeComplexity("O(n^2)")
	public void sort(K[] array) {
		//iterate from the 1st index to the length of the array
		for(int i = 1; i < array.length; i++) {
			//previous index from i
			int j = i-1;
			//store the value at i
			K value = array[i];
			
			//while the condition is true, flip elements
			while(j >= 0 && array[j].compareTo(value) >= 0) {
				array[j+1] = array[j];
				j--;
			}
			array[j+1] = value;
		}
	}

}
