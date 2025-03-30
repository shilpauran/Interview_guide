package main.java.urandoor.shilpa.Datastructures.LinkedList.Circularlinkedlist.singly;

public class SCLLDeleteHead {

    //time complexity O(n)
    //naive solution
    SCLLNode deleteHead (SCLLNode head)
    {
        if(head == null|| head.next == head)
        {
            return null;
        }

        SCLLNode curr = head;
        while(curr.next != head)
        {
            curr =  curr.next;
        }

        curr.next = head.next;
        head = head.next;
        return head;

    }

    //O(1)
    // idea is to copy the data of 2nd node to head and delete the 2nd node
    SCLLNode deleteHeadEfficitient(SCLLNode head)
    {
        // **1. లిస్ట్ ఖాళీ అయితే లేదా ఒకే ఒక్క నోడ్ ఉంటే, Null రిటర్న్ చేయాలి**
        if (head == null || head.next == head) {
            return null;
        }

        // **2. Head మరియు రెండవ నోడ్ మధ్య Data Swap చేయడం**
        head.data = head.next.data;

        // **3. రెండవ నోడ్ ను Delete చేయడం**
        //head.next pointer ni aa next pointer ki pampisthe 2nd node delete avtundi
        head.next = head.next.next;

        return head; // **Head మారదు, కాబట్టి అదే రిటర్న్ చేయాలి**
    }
}
