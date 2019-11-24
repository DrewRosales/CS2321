package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FractionalKnapsackTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMaximumValue() {
		int[][] items = {{10,60}, {20,100}, {30,120}};
		int W=50;
		double j=240;
		double p = FractionalKnapsack.MaximumValue(items, W);
		assertEquals(j,p,1);
		
	}

}
