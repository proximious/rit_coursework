package com.webcheckers.model;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * The unit test suite for the {@link Player} component.
 *
 * @author jec5704@rit.edu'>Julio Cuello
 */
@Tag("application-tier")
public class PlayerTest {

    /**
     * Tests NotNull for creating Player
     */
    @Test
    public void create_player() {
        Player CuT = new Player("Peepee");
        assertNotNull(CuT);
    }

    /**
     * Tests False for player is in game by default
     */
    @Test
    public void inGame_starts_false() {
        Player CuT = new Player("poopoo");
        assertFalse(CuT.isInGame());
    }

    /**
     * Tests Null for player's opponent by default
     */
    @Test
    public void oponent_starts_Null() {
        Player CuT = new Player("Juan");
        assertNull(CuT.getOpponent());
    }

    /**
     * Tests setter function for player opponent
     * Tests Equal for getter function for player's opponent after setting
     */
    @Test
    public void setOpponent_works() {
        Player CuT = new Player("Juan");
        Player opponent = new Player("puto");
        CuT.setOpponent(opponent);
        assertEquals(CuT.getOpponent(), opponent);
    }

    /**
     * Tests true for setter function for player in game
     */
    @Test
    public void setInGame_works() {
        Player CuT = new Player("Juan");
        CuT.setInGame(true);
        assertTrue(CuT.isInGame());
    }

    /**
     * Tests equals for getter function for player's name
     */
    @Test
    public void getName_works() {
        Player CuT = new Player("Juan");
        assertEquals(CuT.getName(), "Juan");
    }

    /**
     * Tests for setter function for player to be red player
     */
    @Test
    public void setGetColorRed_works() {
        Player CuT = new Player("Jack");
        CuT.setColor(PieceColor.RED);
        assertEquals(CuT.getColor(), PieceColor.RED);
    }

    /**
     * Tests for setter function for player to be white player
     */
    @Test
    public void setGetColorWhite_works() {
        Player CuT = new Player("Jack");
        CuT.setColor(PieceColor.WHITE);
        assertEquals(CuT.getColor(), PieceColor.WHITE);
    }
}
