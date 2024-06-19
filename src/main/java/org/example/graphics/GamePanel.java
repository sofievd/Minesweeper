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
                    cellPanelList.get(i).setMine(true);
                    cellPanelList.get(i).addMineinFiled();
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

    private void editFlag(CellPanel panel) {

        List<Cell> allCells = MINESWEEPER.getAllCells();
        int index = panel.getIndex();

        System.out.println(panel.getIndex());
        for(int i = 0; i<allCells.size(); i++){
            if(allCells.get(i).getINDEX() == index && !allCells.get(i).isFlagged()){
                allCells.get(i).setFlagged(true);
                panel.setFlag();
                break;
            }
           else if(allCells.get(i).getINDEX() == index && allCells.get(i).isFlagged()){
                allCells.get(i).setFlagged(false);
                panel.removeFlag();
                break;
            }
        }
        //panel.setFlag();
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
        int index = cell.getINDEX();
        // make cell visible
        cell.setSeen(true);

        List<Cell> cellsToSetVisible = checkBounds(index);

        //check bounds and surounding cells, add to a list;
            //  bounds check
            // not mine, and  <= 0;
            // not been seen
        // go throught the list and for each item check the surounding cells ( add to list)  and set visible
        // repeat until the list is empty

        for (int i = 0; i < cellsToSetVisible.size(); i++) {
            Cell currentCell = cellsToSetVisible.get(i);
            int cellIndex = currentCell.getINDEX();
            currentCell.setSeen(true);
            setCellVisable(currentCell);
            cellsToSetVisible.addAll(checkBounds(cellIndex));
        }
    }
    private List<Cell> checkBounds(int index) {
        //  bounds check
        // not mine, and  <= 0;
        // not been seen

        List<Cell> indexList = new ArrayList<>();

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
            return checkNeighNum(topRightCorner);

        } else if (index == rows - 1) {
            int[] topRightCorner = {L, BL, B};
            return checkNeighNum(topRightCorner);


        } else if (index == columns * rows - 1) {
            int[] bottomRightCorner = {TL, T, L};
            return checkNeighNum(bottomRightCorner);


        } else if (index == columns * (rows - 1)) {
            int[] bottomLeftCorrner = {T, TR, R};
            return checkNeighNum(bottomLeftCorrner);

        }
        //sides
        else if (index > 0 && index < rows - 1) {
            int[] topSideList = {L, R, BL, B, BR};
            return checkNeighNum(topSideList);

        } else if (index > columns * (rows - 1) && index < columns * rows - 1) {
            int[] bottomSideList = {TL, T, TR, L, R};
            return checkNeighNum(bottomSideList);


        } else if (index % rows == 0) {
            int[] leftSideList = {T, TR, R, B, BR};
            return checkNeighNum(leftSideList);

        } else if (index % rows == 9) {
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
        List<Cell> allCells = MINESWEEPER.getAllCells();
        for (int i = 0; i < list.length; i++) {
            if (allCells.get(list[i]).getNumOfNeighbourMines() == 0 && !allCells.get(list[i]).isSeen()) {
                returnList.add(allCells.get(list[i]));
            }
           else if(allCells.get(list[i]).getNumOfNeighbourMines()>0 ){
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
