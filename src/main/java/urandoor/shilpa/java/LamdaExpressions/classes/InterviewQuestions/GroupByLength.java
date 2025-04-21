package main.java.urandoor.shilpa.java.LamdaExpressions.classes.InterviewQuestions;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByLength {

    public void length()
    {
        List<String> list = Arrays.asList("a", "ab", "abc", "abcd", "abcde");

        Map<Integer,List<String>> map = list.stream().collect(Collectors.groupingBy(String::length));

        map.forEach((key,value)->{
            System.out.println(key);
            System.out.println(value);
        });
    }
}
