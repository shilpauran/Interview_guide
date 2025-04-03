package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class DeletionInArray {

    int[] delete(int[] a , int val, int length)
    {
        int i;
        //first search
        for(i =0 ; i< length ; i++)
        {
            if(a[i] == val)
            {
               break;
            }
        }

        //element not found
        if(i == length)
        {
            return a;
        }

        //start shifting the next elements front
        for(int j = i ; j< length ; j ++)
        {
            a[j] = a[j+1];
        }

        return a;
    }
}
