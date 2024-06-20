package org.example;

import org.example.graphics.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame myframe = new JFrame("minesweeper");
        Game minesweeper = new Game();

        myframe.setSize(minesweeper.ROW*40+30,minesweeper.COLUMN*40+50);
        myframe.add(new GamePanel(minesweeper));
        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myframe.setResizable(false);
        myframe.setLocationRelativeTo(null);
        myframe.show();
    }
}