package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * PlayerLobby
 * Handles all of the players that are
 * currently signed into the game
 */
public class PlayerLobby {
    private HashMap<String, Player> activePlayers;
    private int numPlayers;

    /**
     * Initializes activePlayers HashMap
     */
    public PlayerLobby(){
        activePlayers = new HashMap<String, Player>();
        this.numPlayers = 0;
    }

    public ArrayList<String> playersOnline(){
        ArrayList<String> plo = new ArrayList<>();
        for (String name:activePlayers.keySet()){
            plo.add(name);
        }
        return plo;
    }

    /**
     *
     * @return HashMap that contains all active players
     */
    public HashMap<String, Player> getActivePlayers(){
        return activePlayers;
    }

    /**
     *  Gets the total number of active players
     * @return int of activePlayerCount
     */
    public int getActivePlayerCount(){
        return this.activePlayers.size();
    }

    /**
     * Returns true or false depending if the target player is in the lobby
     * @param player: player target
     * @return true if player in lobby false if not
     */
    public boolean isPlayerInLobby(Player player){
        if (getActivePlayerCount() == 0){
            return false;
        }
        if (player == null){
            return false;
        }
        return activePlayers.containsKey(player.getName());
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Creates a new player and adds them to the active lobby
     * @param name Name of the new player
     * @return returns the new player
     */
    public Player newPlayer(String name)throws PlayerLobbyExceptions{
        // Need to check if name is taken
        if(activePlayers.containsKey(name)){
            String errorMessage = "The username '" + name + "' is already taken. " +
                    "Please use a different name. ";
            throw new PlayerLobbyExceptions(errorMessage);
        }

        if(!validUsername(name)){
            String errorMessage = "The username '" + name + "' contains invalid characters. " +
                    "Please only use alphanumeric characters and spaces.";

            throw new PlayerLobbyExceptions(errorMessage);
        }

        final Player newPlayer = new Player(name);
        this.activePlayers.put(name, newPlayer);
        numPlayers++;
        return newPlayer;
    }

    /**
     * Gets an active player from the activePlayers HashMap
     * @param name the name of the player being requested
     * @return returns the player that was requested
     */
    public Player getPlayer(String name){
        return activePlayers.get(name);
    }

    /**
     * Clears the activePlayers HashMap
     */
    public void clearLobby(){
        this.activePlayers.clear();
    }

    /**
     * Removes the player from the HashMap
     * @param name: the name to be removed
     */
    public void removePlayer(String name){
        this.activePlayers.remove(name);
    }

    /**
     * Checks that the username entered uses valid characters and is not in use
     * @param username the username to be checked
     * @return true if can be used, false otherwise
     */
    public boolean validUsername(String username){
        if (username.isBlank()) {
            return false;
        }
        Pattern invalidChars = Pattern.compile("[^a-zA-Z0-9 ]");
        return !invalidChars.matcher(username).find();
    }

}
