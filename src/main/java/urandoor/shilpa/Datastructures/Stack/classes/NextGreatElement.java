package main.java.urandoor.shilpa.Datastructures.Stack.classes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class NextGreatElement {

    Deque<Integer> stack = new ArrayDeque<>();
    ArrayList<Integer> greatList = new ArrayList<>();

    //this stores the array list in reverse order. but if you want to print in correct order. just reverse the greatlist
    void findGreat(int[] a, int n)
    {
        stack.push(a[n-1]);
        for(int i = n-2 ; i<n;i++)
        {
            while(!stack.isEmpty() && stack.peek() <= a[i])
            {
                stack.pop();
            }

            if(stack.isEmpty())
            {
                greatList.add(-1);
            }
            else {
                greatList.add(stack.peek());
            }
            stack.push(i);
        }
    }
}
