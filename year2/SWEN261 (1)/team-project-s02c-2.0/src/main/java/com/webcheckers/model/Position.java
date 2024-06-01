package com.webcheckers.model;

import java.util.Objects;

/**
 * Contains code for all things Positional for pieces on a board
 * It will have the row and the cell of a piece position. It can tell
 * you if the piece position is on the board. It also contains getters for
 * exact location of the Position on the board.
 */
public class Position {
    private int row;
    private int cell;

    /**
     * Constructor for a Position object
     * @param row the position's row
     * @param cell the position's column
     */
    public Position(int row, int cell){
        this.row = row;
        this.cell = cell;
    }

    /**
     * An accessor for the row value of the position
     * @return the Position's row
     */
    public int getRow() {
        return row;
    }

    /**
     * An accessor for the cell value of the position
     * @return the Position's cell
     */
    public int getCell() {
        return cell;
    }

    /**
     * Checks if the position is on the board
     * @return True if the position is on the board false if it is not
     */
    public boolean onBoard(){
        return (row >= 0 && row < 8) && (cell >= 0 && cell < 8);
    }

    /**
     * Finds the difference in board positions
     * @param start: starting position of move
     * @param end: ending position of move
     * @return position that is the difference of start and end positions
     */
    public static Position getDifference(Position start, Position end){
        int x = Math.abs(end.row - start.row);
        int y = Math.abs(end.cell - start.cell);
        Position difference = new Position(x, y);
        return difference;
    }

    /**
     * Generates a readable string detailing the information of a Position object
     * @return the generated string
     */
    @Override
    public String toString() {
        return "Position{row = " + row + ", col = " + cell + "}";
    }

    /**
     * Ensures that two Position objects with the same data are equal to each other
     * @param obj the object to check equality with
     * @return true if row and cell of obj equal the row and cell of this Position, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Position)) {
            return false;
        }
        final Position position = (Position) obj;
        return row == position.row && cell == position.cell;
    }

    /**
     * Associates an int with the Position object based on its row and cell
     * @return the associated int
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, cell);
    }
}
