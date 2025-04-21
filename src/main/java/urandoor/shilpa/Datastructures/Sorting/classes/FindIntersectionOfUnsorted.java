package main.java.urandoor.shilpa.Datastructures.Sorting.classes;

import java.util.*;

public class FindIntersectionOfUnsorted {

    //using set
    int[] find(int[] a, int[] b , int m, int n)
    {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();

        for(int i : a)
        {
            set.add(i);
        }

        for(int i: b)
        {
            if(set.contains(i))
            {
                list.add(i);
                set.remove(i);
            }
        }

        return list.stream().mapToInt(i->i).toArray();
    }

    //using map
    int[] find1(int[] a, int[] b , int m, int n)
    {
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for(int i : a)
        {
            if(!map.containsKey(i))
            {
                map.put(i,1);
            }
        }

        for(int i:b)
        {
            if(map.containsKey(i) && map.get(i) == 1)
            {
                list.add(i);
                map.put(i,0);
            }
        }

        return list.stream().mapToInt(i->i).toArray();
    }
}
