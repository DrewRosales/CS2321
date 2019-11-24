package cs2321;

public class InfixToPostfix {
	/* Convert an infix expression and to a postfix expression
	 * infix expression : operator is between operands. Ex. 3 + 5
	 * postfix Expression: operator is after the operands. Ex. 3 5 +
	 * 
	 * The infixExp expression includes the following
	 *      operands:  integer or decimal numbers 
	 *      and operators: + , - , * , /
	 *      and parenthesis: ( , )
	 *      
	 *      For easy parsing the expression, there is a space between operands and operators, parenthesis. 
	 *  	Ex: "1 * ( 3 + 5 )"
	 *      Notice there is no space before the first operand and after the last operand/parentheses. 
	 *  
	 * The postExp includes the following 
	 *      operands:  integer or decimal numbers 
	 *      and operators: + , - , * , /
	 *      
	 *      For easy parsing the expression, there should have a space between operands and operators.
	 *      Ex: "1 3 5 + *"
	 *      Notice there is space before the first operand and last operator. 
	 *      Notice that postExp does not have parenthesis. 
	 */
	
	static int precedence (char c) {
		if(c == '+' || c == '-') {
			return 1;
		} else if (c == '*' || c == '/') {
			return 2;
		}
		return -1;
	}
	public static String convert(String infixExp) {
		//Hint: you can use the string.split(" ") to return an array of tokens in infixExp. 
		
		DLLStack<Character> stack = new DLLStack<Character>();
		String[] expression  = infixExp.split(" ");
		
		String result = "";
		
		for(int i=0; i<expression.length; ++i) {
			//converts the string to a char
			char c = expression[i].charAt(0);
			//Determines if the char is a number
			if(Character.isDigit(c)) {
				//adds to the output
				result += c;
				result += " " ;
			}else if(c == '(') {
				stack.push(c);
			}else if(c ==')') {
				//Pops the stack until the other parentheses is found
				while(!stack.isEmpty() && (stack.top() != '(')) {
					result += stack.pop();
					result += " ";
				}
			//Pops operations basedo on the precendence
			}else {
				while(!stack.isEmpty() && (precedence(c) <= precedence(stack.top())) ) {
					result += stack.pop();
					if(i<expression.length) 
						result += " " ;
					
					
				}
				stack.push(c);
			}
		}
		
		//Pushes everything out of the stack
		while(!stack.isEmpty()) {
			result +=stack.pop();
			result += " " ;
		}
		
		//Removes the space at the end of the completed result
		if(result.charAt(result.length()-1 )== ' ')
			result = result.substring(0, result.length()-1);
			
		
		return result;
	}	
}
