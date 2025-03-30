package main.java.urandoor.shilpa.Datastructures.LinkedList.ZAdditionalProblems;


import main.java.urandoor.shilpa.Datastructures.LinkedList.SinglyLinkedList.Node;

import java.util.HashSet;

public class DetectLoop {

    //time complexity = O(n)
    //but requires modification for the structure of ll
    Boolean detectLoopVisited(VisitedNode head)
    {

        if(head == null)
        {
            return Boolean.FALSE;
        }

        // as we traverse, we keep marking the current node as visited.
        //if we encounter already visited node then that means there is a loop
        VisitedNode curr = head;
        do {
            curr.visited = Boolean.TRUE;
            curr = (VisitedNode) curr.next;
        }while(curr != null && Boolean.FALSE.equals(curr.visited));

        // if there is no loop then current would have been stopped at null at the end. so we return false
        if(curr == null)
        {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }


    //time complexity = O(n)
    // no need to modify the structure of LL
    // but needs auxillary space
    Boolean detectLoop(Node head)
    {

        if(head == null || head.next == null)
        {
            return Boolean.FALSE;
        }

        Node dummy = new Node(100);

        Node curr = head;

        while(curr != null)
        {
            if(curr.next == dummy)
            {
                return Boolean.TRUE;
            }
            Node temp = curr.next;
            curr.next = dummy;
            curr = temp;
        }

        return Boolean.FALSE;

    }


    //another approach is to put in HashSet all the visited nodes
    //O(n) time complexity and O(n) auxillary space
    Boolean detectLoopHashSet(Node head)
    {
        if(head == null || head.next == null)
        {
            return Boolean.FALSE;
        }

        HashSet<Node> hashSet = new HashSet<>();
        Node curr;
        for(curr = head; curr != null ; curr = curr.next)
        {
            if(hashSet.contains(curr))
            {
                return Boolean.TRUE;
            }
            hashSet.add(curr);
        }

        //if dint find anything then return false
        return Boolean.FALSE;
    }

}

