package main.java.urandoor.shilpa.LinkedList.ZAdditionalProblems;

import main.java.urandoor.shilpa.LinkedList.SinglyLinkedList.Node;

public class PairwiseSwapNodesInLL {


    // by swapping the data. i tried this very easy approach in leetcode .
    // but it says time limit exceeded
    Node swap(Node head)
    {

        Node curr = head;
        while(curr != null && curr.next != null)
        {
            int temp = curr.data;
            curr.data = curr.next.data;
            curr.next.data = temp;
        }

        return head;
    }

    // by swapping the links.
    // this is the ideal method
    //but i dint understand properly. so left it
//    https://www.geeksforgeeks.org/pairwise-swap-elements-of-a-given-linked-list-by-changing-links/
}
