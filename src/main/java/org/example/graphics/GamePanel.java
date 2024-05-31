package org.example.graphics;

import org.example.Cell;
import org.example.Game;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel {
    private int rows;
    private int columns;
    private final int CELL_SIZE = 40;
    private final Game MINESWEEPER;

    public GamePanel(Game game) {
        MINESWEEPER = game;
        this.rows = game.ROW;
        this.columns = game.COLUMN;

        createGamePanel();
    }

    private void createGamePanel() {
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

    private void setMinesInGrid() {
        JPanel[][] gridholder = new JPanel[rows][columns];
        setLayout(new GridLayout(rows, columns));

        List<Cell> mineCells = MINESWEEPER.getMineCells();

        JTextField mine = new JTextField("M");
        mine.setEditable(false);
        JTextField nonMine = new JTextField("");
        mine.setEditable(false);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int index = ((i+1)*i)+j;
                gridholder[i][j] = new CellPanel(index);
                add(gridholder[i][j]);

            }
        }
        //TODO: make sure all the mines are set out, not only 1.

        for (int i = 0; i < mineCells.size(); i++) {
            int row = mineCells.get(i).getRowNum();
            int column = mineCells.get(i).getColumnNum();
            gridholder[row][column].add(mine);

        }

//        gridholder[1][2].add(mine);
//        gridholder[3][9].add(mine);
//        gridholder[0][0].add(mine);

    }

    private void setMines() {

    }

}
