package cs2321;

public class PostfixExpression {
	/**
	 * Evaluate a postfix expression. 
	 * Postfix expression notation has operands first, following by the operations.
	 * For example:
	 *    13 5 *           is same as 13 * 5 
	 *    4 20 5 + * 6 -   is same as 4 * (20 + 5) - 6  
	 *    
	 * In this homework, expression in the argument only contains
	 *     integer, +, -, *, / and a space between every number and operation. 
	 * You may assume the result will be integer as well. 
	 * 
	 * @param exp The postfix expression
	 * @return the result of the expression
	 */
	public static int evaluate(String exp) {
			DLLStack<Integer> stack = new DLLStack<Integer>();
			String[] expression =  exp.split(" ");
			for(int i=0; i<expression.length;i++) {
				//String is based on the components of the expression array
				String s = expression[i];
				if(Character.isDigit(s.charAt(0)) || (expression[i].length() > 1 && Character.isDigit(expression[i].charAt(1)))) {
	
					stack.push(Integer.parseInt(s));
				} else {
					
					//operands popped out of the stack
					int pop1 = stack.pop();
					int pop2 = stack.pop();
					
					//statement to determine mathematical operations once popped
					switch(s) {
					case "+":
						int sum = (pop2 + pop1);
						stack.push(sum);
					break;
					
					case "-":
						int difference = (pop1 - pop2);
						stack.push(difference);
					break;
					
					case "*":
						int product = (pop1 * pop2);
						stack.push(product);
					break;
					
					case "/":
						int quotient = (pop1 / pop2);
						stack.push(quotient);
					break;
					
				}
			}
		}
			//returns the last number in the stack, after all operators are cleared
			return stack.pop();
	}
				
}
