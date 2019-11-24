package cs2321;

import net.datastructures.Stack;

public class DLLStack<E> implements Stack<E> {
	DoublyLinkedList<E> stack = new DoublyLinkedList<E>();
	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	//Adds to the last element of the DLL
	@Override
	public void push(E e) {
		stack.addLast(e);
		
	}
	//Takes a peek of the top element in the stack
	@Override
	public E top() {
		return stack.first().getElement();
	}
	//Removes the first element of the DLL
	@Override
	public E pop() {
		return stack.removeFirst();
	}

}
