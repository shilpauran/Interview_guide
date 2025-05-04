package main.java.urandoor.shilpa.PatternWiseDS.twopointer.classes;

import java.util.*;

//https://leetcode.com/problems/3sum/
public class ThreeSum {

    //this problem is an extenstion of 2 sum problem  // all 3 elements sum must be 0
    //where one of the approaches is sorting the array first, and using 2 pointers to check if the sum is equal to target
    //here in this problem according to leetcode, we also need to make sure no duplicate lists are stored.
    //so we use hashset.
    //we use 3 pointers, current, left, right
    public List<List<Integer>> threeSum(int[] a) {

        //sort the array first
        Arrays.sort(a);

        //create an empty hashset
        Set<List<Integer>> resultSet = new HashSet<>();

        //traverse through each element in the array until the 3rd last element. because there should be 3 elements for calculating sum
        for(int curr = 0; curr < a.length-2; curr++)
        {
            int left = curr+1;
            int right = a.length-1;

            while(left<right)
            {
                //calculate total sum of elements in all 3 pointers
                int sum = a[curr] + a[left] + a[right];
                if(sum == 0)
                {
                    //then add to set
                    //increment left
                    //increment right
                    resultSet.add(Arrays.asList(a[curr],a[left],a[right])); //takes care of not adding duplicates
                    left++;
                    right--;
                }
                else if(sum < 0 )
                {
                    left++ ; //move only left
                }
                else {
                    right --; //move right
                }
            }
        }

        //at last return the set. but they asked to return a list. so convert to list and return
        return new ArrayList<>(resultSet);

    }
}
