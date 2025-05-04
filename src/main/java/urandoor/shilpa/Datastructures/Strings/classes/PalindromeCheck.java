package main.java.urandoor.shilpa.Datastructures.Strings.classes;

public class PalindromeCheck {

    public boolean check(String s)
    {
        int i = 0;
        int j = s.length()-1;

       while(s.charAt(i) == s.charAt(j))
       {
           if(i == j || i>j)
           {
               return true;
           }

           i++ ; j--;
       }
       return false;
    }

    public boolean checka(String s)
    {
        int i = 0;
        int j = s.length()-1;
        while(i<j)
        {
            if(s.charAt(i) != s.charAt(j))
            {
               return false;
            }
            i++;j++;
        }
        return true;
    }

}
