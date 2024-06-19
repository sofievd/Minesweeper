package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Cell> allCells = new ArrayList<>();
    public final int ROW;
    public final int COLUMN;
    private final int NUM_OF_MINES;


    private List<Cell> mineCells = new ArrayList<>();

    public Game(int rows, int columns) {
        this.ROW = rows;
        this.COLUMN = columns;
        this.NUM_OF_MINES = (rows * columns) / 10;
        createCells();
        createMines();
        setNeighbeourghMines();
    }

    public Game() {
        NUM_OF_MINES = 10;
        ROW = 10;
        COLUMN =10;
        createCells();
        createMines();
        setNeighbeourghMines();
    }

    private List<Cell> createCells() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                int index = (i * ROW) + j;
                Cell cell = new Cell(i, j, index);
                allCells.add(cell);
            }
        }
        return allCells;
    }


    private void createMines() {
        // as long as not all the mines are set:
        // skapa en random row and column number
        // check if the cell with that row and column number has a mine
        // if it has a mine then the counter for mines set goes up

        int minesSet = 0;
        while (minesSet < NUM_OF_MINES) {
            int mineRowNum = createRandomIntInRange(0, ROW);
            int mineColNum = createRandomIntInRange(0, COLUMN);
            for (Cell cell : allCells) {
                if (cell.getRowNum() == mineRowNum && cell.getColumnNum() == mineColNum && !cell.isMine()) {
                    cell.setMine(true);
                    mineCells.add(cell);
                    minesSet++;
                }
            }
        }
    }

    private void setNeighbeourghMines() {

        for (Cell mines : mineCells) {
            int index = mines.INDEX;

            // check all corners
            if (index == 0) {
                setNeighbeourghMines(index + 1);
                setNeighbeourghMines(index + COLUMN);
                setNeighbeourghMines(index + COLUMN + 1);
            } else if (index == ROW - 1) {
                setNeighbeourghMines(index - 1);
                setNeighbeourghMines(index + COLUMN);
                setNeighbeourghMines(index + COLUMN - 1);

            } else if (index == COLUMN * ROW - 1) {
                setNeighbeourghMines(index - 1);
                setNeighbeourghMines(index - COLUMN);
                setNeighbeourghMines(index - COLUMN - 1);

            } else if (index == COLUMN * (ROW - 1)) {
                setNeighbeourghMines(index + 1);
                setNeighbeourghMines(index - COLUMN);
                setNeighbeourghMines(index - COLUMN + 1);
            }
            // check the sides( top and bottom)

            else if (index > 0 && index < ROW - 1) {
                setNeighbeourghMines(index-1);
                setNeighbeourghMines(index+1);
                setNeighbeourghMines(index +COLUMN);
                setNeighbeourghMines(index + COLUMN -1);
                setNeighbeourghMines(index + COLUMN +1);

            }
           else if (index > COLUMN * (ROW - 1) && index < COLUMN * ROW - 1) {
                setNeighbeourghMines(index - 1);
                setNeighbeourghMines(index + 1);
                setNeighbeourghMines(index - COLUMN);
                setNeighbeourghMines(index - COLUMN - 1);
                setNeighbeourghMines(index - COLUMN + 1);
            }
            // check sidees (left and right)
           else if (index % ROW == 0) {
               setNeighbeourghMines(index +1);
               setNeighbeourghMines(index - COLUMN);
               setNeighbeourghMines(index + COLUMN);
               setNeighbeourghMines(index- COLUMN +1);
               setNeighbeourghMines(index + COLUMN +1);
            }
           else if (index % ROW == 9) {
               setNeighbeourghMines(index-1);
               setNeighbeourghMines(index -COLUMN);
               setNeighbeourghMines(index + COLUMN);
               setNeighbeourghMines(index + COLUMN -1);
               setNeighbeourghMines(index - COLUMN -1);

            }
           else{
               setNeighbeourghMines(index -1);
               setNeighbeourghMines(index +1);
               setNeighbeourghMines(index - COLUMN);
               setNeighbeourghMines(index + COLUMN);
               setNeighbeourghMines(index - COLUMN -1);
               setNeighbeourghMines(index - COLUMN +1);
               setNeighbeourghMines(index + COLUMN -1);
               setNeighbeourghMines(index + COLUMN +1);
            }
        }

    }


    private void setNeighbeourghMines(int index) {
        for (Cell cell : allCells) {
            if (cell.getINDEX() == index) {
                int numNeigh = cell.getNumOfNeighbourMines();
                numNeigh += 1;
                if (!cell.isMine()) {
                    cell.setNumOfNeighbourMines(numNeigh);
                }
            }
        }
    }


    private int createRandomIntInRange(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public List<Cell> getAllCells() {
        return allCells;
    }

    public List<Cell> getMineCells() {
        return mineCells;
    }

    public int getNUM_OF_MINES() {
        return NUM_OF_MINES;
    }
}
