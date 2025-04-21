package main.java.urandoor.shilpa.Datastructures.Sorting.classes;

public class SelectionSort {

    int[] selectionSort(int[] a,int n)
    {
        for(int i = 0 ; i < n-1 ; i++)//only till last before element, because j = i+1 then u will get error
        {
            int min = i;
            //find the minimum in that pass
            for(int j = i + 1; j< n;j++)
            {
                if(a[min] > a[j])
                {
                    min = j;
                }
            }

            //next push the minimum element to front
            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }

        return a;
    }
}
