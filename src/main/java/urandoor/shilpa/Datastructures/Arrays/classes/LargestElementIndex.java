package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class LargestElementIndex {

    int largest(int[] a, int n)
    {
        // empty array
        if(n == 0)
        {
            return -1;
        }

        //if single element
        if( n == 1)
        {
            return 0;
        }

        int large = 0;
        for(int i = 0 ; i< n ; i++)
        {
            if(a[large] < a[i])
            {
                large = i;
            }
        }

        return large;

    }
}
