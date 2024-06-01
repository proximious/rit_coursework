package com.webcheckers.model;

/**
 * Code for making a move.
 * It creates a move by using two positions a start and an end. Then can obtain information
 * about the move using its methods for getting move information. This class works very closely
 * with the Position class.
 */
public class Move {
    private Position start;
    private Position end;

    /**
     * Constructor for making a move
     * @param start: Start position of move
     * @param end: End position of move
     */
    public Move(Position start, Position end){
        this.start = start;
        this.end = end;
    }

    /**
     * An accessor for Position at the start of the move
     * @return the start position
     */
    public Position getStart() {
        return this.start;
    }

    /**
     * An accessor for Position at the end of the move
     * @return the end position
     */
    public Position getEnd() {
        return this.end;
    }

    /**
     * Gets the row of the starting position
     * @return The row
     */
    public int getStartRow(){
        return start.getRow();
    }

    /**
     * Gets the cell of the starting position
     * @return The cell
     */
    public int getStartCell(){
        return start.getCell();
    }

    /**
     * Gets the row of the ending position
     * @return The row
     */
    public int getEndRow(){
        return end.getRow();
    }

    /**
     * Gets the cell of the ending position
     * @return The cell
     */
    public int getEndCell(){
        return getEnd().getCell();
    }

    /**
     * Determines and returns the MoveType of this move
     * @return the determined move type
     */
    public MoveType determineMoveType(){
        int startRow = start.getRow();
        int endRow = end.getRow();
        MoveType type;

        if (endRow == 0){
            type = MoveType.KING_PIECE;
        } else if (startRow - 1 == endRow) {
            type = MoveType.SIMPLE_MOVE;
        } else if (startRow + 1 == endRow) {
            type = MoveType.SIMPLE_MOVE_BACK;
        } else if (startRow - 2 >= endRow) {
            type = MoveType.JUMP_MOVE;
        } else {
            type = MoveType.JUMP_MOVE_BACK;
        }
        return type;
    }

}
