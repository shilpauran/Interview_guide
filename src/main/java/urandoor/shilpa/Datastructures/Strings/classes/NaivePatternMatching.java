package main.java.urandoor.shilpa.Datastructures.Strings.classes;

public class NaivePatternMatching {

    public void match(String s, String p)
    {
        int n = s.length();
        int m = p.length();
        for(int i = 0 ; i <= n-m ; i++)
        {
            int j ;
            for(j = 0 ; j<m;j++)
            {
                if(s.charAt(j) != p.charAt(j))
                {
                    break;
                }
            }

            if(j == m)
            {
                System.out.println(i);
            }
        }
    }
}
