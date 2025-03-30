package main.java.urandoor.shilpa.Datastructures.LinkedList.ZAdditionalProblems;

import main.java.urandoor.shilpa.Datastructures.LinkedList.SinglyLinkedList.Node;

import java.util.Optional;

public class FindNthNodeFromEnd {


    //effective approach
    void findNthNodeTwoPointerApproach(Node head, int n)
    {
        if(head == null)
        {
            System.out.println(Optional.empty());//prints null
            return;
        }

        Node first = head;
        for(int i =0; i<n; i++)
        {
            //if ll has less no.of nodes than n. then we just dont print anything. we return
            if(first == null)
            {
                System.out.println(Optional.empty());
                return;
            }
            first = first.next;
        }

        Node second ;
        for(second =  head; first != null ; first = first.next)
        {
            second = second.next;
        }

        System.out.println(second.data);
    }
    //ll is traversed twice. though time complexity is same time taken will be more
    void findNthNodeFromEnd(Node head, int n)
    {
        if(head == null)
        {
            System.out.println(Optional.empty());//prints null
            return;
        }

        int count = 0;
        //return count of the ll
        Node curr ;
        for(curr = head; curr != null ;curr = curr.next)
        {
            count ++ ;
        }

        //if linked list length is less than n
        if(count < n)
        {
            System.out.println(Optional.empty());//prints null
        }

        //get the position of the node which needs to be printed
        int printNode = count - n;
        curr = head;

        //traverse till that node and update the current and print
        for(int i = 1 ; i<printNode+1 ; i++)
        {
            curr = curr.next;
        }

        // this is written by me. this might also be correct
//        for(int i = 0; i<=printNode; i++ )
//        {
//            curr = curr.next;
//        }
        System.out.println(curr.data);


    }
}
