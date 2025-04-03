package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class MaximumDiff {

    //keep track of minimum element found so far
    //update max difference
    //update min element
    int diff(int[] a,int n)
    {
        int minElement = a[0];
        int maxDiff = 0; // this declaration wont work for negative differences.
        //check out https://leetcode.com/problems/maximum-difference-between-increasing-elements/

        for(int i = 1; i< n ; i++)
        {
            //update max diff
            if(a[i]-minElement > maxDiff) //see here also as the array might always not have values in order, its better to check if a[i] > min and then only start updating the maxDiff
            {
                maxDiff = a[i]-minElement;
            }

            if(a[i] < minElement)
            {
                minElement = a[i];
            }
        }
        return maxDiff;
    }


    //below works fine
//    public int maximumDifference(int[] nums) {
//        int n = nums.length;
//        int min = nums[0]; // Initialize minimum with the first element
//        int maxDiff = -1;  // Start with -1 (no valid pair found)
//
//        for (int i = 1; i < n; i++) {
//            if (nums[i] > min) { // Only update maxDiff if nums[i] is greater than min
//                maxDiff = Math.max(maxDiff, nums[i] - min);
//            }
//            if (nums[i] < min) { // Update the min element
//                min = nums[i];
//            }
//        }
//
//        return maxDiff;
//    }
}
