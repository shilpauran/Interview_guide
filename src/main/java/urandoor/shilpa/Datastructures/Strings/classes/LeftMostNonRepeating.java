package main.java.urandoor.shilpa.Datastructures.Strings.classes;

import java.util.Arrays;

public class LeftMostNonRepeating {

    //efficient with O(n) and single loop of string. the other loop is just a constant loop of 256
    public static int leftMost(String s)
    {
        int[] alpha = new int[256];
        Arrays.fill(alpha,-1);
        for(int i = 0 ; i< s.length() ; i++)
        {
            if(alpha[s.charAt(i)] == -1)
            {
                //first occurance. then store the actual index
                alpha[s.charAt(i)] = i;
            }
            else
            {
                //repeating. so just make it something else
                alpha[s.charAt(i)] = -2; //should not make it -1 because already -1 means it is not available.
            }
        }

        //constant operation of 256 times
        int res = Integer.MAX_VALUE;
        for(int i = 0 ; i < 256;i++)
        {
            if(alpha[i] >= 0)
            {
                //means it can be potential last non-repeating element. but we should find the min one. which is left more non-repeating element.
                res = Math.min(res, alpha[i]);
            }
        }

        if(res == Integer.MAX_VALUE)
        {
            res = -1;
        }

        return res;
    }

    //below also ok. but 2 loops of string
    public static int leftMostEle(String s)
    {
        int[] alpha = new int[256];
        for(int i = 0 ; i < s.length() ; i++)
        {
            alpha[s.charAt(i)]++;
        }
        for(int i =0 ; i < s.length();i++)
        {
            if(alpha[s.charAt(i)] == 1)
            {
                return i;
            }
        }

        return -1;
    }
}
