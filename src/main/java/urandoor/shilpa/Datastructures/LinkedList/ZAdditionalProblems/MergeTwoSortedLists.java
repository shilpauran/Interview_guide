package main.java.urandoor.shilpa.Datastructures.LinkedList.ZAdditionalProblems;

import main.java.urandoor.shilpa.Datastructures.LinkedList.SinglyLinkedList.Node;

public class MergeTwoSortedLists {

    //time complexity O(m+n) where m is length of a and n is length of b
    //key is to use a tail pointer
    Node merge(Node a, Node b)
    {
        if(a == null)
        {
            return b;
        }
        if(b == null)
        {
            return a;
        }

        Node head = null;
        Node tail = null;
        //initialize head and tail
        if(a.data <= b.data)
        {
            head = tail = a;
            a = a.next;
        }
        else {
            head = tail = b;
            b = b.next;
        }

        //keep moving the tail and building the links
        while(a != null && b != null)
        {
            if(a.data<= b.data)
            {
                tail.next = a;
                tail = a ;
                a = a.next;
            }
            else {
                tail.next = b;
                tail = b;
                b = b.next;
            }
        }

        if(a == null)
        {
            tail.next = b;
        }
        else {
            tail.next = a ;
        }

        return head;
    }
}
