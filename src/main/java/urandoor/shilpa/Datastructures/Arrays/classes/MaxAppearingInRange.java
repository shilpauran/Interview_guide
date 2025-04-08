package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class MaxAppearingInRange {

    int[] freq = new int[101];//check line 12
    int max(int[] a, int[] b)
    {
        //a and b will be in the same length
        for(int i = 0 ; i < a.length ; i++)
        {
            freq[a[i]] ++;
            freq[b[i]+1] -- ; //since we are doing +1 to avoid index out of bound, we declare it as 101
        }

        //find prefix
        int maxFreqIndex = 0; //check line 26
        for(int i = 1 ; i<100; i++)
        {
            freq[i] = freq[i-1] + freq[i];
            if(freq[maxFreqIndex] < freq[i])
            {
                maxFreqIndex = i;
            }
        }

//        //instead doing again in another loop , we can do this in the same above loop
//        //find the max index .
//        int maxFreqIndex = 0;
//        for(int i = 0 ;i<100;i++)
//        {
//            if(freq[i] > freq[maxFreqIndex])
//            {
//                maxFreqIndex = i;
//            }
//        }

        return maxFreqIndex;
    }
}
