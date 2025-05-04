package main.java.urandoor.shilpa.Datastructures.Strings.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnagramSearch {
        public List<Integer> findAnagrams(String s, String p) {

            List<Integer> anagrams = new ArrayList<>();

            if(p.length() > s.length())
            {
                return anagrams;
            }
            int[] csw = new int[26];
            int[] cp = new int[26];

            //initial window and freqency of pattern
            for(int i = 0; i< p.length(); i++)
            {
                csw[s.charAt(i) - 'a']++;
                cp[p.charAt(i) - 'a']++;
            }

            //sliding window
            for(int i=p.length(); i<s.length(); i++)
            {
                if(Arrays.equals(csw,cp))
                {
                    anagrams.add(i-p.length());
                }
                csw[s.charAt(i) - 'a']++;
                csw[s.charAt(i-p.length()) - 'a']--;
            }

            //final window
            if(Arrays.equals(csw,cp))
            {
                anagrams.add(s.length()-p.length());
            }

            return anagrams;
        }
}
