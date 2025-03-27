package urandoor.shilpa.LinkedList.SinglyLinkedList;

public class SLLInsertAtPosition {

    SLLNode head;

    void insertAtPosition(int data, int position) {
        SLLNode newNode = new SLLNode(data); // కొత్త Node సృష్టించటం

        // 1. **Position 1 లో Insert చేయాలి అంటే head ని మారుస్తాం**
        if (position == 1) {
            newNode.next = head; // కొత్త Node యొక్క next ని head కి pointing చేయడం
            head = newNode; // head ని కొత్త Node కి మార్చడం
            return;
        }

        // 2. **ముందుగా ఉన్న నోడ్స్ ను Traverse చేయాలి**
        SLLNode temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next; // Next Node కి వెళ్ళడం
        }

        // 3. **Position లిస్ట్ పరిమితిని మించి ఉందా అనునది తనిఖీ చేయాలి**
        //if position is not valid . eg : linked list has only 4 elements but its asking to insert at position 50, then this will take care of that
        if (temp == null) {
            System.out.println("Invalid Position");
            return;
        }

        // 4. **కొత్త Node ను Position లో Insert చేయండి**
        newNode.next = temp.next; // కొత్త Node ని ముందుగా ఉన్న లింక్ కి pointing చేయించండి
        temp.next = newNode; // ముందున్న Node ని కొత్త Node కి pointing చేయండి
    }

    void display() {
        SLLNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> "); // ప్రింట్ చేయడం
            temp = temp.next; // Next Node కి వెళ్ళడం
        }
        System.out.println("null"); // లిస్ట్ ముగిసినట్లు సూచించేందుకు
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        // **ప్రత్యేక స్థానాల్లో Insert చేయడం**
        list.insertAtPosition(10, 1); // 10 ని 1st స్థానంలో Insert చేయండి
        list.insertAtPosition(20, 2); // 20 ని 2nd స్థానంలో Insert చేయండి
        list.insertAtPosition(30, 2); // 30 ని 2nd స్థానంలో Insert చేయండి

        // **Final Linked List Display**: 10 -> 30 -> 20 -> null
        list.display();
    }
}
}
