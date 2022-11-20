package org.example;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Node> children = null;
    private String value;
    private int score;
    private Node parent;
    private Node sonChoosen = null;
    //private boolean isMaximizing;

    public Node(String value,int score, Node parent)
    {
        this.children = new ArrayList<>();
        this.value = value;
        this.score = score;
        this.parent = parent;
        //this.isMaximizing = isMaximizing;
    }

    public void addChild(Node child)
    {
        children.add(child);
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public List<Node> getChildren(){
        return children;
    }

    public String getValue(){
        return value;
    }

    public Node getParent() {
        return parent;
    }

    public void setSonChoosen(Node sonChoosen) {
        this.sonChoosen = sonChoosen;
    }

    public Node getSonChoosen() {
        return sonChoosen;
    }

    public void displayTree(){
        System.out.println("root :" + score);
        for (Node node:children) {
            System.out.print(node.getValue() + "  ");
        }
        System.out.println ("----");
        for (Node node:children.get(0).getChildren()
             ) {
            System.out.print(node.getValue() + "  ");
        }
    }
}
