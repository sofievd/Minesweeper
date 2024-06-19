package org.example.graphics;

import org.example.Cell;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CellPanel extends JPanel {
    int index;
    private final JTextArea mineText = new JTextArea("M");
    private JTextArea flag = new JTextArea("F");
    private JTextArea numNeigh;
    private JTextArea defaultText = new JTextArea("");

    boolean mine;

    public CellPanel(int index) {
        this.setBackground(Color.LIGHT_GRAY);
        setDefaultText();
        Border greyLine = BorderFactory.createLineBorder(Color.DARK_GRAY);
        this.setBorder(greyLine);
        this.index = index;
        //JTextArea indexNum = new JTextArea(""+index);
        //indexNum.setEditable(false);
        //this.add(indexNum);
    }

    private void setDefaultText() {
        defaultText.setBackground(Color.LIGHT_GRAY);
        defaultText.setVisible(true);
        defaultText.setEditable(false);
        add(defaultText);
    }

    public boolean isMine() {
        return mine;
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
        mineText.setForeground(Color.RED);
        mineText.setVisible(true);

    }

    public void setFlag() {
        this.setBackground(Color.WHITE);
        //mineText.setVisible(false);
        defaultText.setVisible(false);

        flag.setBackground(Color.WHITE);
        flag.setEditable(false);
        flag.setForeground(Color.green);

        this.add(flag);
        flag.setVisible(true);
        repaint(this.getX(), this.getY(), this.getWidth(), this.getHeight());

    }

    public void removeFlag() {
        this.setBackground(Color.LIGHT_GRAY);
        flag.setVisible(false);
        System.out.println("remove flag");
        setDefaultText();
    }
}
