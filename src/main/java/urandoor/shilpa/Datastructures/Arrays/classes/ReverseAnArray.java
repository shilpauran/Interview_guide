package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class ReverseAnArray {

    int[] reverse(int[] a, int n)
    {

        //we can use while loop as well
        for(int i = 0 , j = n-1 ; i< n && j > i ; i++ , j--)
        {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        return a;
    }
}
