package main.java.urandoor.shilpa.LinkedList.ZAdditionalProblems;

import main.java.urandoor.shilpa.LinkedList.SinglyLinkedList.Node;

public class RemoveDuplicatesFromSortedLL {

    Node removeDuplicates(Node head){


        //we use a pointer called current and keep checking if the next one is same or not

        if(head == null|| head.next == null)
        {
            return head;
        }

        Node curr = head;
        while(curr != null && curr.next != null)
        {
            if(curr.data == curr.next.data)
            {
                curr.next = curr.next.next;
                //but geeksforgeeks gave below line as well. but chatgpt corrected
//                curr = curr.next; //if there are multiple duplicates then this wont work. it will not check everything.
            }
            else {
                curr = curr.next;
            }
        }

        return head;


        //my approach
//        if(head == null|| head.next == null)
//        {
//            return head;
//        }
//
//        Node prev = head;
//        Node curr = head;
//        while(curr != null)
//        {
//            if(prev.data == curr.data)
//            {
//                curr = curr.next;
//            }
//            else {
//
//                prev.next = curr;
//                prev = curr;
//                curr = curr.next;
//            }
//        }
//
//
//        return head;
    }
}
