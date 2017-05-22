package com.company;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Klasa tworząca drzewo czerwono - czarne
 */
 class RedBlackTree<Key extends Comparable<Key>> {
    private Node root;
    public enum _color {RED, BLACK}

    void InsertBST(Key key, int rowNumber){
        if (key==null) throw new IllegalArgumentException();
        Node duplicate=checkForDuplicates(key);
        if (duplicate==null) {
            Node newNode = new Node(key, rowNumber);
            root = InsertRec(root, newNode);
            fixViolation(root, newNode);
            root.setColor(_color.BLACK);
        } else
            duplicate.getRowNr().add(rowNumber);
    }

    private Node InsertRec(Node root, Node newNode) {
        if (root == null) {
            return newNode;
        }
        int cmp = newNode.getValue().compareTo(root.getValue());
        if (cmp < 0) {
            root.setLeft(InsertRec(root.getLeft(),newNode));
            root.getLeft().setParent(root);
        } else if (cmp > 0) {
            root.setRight(InsertRec(root.getRight(), newNode));
            root.getRight().setParent(root);
        }

        return root;
    }

    // This function fixes violations caused by BST insertion
    private void fixViolation(Node root, Node node)
    {
        Node parent_pt;
        Node grand_parent_pt;

        while ((node != root) && (node.getColor() != _color.BLACK) &&
                (node.getParent().getColor() == _color.RED))
        {

            parent_pt = node.getParent();
            grand_parent_pt = node.getParent().getParent();

        /*  Case : A
            Parent of pt is left child of Grand-parent of pt */
            if (parent_pt == grand_parent_pt.getLeft())
            {

                Node uncle_pt = grand_parent_pt.getRight();

            /* Case : 1
               The uncle of pt is also red
               Only Recoloring required */
                if (uncle_pt != null && isRed(uncle_pt))
                {
                    grand_parent_pt.setColor(_color.RED);
                    parent_pt.setColor(_color.BLACK);
                    uncle_pt.setColor(_color.BLACK);
                    node = grand_parent_pt;
                }

                else
                {
                /* Case : 2
                   pt is right child of its parent
                   Left-rotation required */
                    if (node == parent_pt.getRight())
                    {
                        rotateLeft(parent_pt);
                       node = parent_pt;
                        parent_pt = node.getParent();
                    }

                /* Case : 3
                   pt is left child of its parent
                   Right-rotation required */
                    rotateRight(grand_parent_pt);
                    swap(parent_pt);
                    swap(grand_parent_pt);
                    node = parent_pt;
                }
            }

        /* Case : B
           Parent of pt is right child of Grand-parent of pt */
            else
            {
                Node uncle_pt = grand_parent_pt.getLeft();

            /*  Case : 1
                The uncle of pt is also red
                Only Recoloring required */
                if ((uncle_pt != null) && (isRed(uncle_pt)))
                {
                    grand_parent_pt.setColor(_color.RED);
                    parent_pt.setColor(_color.BLACK);
                    uncle_pt.setColor(_color.BLACK);
                    node = grand_parent_pt;
                }
                else
                {
                /* Case : 2
                   pt is left child of its parent
                   Right-rotation required */
                    if (node == parent_pt.getLeft())
                    {
                        rotateRight(parent_pt);
                        node = parent_pt;
                        parent_pt = node.getParent();
                    }

                /* Case : 3
                   pt is right child of its parent
                   Left-rotation required */
                    rotateLeft(grand_parent_pt);
                    swap(parent_pt);
                    swap(grand_parent_pt);
                    node = parent_pt;
                }
            }
        }

    }


    /**Help functions*/

    Node getRoot() {
        return root;
    }

    /*Funkcja sprawdza czy węzeł jest czerwoy*/
    private boolean isRed(Node node){
        return node!=null && (node.getColor()==_color.RED);
    }

    private Node checkForDuplicates(Key key){
        Node _root=root;
        while (_root!=null && _root.getValue().compareTo(key)!=0){
            if (_root.getValue().compareTo(key)>0)
                _root=_root.getLeft();
            else
                _root=_root.getRight();
        }
        return _root;
    }

    private void swap(Node node){
        if (node!=null){
            if(node.getColor()==_color.RED)
                node.setColor(_color.BLACK);
            else
                node.setColor(_color.RED);
        }
    }
    private void rotateLeft(Node node){
        Node node_right = node.getRight();
        node.setRight(node_right.getLeft());

        if (node.getRight() != null)
            node.getRight().setParent(node);

        node_right.setParent(node.getParent());

        if (node.getParent() == null)
            root = node_right;

        else if (node == node.getParent().getLeft())
            node.getParent().setLeft(node_right);

        else
            node.getParent().setRight(node_right);

        node_right.setLeft(node);
        node.setParent(node_right);
    }

    private void rotateRight(Node node){
        Node node_left = node.getLeft();

        node.setLeft(node_left.getRight());

        if (node.getLeft() != null)
            node.getLeft().setParent(node);

        node_left.setParent(node.getParent());

        if (node.getParent() == null)
            root = node_left;

        else if (node == node.getParent().getLeft())
            node.getParent().setLeft(node_left);

        else
            node.getParent().setRight(node_left);

        node_left.setRight(node);
        node.setParent(node_left);
    }


/**Tree parameters*/



    void levelOrder(Node root)
    {
        if (root == null)
            return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty())
        {
            Node temp = q.poll();
            System.out.print(temp+"  ");
            if (temp.getLeft() != null)
                q.add(temp.getLeft());

            if (temp.getRight() != null)
                q.add(temp.getRight());
        }
    }


    /*Funkcja pokazuje węzły w kolejności InOrder - Infix z nawiasami*/
    private void showInOrder(Node node, StringBuilder stringBuilder){
        if (node==null)
            return;
        showInOrder(node.left, stringBuilder);
        stringBuilder.append(node);
        stringBuilder.append(" ");
        showInOrder(node.right,stringBuilder);
    }

    /*Funkcja dostępna publicznie do wyświetlania w kolejności InOrder*/
    StringBuilder InfixFromTree(){
        StringBuilder stringBuilder = new StringBuilder();
        showInOrder(root,stringBuilder);
        return stringBuilder;
    }


    /*Fuckja zwraca wysokośc drzewa*/
    private int treeHeight(Node node){
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
        private final Key value;
        private final Queue<Integer> rowNr;
        private Node left;
        private Node right;
        private Node parent;
        private _color color;

        Node(Key val,int rowNumber){
            value=val;
            left=null;
            right=null;
            color=_color.RED;
            rowNr=new LinkedList<>();
            addToQueue(rowNumber);
        }

        void setLeft(Node left) {
            this.left = left;
        }

        void setRight(Node right) {
            this.right = right;
        }

        void setParent(Node parent) {
            this.parent = parent;
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

        Node getParent(){
            return parent;
        }

        Key getValue() {
            return value;
        }


        Queue<Integer> getRowNr(){
        return rowNr;}

        @Override
        public String toString() {
            return value.toString();
        }
    }


}

