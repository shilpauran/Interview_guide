package main.java.urandoor.shilpa.LinkedList.DoublyLinkedList;

public class DLLInsertAtEnd {

    DLLNode insertEnd(DLLNode head, int data)
    {

        //create a new node
        DLLNode newNode = new DLLNode(data);

        if(head == null)
        {
            return newNode;
        }
        //traverse to the last
        DLLNode current = head;
        while(current.next != null)
        {
            current = current.next;
        }

        //when current is at the end, make the connection
        current.next = newNode;
        newNode.prev = current;

        return head;


    }
}
