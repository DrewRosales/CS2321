package cs2321;
import org.junit.*;
import java.util.Random;
import static org.junit.Assert.*;
import cs2321.*;
public class SelectionSortTest {

		 Sorter <Integer> sorter;
		
		@Before
		public void setup() {
			sorter = new  InPlaceSelectionSort<Integer>();
		}
		
		@Test
		public void test1() {
			Integer[] data={1,2,3,4,5,6,7,8,9,10};
			Integer[] expectedData={1,2,3,4,5,6,7,8,9,10};
			
			sorter.sort(data);
			assertArrayEquals(expectedData, data);
		}
		
		@Test
		public void test2() {
			Integer[] data={10,9,8,7,6,5,4,3,2,1};
			Integer[] expectedData={1,2,3,4,5,6,7,8,9,10};
			
			sorter.sort(data);
			assertArrayEquals(expectedData, data);
		}
		
		@Test
		public void test3() {
			Integer[] data={1, 10, 2, 9,3, 8,4, 5, 7,6};
			Integer[] expectedData={1,2,3,4,5,6,7,8,9,10};
			
			sorter.sort(data);
			assertArrayEquals(expectedData, data);
		}
		
		@Test
		public void test4() {
			Integer[] data={10,9,9,7,6,6,6,3,2,1};
			Integer[] expectedData={1,2,3,6,6,6,7,9,9,10};
			
			sorter.sort(data);
			assertArrayEquals(expectedData, data);
		}
		
		@Test
		public void test5() {
			Integer[] data= new Integer[1024];
			Integer[] expectedData = new Integer[1024];

	        int a=5; //a must be 4c+1
	        int b=3; //b must be odd
	        int m = 1024;
	        
	        // This for loop is a number generator: it puts number 0-1023 to the array in unordered fashion. 
	        int x = 10; //the first number;
	        data[0] = x;
			for (int i=1;i<1024;i++ ) {
				x = ( a * x + b ) % m;
				data[i] = x;
			}

			for (int i=0;i<1024;i++ ) {
				expectedData[i] = i;
			}
			
			sorter.sort(data);
			assertArrayEquals(expectedData, data);
		}
		
		@Test
		public void test6() {
			// Create the test array :
	        Integer[] arr = new Integer[100000];
	        SortTiming timing = new SortTiming();
	        java.util.Random random = new java.util.Random();

	        // generate random number and put them in the array 
	        for(int i=0; i < 100000; i++)
	           arr[i] = random.nextInt(100000);
	        
	        double time = timing.testSort(arr, sorter);
	        System.out.println(time);
		}
		
		
	}

