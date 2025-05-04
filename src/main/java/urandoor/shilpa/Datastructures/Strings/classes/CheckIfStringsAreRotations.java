package main.java.urandoor.shilpa.Datastructures.Strings.classes;

public class CheckIfStringsAreRotations {

    public boolean check(String s, String goal)
    {
        if(s.length() != goal.length())
        {
            return false;
        }
        return (s+s).contains(goal);
    }
}
