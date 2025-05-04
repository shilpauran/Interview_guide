package main.java.urandoor.shilpa.Datastructures.Strings.classes;

public class LeftMostRepeating {


    public int leftMostBest(String s)
    {
        boolean[] visited = new boolean[256];
        int resultIndex = -1 ;
        for(int i = s.length() -1 ; i>=0;i--)
        {
            //if already visited then just remember that index
            if(visited[s.charAt(i)])
            {
                resultIndex = i;
            }
            else {
                visited[s.charAt(i)] = true;
            }
        }

        return resultIndex;
    }

    //this also good. but need 2 traversals of string.
    public static int leftMost(String s)
    {
        int[] alpha = new int[256]; //might contain

        for(int i = 0 ; i < s.length() ; i ++)
        {
            alpha[s.charAt(i)]++ ;
        }

        for(int i = 0 ; i < s.length() ; i ++)
        {
            if(alpha[s.charAt(i)] > 1)
            {
                return i;
            }
        }

        return -1;
    }

}
