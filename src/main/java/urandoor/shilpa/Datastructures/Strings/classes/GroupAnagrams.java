package main.java.urandoor.shilpa.Datastructures.Strings.classes;

import java.util.*;

public class GroupAnagrams {

        public List<List<String>> groupAnagrams(String[] strs) {

            Map<String,List<String>> map = new HashMap<>();

            for(String str:strs)
            {
                int[] charArray = new int[26];
                for(int i= 0; i<str.length(); i++)
                {
                    charArray[str.charAt(i) -'a']++;
                }

                String chars = Arrays.toString(charArray);
                if(map.containsKey(chars))
                {
                    map.get(chars).add(str);
                }
                else
                {
                    List<String> anagrams = new ArrayList<>();
                    anagrams.add(str);
                    map.put(chars,anagrams);
                }
            }

            return new ArrayList<>(map.values());

        }


}
