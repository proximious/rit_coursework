//Alex Iacob
//ai9388@rit.edu

package htree;

import static turtle.Turtle.*;

public class HTree {
    public static void init(int length, int depth){
        Turtle.setWorldCoordinates(-length*2, -length*2, length*2, length*2);
        Turtle.title("H-Tree, depth: " + depth);
        Turtle.speed(0);
    }

    public static void drawHtree(float length, int depth){
        if (depth > 0){
            // start in the center of the H, then move to upper right
            Turtle.forward(length / 2);
            Turtle.left(90);
            Turtle.forward(length / 2);
            Turtle.right(90);

            // recurse
            drawHtree(length / 2, depth - 1);

            // move to lower right of H
            Turtle.right(90);
            Turtle.forward(length);
            Turtle.left(90);

            // recurse
            drawHtree(length / 2, depth - 1);

            // move to upper left of H
            Turtle.left(90);
            Turtle.forward(length / 2);
            Turtle.left(90);
            Turtle.forward(length);
            Turtle.right(90);
            Turtle.forward(length / 2);
            Turtle.right(90);

            // recurse
            drawHtree(length / 2, depth - 1);

            // move to lower left of H
            Turtle.right(90);
            Turtle.forward(length);
            Turtle.left(90);

            // recurse
            drawHtree(length / 2, depth - 1);

            // return to center of H
            Turtle.left(90);
            Turtle.forward(length / 2);
            Turtle.right(90);
            Turtle.forward(length / 2);
        }
    }

    public static void main(String[] args) {
        int MAX_SEGMENT_LENGTH = 200;

        int depth = Integer.parseInt(args[0]);

        // error checking
        if (depth < 0){
            System.out.println("The depth must be greater than or equal to 0.");
        }

        init(MAX_SEGMENT_LENGTH, depth);

        drawHtree(MAX_SEGMENT_LENGTH, depth);
    }

}
