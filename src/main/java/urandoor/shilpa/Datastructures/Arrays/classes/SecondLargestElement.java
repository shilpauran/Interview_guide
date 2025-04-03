package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class SecondLargestElement {


    int secondLarge(int[] a, int n)
    {
        int second = - 1 ;
        int first = 0 ;

        for(int i = 0 ; i< n ; i++)
        {
            if(a[i] > a[first])
            {
                second = first;
                first = i ;
            }
            else if (a[i] < a[first]) //or a[i] != a[first] // because we ignore equal to case
            {
                if(second == -1 || a[i]>a[second])
                {
                    second = i;
                }
            }
        }

        return second;
    }
}
