package org.example;

import org.example.graphics.GameFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        boolean isPlaying = true;
        Game minesweeper = new Game();

        JFrame gameFrame = new GameFrame("minesweeper", minesweeper);
        gameFrame.add(minesweeper);
        gameFrame.show();


        /*JFrame gameFrame = new GameFrame("minesweeper", minesweeper);
        gameFrame.add(minesweeper);
        gameFrame.show();*/

    }
}