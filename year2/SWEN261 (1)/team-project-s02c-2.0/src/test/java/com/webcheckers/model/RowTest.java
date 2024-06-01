package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-Tier")
public class RowTest {
    private final int INDEX = 4;
    private final ArrayList<Space> spaces = new ArrayList<>(8);
    private final ArrayList<Space> test_new_space = new ArrayList<>(12);

    /**
     * Tests NotNull after creating constructor
     */
    @Test
    public void constructorRow(){
        Row CuT = new Row(INDEX);
        assertNotNull(CuT);
    }

    /**
     * Tests getter function for row space
     */
    @Test
    public void test_getSpaces(){
        Row CuT = new Row(INDEX);
        assertNotNull(CuT.getSpaces());
        assertEquals(CuT.getSpaces(), spaces);
    }

    /**
     * Tests setter function for row space
     */
    @Test
    public void test_setSpaces(){
        Row CuT = new Row(INDEX);
        CuT.setSpaces(test_new_space);
        assertNotNull(CuT.getSpaces());
        assertEquals(CuT.getSpaces(), test_new_space);
    }

    /**
     * Tests iterator() for NotNull
     */
    @Test
    public void test_iterator(){
        Row CuT = new Row(INDEX);
        assertNotNull(CuT.iterator());
    }

    /**
     * Tests getter function for index
     */
    @Test
    public void test_getIndex(){
        Row CuT = new Row(INDEX);
        assertEquals(CuT.getIndex(), 4);
    }
}
