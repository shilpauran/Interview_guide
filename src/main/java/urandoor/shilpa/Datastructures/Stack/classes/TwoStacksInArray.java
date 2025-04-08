package main.java.urandoor.shilpa.Datastructures.Stack.classes;

public class TwoStacksInArray {

    int[] a ;
    int capacity;
    int top1 , top2;

    TwoStacksInArray(int cap)
    {
        this.capacity = cap;
        this.top1 = -1;
        this.top2 = cap;
    }

    Boolean push1(int data)
    {
        if(top1 < top2-1)
        {
            top1++;
            a[top1] = data;
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    Boolean push2(int data)
    {
        if(top2>top1+1)
        {
            top2--;
            a[top2] = data;
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    Integer pop1()
    {
        if(top1 > -1)
        {
            int temp = a[top1];
            top1 --;
            return temp;
        }
        return null;
    }

    Integer pop2()
    {
        if(top2 < capacity)
        {
            int temp = a[top2];
            top2++;
            return temp;
        }
        return null;
    }

    int size1()
    {
        return top1+1;
    }

    int size2()
    {
        return capacity - top1;//check once
    }
}
