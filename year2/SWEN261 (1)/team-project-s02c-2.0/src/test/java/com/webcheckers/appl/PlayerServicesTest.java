package com.webcheckers.appl;

import com.webcheckers.model.Board;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Application-tier")
public class PlayerServicesTest {
    private final GameCenter gameCenter = new GameCenter();
    private Player red = new Player("red");
    private Player white = new Player("white");
    private final Board board = new Board("red", "white");
    PlayerServices CuT;

    /**
     * Sets up a PlayerServices
     */
    @BeforeEach
    public void setup(){
        CuT = new PlayerServices(board, gameCenter);
    }

    /**
     * Tests the constructor by creating a new PLayerServices and checks
     * for it to be not null
     */
    @Test
    public void testConstructor(){
        PlayerServices playerServices = new PlayerServices(board, gameCenter);
        assertNotNull(playerServices);
    }

    /**
     * Tests getting the board out of PlayerServices by checking if the board
     * returned is equal to the board it was initially given
     */
    @Test
    public void testGetBoard(){
        assertEquals(board, CuT.getBoard());
    }

    /**
     * Tests setting the board in PlayerServices by creating two boards and first
     * seeing if the first two boards do not match up. Then it will set the boards to match
     * and checks if they are the same.4
     */
    @Test
    public void testSetBoard(){
        Board board1 = new Board("red", "white");
        Board board2 = new Board("white", "red");

        CuT.setBoard(board2);
        boolean pass;
        if(CuT.getBoard() != board1){
            pass = true;
        }else{
            pass = false;
        }
        assertTrue(pass);
        CuT.setBoard(board1);
        if(CuT.getBoard() == board1){
            pass = true;
        }else{
            pass = false;
        }
        assertTrue(pass);
    }
}
