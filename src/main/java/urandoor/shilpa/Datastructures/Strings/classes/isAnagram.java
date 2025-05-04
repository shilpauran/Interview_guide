package main.java.urandoor.shilpa.Datastructures.Strings.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class isAnagram {

    //using array
    public boolean isAnagram(String s, String t) {

        if(s.length() != t.length())
        {
            return false;
        }

        int[] alpha = new int[26]; //make it 256 if capitals and other characters also included
        for(int i = 0 ; i < s.length(); i++)
        {
            alpha[s.charAt(i) - 'a'] = alpha[s.charAt(i) - 'a'] + 1;
            alpha[t.charAt(i) - 'a'] = alpha[t.charAt(i) - 'a'] - 1;
        }

        for(int i = 0 ; i< 26 ; i ++)
        {
            if(alpha[i] != 0)
            {
                return false;
            }
        }

        return true;

    }

    //using hashmap
    public boolean isAnagram1(String s, String t)
    {
             if(s.length() != t.length())
                 return false;
             Map<Character,Integer> map = new HashMap<>();

             Integer count = 0;
             for(int i = 0;i <s.length();i++)
             {
                 char curr = s.charAt(i);
                 count = map.getOrDefault(curr,0);
                 map.put(curr, ++count);
             }

             Integer count1  = 0;
             for(int i = 0 ; i<t.length();i++)
             {
                 char curr = t.charAt(i);
                 if(map.containsKey(curr))
                 {
                     Integer value = map.get(curr);
                     map.put(curr, --value);
                     count1 = map.getOrDefault(curr,0);
                     if(count1 < 0)
                     {
                         return false;
                     }
                 }
                 else
                 {
                     return false;
                 }

             }

             List<Integer> values = new ArrayList<>(map.values());

             for(Integer value : values)
             {
                 if(value != null && value != 0)
                 {
                     return false;
                 }
             }

             return true;
    }
}
