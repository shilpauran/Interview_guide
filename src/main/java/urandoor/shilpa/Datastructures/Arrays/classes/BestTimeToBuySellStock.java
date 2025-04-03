package main.java.urandoor.shilpa.Datastructures.Arrays.classes;

public class BestTimeToBuySellStock {

    int buySell(int[] prices)
    {
        //find the min values and keep updating max
        //because we sell only min and sell at peak. so where ever the profit is max, we update the max value

        int min = Integer.MAX_VALUE;
        int max = 0;

        for(int price : prices)
        {
            if(price < min)
            {
                min = price;
            }
            else if( price - min > max)
            {
                max = price - min;
            }
        }

        return max;
    }
}
