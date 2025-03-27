package urandoor.shilpa.LinkedList.SinglyLinkedList;

public class SLLDeleteHead {

    SLLNode deleteHead(SLLNode head)
    {
        //check if head is null
        if(head == null)
        {
            return null;
        }

        //
        if(head.next != null)
        {
           head = head.next; // u can also just return head.next
        }

        return head;

        //delete node - no need to worry about that. because java takes care of memory deallocation

    }

    //time complexity is O(n)
}
