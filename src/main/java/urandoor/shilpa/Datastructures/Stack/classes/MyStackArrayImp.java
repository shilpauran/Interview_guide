package main.java.urandoor.shilpa.Datastructures.Stack.classes;

public class MyStackArrayImp {

    int capacity ;
    int top; // required to track the elements
    int[] a ; //since we are doing an array implementation we use this.
    MyStackArrayImp(int cap)
    {
        this.top = -1;
        this.capacity = cap;
        this.a = new int[cap];
    }

    void push(int element)
    {
        top++;
        a[top] = element;
    }

    int pop()
    {
        int result = a[top];
        top --;
        return result;
    }

    int peek()
    {
        return a[top];
    }

    Boolean isEmpty()
    {
        if(top == -1)
            return  Boolean.TRUE;

        return Boolean.FALSE;
    }

    int size()
    {
        return top+1;
    }
}
