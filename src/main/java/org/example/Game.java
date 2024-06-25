package org.example;

import org.example.graphics.CellPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Game extends JPanel implements MouseListener {
    private List<Cell> allCells = new ArrayList<>();
    public final int ROW;
    public final int COLUMN;
    private final int NUM_OF_MINES;
    private List<CellPanel> cellPanelList = new ArrayList<>();


    private List<Cell> mineCells = new ArrayList<>();

    public Game(int rows, int columns) {
        this.ROW = rows;
        this.COLUMN = columns;
        this.NUM_OF_MINES = (rows * columns) / 10;
        gameSetUp();
    }

    public Game() {
        NUM_OF_MINES = 10;
        ROW = 10;
        COLUMN = 10;
        gameSetUp();
    }

    private void gameSetUp() {
        createCells();
        createMines();
        setNeighbeourghMines();

        createGameGrid();

    }

    private void createGameGrid() {
        this.addMouseListener(this);
        createGrid();
        setMinesInGrid();
        setNeighbourghs();
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
            int index = mines.getINDEX();

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
                setNeighbeourghMines(index - 1);
                setNeighbeourghMines(index + 1);
                setNeighbeourghMines(index + COLUMN);
                setNeighbeourghMines(index + COLUMN - 1);
                setNeighbeourghMines(index + COLUMN + 1);

            } else if (index > COLUMN * (ROW - 1) && index < COLUMN * ROW - 1) {
                setNeighbeourghMines(index - 1);
                setNeighbeourghMines(index + 1);
                setNeighbeourghMines(index - COLUMN);
                setNeighbeourghMines(index - COLUMN - 1);
                setNeighbeourghMines(index - COLUMN + 1);
            }
            // check sidees (left and right)
            else if (index % ROW == 0) {
                setNeighbeourghMines(index + 1);
                setNeighbeourghMines(index - COLUMN);
                setNeighbeourghMines(index + COLUMN);
                setNeighbeourghMines(index - COLUMN + 1);
                setNeighbeourghMines(index + COLUMN + 1);
            } else if (index % ROW == 9) {
                setNeighbeourghMines(index - 1);
                setNeighbeourghMines(index - COLUMN);
                setNeighbeourghMines(index + COLUMN);
                setNeighbeourghMines(index + COLUMN - 1);
                setNeighbeourghMines(index - COLUMN - 1);

            } else {
                setNeighbeourghMines(index - 1);
                setNeighbeourghMines(index + 1);
                setNeighbeourghMines(index - COLUMN);
                setNeighbeourghMines(index + COLUMN);
                setNeighbeourghMines(index - COLUMN - 1);
                setNeighbeourghMines(index - COLUMN + 1);
                setNeighbeourghMines(index + COLUMN - 1);
                setNeighbeourghMines(index + COLUMN + 1);
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


    public void createGrid() {
        CellPanel[][] gridHolder = new CellPanel[ROW][COLUMN];
        setLayout(new GridLayout(ROW, COLUMN));

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                int index = (i * ROW) + j;
                gridHolder[i][j] = new CellPanel(index);
                this.add(gridHolder[i][j]);
                cellPanelList.add(gridHolder[i][j]);

            }
        }
    }

    private void setMinesInGrid() {
        for (int i = 0; i < cellPanelList.size(); i++) {
            for (int j = 0; j < mineCells.size(); j++) {
                if (mineCells.get(j).getINDEX() == cellPanelList.get(i).getIndex()) {
                    cellPanelList.get(i).setMine(true);
                    cellPanelList.get(i).addMineinFiled();
                }
            }
        }

    }

    private void setNeighbourghs() {
        for (int i = 0; i < cellPanelList.size(); i++) {
            for (int j = 0; j < allCells.size(); j++) {
                if (allCells.get(j).getINDEX() == cellPanelList.get(i).getIndex()) {
                    cellPanelList.get(i).setOutNeigh(allCells.get(j));
                }
            }
        }
    }

    private void editFlag(CellPanel panel) {
        int index = panel.getIndex();

        System.out.println(panel.getIndex());
        for (int i = 0; i < allCells.size(); i++) {
            if (allCells.get(i).getINDEX() == index && !allCells.get(i).isFlagged()) {
                allCells.get(i).setFlagged(true);
                panel.setFlag();
                break;
            } else if (allCells.get(i).getINDEX() == index && allCells.get(i).isFlagged()) {
                allCells.get(i).setFlagged(false);
                panel.removeFlag();
                break;
            }
        }
        //panel.setFlag();
    }

    private void showCell(CellPanel panel) {
        for (int i = 0; i < allCells.size(); i++) {
            if (panel.getIndex() == allCells.get(i).getINDEX()) {
                if (allCells.get(i).getNumOfNeighbourMines() != 0) {
                    allCells.get(i).setSeen(true);
                    panel.setNumCellvisible();
                }
                if (allCells.get(i).getNumOfNeighbourMines() == 0) {
                    panel.setNumCellvisible();
                    showMultipleCells(allCells.get(i));
                }
                if (allCells.get(i).isMine()) {
                    endOfGame();
                }
            }
        }
    }

    private void showMultipleCells(Cell cell) {
        int index = cell.getINDEX();

        cell.setSeen(true);

        List<Cell> cellsToSetVisible = checkBoundsToShow(index);

        for (int i = 0; i < cellsToSetVisible.size(); i++) {
            Cell currentCell = cellsToSetVisible.get(i);
            int cellIndex = currentCell.getINDEX();
            currentCell.setSeen(true);
            setCellVisable(currentCell);
            cellsToSetVisible.addAll(checkBoundsToShow(cellIndex));
        }
    }

    private List<Cell> checkBoundsToShow(int index) {
        //  bounds check
        // not mine, and  <= 0;
        // not been seen

        List<Cell> indexList = new ArrayList<>();

        int TL = index - COLUMN - 1;
        int T = index - COLUMN;
        int TR = index - COLUMN + 1;
        int L = index - 1;
        int R = index + 1;
        int BL = index + COLUMN - 1;
        int B = index + COLUMN;
        int BR = index + COLUMN + 1;

        //corners
        if (index == 0) {
            int[] topRightCorner = {R, B, BR};
            return checkNeighNum(topRightCorner);

        } else if (index == ROW - 1) {
            int[] topRightCorner = {L, BL, B};
            return checkNeighNum(topRightCorner);


        } else if (index == COLUMN * ROW - 1) {
            int[] bottomRightCorner = {TL, T, L};
            return checkNeighNum(bottomRightCorner);


        } else if (index == COLUMN * (ROW - 1)) {
            int[] bottomLeftCorrner = {T, TR, R};
            return checkNeighNum(bottomLeftCorrner);

        }
        //sides
        else if (index > 0 && index < ROW - 1) {
            int[] topSideList = {L, R, BL, B, BR};
            return checkNeighNum(topSideList);

        } else if (index > COLUMN * (ROW - 1) && index < COLUMN * ROW - 1) {
            int[] bottomSideList = {TL, T, TR, L, R};
            return checkNeighNum(bottomSideList);


        } else if (index % ROW == 0) {
            int[] leftSideList = {T, TR, R, B, BR};
            return checkNeighNum(leftSideList);

        } else if (index % ROW == 9) {
            int[] rightSideList = {T, TL, L, BL, B};
            return checkNeighNum(rightSideList);

        }
        //all sides
        else {
            int[] list = {T, TL, TR, L, R, BL, B, BR};
            return checkNeighNum(list);

        }
    }

    private List<Cell> checkNeighNum(int[] list) {
        List<Cell> returnList = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            if (allCells.get(list[i]).getNumOfNeighbourMines() == 0 && !allCells.get(list[i]).isSeen()) {
                returnList.add(allCells.get(list[i]));
            } else if (allCells.get(list[i]).getNumOfNeighbourMines() > 0) {
                setCellVisable(allCells.get(list[i]));
                allCells.get(list[i]).setSeen(true);
            }
        }
        return returnList;
    }

    private void setCellVisable(Cell cell) {
        int index = cell.getINDEX();
        for (int i = 0; i < cellPanelList.size(); i++) {
            if (cellPanelList.get(i).getIndex() == index) {
                cellPanelList.get(i).setNumCellvisible();
            }
        }
    }

    public void endOfGame() {
        //TODO: add pop up window to restart a game
        for (CellPanel panel : cellPanelList) {
            panel.setNumCellvisible();
            panel.setMineVisible();
        }
         startNewGame("Game ended!");
        // popup window to show
    }

    private void startNewGame(String prompt) {
        JOptionPane.showMessageDialog(null, prompt);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            for (int i = 0; i < cellPanelList.size(); i++) {
                if (cellPanelList.get(i).getBounds().contains(e.getPoint())) {
                    showCell(cellPanelList.get(i));
                }
            }

        }
        if (e.getButton() == 3) {
            for (int i = 0; i < cellPanelList.size(); i++) {
                if (cellPanelList.get(i).getBounds().contains(e.getPoint())) {
                    editFlag(cellPanelList.get(i));
                }
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
