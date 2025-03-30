package main.java.urandoor.shilpa.Datastructures.LinkedList.ZAdditionalProblems;

import main.java.urandoor.shilpa.Datastructures.LinkedList.SinglyLinkedList.Node;

public class FloydsCycleDetection {

    Boolean detectLoop(Node head)
    {

        if(head == null || head.next == null)
        {
            return Boolean.FALSE;
        }

        Node slow = head;// Moves 1 step at a time
        Node fast = head; // Moves 2 steps at a time
        while(fast != null && fast.next != null)
        {
            slow = slow.next;// Move one step
            fast = fast.next.next;// Move two steps
            if(slow == fast)// If they meet, cycle detected
            {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE; // No cycle found
    }
}
