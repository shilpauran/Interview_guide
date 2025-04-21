package main.java.urandoor.shilpa.Datastructures.Sorting.classes;

public class CountingSort {

    //this is using an array. check the hashmap implementation also in the video.
    int[] sort(int[] a, int n)
    {
        if(n <= 1)
        {
            return a;
        }
        //find the largest element
        int max = a[0];
        for(int i : a)
        {
            max = Math.max(max,a[i]);
        }

        //create an auxillary space
        int[] newA = new int[max+1]; //we create with max+1 because if the max is 6 then we have to put 6 also in the auxillary array.

        //start adding the elements
        for(int i:a)
        {
            newA[a[i]]++;
        }

        //now traverse the auxillary array and put the elements in original array in right position

        int index = 0;
        for(int i = 0 ; i< max+1;i++) //because size of newA is max+1
        {
            while(newA[i] > 0)
            {
                a[index] = i;
                index++;
                newA[i]--;
            }
        }

        return a;
    }
}
