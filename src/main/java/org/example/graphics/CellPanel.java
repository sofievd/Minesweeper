package org.example.graphics;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CellPanel  extends JPanel {
    int index;
    public CellPanel(int index) {
        this.setBackground(Color.WHITE);
        Border greyLine = BorderFactory.createLineBorder(Color.GRAY);
        this.setBorder(greyLine);
        this.index = index;
    }
    public void addMine(){
        JTextArea mine = new JTextArea("M");
        mine.setBackground(Color.WHITE);
        mine.setEditable(false);
    }

    public int getIndex() {
        return index;
    }
}
