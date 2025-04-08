package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class MinimumConsFlip {

    //in binary array
    void flip(int[] a, int n)
    {
        if(n == 1)
        {
            System.out.println("no flip needed");
        }
        for(int i = 1;i < n;i++)
        {
            // if same just move on till u find the 2nd group elements
            if(a[i-1] != a[i])
            {
                //checks for 2nd group or not. because we do not flip the 1st group
                if(a[i] != a[0])
                {
                    System.out.println("From" + i + "to ");
                }
                //if it is not the 2nd group then it must be already ended at i-1
                else {
                    System.out.print(i-1);
                }

            }

            //edge case where the 2nd group flip must be closed
            if(a[n-1] != a[0])
            {
                System.out.println(n-1);
            }
        }
    }
}
