package com.webcheckers.model;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * The rows of the board. It will create rows on the board
 * and place down spaces,
 */
public class Row implements Iterable {
    private int index;
    private ArrayList<Space> spaces;

    /**
     * Constructor for a Row object
     * @param index the index of the row
     */
    public Row(int index) {
        spaces = new ArrayList<>(8);
        this.index = index;
    }

    /**
     * Get the arraylist of spaces.
     * @return Arraylist of spaces
     */
    public ArrayList<Space> getSpaces() {
        return spaces;
    }

    /**
     * Set the spaces for Row
     * @param spaces ArrayList of spaces
     */
    public void setSpaces(ArrayList<Space> spaces) {
        this.spaces = spaces;
    }

    @Override
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }

    /**
     * get the index of the row
     * @return index of row
     */
    public int getIndex() {
        return index;
    }

}
