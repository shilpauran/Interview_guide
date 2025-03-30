package main.java.urandoor.shilpa.Datastructures.LinkedList.ZAdditionalProblems;

import main.java.urandoor.shilpa.Datastructures.LinkedList.SinglyLinkedList.Node;

public class SegregateEvenandOdd {

    //time complexity O(n) and auxillary space O(1)
    //4 pointer approach. we will use 4 pointers,
    //estart - to remember the head
    //eodd - to remeber where odd is starting
    //eEnd - navigates through the list
    //oEnd = navigates through odd list
    Node segregate (Node head)
    {
        if(head == null || head.next == null)
        {
            return head;
        }

        //creating dummy nodes for even and odd
        Node eStart = new Node(0);
        Node oStart = new Node(0);

        //initializing end nodes also at the start point to traverse
        Node eEnd = eStart;
        Node oEnd = oStart;

        Node curr = head;
        while(curr != null)
        {
            //check whether even or odd
            if(curr.data % 2 == 0)
            {
                //add the current node to the even list
                //move even End pointer. and update the end
                eEnd.next = curr;
                eEnd = eEnd.next;
            }
            else {

                //add the current node to the odd list
                //move odd end pointer to update the end
                oEnd.next = curr;
                oEnd = oEnd.next;
            }

            curr = curr.next;
        }

        //connect the even list to odd list
        eEnd.next = oStart.next;

        //make the oEnd connection to null
        oEnd.next = null;

        //mark the new head
        head = eStart.next;


        return head;
    }


    //this program can also be done with 2 pointer approach or 2 lists but needs more traversals and auxillary space
}
