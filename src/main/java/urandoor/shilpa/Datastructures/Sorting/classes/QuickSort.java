package main.java.urandoor.shilpa.Datastructures.Sorting.classes;

public class QuickSort {
    //Hoare's partition
    int[] quickSortHoare(int[] a , int low , int high)
    {
        if(low<high)
        {
            int piIndex = partitionHoare(a,low,high);
            quickSortHoare(a,low,piIndex);
            quickSortHoare(a,piIndex+1,high);

        }

        return a;
    }

    private int partitionHoare(int[] a, int low, int high) {

        int pivot = a[low];
        int i = low-1;
        int j = high+1;

        while(true)
        {
            do {
                i++;
            }while(a[i] < pivot);

            do{
                j++;
            }while (a[i] > pivot);

            if(i>=j)
            {
                return j;   //Returning j gives us the final position of the partition boundary, where: All elements to the left of j are less than or equal to pivot .All elements to the right of j are greater than or equal to pivot But note: the pivot itself might not be at j. Unlike Lomuto partitioning, Hoare’s does not guarantee pivot ends up in the middle — but the array is still partitioned correctly.
            }

            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    //low is a[0] and high is a[n-1]
    int[] quickSortLomuto(int[] a , int low , int high)
    {

        if(low < high)
        {
            int piIndex = partition(a, low, high);
            quickSortLomuto(a, low,piIndex-1);
            quickSortLomuto(a,piIndex+1,high);
        }
        return a;
    }

    private int partition(int[] a, int low, int high) {
// taking last element as a pivot is called Lomuto partition algorithm
        int pivot = a[high];
        int i = low -1 ;
        for(int j = low ; j< high;j++)
        {
            if(a[j] < pivot)
            {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        //one final swap for the pivot to go into the right place.
        int temp = a[i+1];
        a[i+1] = a[high];
        a[high] = temp;

        return i+1;
    }
}
