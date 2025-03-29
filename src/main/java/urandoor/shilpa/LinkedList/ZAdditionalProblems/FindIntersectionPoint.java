package main.java.urandoor.shilpa.LinkedList.ZAdditionalProblems;

import main.java.urandoor.shilpa.LinkedList.SinglyLinkedList.Node;

public class FindIntersectionPoint {

    //time complexity O(m+n)
    //2 heads are given , and we need to find the intersection of it.
    Node findIntersection(Node head1, Node head2)
    {

        //check if any heads are null
        if(head1 == null || head2 == null)
        {
            return null;
        }

        Node curr1 = head1;
        Node curr2 =  head2;

        // idea is like floyd's loop detection
        //when the pointers meet thats the point of intersection
        //if they reach the end point without meeting then exchange the points and continue.
        while(curr1 != curr2)
        {
            //update curr1
            curr1 = (curr1 != null) ? curr1.next : head2;
            //update curr2
            curr2 = (curr2 != null) ? curr2.next : head1;
        }

        return curr1; //or curr2 because both will be same at this point
    }
}
