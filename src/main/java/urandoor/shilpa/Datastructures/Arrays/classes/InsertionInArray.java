package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class InsertionInArray {

    int[] insert(int[] a, int length, int capacity, int position, int x)
    {
        if(length == capacity)
        {
            return a;
        }

        //traverse from back side to move the elements till index
        for(int i = length-1 ; i >= position ; i--)
        {
            a[i+1] = a[i];
        }
        a[position] = x;
        return a;

    }
}
