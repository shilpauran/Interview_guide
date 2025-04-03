package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class MaxEvenOddSubarray {

    //keden's algorithm
    //start from the 2nd element and if the previous element nature is different from current element
    //then increment the curr count and update the max length
    //else (if same nature) then reset the curr count.
    int evenOddLength(int[] a, int n)
    {
        int maxLength = 1;
        int curr = 1;
        for(int i = 1; i<n ; i++)
        {
            if((a[i-1] % 2) != (a[i]%2))
            {
                curr ++;
                maxLength = Math.max(maxLength,curr);
            }
            else {
                curr = 1;
            }
        }
        return maxLength;
    }

}
