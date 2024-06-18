package org.example;

import org.example.graphics.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame myframe = new JFrame("minesweeper");
        Game minesweeper = new Game();

        // should not be resizeable ( dimensions wil be off)



      //  System.out.println(minesweeper.getNUM_OF_MINES());
        //System.out.println(minesweeper.getMineCells().size());
        myframe.setSize(minesweeper.ROW*40+30,minesweeper.COLUMN*40+50);

        myframe.add(new GamePanel(minesweeper));

        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myframe.show();
    }
}