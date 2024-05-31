package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Cell> allCells = new ArrayList<>();
      public final int ROW;
      public final int COLUMN;
    private final int NUM_OF_MINES;


    private List<Cell> mineCells = new ArrayList<>();

    public Game(int rows, int columns){
        this.ROW = rows;
        this.COLUMN = columns;
        this.NUM_OF_MINES = (rows * columns)/10;
        createCells();
        createMines();
    }

    public Game(){
        NUM_OF_MINES = 10;
        ROW = 10;
        COLUMN = 10;
        createCells();
        createMines();
    }

    private List<Cell> createCells(){
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j< COLUMN; j++){
                int index = ((i+1)*i)+j;
                Cell cell = new Cell(i,j, index);
                allCells.add(cell);
            }
        }
        return allCells;
    }


    private void createMines(){
        // as long as not all the mines are set:
        // skapa en random row and column number
        // check if the cell with that row and column number has a mine
        // if it has a mine then the counter for mines set goes up

      int minesSet = 0;
      while (minesSet< NUM_OF_MINES){
          int mineRowNum = createRandomIntInRange(0, ROW);
          int mineColNum = createRandomIntInRange(0, COLUMN);
          for(Cell cell: allCells){
              if(cell.getRowNum() == mineRowNum && cell.getColumnNum()== mineColNum && !cell.isMine()){
                  cell.setMine(true);
                  mineCells.add(cell);
                  minesSet ++;
              }
          }
      }
    }

    private int createRandomIntInRange(int min, int max){
        return (int) (Math.random()*(max-min))+min;
    }

    public List<Cell> getAllCells() {
        return allCells;
    }
    public List<Cell> getMineCells() {
        return mineCells;
    }

}
