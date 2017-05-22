package com.company;

 class Main {

    public static void main(String[] args) {

        RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();
        redBlackTree.InsertBST(1,1);
        redBlackTree.InsertBST(2,1);
        redBlackTree.InsertBST(3,1);
        redBlackTree.InsertBST(4,1);
        redBlackTree.InsertBST(5,1);
        redBlackTree.InsertBST(6,1);
        redBlackTree.InsertBST(7,1);
        redBlackTree.InsertBST(8,1);
        redBlackTree.InsertBST(9,1);
        redBlackTree.InsertBST(10,1);
        redBlackTree.InsertBST(11,1);
        redBlackTree.InsertBST(1,2);
        redBlackTree.InsertBST(15,1);
        redBlackTree.InsertBST(7,2);
        System.out.println(redBlackTree.InfixFromTree());
        System.out.println(redBlackTree.getRoot());
        redBlackTree.levelOrder(redBlackTree.getRoot());
    }
}
