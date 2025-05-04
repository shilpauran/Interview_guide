package main.java.urandoor.shilpa.PatternWiseDS.twopointer.classes;

//https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
public class TwoSumWithoutAuxillarySpace {

    //note that here array is not 0 based index, but 1 based index
    public int[] twoSum(int[] a, int target) {
        //should use only constant extra space. not hashmap. using hashmap we keep updating the differences . but here we are not supposed to use hashmap.

        int left = 0 ; int right = a.length-1;

        while(left<right)
        {
            int sum = a[left]+a[right];
            if(sum > target)
            {
                right--;
            }
            else if(sum < target)
            {
                left++;
            }
            else
            {
                return new int[]{left+1,right+1};
            }
        }

        return new int[]{-1,-1};

    }
}
