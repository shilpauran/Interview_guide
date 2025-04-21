package main.java.urandoor.shilpa.Datastructures.Sorting.classes;

import java.util.HashMap;
import java.util.Map;

public class BubbleSort {

    //if array is already sorted, then we need only O(n) complexity
    int[] alreadySorted(int[] a , int n)
    {
        Boolean swapped ;
        for(int i = 0 ; i< n;i++)
        {
            swapped = Boolean.FALSE; //because may be 1st pass it has swapped, but in 2nd pass its already sorted. so we need to reset this.
            for(int j=0; j<n-i-1;j++)
            {
                if(a[j]> a[j+1])
                {
                    int temp =  a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;

                    swapped = Boolean.TRUE;
                }
            }
            //no swaps. array already swapped.
            if(Boolean.FALSE.equals(swapped))
                break;
        }
        return a;
    }
    //if array is random
    int[] sort(int[] a, int n)
    {
        for(int i =0 ;i<n;i++)
        {
            for(int j = 0;j<n-1-i;j++)
            {
                if(a[j] > a[j+1])
                {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }

            }
        }
        return a;
    }

}

//time complexity is O(n^2)
