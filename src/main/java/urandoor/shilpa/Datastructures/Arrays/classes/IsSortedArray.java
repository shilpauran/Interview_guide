package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class IsSortedArray {


    Boolean isSorted(int[] a, int n)
    {
        for(int i = 1 ; i< n ; i++)
        {
            if(a[i] < a[i-1])
            {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;

    }
}
