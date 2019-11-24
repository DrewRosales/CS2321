package cs2321;

public class InPlaceSelectionSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place selection sort
	 * @param array - Array to sort
	 */
	
	@TimeComplexity("O(n^2)")
	public void sort(K[] array) {
		
		//Iterate from the start to the length of the array
		for(int i=0; i<array.length-1;i++) {
			//The minimum index
			int min = i;
			for(int j=i+1; j<array.length; j++) {
				//sets the new minimum if element at the index j is less than the min index
				if(array[j].compareTo(array[min])  <= 0)
					min = j;
			}
			
			//Swap the minimum with the index i
			K temp = array[min];
			array[min] =array[i];
			array[i] = temp;
		}
		
	}
	
}
