package main.java.urandoor.shilpa.LinkedList.DoublyLinkedList;

public class DLLInsertAtTheBeggining {

    DLLNode insertBegin(DLLNode head, int value)
    {
        //create node
        DLLNode newNode = new DLLNode(value);

        //check if ll is not null
        if(head == null)
        {
            return newNode;
        }
        //make connection
        newNode.next = head;
        head.prev = newNode;

        //make new node head;
        head = newNode;

        return head;
    }

}
