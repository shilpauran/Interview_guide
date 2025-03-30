package main.java.urandoor.shilpa.Datastructures.LinkedList.Circularlinkedlist.singly;

public class SCLLDeleteKthNode {

    //assuption is k is always less than or equal to length of the list
    SCLLNode deleteKthNode(SCLLNode head, int k)
    {

        SCLLDeleteHead deleteHead = new SCLLDeleteHead();
        if(head == null)
            return head;

        // if only 1 node, then delete that node. check the deleteHead function to delete that
        if(k == 1)
        {
            return deleteHead.deleteHeadEfficitient(head);
        }

        // next navigate till 2 nodes before k
        SCLLNode curr = head;
        for(int i = 0 ; i< k-2 ; i++)
        {
            curr = curr.next;
        }

        // and delete the kth node
        curr.next = curr.next.next;

        return head;


//        SCLLNode curr = head;
//        for(int i = 1 ; i< k-1 ; i++)
//        {
//            curr = curr.next;
//        }
//
//        curr.next = curr.next.next;
//
//        return head;

    }
}
