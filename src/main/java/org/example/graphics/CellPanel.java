package org.example.graphics;

import org.example.Cell;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CellPanel extends JPanel {
    int index;
    private final JTextArea mineText = new JTextArea("M");
    private JTextArea flagText = new JTextArea("F");
    private JTextArea numNeigh;
    private JTextArea defaultText = new JTextArea("");

    boolean mine;
    boolean flag;

    public CellPanel(int index) {
        this.setBackground(Color.LIGHT_GRAY);
        setDefaultText();
        Border greyLine = BorderFactory.createLineBorder(Color.DARK_GRAY);
        this.setBorder(greyLine);
        this.index = index;
    }

    private void setDefaultText() {
        defaultText.setBackground(Color.LIGHT_GRAY);
        defaultText.setVisible(true);
        defaultText.setEditable(false);
        add(defaultText);
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public void addMineinFiled() {
        mineText.setEditable(false);
        mineText.setVisible(false);
        this.add(mineText);
    }

    public void setOutNeigh(Cell cell) {
        numNeigh = new JTextArea("" + cell.getNumOfNeighbourMines());
        if (cell.getNumOfNeighbourMines() == 0) {
            numNeigh.setText("");
        }
        numNeigh.setEditable(false);
        numNeigh.setVisible(false);
        this.add(numNeigh);
    }

    public int getIndex() {
        return index;
    }

    public void setNumCellvisible() {
        defaultText.setVisible(false);
        this.setBackground(Color.WHITE);
        numNeigh.setBackground(Color.WHITE);
        numNeigh.setVisible(true);
    }

    public void setMineVisible() {
        if(!flag){
            mineText.setForeground(Color.RED);
            mineText.setVisible(true);
        }
        flagText.setForeground(Color.GREEN);
    }

    public void setFlag() {
        this.setBackground(Color.WHITE);
        //mineText.setVisible(false);
        defaultText.setVisible(false);

        flagText.setBackground(Color.WHITE);
        flagText.setEditable(false);
        flagText.setForeground(Color.BLUE);

        this.add(flagText);
        flagText.setVisible(true);
        flag = true;

       // repaint(this.getX(), this.getY(), this.getWidth(), this.getHeight());

    }

    public void removeFlag() {
        this.setBackground(Color.LIGHT_GRAY);
        flagText.setVisible(false);
        System.out.println("remove flag");
        flag = false;
        setDefaultText();
    }
}
