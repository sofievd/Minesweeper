package org.example;

import javax.swing.*;
import java.awt.*;

public class Cell {
   private int rowNum;
   private int columnNum;
   private boolean seen;
   private boolean flagged;
    private boolean mine;
    private int numOfNeighbourMines;
    int INDEX;

    public Cell(){
    }

    public Cell(int rowNum, int columnNum, int index) {
        this.rowNum = rowNum;
        this.columnNum = columnNum;
        INDEX= index;

    }
    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public int getNumOfNeighbourMines() {
        return numOfNeighbourMines;
    }

    public void setNumOfNeighbourMines(int numOfNeighbourMines) {
        this.numOfNeighbourMines = numOfNeighbourMines;
    }

    public int getINDEX() {
        return INDEX;
    }

    public void setINDEX(int INDEX) {
        this.INDEX = INDEX;
    }
}
