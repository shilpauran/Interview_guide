package main.java.urandoor.shilpa.java.LamdaExpressions.classes.InterviewQuestions;

import java.util.Arrays;
import java.util.List;

public class CountWordsStartingWithSpecificLetter {

    public void count()
    {
        //count words starting with a
        List<String> list = Arrays.asList("apple", "banana", "apricot", "avocado", "grape");

        long count = list.stream().filter(s->s.startsWith("a")).count();

        //more fine tuning
        List<String> list1 = Arrays.asList("apple", "banana", "apricot", "Avocado", "grape");
        long count1 = list1.stream().filter(s->s.toLowerCase().trim().startsWith("a")).count();


    }
}
