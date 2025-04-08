package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class PrefixSum {

    int[] a = {2,8,3,9,6,5,4};
    int[] prefixSum = new int[a.length];
    void preprocess(int[] a , int n)
    {
        int sum = a[0];
        prefixSum[0] = sum;
        for(int i = 1; i< n ; i++)
        {
            prefixSum[i] = sum + a[i];
        }
    }

    int getSum(int left, int right)
    {
        if(left == 0)
        {
            return prefixSum[right];
        }

        return prefixSum[right] - prefixSum[left-1];
    }
}
