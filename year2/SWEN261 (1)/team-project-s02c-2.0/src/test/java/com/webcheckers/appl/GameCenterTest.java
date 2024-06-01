package com.webcheckers.appl;

import com.webcheckers.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


@Tag("Application-tier")
public class GameCenterTest {
    private GameCenter CuT;

    private Player red;
    private Player white;

    /**
     * Sets up the tests by making a GameCenter and two players
     */
    @BeforeEach
    public void setup(){
        CuT = new GameCenter();
        red = new Player("red");
        white = new Player("white");
    }

    /**
     * Tests the constructor
     */
    @Test
    public void testConstructor(){
        new GameCenter();
    }

    /**
     * Tests creating a new game
     * Checks if the boardID's match to validate
     */
    @Test
    public void testNewGame(){
        String expectedID = red.getName() + white.getName();
        String currentBoardID =  CuT.newGame("red", "white");
        assertEquals(expectedID, currentBoardID);
        currentBoardID = CuT.newGame("white", "red");
        assertNotEquals(expectedID, currentBoardID);
    }

    /**
     * Tests getBoardID
     */
    @Test
    public void testGetBoardId(){
        red.setColor(PieceColor.RED);
        red.setOpponent(white);
        white.setColor(PieceColor.WHITE);
        white.setOpponent(red);
        String expectedID = red.getName() + white.getName();
        String message = expectedID + " is the ID";

        CuT.newGame("red", "white");

        String currentID = CuT.getBoardID(red, red.getOpponent());
        assertEquals(expectedID, currentID, message);

        currentID = CuT.getBoardID(red, white);
        assertEquals(expectedID, currentID, message);

        currentID = CuT.getBoardID(white, red);
        assertEquals(expectedID, currentID, message);

        currentID = CuT.getBoardID(white, white.getOpponent());
        assertEquals(expectedID, currentID, message);
    }

    /**
     * Tests getBoard. Tests both methods and
     * compares if they return the same thing
     */
    @Test
    public void testGetBoard(){
        red.setColor(PieceColor.RED);
        red.setOpponent(red);
        white.setColor(PieceColor.WHITE);
        white.setOpponent(white);

        Player red2 = new Player("red2");
        Player white2 = new Player("white2");
        red2.setColor(PieceColor.WHITE);
        white2.setColor(PieceColor.RED);
        red2.setOpponent(white2);
        white2.setOpponent(red2);

        CuT.newGame("red", "white");
        String boardID = CuT.getBoardID(red, white);

        Board boardIDBoard = CuT.getBoard(boardID);
        Board playerBoard = CuT.getBoard(red);
        assertEquals(boardIDBoard, playerBoard);

        Board whiteplayerBoard = CuT.getBoard(white);
        assertEquals(boardIDBoard, whiteplayerBoard);

        CuT.newGame("red2", "white2");
        boardID = CuT.getBoardID(red2, white2);
        Board wrongPlayer = CuT.getBoard(red);
        boardIDBoard = CuT.getBoard(boardID);

        boolean pass;
        if(wrongPlayer == boardIDBoard){
            pass = false;
        }else{
            pass = true;
        }
        assertTrue(pass, "The boards do not match");

    }

    /**
     * Tests isInGame by checking them before they are in game and after a
     * new game is created
     */
    @Test
    public void testIsInGame(){
        String message = " in game";
        assertFalse(CuT.isInGame("red"), "Not" + message);
        assertFalse(CuT.isInGame("white"), "Not" + message);
        CuT.newGame("red", "white");
        assertTrue(CuT.isInGame("red"), "Is" + message);
        assertTrue(CuT.isInGame("white"), "Is" + message);
    }

    /**
     * Tests getOpponent by getting the opponent through
     * the player class and then comparing the result with
     * GameCenters getOpponent
     */
    @Test
    public void testGetOpponent(){
        red.setColor(PieceColor.RED);
        red.setOpponent(white);
        white.setColor(PieceColor.WHITE);
        white.setOpponent(red);

        CuT.newGame("red", "white");

        String expected = red.getOpponent().getName();
        String current = CuT.getOpponent("red");
        assertEquals(expected, current);

        expected = white.getOpponent().getName();
        current = CuT.getOpponent("white");
        assertEquals(expected, current);
    }
    @Test
    public void test_updateBoard(){
        String boardID=CuT.newGame(red.getName(),white.getName());
        red.setColor(PieceColor.RED);
        assertEquals(boardID,CuT.getBoardID(red,white));
        Board board=CuT.getBoard(boardID);
        board.movePiece(new Position(2,3), new Position(3,4));
        CuT.updateBoard(boardID,board);
        assertEquals(board,CuT.getBoard(boardID));
    }

}
