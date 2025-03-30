package main.java.urandoor.shilpa.Datastructures.LinkedList.ZAdditionalProblems;

import main.java.urandoor.shilpa.Datastructures.LinkedList.SinglyLinkedList.Node;

public class FindMiddle {


    //efficient approach
    //Needs only 1 traversal
    //based on idea of slow and fast pointer references
    //slow pointer moves once and fast pointer moves twice. i.e 1:2 ration
    // so fast is at the end, slow is in the middle
    Node findMiddleSingleTraversal(Node head)
    {
        //check if head is null then return null
        if(head == null)
        {
            return null;
        }

        // assign slow and fast nodes
        Node slow = head;
        Node fast = head;

        //This condition is written to satisfy the odd / even number of nodes
        //in case of even , the fast node will be pointing to null at last
        //in case of odd, the fast node will be pointing to last node.
        //so we need to check both conditions
        //then increment slow and fast accordingly. at the end of the loop, slow is at the middle,
        //so we return the middle node
        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow; //returns the middle node
    }


    //Naive approach
    //this approach needs two traversals of the linked list.
    Node findMiddle(Node head)
    {
        //check if head is null
        if(head == null)
        {
            return null;
        }

        //now start the count from 0 and count total number of nodes
        int count = 0;
        Node curr;
        for(curr = head; curr != null ; curr =  curr.next)
        {
            count ++ ;
        }

        // now count till middle element to find the node
        curr = head;
        for(int i = 0 ; i<count/2 ; i++)
        {
            curr = curr.next;
        }


        //return the current
        return curr;

    }
}
