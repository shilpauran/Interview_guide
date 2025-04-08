package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class MajorityElement {

    //Moore's voting algorithm
    //This algorithm works in 2 phases
    //algorithm states that we assume the first element is the majority element and store the index of it as result
    // we also start a counter to see how many times it repeats
    // then we start our analysis from the 2nd element and compare with the result element
    //if both are same, increment the count. if not decrement the count
    //if count becomes 0, then make the result as current element index and reset the counter
    //in the 2nd phase just verify if the given element is the majority element

    int major(int[] a, int n)
    {
        int res = 0; int count = 1;

        for(int i = 1 ;i<n;i++)
        {
            if(a[i] == a[res])
            {
                count++;
            }
            else {
                count --;
            }

            if(count == 0)
            {
                res = i;
                count = 1;
            }
        }

        count = 0;
        for(int i =0;i<n;i++)
        {
            if(a[i] == a[res])
            {
                count ++;
            }
        }
        if(count > n/2)
            return a[res];

        return -1;
    }
}
