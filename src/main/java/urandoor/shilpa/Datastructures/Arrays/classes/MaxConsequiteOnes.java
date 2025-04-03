package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class MaxConsequiteOnes {

    int maxOnes(int[] a , int n)
    {
        int currMax = 0; int realMax = 0;
        for(int i = 0;i<n;i++)
        {
            if(a[i] == 1)
            {
                currMax++;
            }
            else if(currMax > realMax)
            {
                realMax = currMax;
                currMax = 0;
            }
        }

        return Math.max(currMax, realMax);
    }
}
