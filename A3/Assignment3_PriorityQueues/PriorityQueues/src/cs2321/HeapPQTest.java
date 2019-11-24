package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.datastructures.AdaptablePriorityQueue;
import net.datastructures.Entry;

public class HeapPQTest {

	AdaptablePriorityQueue<String, Integer> heappq;
	
	@Before
	public void setUp() throws Exception {
		heappq = new HeapPQ<String, Integer>();
		heappq.insert("Bulbous Bouffant", 16);
		heappq.insert("Gazebo", 6);
		heappq.insert("Balooga", 7);
		heappq.insert("Galoshes", 8);
		heappq.insert("Eskimo", 6);
		heappq.insert("Mukluks", 7);
		heappq.insert("Macadamia", 9);
	}

	@Test
	public void testRemoveMin() {
		Entry<String, Integer> e;
		String[] expected= {
				"Balooga", 
				"Bulbous Bouffant",
				"Eskimo", 
				"Galoshes", 
				"Gazebo", 
				"Macadamia",
				"Mukluks"
		};
		
		int i=0;
		while(!heappq.isEmpty()){
			e = heappq.removeMin();
			assertEquals(expected[i],  e.getKey());
			i++;
		}
	}


	@Test
	public void testSize() {
		assertEquals(heappq.size(), 7);
	}
	

	@Test
	public void testIsEmpty() {
		assertEquals(heappq.isEmpty(), false);
	}

	@Test
	public void testInsert() {
		heappq.insert("aa", 0);
		assertEquals(heappq.min().getKey(), "aa");
		assertEquals((int) heappq.min().getValue(), 0);
		heappq.insert("zz", 0);
		assertEquals(heappq.min().getKey(), "zz");
		assertEquals((int) heappq.min().getValue(), 0);
	}

	@Test
	public void testMin() {
		heappq.insert("aa", 0);
		assertEquals(heappq.min().getKey(), "aa");
		assertEquals((int) heappq.min().getValue(), 0);
		heappq.insert("zz", 0);
		assertEquals(heappq.min().getKey(), "zz");
		assertEquals((int) heappq.min().getValue(), 0);
	}


	@Test
	public void testRemove() {
		heappq.removeMin();
		assertNotEquals(heappq.min().getKey(), "aa");
		assertNotEquals((int) heappq.min().getValue(), 0);
	}

	@Test
	public void testReplaceKey() {
		Entry<String, Integer> e = new HeapPQ.AdaptablePQEntry<>("aa", 0, 0);
		heappq.replaceKey(e, "AA");
		assertEquals(heappq.min().getKey(), "AA");
	}

	@Test
	public void testReplaceValue() {
		Entry<String, Integer> e = new HeapPQ.AdaptablePQEntry<>("aa", 0, 0);
		heappq.replaceKey(e, "AA");
		assertEquals(heappq.min().getKey(), "AA");
	}

}
