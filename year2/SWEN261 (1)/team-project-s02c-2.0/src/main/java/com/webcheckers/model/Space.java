package com.webcheckers.model;

import com.webcheckers.model.Piece;

/**
 * Creates spaces on the board. It will assign pieces in spaces that
 * can have a piece on it. It can check if a space is able to have a piece on it
 */
public class Space {
    private final int cellIdx;
    private Piece piece;
    private final SpaceColor color;

    /**
     * Constructor for a Space object
     * @param cellIdx the column the cell is in
     * @param color the color of the cell
     */
    public Space (int cellIdx, SpaceColor color){
        this.cellIdx = cellIdx;
        this.color = color;
    }

    /**
     * An accessor for the space's index
     * @return the cell index
     */
    public int getCellIdx() {
        return cellIdx;
    }

    /**
     * Determines whether or not the space can hold a new piece
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        return piece == null && color == SpaceColor.BLACK;
    }

    /**
     * An accessor for the space's color
     * @return the color of the space
     */
    public SpaceColor getColor(){
        return color;
    }

    /**
     * An accessor for the piece the space is holding
     * @return the piece if there is one, null otherwise
     */
    public Piece getPiece(){
        return piece;
    }

    /**
     * A mutator for the piece the space is holding
     * @param piece the piece to set the space to hold
     */
    public void setPiece(Piece piece){
        this.piece = piece;
    }
}
