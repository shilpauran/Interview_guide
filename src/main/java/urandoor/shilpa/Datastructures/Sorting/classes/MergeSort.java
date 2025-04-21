package main.java.urandoor.shilpa.Datastructures.Sorting.classes;

public class MergeSort {

    int[] mergeSort(int[] a ,int left, int right)
    {
        //find mid
        int mid = (left+right) /2;

        //start recursion
        mergeSort(a,left,mid);
        mergeSort(a,mid+1,right);

        //create left and right
        int n1 = mid-left+1;
        int n2 = right - mid;
        int[] la = new int[n1];
        int[] ra = new int[n2];

        //fill left and right
        for(int i = 0; i<n1 ; i++)
        {
            la[i] = a[left+i];
        }
        for(int i = 0; i<n2 ; i++)
        {
            ra[i] = a[mid+1+i];
        }

        //start merging
        int i = 0, j = 0, k = left;

        while(i<n1 && j < n2)
        {
            if(la[i] <= ra[j])
            {
                a[k] = la[i];
                i++;
            }
            else {
                a[k] = ra[j];
                j++;
            }
            k++;
        }

        //fill the remaining elements
        while(i<n1)
        {
            a[k] = la[i];
            i++; k++;
        }

        while(j<n1)
        {
            a[k] = ra[j];
            j++; k++;
        }

        return a;
     }
}
