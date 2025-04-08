package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class MaxAppearingInGivenArray {

    int findMax(int[] a, int[] b)
    {
        int[] mainArray = new int[100];
        for(int i = 0, j = 0 ; i< a.length && j<b.length ; i++, j++)
        {
            mainArray[a[i]] ++;
            mainArray[b[j]] ++;
        }

        int maxIndex = 0;
        for(int i = 0; i< 100; i++)
        {
            if(mainArray[i] > mainArray[maxIndex])
            {
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}
