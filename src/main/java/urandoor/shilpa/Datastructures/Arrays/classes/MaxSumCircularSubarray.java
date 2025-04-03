package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class MaxSumCircularSubarray {

    int circularSubArray(int[] a, int n)
    {
        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;
        int maxSumCurr = 0;
        int minSumCurr = 0;
        int totalSum = 0;

        //find max sum subarray
        for(int i=0; i<n;i++)
        {
            maxSumCurr = Math.max(maxSumCurr + a[i],a[i]);
            maxSum = Math.max(maxSumCurr,maxSum);
        }

        for(int i = 0; i< n;i++)
        {
            totalSum += a[i];
        }

        for(int i=0; i<n;i++)
        {
            minSumCurr = Math.min(minSumCurr + a[i],a[i]);
            minSum = Math.min(minSumCurr,minSum);
        }

        return Math.max(maxSum, totalSum - minSum);
    }
}
