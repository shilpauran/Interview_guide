package main.java.urandoor.shilpa.Datastructures.Sorting.classes;

public class InsertionSort {

    int[] sort(int[] a ,int n)
    {
        for(int i = 1 ; i< n;i++)
        {
            int key  = a[i]; //current element which needs to be inserted at right place
            int j = i-1;
            while(j>= 0 && a[j+1] > key)
            {
                a[j+1] = a[j];
                j--;
            }

            a[i] = key;

        }

        return a;
    }
}
