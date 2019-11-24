package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TaskSchedulingTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNumOfMachines() {
		int[][] test = {{1,4},{1,3},{2,5},{3,7},{4,7},{6,9},{7,8}};
	    assertEquals(3,TaskScheduling.NumOfMachines(test));
	}

}
