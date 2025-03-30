package main.java.urandoor.shilpa.Datastructures.LinkedList.ZAdditionalProblems;

import main.java.urandoor.shilpa.Datastructures.LinkedList.SinglyLinkedList.Node;

public class ReverseLinkedList {

    //time complexity is O(n)
    Node reverse(Node head)
    {
        if(head == null || head.next == null)
        {
            return head;
        }

        //we start using 2 pointers prev and curr and store the next reference as temporary
        //then reverse the links as we move forward
        Node prev = null;
        Node curr = head;
        while(curr != null)
        {
            Node tempNext = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNext;
        }

        head = prev;
        return head;

    //this is my approach. slightly different
//        //if head is null or linked list has only 1 node then return that head
//        if(head == null || head.next == null)
//        {
//            return head;
//        }
//
//        //idea is to use 2 pointers prev and current and store the temp as next of current
//        Node prev = head;
//        Node curr = prev.next;
//
//        while(curr != null)
//        {
//            Node temp = curr.next;
//            curr.next = prev;
//            prev = curr;
//            curr = temp;
//        }
//
//        return prev; //previous is the new head
    }
}
