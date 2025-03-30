package urandoor.shilpa.LinkedList.SinglyLinkedList;

import urandoor.shilpa.LinkedList.SinglyLinkedList.SLLNode;
public class BasicImplementation {

    public static void main(String[] args)
    {
        // **Nodes Create చేయడం**
        SLLNode head = new SLLNode(10);
        SLLNode second = new SLLNode(20);
        SLLNode third = new SLLNode(30);

        // **Nodes లింక్ చేయడం**
        head.next = second;
        second.next = third;


        // **Linked List Print చేయడం**
        SLLNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null"); // Output: 10 -> 20 -> 30 -> null


    }
}
