package main.java.urandoor.shilpa.Datastructures.Stack.classes;

import java.util.ArrayDeque;
import java.util.Deque;

public class BalancedParanthesis {

    Boolean matching(char a, char b)
    {
        return ((a == '(' && b == ')')||
                (a == '{' && b == '}') ||
                (a =='[' && b == ']'));
    }

    Boolean isBalanced(String str)
    {
        Deque<Character> stack = new ArrayDeque<>();
        for(int i =0;i<str.length();i++)
        {
            char x = str.charAt(i);
            if(i == '(' || i == '{' || i =='[')
            {
                stack.push(x);
            }
            else if(i == ')' || i == '}' || i == ']')
            {
                //if stack is empty or first element does not match with the x then also false
                if(stack.isEmpty() || Boolean.FALSE.equals(matching(stack.peek(),x)))
                {
                    return Boolean.FALSE;
                }
                stack.pop();
            }
        }
        return stack.isEmpty(); //if any element is left over in the stack it will return false
    }
}
