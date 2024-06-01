package com.webcheckers.appl;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;


import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Application-tier")
public class PlayerLobbyTest {

    /**
     * Tests if a name is invalid
     */
    @Test
    void invalidName(){
        PlayerLobby playerLobby = new PlayerLobby();
        String empty = "";
        String invalid = "!!!*invalid**!";

        assertFalse(playerLobby.validUsername(empty), "The empty string should not be a valid username");
        assertFalse(playerLobby.validUsername(invalid), "Names that are non alphanumeric are not valid");
    }

    /**
     * Tests if a name is valid
     */
    @Test
    void validNames(){
        PlayerLobby playerLobby = new PlayerLobby();
        String message = "is valid";
        String one_letter = "j";
        String one_number = "32";
        String word  = "Hello";
        boolean name1 = playerLobby.validUsername(one_letter);
        assertTrue(name1, one_letter + message);
        boolean name2 = playerLobby.validUsername(one_number);
        assertTrue(name2, one_number + message);
        boolean name3 = playerLobby.validUsername(word);
        assertTrue(name3, word + message);
    }

    /**
     * Tests adding new players to the player lobby
     */
    @Test
    void newPlayers(){
        PlayerLobby playerLobby = new PlayerLobby();
        String message = "added";
        String name1 = "KitKat";
        String name2 = "Jolly Rancher";
        String wrong = "!!HEY!!";
        playerLobby.newPlayer(name1);
        playerLobby.newPlayer(name2);

        boolean pass;
        if (playerLobby.getPlayer("KitKat").getName().equals(name1)) {
            pass = true;
        } else {
            pass = false;
        }
        assertTrue(pass, name1 + message);
        if (playerLobby.getPlayer("Jolly Rancher").getName().equals(name2)) {
            pass = true;
        } else {
            pass = false;
        }
        assertTrue(pass, name2 + message);
        try{
            playerLobby.newPlayer(wrong);
        }catch (PlayerLobbyExceptions e){
            pass = false;
            assertFalse(pass, wrong + " Not added");
        }
    }

    /**
     * Tests getting players from the playerLobby
     */
    @Test
    void getPlayers(){
        PlayerLobby playerLobby = new PlayerLobby();
        playerLobby.newPlayer("KitKat");
        playerLobby.newPlayer("Jolly Rancher");
        playerLobby.newPlayer("Reese");
        Player player1 = new Player("KitKat");
        Player player2 = new Player("Jolly Rancher");
        Player player3 = new Player("Reese");
        Player got1 = playerLobby.getPlayer("KitKat");
        Player got2 = playerLobby.getPlayer("Jolly Rancher");
        Player got3 = playerLobby.getPlayer("Reese");
        boolean pass;
        if(got1.getName().equals(player1.getName())){
            pass = true;
        }else{
            pass = false;
        }
        assertTrue(pass);
        if(got2.getName().equals(player2.getName())){
            pass = true;
        }else{
            pass = false;
        }
        assertTrue(pass);
        if(got3.getName().equals(player3.getName())){
            pass = true;
        }else{
            pass = false;
        }
        assertTrue(pass);
    }

    /**
     * Tests getting the active player count from the playerLobby
     */
    @Test
    void getActivePlayerCount(){
        PlayerLobby playerLobby = new PlayerLobby();
        playerLobby.newPlayer("KitKat");
        playerLobby.newPlayer("Jolly Rancher");
        int expected = 2;
        int actual = playerLobby.getActivePlayerCount();
        assertEquals(expected, actual, "Size should be 2");
    }

    /**
     * Tests the removing player features
     */
    @Test
    void removePlayers(){
        PlayerLobby playerLobby = new PlayerLobby();
        playerLobby.newPlayer("KitKat");
        playerLobby.newPlayer("Jolly Rancher");
        playerLobby.newPlayer("Hershey");
        int end = 2;
        int start = 3;
        int actual = playerLobby.getActivePlayerCount();
        assertEquals(start, actual);
        playerLobby.removePlayer("KitKat");
        actual = playerLobby.getActivePlayerCount();
        assertEquals(end, actual);
    }

    /**
     * Tests clearing the playerLobby of all its players
     */
    @Test
    void clearPlayers(){
        PlayerLobby playerLobby = new PlayerLobby();
        playerLobby.newPlayer("KitKat");
        playerLobby.newPlayer("Jolly Rancher");
        playerLobby.newPlayer("Hershey");
        int end = 0;
        int start = 3;
        int actual = playerLobby.getActivePlayerCount();
        assertEquals(start, actual);
        playerLobby.clearLobby();
        actual = playerLobby.getActivePlayerCount();
        playerLobby.clearLobby();
        assertEquals(end, actual);
    }

    /**
     * Tests getting the current number of players in the lobby
     */
    @Test
    void numPlayers(){
        PlayerLobby playerLobby = new PlayerLobby();
        playerLobby.newPlayer("KitKat");
        playerLobby.newPlayer("Jolly Rancher");
        playerLobby.newPlayer("Hershey");
        int expected = 3;
        int actual = playerLobby.getNumPlayers();
        assertEquals(expected, actual);
    }

    /**
     * Tests checking if the requested player is in lobby
     */
    @Test
    void playerInLobby(){
        PlayerLobby playerLobby = new PlayerLobby();
        Player player1 = new Player("KitKat");
        assertFalse(playerLobby.isPlayerInLobby(player1));
        playerLobby.newPlayer("KitKat");
        assertTrue(playerLobby.isPlayerInLobby(player1));
        assertFalse(playerLobby.isPlayerInLobby(null));
    }

    /**
     * Tests getting the activePlayerMap. Copies the activePlayer HashMap and checks whats inside
     */
    @Test
    void activePlayerMap(){
        PlayerLobby playerLobby = new PlayerLobby();
        playerLobby.newPlayer("KitKat");
        playerLobby.newPlayer("Jolly Rancher");
        playerLobby.newPlayer("Hershey");
        HashMap<String, Player>  currentPlayers = playerLobby.getActivePlayers();
        boolean pass;
        if(currentPlayers.containsKey("KitKat") & currentPlayers.containsKey("Jolly Rancher") & currentPlayers.containsKey("Hershey")){
            pass = true;
        }else{
            pass = false;
        }
        assertTrue(pass);
    }
    /**
     * Tests getting the activePlayerMap. Copies the activePlayer HashMap and checks whats inside
     */
    @Test
    void test_username_Taken() {
        try {
            PlayerLobby playerLobby = new PlayerLobby();
            playerLobby.newPlayer("KitKat");
            playerLobby.newPlayer("Jolly Rancher");
            playerLobby.newPlayer("KitKat");
            HashMap<String, Player> currentPlayers = playerLobby.getActivePlayers();
        }
        catch(PlayerLobbyExceptions exceptions){

        }
    }
    /**
     * Tests getting the activePlayerMap. Copies the activePlayer HashMap and checks whats inside
     */
    @Test
    void test_playersOnline(){
        PlayerLobby playerLobby = new PlayerLobby();
        ArrayList<String> playerList= new ArrayList<>();
        playerLobby.newPlayer("KitKat");
        playerLobby.newPlayer("Jolly Rancher");
        playerLobby.newPlayer("Hershey");
        playerList.add("KitKat");
        playerList.add("Jolly Rancher");
        playerList.add("Hershey");
        ArrayList<String>playersOnline= playerLobby.playersOnline();
        for (String s:playerList) {
            assertTrue(playersOnline.contains(s));
        }
    }

}
