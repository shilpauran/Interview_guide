package main.java.urandoor.shilpa.LinkedList.ZAdditionalProblems;

import main.java.urandoor.shilpa.LinkedList.SinglyLinkedList.Node;

public class VisitedNode extends Node {


    Boolean visited;
    public VisitedNode(int data) {
        super(data);
        visited = Boolean.FALSE;
    }
}
