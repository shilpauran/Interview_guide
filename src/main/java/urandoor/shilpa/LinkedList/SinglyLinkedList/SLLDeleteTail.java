package urandoor.shilpa.LinkedList.SinglyLinkedList;

public class SLLDeleteTail {

    SLLNode deleteLast(SLLNode head)
    {
        if (head == null) { // **List ఖాళీగా ఉంటే**
            System.out.println("List is Empty!");
            return null;
        }

        if (head.next == null) { // **Linked List లో ఒక్క Node మాత్రమే ఉంటే**
            head = null;
            return null;
        }

        //see here we are going till second last node because, if u go to last node which u have to delete
        //how will u come back to get the .next to make it null.
        //so traverse only till second last node
        SLLNode secondLast = head;
        while (secondLast.next.next != null) { // **చివరి Node ముందు Node వరకు వెళ్ళండి**
            secondLast = secondLast.next;
        }

        secondLast.next = null; // **చివరి Node ను తొలగించండి**

        return head;
    }

//time complexity is O(n) as we will be traversing though out the linked list
}
