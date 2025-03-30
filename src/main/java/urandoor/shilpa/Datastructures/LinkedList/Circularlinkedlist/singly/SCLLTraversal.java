package main.java.urandoor.shilpa.Datastructures.LinkedList.Circularlinkedlist.singly;


public class SCLLTraversal {

    SCLLNode traverse(SCLLNode head)
    {
        if(head == null)
        {
            return null;
        }

        SCLLNode curr = head;
        //use a do while loop , instead of while, because we need to find out when
        //the list ends, since its circular it never ends.
        // tail.next = head so use do while
        do {
            curr = curr.next;
        }while(curr != head);

        return head;
    }
}
