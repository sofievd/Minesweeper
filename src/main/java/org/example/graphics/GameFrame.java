package org.example.graphics;

import org.example.Game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    //TODO: add methods to create the frame of the game:
    //it's size, place on screen and possibility to change the size of the window.
    private Game minesweeper;
    public GameFrame(String title, Game minesweeper) throws HeadlessException {
        super(title);
        this.minesweeper = minesweeper;
        setSize(minesweeper.ROW*40+30, minesweeper.COLUMN*40+50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

}
