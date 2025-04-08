package main.java.urandoor.shilpa.Datastructures.Stack.classes;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackWithGetMin {

    Deque<Integer> mainStack = new ArrayDeque<>();
    Deque<Integer> minStack = new ArrayDeque<>();

    void push(int data)
    {
        mainStack.push(data);

        //push only if it is minimum or empty
        if(minStack.isEmpty() || data <= minStack.peek())
        {
            minStack.push(data);
        }
    }

    void pop()
    {
        if(mainStack.isEmpty())
        {
            return;
        }
        int removed = mainStack.pop();
        //pop only if it is minimum element
        if(!minStack.isEmpty() && minStack.peek() == removed)
        {
            minStack.pop();
        }
    }

    int peek()
    {
        return mainStack.peek();
    }

    int getMin()
    {
        return minStack.peek();
    }
}



//leetcode another approach using int[] {x,x} array inside the stock
//class MinStack {
//    private Stack<int[]> stack = new Stack<>();
//
//    public MinStack() {}
//
//    public void push(int x) {
//        /* If the stack is empty, then the min value
//         * must just be the first value we add. */
//        if (stack.isEmpty()) {
//            stack.push(new int[] { x, x });
//            return;
//        }
//
//        int currentMin = stack.peek()[1];
//        stack.push(new int[] { x, Math.min(x, currentMin) });
//    }
//
//    public void pop() {
//        stack.pop();
//    }
//
//    public int top() {
//        return stack.peek()[0];
//    }
//
//    public int getMin() {
//        return stack.peek()[1];
//    }
//}
