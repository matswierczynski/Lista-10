package com.company;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Mati on 2017-05-22.
 */
public class RedBlackTree<Key extends Comparable<Key>> {
    private Node root;
    public enum _color {RED, BLACK};

    void InsertBST(Key key){
        if (key==null) throw new IllegalArgumentException();
        root = InsertRec(root,key);
        root.setColor(_color.BLACK);
    }

    Node InsertRec(Node root, Key key){
        if (root==null){
            return new Node(key);
        int cmp=key.compareTo(root.getValue());
        if (cmp<0)
            root.setLeft(InsertRec(root.getLeft(),key));
        else if (cmp>0)
            root.setRight(InsertRec(root.getRight(),key));
        /**else warunek na dopisanie do listy nr wiersza*/

    }


    Node getRoot() {
        return root;
    }




/**Tree parameters*/

    /*Funkcja pokazuje węzły w kolejności InOrder - Infix z nawiasami*/
    private void showInOrder(Node node, StringBuilder stringBuilder){
        if (node==null)
            return;
        stringBuilder.append("(");
        showInOrder(node.left, stringBuilder);
        stringBuilder.append(node);
        showInOrder(node.right,stringBuilder);
        stringBuilder.append(")");
    }

    /*Funkcja dostępna publicznie do wyświetlania w kolejności InOrder*/
    StringBuilder InfixFromTree(){
        StringBuilder stringBuilder = new StringBuilder();
        showInOrder(root,stringBuilder);
        return stringBuilder;
    }


    /*Funkcja zwraca liczbę liści drzewa*/
    int leavesNr(Node node){
        if (node==null)
            return 0;
        else
        if (node.getLeft()==null && node.getRight()==null)
            return 1;
        else
            return leavesNr(node.left)+leavesNr(node.right);
    }

    /*Funkcja zwraca liczbę węzłów drzewa*/
    int nodesNr(Node node){
        if (node==null)
            return 0;
        else
            return 1+nodesNr(node.left)+nodesNr(node.right);
    }

    /*Fuckja zwraca wysokośc drzewa*/
    int treeHeight(Node node){
        if (node == null) {
            return -1;
        }

        int left = treeHeight(node.getLeft());
        int right = treeHeight(node.getRight());

        if (left > right) {
            return left + 1;
        } else {
            return right + 1;
        }
    }

    /**Prywatna klasa Węzeł o atrybutach: wartość, prawe dziecko, lewe dzeicko*/
    private class Node {
        private Key value;
        private Queue<Integer> rowNr;
        private Node left;
        private Node right;
        private _color color;

        Node(Key val){
            value=val;
            left=null;
            right=null;
            color=_color.RED;
            rowNr=new LinkedList<>();
        }

        void setLeft(Node left) {
            this.left = left;
        }

        void setRight(Node right) {
            this.right = right;
        }

        void setColor(_color color){
            this.color=color;
        }

        void addToQueue(int value){
            rowNr.add(value);
        }

        Node getLeft() {
            return left;
        }

        Node getRight() {
            return right;
        }

        _color getColor(){
            return color;
        }

        Key getValue() {
            return value;
        }

        Queue getRowNr(){
        return rowNr;}

        @Override
        public String toString() {
            return value.toString();
        }
    }


}

