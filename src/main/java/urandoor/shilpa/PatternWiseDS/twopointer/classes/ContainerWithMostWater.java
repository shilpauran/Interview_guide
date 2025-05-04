package main.java.urandoor.shilpa.PatternWiseDS.twopointer.classes;

public class ContainerWithMostWater {

        public int maxArea(int[] height) {

            int left = 0 ; int right = height.length - 1;
            int maxArea = 0 ;
            //initialize two pointer left and right.
            //find the area of water between each interval. a = w*h - h is min height. because if we use max height then water will over flow.
            //then update the max area accordingly.
            //if left height is less than right . move left. if not move right
            while(left <= right)
            {
                int width = right - left;
                int minHeight = Math.min(height[left],height[right]);
                int area = width * minHeight;
                maxArea = Math.max(area, maxArea);
                if(height[left] < height[right])
                {
                    left++;
                }
                else
                {
                    right--;
                }
            }

            return maxArea;

        }
}
