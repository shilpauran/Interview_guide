package main.java.urandoor.shilpa.Datastructures.Sorting.classes;

public class CycleSort {

    int[] sort(int[] a, int n)
    {
        int i = 0;
        while(i<n)
        {
            if(a[i] != a[a[i]-1])
            {
                int temp = a[i];
                a[i] = a[a[i]-1];
                a[a[i]-1] = temp;
            }
            else {
                i++;
            }
        }
        return a;
    }
}
