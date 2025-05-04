package main.java.urandoor.shilpa.Datastructures.Strings.classes;

public class NaivePatternMatchingDistinct {

    public void match(String s, String p)
    {
        int n = s.length();
        int m = p.length();

        for(int i = 0 ; i <= n-m ;)
        {
            int j;
            for(j=0;j< m;j++)
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
            else if(j == 0)
            {
                i++;
            }
            else {
                i = i+j; //we are doing this, because, already distinct anukunnam kabatti avi already previous
                // elements tho match ainappudu malli avi first element tho match avvaddu. adi check cheyalsina avasaram ledu
            }
        }
    }
}
