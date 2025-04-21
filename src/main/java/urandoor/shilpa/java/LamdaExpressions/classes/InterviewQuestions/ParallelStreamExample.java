package main.java.urandoor.shilpa.java.LamdaExpressions.classes.InterviewQuestions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ParallelStreamExample {

    public void doWell()
    {

        List<Integer> numbers = Arrays.asList(2, 5, 8, 3, 12, 7, 10, 6);


        numbers.parallelStream().filter(i->i%2 == 0) //find all even numbers
                .map(n-> n* n) //square them
                .sorted(Comparator.reverseOrder()) //sort in descending order
                .forEach(System.out::println); //print them

    }
}
