package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class EquilibriumPoint {

    Boolean findEquilibrium(int[] a, int n)
    {
        //find total
        int total = 0;
        for(int i = 0; i<n ; i++)
        {
            total += a[i];
        }

        int leftSum = 0, rightSum = total;
        for(int i=0;i<n;i++)
        {
            rightSum = rightSum - a[i];
            if(leftSum == rightSum)
            {
                return Boolean.TRUE;
            }

            leftSum += a[i];
        }

        return Boolean.FALSE;
    }
}
