package main.java.urandoor.shilpa.Datastructures.Strings.classes;

public class SortTheString {

    //considering the string has unique characters
    public String sort(String s)
    {
        String temp = null;
        String newString = null;
        int[] alpha = new int[26];

        for(int i =0 ; i<s.length();i++)
        {
            alpha[s.charAt(i) - 'a'] = 1;
        }

        for(int i = 0 ; i< 26 ; i++)
        {
            if(alpha[i] > 0)
            {
                newString = temp +(char) + (i+'a');
                temp = newString;

            }
        }
        return newString;
    }
}
