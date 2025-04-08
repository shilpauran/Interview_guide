package main.java.urandoor.shilpa.Datastructures.Stack.classes;

import main.java.urandoor.shilpa.Datastructures.LinkedList.SinglyLinkedList.Node;

public class MyStackLLImp {

    Node head ;

    int size;

    MyStackLLImp()
    {
        this.head = null;
        size = 0;
    }

    void push(int data)
    {
        Node temp = new Node(data); //create new node
        temp.next = head; //link next node
        head = temp; //make the node head
        size++; //increment the size
    }

    int pop()
    {
        if(head == null)
        {
            //if stack is empty then we cannot pop
            //print some error message.
            return Integer.MAX_VALUE; //can be some exception
        }
        int value = head.data;
        head = head.next;
        return value;
    }

    int peek()
    {
        if(head == null)
        {
            //if stack is empty then we cannot peek
            //print some error message.
            return Integer.MAX_VALUE;//can be some exception
        }
        return head.data;
    }
    int size()
    {
        return size;
    }
    Boolean isEmpty()
    {
        if(head == null)
        {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
