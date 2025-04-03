package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class Leaders {

//    int a ={ 1, 10, 3, 5, 4, 2}

    int[] leaders(int[] a, int n)
    {
       System.out.println(a[n-1]); //always first element is leader

        //traverse from right and all we need to do is check if the curr element is greater than leader
        int leader = a[n-1];
        for(int i= n-2 ; i>=0;i--)
        {
            if(a[i] > leader)
            {
                System.out.println(a[i]); //print the leader
                leader = a[i]; // update the leader
            }
        }

     return a; //this will return the leaders in the reverse order. if you want , then reverse the list and return
    }
}
