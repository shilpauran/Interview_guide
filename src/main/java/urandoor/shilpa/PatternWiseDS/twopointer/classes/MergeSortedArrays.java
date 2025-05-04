package main.java.urandoor.shilpa.PatternWiseDS.twopointer.classes;

public class MergeSortedArrays {

    //this is an extention of two pointer approach. its 3 pointer approach.
    //all three points starts at the end.
    public void merge(int[] a, int m, int[] b, int n) {

        int first = m-1;
        int second = n-1;
        int third;
        for(third = (m+n)-1;third>=0 ; third--)
        {
            if(first >= 0 && second >=0)
            {
                if(a[first]>b[second])
                {
                    a[third] =a[first];
                    first--;
                }
                else
                {
                    a[third] = b[second];
                    second--;
                }
            }

            if(second >=0)
            {
                for(int i = second; i>=0;i--)
                {
                    a[i] = b[i];
                }
            }

        }
    }
}
