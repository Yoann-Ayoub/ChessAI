package org.example;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Node> children = null;
    private String value;
    private int score;
    private Node sonChoosen = null;

    public Node(String value,int score)
    {
        this.children = new ArrayList<>();
        this.value = value;
        this.score = score;
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
    
    public void setSonChoosen(Node sonChoosen) {
        this.sonChoosen = sonChoosen;
    }

    public Node getSonChoosen() {
        return sonChoosen;
    }
}
