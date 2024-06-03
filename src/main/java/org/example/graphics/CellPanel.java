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
//        JTextArea indexNum = new JTextArea(""+index);
//        indexNum.setEditable(false);
//        this.add(indexNum);
    }
    public void addMine(){
        JTextArea mine = new JTextArea("M");
        mine.setEditable(false);
        this.add(mine);
    }



    public int getIndex() {
        return index;
    }
}
