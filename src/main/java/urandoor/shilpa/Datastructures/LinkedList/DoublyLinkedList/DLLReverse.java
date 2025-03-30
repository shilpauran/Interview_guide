package main.java.urandoor.shilpa.Datastructures.LinkedList.DoublyLinkedList;

public class DLLReverse {

    // **1️⃣ డౌబుల్ లింక్డ్ లిస్ట్ రివర్స్ చేసే మెథడ్**
    static DLLNode reverse(DLLNode head) {
        DLLNode temp = null;  // **కొత్త టైమ్‌పరరీ Node** //temp is nothing but storage for previous node
        DLLNode current = head; // **ప్రస్తుత Head Node నుండి స్టార్ట్ చేయాలి**

        // **2️⃣ లూప్ ద్వారా ప్రతి Node కి prev & next స్వాప్ చేయాలి**
        while (current != null) {
            temp = current.prev; // **Prev కాపీ చేయడం**
            current.prev = current.next; // **Next ను Prev గా మార్చడం**
            current.next = temp; // **Prev ను Next గా మార్చడం**
            current = current.prev; // **ముందుకు వెళ్లడం (స్వాప్ అయ్యాక, కొత్త Prev)**
        }

        // **3️⃣ కొత్త Head ను రిటర్న్ చేయాలి**
        if (temp != null) {
            head = temp.prev; // **కొత్త Head రిటర్న్ చేయాలి (తీవ్రంగా వెనక్కి వెళ్లి)**
        }
        return head;

//    DLLNode reverse(DLLNode head)
//    {
//        if(head == null|| head.next == null)
//        {
//            return head;
//        }
//
//        DLLNode prev = null;
//        DLLNode curr = head;
//        while(curr != null)
//        {
//            prev = curr.prev ;
//            curr.prev = curr.next;
//            curr.next = prev;
//            curr = curr.prev;
//        }
//        return prev.prev;
////        DLLNode curr = head;
////        DLLNode nextNode = head.next;
////        while(curr != null)
////        {
////
////            curr.prev = nextNode;
////            nextNode = curr.next.next;
////            curr.next = curr.prev;
////            curr = curr.next;
////        }
//    }
}
