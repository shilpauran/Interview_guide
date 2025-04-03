package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class FrequenciesOfEachElement {

    void freq(int[] a, int n)
    {
        int low = a[0];
        int freq = 0;
        for(int i = 1 ; i < n;i++)
        {
            if(a[i]!= low)
            {
                System.out.println("frequency of " + low + " is " + freq);
                low = a[i];
                freq = 0;
            }
            else {
                freq++;
            }
        }
        System.out.println("frequency of " + low + " is " + freq); //print the last left statement
    }


//    class Codechef
//    {
//        public static void main (String[] args) throws java.lang.Exception
//        {
//
//            int a[] = {1,1,3,4,5,5,5};
//            int n = 7 ;
//
//            int low = a[0];
//            int freq = 1;
//            for(int i = 1 ; i < n;i++)
//            {
//                if(a[i]!= low)
//                {
//                    System.out.println("frequency of " + low + " is " + freq);
//                    low = a[i];
//                    freq = 1;
//                }
//                else {
//                    freq++;
//                }
//            }
//            System.out.println("frequency of " + low + " is " + freq);
//
//        }
//
//    }
}
