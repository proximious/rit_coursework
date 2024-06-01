package com.webcheckers.util;

import com.webcheckers.model.*;

import java.util.Objects;

/**
 * Class runs the validation for each player movement
 */
public class Validate {
    public String messageString;
    private Board gameBoard;

    public Validate(Board gameBoard) {
        Objects.requireNonNull(gameBoard, "gameBoard must not be null");
        this.gameBoard = gameBoard;
    }

    public Space getSpace(int row, int col){
        Row specifiedRow = gameBoard.getActiveBoardView().getRows().get(row);
        return specifiedRow.getSpaces().get(col);
    }

    /**
     * gets the message string
     * @return message string
     */
    public String getMessageString(){
        return this.messageString;
    }


    /**
     * Validates that the given move is a simple forwards move with no reference to other made or available moves
     * @param actionData the move to validate
     * @return true if it is a valid forwards simple move, false otherwise
     */
    public boolean justValidateForwardSimpleMove(Move actionData){
        int startCol = actionData.getStartCell();
        int endCol = actionData.getEndCell();
        int startRow = actionData.getStartRow();
        int endRow = actionData.getEndRow();

        if (startRow - 1 == endRow) {
            if (startCol + 1 == endCol || startCol - 1 == endCol) {
                messageString = "Valid simple move";
                return true;
            } else {
                messageString = "A simple move can only move one row and one column to the left or right";
                return false;
            }
        }else{
            messageString = "A simple move can only move one row towards the opponent";
            return false;
        }
    }

    /**
     * Validates that the given move is a simple backwards move with no reference to other made or available moves
     * @param actionData the move to validate
     * @return true if it is a valid backwards simple move, false otherwise
     */
    public boolean justValidateBackwardSimpleMove(Move actionData){
        int startCol = actionData.getStartCell();
        int endCol = actionData.getEndCell();
        int startRow = actionData.getStartRow();
        int endRow = actionData.getEndRow();

        Piece pieceAtStart = gameBoard.getPieceAt(actionData.getStart());
        PieceType type = pieceAtStart.getType();

        if (type != PieceType.KING){
            messageString = "Only King pieces can move towards you";
            return false;
        }

        if (startRow + 1 == endRow) {
            if (startCol + 1 == endCol || startCol - 1 == endCol) {
                messageString = "Valid simple backwards move";
                return true;
            } else {
                messageString = "A simple backwards move can only move one row and one column to the left or right";
                return false;
            }
        } else {
            messageString = "A simple backwards move can only move one row towards you";
            return false;
        }
    }

    /**
     * Validates that the given move is a backward jump move with no reference to other made or available moves
     * @param actionData the move to validate
     * @return true if it is a valid backward jump move, false otherwise
     */
    public boolean justValidateForwardJumpMove(Move actionData) {
        int startCol = actionData.getStartCell();
        int endCol = actionData.getEndCell();
        int startRow = actionData.getStartRow();
        int endRow = actionData.getEndRow();

        if (startRow - 2 == endRow) {
            PieceColor activeColor = gameBoard.getActivePlayer();
            if (startCol + 2 == endCol || startCol - 2 == endCol) {
                Position jumpingOverPosition;
                if (startCol + 2 == endCol){
                    jumpingOverPosition = new Position(startRow-1, startCol+1);

                } else {
                    jumpingOverPosition = new Position(startRow-1, startCol-1);
                }
                return validateJumpingOver(activeColor, jumpingOverPosition);
            }
        }
        messageString = "A jump move can only jump over one row";
        return false;
    }

    /**
     * Validates that the given move is a forwards jump move with no reference to other made or available moves
     * @param actionData the move to validate
     * @return true if it is a valid forward jump move, false otherwise
     */
    public boolean justValidateBackwardJumpMove(Move actionData) {
        int startCol = actionData.getStartCell();
        int endCol = actionData.getEndCell();
        int startRow = actionData.getStartRow();
        int endRow = actionData.getEndRow();

        Piece pieceAtStart = gameBoard.getPieceAt(actionData.getStart());
        PieceType type = pieceAtStart.getType();

        if (type != PieceType.KING){
            messageString = "Only King pieces can move towards you";
            return false;
        }

        if (startRow + 2 == endRow) {
            PieceColor activeColor = gameBoard.getActivePlayer();
            if (startCol + 2 == endCol || startCol - 2 == endCol) {
                Position jumpingOverPosition;
                if (startCol + 2 == endCol){
                    jumpingOverPosition = new Position(startRow + 1, startCol + 1);
                } else {
                    jumpingOverPosition = new Position(startRow + 1, startCol - 1);
                }
                return validateJumpingOver(activeColor, jumpingOverPosition);
            }
        }
        messageString = "A jump move can only jump over one row";
        return false;
    }

    /**
     * Validates that there is a piece being jumped over and it belongs to the inactive player
     * @param activeColor the color making the move
     * @param jumpingOverPosition the position the player is jumping over
     * @return true if the piece being jumped over belongs to the other player, false if there is no piece or the
     * piece belongs to the player making the move
     */
    private boolean validateJumpingOver(PieceColor activeColor, Position jumpingOverPosition) {
        Piece jumpingOverPiece = gameBoard.getPieceAt(jumpingOverPosition);

        if (jumpingOverPiece == null){
            messageString = "A jump move must be made over an opponents piece";
            return false;
        } else if(jumpingOverPiece.getColor() == activeColor){
            messageString = "A jump move must be made over an opponents piece";
            return false;
        }
        else{
            messageString = "Valid jump move";
            return true;
        }
    }

    /**
     * Checks the spaces diagonally in front of a given position to see if a jump is available
     * @param startPosition the starting position of the potential jump
     * @return true if available, false otherwise
     */
    public boolean forwardJumpAvailable(Position startPosition) {
        int startRow = startPosition.getRow();
        int startCol = startPosition.getCell();

        if (startRow > 1) {
            if (startCol < 6) {
                Position rightDiagonal = new Position(startRow - 1, startCol + 1);
                Position rightDiagonalEnd = new Position(startRow - 2, startCol + 2);
                if (gameBoard.containsInactivePiece(rightDiagonal) && gameBoard.noPieceInLanding(rightDiagonalEnd)) {
                    return true;
                }
            }
            if (startCol > 1) {
                Position leftDiagonal = new Position(startRow - 1, startCol - 1);
                Position leftDiagonalEnd = new Position(startRow - 2, startCol - 2);
                return gameBoard.containsInactivePiece(leftDiagonal) && gameBoard.noPieceInLanding(leftDiagonalEnd);
            }
        }

        return false;
    }

    /**
     * Checks the spaces diagonally in behind a given position to see if a jump is available
     * @param startPosition the starting position of the potential jump
     * @return true if available, false otherwise
     */
    public boolean backwardJumpAvailable(Position startPosition) {
        int startRow = startPosition.getRow();
        int startCol = startPosition.getCell();

        if (startRow < 6) {
            if (startCol < 6) {
                Position rightDiagonal = new Position(startRow + 1, startCol + 1);
                Position rightDiagonalEnd = new Position(startRow + 2, startCol + 2);
                if (gameBoard.containsInactivePiece(rightDiagonal) && gameBoard.noPieceInLanding(rightDiagonalEnd)) {
                    return true;
                }
            }
            if (startCol > 1) {
                Position leftDiagonal = new Position(startRow + 1, startCol - 1);
                Position leftDiagonalEnd = new Position(startRow + 2, startCol - 2);
                return gameBoard.containsInactivePiece(leftDiagonal) && gameBoard.noPieceInLanding(leftDiagonalEnd);
            }
        }

        return false;
    }

    /**
     * Iterates through each square on the board checking to see if any of the
     * active players pieces can make a jump
     * @return true if at least one jump is available, false otherwise
     */
    public boolean anyJumpsAvailable(){
        Position testPosition;
        for(Row row:gameBoard.getActiveBoardView().getRows()) {
            for(Space space:row.getSpaces()) {
                Piece pieceAtSpace = space.getPiece();
                if(pieceAtSpace != null){
                    if(pieceAtSpace.getColor() == gameBoard.getActivePlayer()){
                        testPosition = new Position(row.getIndex(), space.getCellIdx());
                        if (forwardJumpAvailable(testPosition)){
                            return true;
                        }
                        if(pieceAtSpace.getType() == PieceType.KING){
                            if (backwardJumpAvailable(testPosition)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


}
