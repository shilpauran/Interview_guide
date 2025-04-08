package main.java.urandoor.shilpa.Datastructures.Stack.classes;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class LargestRectangleArea {

    //gives with 90% complexity
    int findMaxArea(int[] a, int n)
    {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] newA = Arrays.copyOf(a,n+1);

        int maxArea = 0;
        for(int i = 0; i< newA.length;i++) {

            while(!stack.isEmpty() && newA[i] < newA[stack.peek()])
            {
                int height =  newA[stack.pop()];
                int width = stack.isEmpty() ? i : i-stack.peek()-1;
                int area = height * width;
                maxArea = Math.max(maxArea,area);

            }

            stack.push(i);
        }

        return maxArea;
    }

}
