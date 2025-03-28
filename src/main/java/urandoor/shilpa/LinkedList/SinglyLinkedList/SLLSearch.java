package urandoor.shilpa.LinkedList.SinglyLinkedList;
import urandoor.shilpa.LinkedList.SinglyLinkedList.SLLNode;
public class SLLSearch {

    int search(SLLNode head, int data) {
        // **1. Head null అయితే, లిస్ట్ ఖాళీగా ఉంది → డేటా కనుగొనబడలేదు**
        if (head == null) {
            return -1; // **Element not found**
        }

        SLLNode current = head;
        int pos = 1; // **2. Position ను 1 నుంచి ప్రారంభించాలి (1-based index)**

        // **3. Linked List లో ప్రతి Node ను Traverse చేయండి**
        while (current != null) {
            if (current.data == data) {
                return pos; // **Element కనుగొనబడ్డ స్థానం (Position) return చేయండి**
            }
            current = current.next; // **Next Node move అవ్వండి**
            pos++; // **Position update చేయండి**
        }

        return -1; // **4. Data కనుగొనబడకపోతే -1 return చేయండి**
    }

}
