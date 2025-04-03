package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class MaximumSumSubarray {


    int max ( int[] a, int n)
    {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0; i<n;i++)
        {
            sum += a[i];
            maxSum = Math.max(sum,maxSum);

            if(sum < 0)
            {
                sum = 0;
            }
        }

        return maxSum;

//        // this dint work in leetCode
        //oka element varaki unna sum peddada or aa element ey peddada ani chustam
        //renditilo yedi peddadi aithe adi maxEnding ani update chestam
        //and unna max eNDING and result lo edi peddadi adi result lo update chestam

//        //we need 2 pointers . one to store max Ending of the subarray till that element
//        int maxEnding = a[0];
//        //result
//        int result = a[0];
//
//        for(int i = 1 ; i< n ; i++)
//        {
//            maxEnding = Math.max(maxEnding,maxEnding+a[i]);
//            result = Math.max(maxEnding,result);
//        }
//
//        return result;
    }
}
