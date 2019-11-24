package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PostfixExpressionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEvaluate1() {
		int i = PostfixExpression.evaluate("2");
		org.junit.Assert.assertEquals(2, i);
	}
	
	@Test
	public void testEvaluate2() {
		int i = PostfixExpression.evaluate("4 20 5 + * 6 -");
		org.junit.Assert.assertEquals(94, i);
	}
	
	@Test
	public void testEvaluate3() {
		int i = PostfixExpression.evaluate("20 5 +");
		org.junit.Assert.assertEquals(25, i);
	}
	
	@Test
	public void testEvaluate4() {
		int i = PostfixExpression.evaluate("4 20 5 + *");
		org.junit.Assert.assertEquals(100, i);
	}
	
//	@Test
//	public void testEvaluate5() {
//		fail("not implemented");
//	}
//	
//	@Test
//	public void testEvaluate6() {
//		fail("not implemented");
//	}
//	
//	@Test
//	public void testEvaluate7() {
//		fail("not implemented");
//	}
//	
//	@Test
//	public void testEvaluate8() {
//		fail("not implemented");
//	}

}
