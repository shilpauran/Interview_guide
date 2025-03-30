package main.java.urandoor.shilpa.Datastructures.LinkedList.DoublyLinkedList;

public class DLLDeleteHead {

    DLLNode deleteHead(DLLNode head)
    {

        if(head == null || head.next == null)
        {
            return null;
        }

        head = head.next;
        head.prev = null;

        return head;

        //this is enough

        //actually below approach is also ok but no need to make head.next null. that we do not care
//        DLLNode currentHead = head.next;
//        currentHead.prev = null;
//        head.next = null;
//        head = currentHead;
//
//        return head;
    }
}
