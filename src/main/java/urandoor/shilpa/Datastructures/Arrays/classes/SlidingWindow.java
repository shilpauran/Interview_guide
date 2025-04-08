package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class SlidingWindow {

    int sum(int[] a, int n, int k)
    {
        int sum = 0;
        for(int i = 0 ; i < k ; i++)
        {
            sum = sum + a[i];
        }
        int maxSum = sum;
        for(int i = k ; i<n; i++)
        {
            sum = sum + a[i] - a[i-k];
            maxSum = Math.max(maxSum,sum);
        }

//        //while
//        int low = 1 , high = k;
//        while(low < high)
//        {
//            sum = sum + a[high] - a[low];
//            maxSum = Math.max(maxSum,sum);
//        }

        return maxSum;
    }

}
