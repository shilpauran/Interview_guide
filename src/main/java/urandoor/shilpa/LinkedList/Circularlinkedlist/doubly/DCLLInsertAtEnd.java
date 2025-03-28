package main.java.urandoor.shilpa.LinkedList.Circularlinkedlist.doubly;

public class DCLLInsertAtEnd {

    DCLLNode insertAtEnd(DCLLNode head, int data)
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
//        head = newNode; only this line not needed. rest everything same

        return head;

    }
}
