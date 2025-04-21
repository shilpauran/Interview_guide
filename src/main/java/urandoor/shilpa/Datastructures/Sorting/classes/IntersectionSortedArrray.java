package main.java.urandoor.shilpa.Datastructures.Sorting.classes;

public class IntersectionSortedArrray {

    //two pointer approach
    int[] findIntersection(int[] a, int[] b, int m, int n)
    {
        int i = 0 , j = 0 , k = 0;
        int[] c = new int[Math.min(m,n)];

        while(i<m && j<n)
        {
            if(i> 0 && a[i] == a[i-1])
            {
                i++;
            }
            if(a[i]<b[j])
            {
                i++;
            }
            else if(a[i] > b[j])
            {
                j++;
            }
            else {

                c[k] = a[i];
                i++;j++;k++;
            }
        }
    }
}
