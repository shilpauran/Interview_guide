package urandoor.shilpa.LinkedList.SinglyLinkedList;

import urandoor.shilpa.LinkedList.SinglyLinkedList.SLLNode;
public class SLLInsertAtTheBeginning {

    static SLLNode insertAtBeginning(SLLNode head, int x)
    {
        //first create node
        SLLNode newNode = new SLLNode(x);

        //check if head is not null
        if(head != null)
        {
            newNode.next = head;
            head = newNode;
        }

        return head;
    }
}
