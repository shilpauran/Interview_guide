package main.java.urandoor.shilpa.Datastructures.LinkedList.DoublyLinkedList;

public class DLLDeleteLast {

    DLLNode deleteLast(DLLNode head)
    {

        if(head == null || head.next == null)
        {
            return null;
        }

        //traverse till last
        DLLNode current =  head;
        while(current.next != null)
        {
            current  =  current.next;
        }

        //and then once u find the last node, come back 1 step and delete the link to next (last)
        current.prev.next = null;
        return head;
    }
}
