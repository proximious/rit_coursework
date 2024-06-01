package com.webcheckers.model;


/**
 * Represents a single piece on the board
 */
public class Piece {

    private PieceType type;
    private final PieceColor color;

    /**
     * Constructor for a Piece object
     * @param type the type of piece, SINGLE or KING
     * @param color the color of the piece, RED or WHITE
     */
    public Piece (PieceType type, PieceColor color){
        this.type = type;
        this.color = color;
    }

    /**
     * An accessor for the piece's type
     * @return the type of the piece
     */
    public PieceType getType() {
        return type;
    }

    /**
     * An accessor for the piece's color
     * @return the color of the piece
     */
    public PieceColor getColor(){
        return color;
    }

    /**
     * A mutator for the piece's type
     * @param type the type to change the piece to
     */
    public void setType(PieceType type) {
        this.type = type;
    }

}
