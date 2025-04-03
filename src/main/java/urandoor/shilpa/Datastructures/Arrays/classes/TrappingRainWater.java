package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class TrappingRainWater {

    int trap(int[] a, int n)
    {
        int left = 0;
        int right = n-1;

        int leftMax = 0; int rightMax = 0 ;

        int trappedWater = 0;

        while(left < right)
        {
            if(a[left] < a[right])
            {
                if(a[left] >= leftMax)
                {
                    leftMax = a[left];
                }
                else {
                    trappedWater = trappedWater + (leftMax - a[left]);
                }
                left ++ ;
            }
            else {

                if(a[right] >= rightMax)
                {
                    rightMax = a[right];
                }
                else {
                    trappedWater = trappedWater + (rightMax - a[right]);
                }
                right ++ ;

            }
        }

        return trappedWater;
    }
}
