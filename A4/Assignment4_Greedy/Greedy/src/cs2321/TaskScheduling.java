package cs2321;

public class TaskScheduling {
	/**
	 * Goal: Perform all the tasks using a minimum number of machines. 
	 * 
	 *       
	 * @param tasks tasks[i][0] is start time for task i
	 *              tasks[i][1] is end time for task i
	 * @return The minimum number or machines
	 */
	
	//compares if the first parameter i is less than the ending time for the passed task
	public static boolean compare(int i, ArrayList<int[]> x) {
		//flag for comparing value and array
		boolean flag = false;
		//Iterate through the array list
		for(int[] j :x)
				//flag becomes true, new machine
				if(i < j[1]) {
						flag = true;
				}else {
						flag = false;
				}
		return flag;
	}
   public static int NumOfMachines(int[][] tasks) {
	  HeapPQ<Integer, int[]> heap = new HeapPQ<>();
	  ArrayList<int[]> machines = new ArrayList<>();
	  
	  //loads the heap with the start time as the key and a row for the tasks
	  for(int i=0; i<tasks.length;i++) {
		  heap.insert(tasks[i][0], tasks[i]);
	  }
	  
	  //proceed until the heap is empty
	  while(!heap.isEmpty()) {
		  int [] row = heap.removeMin().getValue();
		  //create add a new machine
		  if(!compare(row[0], machines)) {
			  machines.addLast(row);
		  }
	  }
	  
	  	//number of machines is equal to the number of elements, arraylists, in the machines
	   return machines.size();
   }
}
