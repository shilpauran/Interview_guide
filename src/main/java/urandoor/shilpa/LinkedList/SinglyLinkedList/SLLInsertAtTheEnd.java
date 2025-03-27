package urandoor.shilpa.LinkedList.SinglyLinkedList;

public class SLLInsertAtTheEnd {

    SLLNode insertAtEnd(SLLNode head, int x)
    {
        //create new node
        SLLNode newNode = new SLLNode(x);

        // **Linked List ఖాళీగా ఉంటే** → కొత్త Node ను Head గా పెట్టండి
        if (head == null) {
            head = newNode;
            return head;
        }

        //traverse till last
        // **చివరి Node వరకు వెళ్ళండి**
        SLLNode current = head;
        while(current != null)
        {
            current = current.next;
        }

        // **చివరి Node ను కొత్త Node తో లింక్ చేయండి**
        current.next = newNode;

        return head;
    }
}
