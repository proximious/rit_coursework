package com.webcheckers.ui;

import com.webcheckers.model.*;

import java.util.*;

/**
 * BoardView
 * Displays a Checker Board including:
 * Creating all 64 spaces in the board
 * Add pieces on corresponding spaces with right SpaceColor.
 */
public class BoardView implements Iterable<Row> {
    private ArrayList<Row> rows;

    public BoardView(){
        rows= new ArrayList<>(8);
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }

    /**
     * creates the spaces for the board from the top left white space
     */
    public void createSpaces() {
        // make a new instance of a board square color
        SpaceColor color;

        // make instances of all 64 spaces
        for (int rowNum = 0; rowNum <= 7; rowNum++) {
            Row row = new Row(rowNum);
            for (int colNum = 0; colNum <= 7; colNum++){
                //create each space and assign a square color to it
                if(rowNum % 2 == 0){
                    if(colNum % 2 == 1){
                        color = SpaceColor.BLACK;
                    } else {
                        color = SpaceColor.WHITE;
                    }
                } else {
                    if(colNum % 2 == 0){
                        color = SpaceColor.BLACK;
                    } else {
                        color = SpaceColor.WHITE;
                    }
                }

                Space space = new Space(colNum, color);
                row.getSpaces().add(space);
            }
            rows.add(row);
        }
    }

    /**
     * adds pieces to board in the starting config
     * This is 12 red and 12 white pieces
     */
    public void addPieces(){
        // on rows 0, 1, 2 we should place the 12 white pieces
        // on rows 5, 6, 7 we should place the 12 red pieces

        Space currentSpace;

        // places the white pieces
        for (int rowNum = 0; rowNum <= 2; rowNum++) {
            for (int colNum = 0; colNum <= 7; colNum++) {
                currentSpace = rows.get(rowNum).getSpaces().get(colNum);
                // if the space is valid, then we can place a piece on it
                if(currentSpace.isValid()){
                    currentSpace.setPiece(new Piece(PieceType.SINGLE, PieceColor.WHITE));
                }
            }
        }

        // places the red pieces
        for (int rowNum = 5; rowNum <= 7; rowNum++) {
            for (int colNum = 0; colNum <= 7; colNum++) {
                currentSpace = rows.get(rowNum).getSpaces().get(colNum);
                // if the space is valid, then we can place a piece on it
                if(currentSpace.isValid()){
                    currentSpace.setPiece(new Piece(PieceType.SINGLE, PieceColor.RED));
                }
            }
        }

    }

    /**
     * Flips the view of the board so the other color is on the bottom
     * @return a new BoardView object with the flipped view
     */
    public BoardView flipView(){
        BoardView flippedView = new BoardView();

        ArrayList<Row> flippedRows = flippedView.getRows();
        flippedRows.clear();

        for (int rowNum = 7; rowNum >= 0; rowNum--) {
            Row thisRow = new Row(7-rowNum);
            for (int colNum = 7; colNum >= 0 ; colNum--) {
                Space originalSpace = rows.get(rowNum).getSpaces().get(colNum);

                Space newSpace = new Space(7-colNum, originalSpace.getColor());
                newSpace.setPiece(originalSpace.getPiece());

                thisRow.getSpaces().add(newSpace);
            }
            flippedRows.add(thisRow);
        }
        return flippedView;
    }

}
