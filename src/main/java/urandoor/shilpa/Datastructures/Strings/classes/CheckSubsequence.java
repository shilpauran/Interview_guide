package main.java.urandoor.shilpa.Datastructures.Strings.classes;

public class CheckSubsequence {

    public boolean check(String s1, String s2)
    {

        int i = 0;
        int j = 0;

        while(i < s1.length() && j< s2.length())
        {
            if(s2.charAt(i) == s1.charAt(j))
            {
                i++;
                j++;
            }
            else{
                i++;
            }
        }

        return j == s2.length();

    }

    //the idea is to make sure, if i reaches s1 without j reaching s2 then we return false. meaning the string is not found.
    // at last we check if the j is same as s2 length. if it is it means, it has checked all the letters. and all letters are found. if not then it returns false.
    public boolean check1(String s1, String s2)
    {
        if(s1.length()<s2.length())
        {
            return false;
        }
        int j =0;
        for(int i = 0; (i<s1.length() && j < s2.length()); i++)
        {
            if(s1.charAt(i) == s2.charAt(j))
            {
                j++;
            }
        }

        return j ==s2.length();
    }
}
