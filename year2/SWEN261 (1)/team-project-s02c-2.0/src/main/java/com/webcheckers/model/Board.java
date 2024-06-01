package com.webcheckers.model;

import com.webcheckers.ui.BoardView;

/**
 * Code for the Board.
 * Will create a board and assign players to it. It creates the board
 * and puts pieces down onto the board. The board class can also identify
 * the players using the board and handle moving pieces on the board and doing
 * jumps.
 */
public class Board {
    final private String redPlayer;
    final private String whitePlayer;
    private BoardView boardView;
    private BoardView flippedBoardView;
    private PieceColor activePlayer;

    /**
     * Creates a game board for the two specified players to play on
     * @param redPlayer the red player for the game
     * @param whitePlayer the white player for the game
     */
    public Board(String redPlayer, String whitePlayer) {
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.activePlayer = PieceColor.RED;
        this.boardView = new BoardView();
        boardView.createSpaces();
        boardView.addPieces();
        flippedBoardView = boardView.flipView();
    }

    /**
     * gets the red player's name
     * @return the red player's name
     */
    public String getRedPlayer() {
        return redPlayer;
    }

    /**
     * gets the white player's name
     * @return the white player's name
     */
    public String getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * gets the regular or flipped version of the board view depending on which color is active
     * @return the active board view
     */
    public BoardView getActiveBoardView() {
        if (activePlayer == PieceColor.RED){
            return boardView;
        }
        else{
            return flippedBoardView;
        }
    }

    /**
     * gets the regular or flipped version of the board view depending on which color is inactive
     * @return the inactive board view
     */
    public BoardView getInactiveBoardView(){
        if (activePlayer == PieceColor.RED){
            return flippedBoardView;
        }
        else{
            return boardView;
        }
    }

    /**
     * gets the piece color of the active player
     * @return the active player's color
     */
    public PieceColor getActivePlayer() {
        return activePlayer;
    }

    /**
     * Set the active player of the game
     * @param color the color of the active player
     */
    public void setActivePlayer(PieceColor color){
        this.activePlayer = color;
    }

    /**
     * Checks to see if there is a piece belonging to the inactive player at a specified position
     * @param position the position to check
     * @return true if the position contains a piece belonging to the inactive player, false otherwise
     */
    public boolean containsInactivePiece(Position position){
        Piece piece = getPieceAt(position);

        if (piece == null){ //checks if there is a piece at the position
            return false;
        } else{
            // checks if the piece at the position is the same color as the active player
            return piece.getColor() != activePlayer;
        }
    }

    /**
     * Gets the piece at the desired position
     * @param position the position to get the piece at
     * @return the piece or null if the space does not contain a piece
     */
    public Piece getPieceAt(Position position){
        int rowIndex = position.getRow();
        int columnIndex = position.getCell();

        Row specifiedRow = getActiveBoardView().getRows().get(rowIndex);
        Space specifiedCell = specifiedRow.getSpaces().get(columnIndex);

        return specifiedCell.getPiece();
    }

    /**
     * Sets the piece held by the designated position to the designated piece
     * @param position the position to put the piece at
     * @param piece the piece to put at the position
     */
    private void putPieceAt(Position position, Piece piece){
        int rowIndex = position.getRow();
        int columnIndex = position.getCell();

        Row specifiedRow = getActiveBoardView().getRows().get(rowIndex);
        Space specifiedCell = specifiedRow.getSpaces().get(columnIndex);

        specifiedCell.setPiece(piece);
        updateOtherBoardView();
    }

    /**
     * "Picks up" the piece at the start position and "puts it down" at the end position
     * @param start the starting position of the piece to move
     * @param end the end position for the piece to move
     */
    public void movePiece(Position start, Position end){
        Piece piece = getPieceAt(start);
        putPieceAt(start, null);
        putPieceAt(end, piece);
    }

    /**
     * "Picks up" the piece at the start position and "puts it down" at the end position and
     * removes the piece that has been jumped over
     * @param start the starting position of the piece to move
     * @param end the end position for the piece to move
     * @return the jumped piece
     */
    public Piece jumpWithPiece(Position start, Position end){
        movePiece(start, end);

        int startCol = start.getCell();
        int endCol = end.getCell();
        int startRow = start.getRow();
        int endRow = end.getRow();

        Position jumpedPosition;
        if (startRow < endRow) {
            if (startCol < endCol){
                jumpedPosition = new Position(startRow+1, startCol+1);
            } else {
                jumpedPosition = new Position(startRow+1, startCol-1);
            }
        } else {
            if (startCol < endCol){
                jumpedPosition = new Position(startRow-1, startCol+1);
            } else {
                jumpedPosition = new Position(startRow-1, startCol-1);
            }
        }

        Piece jumped = getPieceAt(jumpedPosition);
        putPieceAt(jumpedPosition, null);
        return jumped;
    }


    /**
     * Checks to see whether or not there is a piece at the given position
     * @param endPosition the position to check
     * @return true if empty, false otherwise
     */
    public boolean noPieceInLanding(Position endPosition){
        return getPieceAt(endPosition) == null;
    }

    /**
     * Updates the version of the BoardView that the inactive player sees to match
     * with the version of the BoardView that was just modified
     */
    private void updateOtherBoardView(){
        if(activePlayer == PieceColor.RED){
            flippedBoardView = boardView.flipView();
        }
        else{
            boardView = flippedBoardView.flipView();
        }
    }

}