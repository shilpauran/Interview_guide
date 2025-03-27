package urandoor.shilpa.LinkedList.SinglyLinkedList;

public class SLLTraversal {

    // **1. Linked List ని Traverse చేయడం (ప్రింట్ చేయడం)**
    static void traversalLinkedList(SLLNode head){

        SLLNode temp = head; // Head నుండి ప్రారంభించండి
        while (temp != null) {
            System.out.print(temp.data + " -> "); // ప్రస్తుత Node ను ప్రింట్ చేయండి
            temp = temp.next; // Next Node కి వెళ్లండి
        }
        System.out.println("null"); // చివరి భాగాన్ని సూచించేందుకు
    }
}
