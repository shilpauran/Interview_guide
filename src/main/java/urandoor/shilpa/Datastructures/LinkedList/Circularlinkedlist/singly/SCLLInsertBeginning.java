package main.java.urandoor.shilpa.Datastructures.LinkedList.Circularlinkedlist.singly;

public class SCLLInsertBeginning {

    //O(n) time Complexity
    SCLLNode insertBegin(SCLLNode head,int data)
    {
        //create New Node
        SCLLNode newNode =  new SCLLNode(data);

        //if no nodes
        // **1. లిస్ట్ ఖాళీ ఉందా?**
        if(head == null)
        {
            newNode.next = newNode;
            return newNode;
        }

        //below one is not required
//        //if LL has single node
//        if(head.next != head)
//        {
//            newNode.next = head;
//            head = newNode;
//            return newNode;
//        }


        //If multiple nodes
        // **2. చివరి నోడ్ కనుగొనాలి (Last Node always points to Head)**
        SCLLNode curr = head;
        while(curr.next != head)
        {
            curr = curr.next;
        }

        // **3. కొత్త నోడ్‌ని హెడ్డు ముందు insert చేయాలి**
        curr.next = newNode;
        newNode.next = head;
        head = newNode;

        return head;

    }


    //time complexity is O(1)
    //in this approach we try to insert the new node as a second node and make a connection
    // later we just swap the data inside it.
    // Eg : 10 -> 20-> 30->10
    //in this approach 10 -> 15 -> 20 -> 30 -> 10
    // later swap the first and 2nd values 15 -> 10 -> 20 -> 30 ->15
    SCLLNode insertBeginLessTime(SCLLNode head, int data)
    {

        // **కొత్త నోడ్ సృష్టించడం**
        SCLLNode newNode = new SCLLNode(data);

        // **1. లిస్ట్ ఖాళీ అయితే, కొత్త నోడ్ తన్ను తాను పాయింట్ చేయాలి**
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }

        // **2. కొత్త నోడ్‌ను మొదటి మరియు రెండవ నోడ్‌ల మధ్య Insert చేయండి**
        newNode.next = head.next; // **కొత్త నోడ్ ప్రస్తుత head.next ను పాయింట్ చేయాలి**
        head.next = newNode;      // **ప్రస్తుత head కొత్త నోడ్ ను పాయింట్ చేయాలి**

        // **3. Data Swap (head & newNode)**
        int temp = head.data;
        head.data = newNode.data;
        newNode.data = temp;

        return head; // **Head మారదు, కాబట్టి అదే రిటర్న్ చేయాలి**
    }
}
