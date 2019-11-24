package cs2321;

/**
 * @author:
 *
 */
public class FractionalKnapsack {

   
	/**
	 * Goal: Choose items with maximum total benefit but with weight at most W.
	 *       You are allowed to take fractional amounts from items.
	 *       
	 * @param items items[i][0] is weight for item i
	 *              items[i][1] is benefit for item i
	 * @param knapsackWeight
	 * @return The maximum total benefit. Please use double type operation. For example 5/2 = 2.5
	 * 		 
	 */
	public static double MaximumValue(int[][] items, int knapsackWeight) {
		HeapPQ<Integer, Double> heap = new HeapPQ<>();
		double weight;
		double benefit;
		double totW = 0;
		double totalBenefit=0;
		//load all the elements
		 for(int i=0; i<items.length; i++) {
			weight = items[i][0];
			benefit = items[i][1];
			//inserts based on the benefit- weight ratio, times by -1 to place the max value as the minimum in the Heap
			heap.insert(i, -1*benefit/weight);
		 }
		 
		 //iterate until the total added weight in the knapsack is greater than or equal to the knapsack weight potential
		 while(totW < knapsackWeight) {
			 int max = heap.min().getKey();
			 
			 //the benefit if the weight is within bounds
			 if(totW + items[max][0] <= knapsackWeight) {
				 totalBenefit += heap.removeMin().getValue() * items[max][0];
				 totW += items[max][0];
			//add a fraction of the benefit when the weight of a specific item is less than the knapsack weight potential but greater than 0
			 }else {
				 double fracion = -1*(knapsackWeight - totW) /items[max][0];
				 totalBenefit += fracion * items[max][1];
				 totW = knapsackWeight;
			 }
			 
		 }
		 //multiply by -1 since the value was previously saved as a negative
		 totalBenefit *= -1;
		return totalBenefit;
	}
}
