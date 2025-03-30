package main.java.urandoor.shilpa.Datastructures.LinkedList.ZAdditionalProblems;

import main.java.urandoor.shilpa.Datastructures.LinkedList.SinglyLinkedList.Node;

public class RemoveLoop {

    // Function to remove a loop in the linked list.
     void removeLoopCorrect(Node head) {

        if(head == null || head.next == null)
        {
            return;
        }

        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
            {
                break;
            }
        }

        if(fast == null || fast.next == null)
        {
            return;
        }

        slow = head;
        while(slow != fast)
        {
            slow = slow.next;
            fast = fast.next;
        }
        //loop again to find the last node of the loop. i.e when fast.next meets slow thats the last node.

        while(fast.next != slow)
        {
            fast = fast.next;
        }

        fast.next = null;
    }

    //chat gpt gave this code. but last that prev and all dint work properly
    void removeLoop(Node head) {
        if (head == null || head.next == null) {
            return;
        }

        Node slow = head;
        Node fast = head;

        // **Step 1: Detect Loop using Floyd’s Algorithm**
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {  // Loop detected
                break;
            }
        }

        // **No loop detected**
        if (fast == null || fast.next == null) {
            return;
        }

        // **Step 2: Find the start of the loop**
        //point slow back to head and fast runs from the meeting point itself
        //the idea is when they meet again, the previous node before that is the point to break the loop
        slow = head;
        Node prev = null;  // To track the node before the loop starts

        while (slow != fast) {
            prev = fast;  // Keep track of the last node before meeting point
            slow = slow.next;
            fast = fast.next;
        }

        // **Step 3: Break the loop**
        prev.next = null;  // Remove the loop by disconnecting the last node
    }


    // Function to remove a loop in the linked list.
    void removeLoopGeeksForGeeks(Node head) {

        if(head == null || head.next == null)
        {
            return;
        }

        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
            {
                break;
            }
        }

        if(slow != fast)
        {
            return;
        }

        slow = head;
        while(slow.next != fast.next)
        {
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = null;
    }


}
