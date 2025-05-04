package main.java.urandoor.shilpa.PatternWiseDS.twopointer.classes;

//https://leetcode.com/problems/move-zeroes/description/
public class MoveZeros {

        public void moveZeroes(int[] a) {

            int left = 0 ;
            for(int right = 0; right < a.length;right++)
            {
                if(a[right] != 0)
                {
                    int temp = a[right];
                    a[right] = a[left];
                    a[left] = temp;
                    left++;
                }
            }

        }
}
