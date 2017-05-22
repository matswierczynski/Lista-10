package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Klasa do otwierania i odczytywania z pliku
 */
 class InputFile {
    private RedBlackTree<String> redBlackTree;
    private Queue<String> fileLines = new LinkedList<>();
    private String path;

    InputFile(String path) {
        this.path = path;
        redBlackTree = new RedBlackTree<>();
        fileLines = new LinkedList<>();
    }

     void readFromFile(){

        try {
            Scanner scan = new Scanner(new File(path));
            while(scan.hasNextLine()){
                fileLines.add(scan.nextLine());
            }
            splitToSubstring();
            redBlackTree.InOrder();
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
            e.printStackTrace();
         }
     }

     private void splitToSubstring(){
         int rowNr=1;
         while(!fileLines.isEmpty()){
             String [] splittedLine = fileLines.poll().split(" ");
             for(String x: splittedLine){
                String trimmed = trimToWord(x);
                if (trimmed!=null)
                    redBlackTree.InsertBST(trimmed,rowNr);
             }
             rowNr++;

         }
     }

     private String trimToWord(String tokens){
         tokens=tokens.toLowerCase();
         char [] array = tokens.toCharArray();
         if (Character.isJavaIdentifierStart(array[0]))
             if (Character.isJavaIdentifierStart(array[array.length-1]))
                 tokens=String.copyValueOf(array,0,array.length);
            else
                tokens=String.copyValueOf(array,0,array.length-1);
         else
             if (Character.isJavaIdentifierStart(array[array.length-1])) {
                 int range = array.length - 1 > 0 ? array.length - 1 : 1;
                 tokens = String.copyValueOf(array, 1, range);
             }
            else {
                 int range = array.length - 2 > 0 ? array.length - 2 : 0;
                 tokens = String.copyValueOf(array, 1, range);
             }

         return tokens.length()>0 ? tokens : null;

     }


}
