package main.java.urandoor.shilpa.Datastructures.Stack.classes;

import java.util.Deque;

public class LargestRectangleOnes {

    int findLargest(int[][] mat, int n)
    {
        //3 column, 3 row
        LargestRectangleArea largestRectangleArea = new LargestRectangleArea();
        int res = largestRectangleArea.findMaxArea(mat[0], 5);
        for(int i =1 ; i< 4 ; i++)//assuming 4 rows
        {
            for(int j =0 ;j<4 ;j++)
            {
                if(mat[i][j] == 1)
                {
                    mat[i][j] += mat[i-1][j];
                }
            }
            res = Math.max(res,largestRectangleArea.findMaxArea(mat[i],4));
        }

        return res;
    }
}
