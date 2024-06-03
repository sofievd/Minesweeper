package org.example.graphics;

import org.example.Cell;
import org.example.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    private int rows;
    private int columns;
    private final int CELL_SIZE = 40;
    private final Game MINESWEEPER;
    private List<CellPanel> cellPanelList = new ArrayList<>();

    public GamePanel(Game game) {
        MINESWEEPER = game;
        this.rows = game.ROW;
        this.columns = game.COLUMN;

        createGamePanel();
    }

    private void createGamePanel() {
        createGrid();
        setMinesInGrid();
        // setMines();
    }

    /*public void gridHolder(){
        JPanel[][] gridholder = new JPanel[rows][columns];
        setLayout(new GridLayout(rows, columns));
        JTextField mine = new JTextField("M");
        mine.setEditable(false);
        JTextField nonMine = new JTextField("");

          for(int i= 0; i<rows; i++){
              for(int j = 0; j< columns; j++){
                  gridholder[i][j] = new JPanel();
                  add(gridholder[i][j]);
              }
          }
          List<Cell> allCells = MINESWEEPER.getAllCells();
          for(Cell cell: allCells){
              if(cell.isMine()){
                  gridholder[cell.getRowNum()][cell.getColumnNum()].add(mine);
                  setVisible(true);
              }
          }
    }*/
    public void createGrid() {
        CellPanel[][] gridHolder = new CellPanel[rows][columns];
        setLayout(new GridLayout(rows, columns));


        JTextField mine = new JTextField("M");
        mine.setEditable(false);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int index = (i *10) + j;
                gridHolder[i][j] = new CellPanel(index);
                add(gridHolder[i][j]);
                cellPanelList.add(gridHolder[i][j]);

            }
        }

    }

    private void setMinesInGrid() {
        List<Cell> mineCells = MINESWEEPER.getMineCells();
        for (int i = 0; i < cellPanelList.size(); i++) {
            for (int j = 0; j < mineCells.size(); j++) {
                if (mineCells.get(j).getINDEX() == cellPanelList.get(i).getIndex()) {
                    cellPanelList.get(i).addMine();
                }
            }
        }
        //TODO: make sure all the mines are set out, not only 1.
/*
        for (int i = 0; i < mineCells.size(); i++) {
            int row = mineCells.get(i).getRowNum();
            int column = mineCells.get(i).getColumnNum();
            gridHolder[row][column].add(mine);

        }*/

//        gridHolde[1][2].add(mine);
//        gridHolde[3][9].add(mine);
//        gridHolde[0][0].add(mine);

    }

    private void setMines() {

    }

}
