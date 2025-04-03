package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class LeftRotate {

    int[] leftRotate(int[] a , int n)
    {
        //swap from back side with first element
        for(int i = n-1 ; i> 0; i -- )
        {
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
        }

        return a;
    }
}
