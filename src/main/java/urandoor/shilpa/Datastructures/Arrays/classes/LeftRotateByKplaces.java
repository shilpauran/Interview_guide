package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class LeftRotateByKplaces {

    int[] rotate(int[] a, int n, int k)
    {
        reverse(a, 0, n-k-1); //till the node before k. but k-1 wont work.
        reverse(a, k, n-1);
        reverse(a, 0, n-1);
    }

    void reverse(int[] a, int low, int high)
    {
        while(low<high)
        {
            int temp = a[low];
            a[low] = a[high];
            a[high] = temp;

            low++;
            high--;
        }
    }
}
