package main.java.urandoor.shilpa.Datastructures.Stack.classes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PrevGreatElement {

    Deque<Integer> stack = new ArrayDeque<>();

    List<Integer> findGreatBasedOnArray(int[] a, int n)
    {
        List<Integer> greatList =  new ArrayList<>();
        stack.push(a[0]);
        for(int i =0;i<n;i++)
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

        return greatList;
    }

    //if whole array is not given
    //for each element find the previous greater element

    int findGreat(int price)
    {
        int great;

        while(!stack.isEmpty() && stack.peek() <= price)
        {
            stack.pop();
        }

        if(stack.isEmpty())
        {
            great = -1;
        }
        else {
            great = stack.peek();
        }
        stack.push(price);
        return great;
    }
}
