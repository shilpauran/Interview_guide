package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class MoveAllZerosToEnd {

    int[] moveZeros(int[] a,int n)
    {

        //the idea is to initialize both i and j from 0 and keep swapping with non zero element
        int i = 0;
        for(int j = 0;j<n;j++)
        {
            if(a[j]!=0)
            {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
            }
        }
        return a;
    }
}
