package main.java.urandoor.shilpa.Datastructures.Strings.classes;

public class FiindFrequenciesInLowerCaseString {


    //O(n)
    public void find(String s)
    {
        int[] alpha = new int[26];

        for(int i = 0; i< s.length();i++)
        {
            alpha[s.charAt(i)- 'a']++;
        }

        for(int i = 0; i< 26;i++)
        {
            if(alpha[i] > 0) {
//                System.out.println(alpha[i]); // but this will give only no.of occurance. but we also want which alphabet belongs to this occurance
                System.out.println( (char) (i+'a') + "has occured" + alpha[i] + "times"); // e has occured 4 times
            }
        }
    }
}
