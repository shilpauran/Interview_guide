package main.java.urandoor.shilpa.Datastructures.Stack.classes;

import java.util.ArrayDeque;
import java.util.Deque;

public class StockSpanProblem {

//    void stockSpan(int[] a, int n)
//    {
//        Deque<Integer> stack = new ArrayDeque<>();
//        stack.push(1);
//        System.out.println(1);
//        //find if previous largest elements in stack are smaller . if smaller pop them
//        //for every element at the end push that into stack
//        for(int i = 1 ; i<n;i++)
//        {
//
//            while(!stack.isEmpty() && a[stack.peek()] <= a[i])
//            {
//                stack.pop();
//            }
//            int span = stack.isEmpty() ? i+1 : i-stack.peek();
//            System.out.println(span);
//            stack.push(i);
//        }
//    }

    Deque<int[]> stack = new ArrayDeque<>();
    StockSpanProblem(){}
    int getSpan(int price)
    {
        int span = 1;

        while(!stack.isEmpty() && stack.peek()[0] <= price)
        {
            span = span + stack.peek()[1];
            stack.pop();
        }
        stack.push(new int[]{price,span});
        return span;
    }


}
