package main.java.urandoor.shilpa.Datastructures.LinkedList.Circularlinkedlist.doubly;

public class DCLLInsertAtHead {

    DCLLNode insertAtHead(DCLLNode head, int data)
    {
        //create new node
        DCLLNode newNode =  new DCLLNode(data);

        //check if ll is present
        if(head == null)
        {
            newNode.prev =  newNode;
            newNode.next = newNode;
            return newNode;
        }

        //now keep swapping the references
        newNode.next = head;
        newNode.prev =  head.prev;
        head.prev.next = newNode;
        head.prev = newNode;
        head = newNode;

        return newNode;

    }
}
