package org.example.graphics;

import org.example.Cell;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CellPanel extends JPanel {
    int index;
    private final JTextArea mine = new JTextArea("M");
    private JTextArea flag = new JTextArea("F");
    private JTextArea numNeigh;

    public CellPanel(int index) {
        this.setBackground(Color.WHITE);
        Border greyLine = BorderFactory.createLineBorder(Color.GRAY);
        this.setBorder(greyLine);
        this.index = index;
        //JTextArea indexNum = new JTextArea(""+index);
        //indexNum.setEditable(false);
        //this.add(indexNum);
    }

    public void addMine() {
        mine.setEditable(false);
        mine.setVisible(true);
        this.add(mine);
    }

    public void setOutNeigh(Cell cell) {
        numNeigh = new JTextArea("" + cell.getNumOfNeighbourMines());
        if (cell.getNumOfNeighbourMines() == 0) {
            numNeigh.setText("0");
        }
        numNeigh.setEditable(false);
        numNeigh.setVisible(false);
        this.add(numNeigh);
    }

    public int getIndex() {
        return index;
    }

    public void setNumCellvisible() {
        numNeigh.setVisible(true);
    }

    public void setMineVisible() {
        mine.setForeground(Color.RED);
        mine.setVisible(true);
    }

    public void setFlag() {
        flag.setEditable(false);
        flag.setForeground(Color.BLACK);
        flag.setVisible(true);
        add(flag);
    }
}
