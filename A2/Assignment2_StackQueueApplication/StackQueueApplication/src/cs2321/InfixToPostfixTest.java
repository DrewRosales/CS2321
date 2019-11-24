package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InfixToPostfixTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConvert1() {
		String s = InfixToPostfix.convert("1 * 2");
		org.junit.Assert.assertEquals("1 2 *", s);
	}
	
	@Test
	public void testConvert2() {
		String s = InfixToPostfix.convert("3");
		org.junit.Assert.assertEquals("3", s);
	}

	@Test
	public void testConvert3() {
		String s = InfixToPostfix.convert("1 + 2 / 3 *  6 / 9 ");
		org.junit.Assert.assertEquals("1 2 3 / 6 * 9 / +* +", s);
		
	}
	
	@Test
	public void testConvert4() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void testConvert5() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void testConvert6() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void testConvert7() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void testConvert8() {
		fail("Not yet implemented"); // TODO
	}
}
