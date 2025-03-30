package main.java.urandoor.shilpa.Datastructures.LinkedList.ZAdditionalProblems;


import main.java.urandoor.shilpa.Datastructures.LinkedList.SinglyLinkedList.Node;

//10 20 30
//insert 25
// 10 20 25 30
//singly linked list already sorted
public class InsertSortedLinkedList {

    //time complexity O(value)
    // if value to be inserted in the linked list at the end then its O(n)
    Node insertSorted(Node head, int value)
    {
        //create the node
        Node newNode = new Node(value);

        //if head is null then return the new node
        if(head == null)
        {
            return newNode;
        }

        //insert as first node if the head data is larger than given value
        if(value < head.data)
        {
            newNode.next = head;
            return newNode;
        }

        Node curr = head;
        while(curr.next != null && curr.next.data < value)
        {
            curr = curr.next;
        }

        newNode.next = curr.next;
        curr.next = newNode;

        return head;

    }

    //my way
    Node insertSortedMyWay(Node head, int value)
    {
        //create the node
        Node newNode = new Node(value);

        if(head == null)
        {
            return newNode;
        }
        else if(head.next == null) {

            if(head.data<value) {
                newNode.next = head;
                return newNode;
            }
            else {
                head.next = newNode;
                return head;
            }
        }
        //loop and compare where the data fits
        Node curr = head;

        while(curr.next.data < value)
        {
            curr = curr.next;
        }

        newNode.next = curr.next;
        curr.next = newNode;

        return head;
    }



}
