package main.java.urandoor.shilpa.LinkedList.Circularlinkedlist.singly;

public class SCLLInsertAtEnd {

    //O(n) time complexity
    SCLLNode insertEnd(SCLLNode head, int data)
    {
        //create node
        SCLLNode newNode = new SCLLNode(data);

        if(head ==  null)
        {
            newNode.next = newNode;
            return newNode;
        }

        //traverse through the list
        SCLLNode curr = head;
        while(curr.next != head)
        {
            curr =  curr.next;
        }
        newNode.next = head;
        curr.next = newNode;

        return head;
    }

    //O(1) time complexity
    //actual 10 -> 20 -> 30 ->10
    //add as 2nd node : 10 -> 40 -> 20 -> 30 -> 10
    //swap the data : 40 -> 10 -> 20 -> 30 -> 10
    //make 2nd node as head: 10-> 20 -> 30 -> 40 -> 10
    SCLLNode insertEndEfficient(SCLLNode head, int data)
    {
        //create node
        SCLLNode newNode = new SCLLNode(data);

        if(head ==  null)
        {
            newNode.next = newNode;
            return newNode;
        }

        //insert new node in the 2nd position
        newNode.next = head.next;
        head.next = newNode;

        // swap the data
        int tempData =  head.data;
        head.data = newNode.data;
        newNode.data = tempData;

        //and make newNode as head
        head =  newNode;

        return head;
    }
}
