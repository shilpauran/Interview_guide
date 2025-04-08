package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class SubArrayWithGivenSum {

    Boolean sum(int[] a, int n, int givenSum)
    {
        int low = 0 , high = 1 , sum = a[0];
        while(low < high && high < n)
        {
            if(sum < givenSum)
            {
                sum = sum + a[high];
                high ++;
            }
            else if(sum > givenSum)
            {
                sum = sum - a[low];
                low ++;
            }

            if(sum == givenSum)
            {
                return Boolean.TRUE;
            }
        }

        if(sum - a[low] == givenSum)
        {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
