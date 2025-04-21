package main.java.urandoor.shilpa.Datastructures.Sorting.classes;

import java.util.HashMap;
import java.util.Map;

public class MergeTwoSortedArrays {

    int[] merge(int[] a , int[] b, int n, int m)
    {
        int[] c  = new int[n+m]; //using auxillary array.
        int i = 0, j = 0 , k =0; //three pointer

        while(i<n && j<m)
        {
            if(a[i]<=b[j])
            {
                c[k] = a[i];
                i++;
            }
            else {
                c[k] = b[j];
                j++;
            }
            k++;
        }

        while(i<n)
        {
            c[k] = a[i];
            i++;k++;
        }

        while(j<m)
        {
            c[k] = a[j];
            j++;k++;
        }

        return c;
    }


    //given a is of length n+m
    //eg: a = { 1,2,4,0,0,0}
    //b = {3,5,6}
    //use the fact that rest of a is free and do it without using auxillary space
    //do greater comparistion from backwards
    int[] merge1(int[] a, int[] b, int n, int m)
    {
        int p1 = m-1;
        int p2 = n-1;

        for(int p = m+n-1; p>=0;p--)
        {
            if(p2 < 0)
                break;

            if(p1>=0 && a[p1] >= b[p2])
            {
                a[p] = a[p1];
                p1--;
            }
            else {
                a[p] = b[p2];
                p2--;
            }
        }

        return a;
    }
}
