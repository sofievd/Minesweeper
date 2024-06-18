package org.example.graphics;

import org.example.Cell;
import org.example.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements MouseListener {
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

        this.addMouseListener(this);
        createGrid();
        setMinesInGrid();
        setNeighbourghs();
    }

    public void createGrid() {
        CellPanel[][] gridHolder = new CellPanel[rows][columns];
        setLayout(new GridLayout(rows, columns));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int index = (i * rows) + j;
                gridHolder[i][j] = new CellPanel(index);
                this.add(gridHolder[i][j]);
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

    }

    private void setNeighbourghs() {
        List<Cell> allCells = MINESWEEPER.getAllCells();
        for (int i = 0; i < cellPanelList.size(); i++) {
            for (int j = 0; j < allCells.size(); j++) {
                if (allCells.get(j).getINDEX() == cellPanelList.get(i).getIndex()) {
                    cellPanelList.get(i).setOutNeigh(allCells.get(j));
                }
            }
        }
    }

    private void setFlag(CellPanel panel) {
        System.out.println(panel.getIndex());
        panel.setFlag();
    }

    private void showCell(CellPanel panel) {
        List<Cell> allCells = MINESWEEPER.getAllCells();
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
        //TODO: make sure the algoritm works.
        int index = cell.getINDEX();
        List<Cell> allCells = MINESWEEPER.getAllCells();
        List<Integer> listToSetvisible = new ArrayList<>();

        // check the bounds and return a list of indexes within the bounds, and where the neighers are not a mine and bigger than 0;
        List<Integer> indexBoundsList;
        indexBoundsList = checkBounds(index);
        // repeat step with each of the items in the list where the neigbourghs are 0, until the list no longer gets any members.
        while (indexBoundsList.isEmpty()) {
            // items are added to the list to set visible
            listToSetvisible.addAll(indexBoundsList);
            for (int i = 0; i < indexBoundsList.size(); i++) {
                if (allCells.get((indexBoundsList.get(i))).getNumOfNeighbourMines() == 0) {
                    List<Integer> tempList = checkBounds(indexBoundsList.get(i));
                    indexBoundsList.addAll(tempList);
                }
            }
        }
        // set the  list visible
        setCellsVisable(listToSetvisible);
    }

    private List<Integer> checkBounds(int index) {
        List<Integer> indexList = new ArrayList<>();

        int TL = index - columns - 1;
        int T = index - columns;
        int TR = index - columns + 1;
        int L = index - 1;
        int R = index + 1;
        int BL = index + columns - 1;
        int B = index + columns;
        int BR = index + columns + 1;

        //corners
        if (index == 0) {
            int[] topRightCorner = {R, B, BR};
            indexList.addAll(arrayToList(checkNumNeigh(topRightCorner)));
            return indexList;
        } else if (index == rows - 1) {
            int[] topRightCorner = {L, BL, B};
            indexList.addAll(arrayToList(checkNumNeigh(topRightCorner)));
            return indexList;

        } else if (index == columns * rows - 1) {
            int[] bottomRightCorner = {TL, T, L};
            indexList.addAll(arrayToList(checkNumNeigh(bottomRightCorner)));
            return indexList;

        } else if (index == columns * (rows - 1)) {
            int[] bottomLeftCorrner = {T, TR, R};
            indexList.addAll(arrayToList(checkNumNeigh(bottomLeftCorrner)));
            return indexList;

        }
        //sides
        else if (index > 0 && index < rows - 1) {
            int[] topSideList = {L, R, BL, B, BR};
            indexList.addAll(arrayToList(checkNumNeigh(topSideList)));
            return indexList;
        } else if (index > columns * (rows - 1) && index < columns * rows - 1) {
            int[] bottomSideList = {TL, T, TR, L, R};
            indexList.addAll(arrayToList(checkNumNeigh(bottomSideList)));
            return indexList;

        } else if (index % rows == 0) {
            int[] leftSideList = {T, TR, R, B, BR};
            indexList.addAll(arrayToList(checkNumNeigh(leftSideList)));
            return indexList;
        } else if (index % rows == 9) {
            int[] rightSideList = {T, TL, L, BL, B};
            indexList.addAll(arrayToList(checkNumNeigh(rightSideList)));
            return indexList;
        }
        //all sides
        else {
            int[] list = {T, TL, TR, L, R, BL, B, BR};
            indexList.addAll(arrayToList(checkNumNeigh(list)));
            return indexList;
        }
    }

    private List<Integer> arrayToList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(i);
        }
        return list;
    }

    private int[] checkNumNeigh(int[] list) {
        int[] returnList;
        returnList = new int[list.length];
        List<Cell> allCells = MINESWEEPER.getAllCells();
        for (int i = 0; i < list.length; i++) {
            if (!allCells.get(list[i]).isMine() && allCells.get(list[i]).getNumOfNeighbourMines() >= 0) {
                returnList[i] = list[i];
            }
        }
        return returnList;
    }

    private void setCellsVisable(List<Integer> list) {
        List<Cell> allCells = MINESWEEPER.getAllCells();
        for (int i = 0; i < list.size(); i++) {
            if (!allCells.get(list.get(i)).isMine() && allCells.get(list.get(i)).getNumOfNeighbourMines() >= 0) {
                cellPanelList.get(list.get(i)).setNumCellvisible();
                allCells.get(list.get(i)).setSeen(true);
            }
        }
    }

    private void endOfGame() {
        for (CellPanel panel : cellPanelList) {
            panel.setNumCellvisible();
            panel.setMineVisible();
        }
        // popup window to show
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            for (int i = 0; i < cellPanelList.size(); i++) {
                if (cellPanelList.get(i).getBounds().contains(e.getPoint())) {
                    showCell(cellPanelList.get(i));
                }
            }

        } else if (e.getButton() == 3) {
            for (int i = 0; i < cellPanelList.size(); i++) {
                if (cellPanelList.get(i).getBounds().contains(e.getPoint())) {
                    setFlag(cellPanelList.get(i));
                }
            }

        }
       /* for(int i = 0; i < cellPanelList.size(); i++){
            if (cellPanelList.get(i).getBounds().contains(e.getPoint())){
                System.out.println(e.getButton());
                System.out.println(cellPanelList.get(i).getIndex());
            }
        }*/
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
