package org.example;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Node> children = null;
    private String value;
    private int score;
    private Node parent;
    private boolean isMaximizing;

    public Node(String value,int score, Node parent,boolean isMaximizing)
    {
        this.children = new ArrayList<>();
        this.value = value;
        this.score = score;
        this.parent = parent;
        this.isMaximizing = isMaximizing;
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
}
