package com.webcheckers.ui;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test suite for the {@link BoardView} component.
 *
 * @author: Alex Iacob ai9388@rit.edu
 */

@Tag("UI-tier")
public class BoardViewTest {
    @Test
    public void constructor_boardView(){
        final BoardView CuT_boardView = new BoardView();
        assertNotNull(CuT_boardView);
    }

    @Test
    public void test_flipView() {

    }
}
