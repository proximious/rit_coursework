import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Alex Iacob ai9388@rit.edu
 * @filename DivideAndConquer.java
 * <p>
 * File runs implementation of the closest pair algorithm via divide and conquer
 */
public class DivideAndConquer extends ClosestPairAlg {
    BruteForce bruteForce = new BruteForce();

    @Override
    public Triple closestPair(Point[] P) {
        Pointx[] xValues = new Pointx[P.length];
        ArrayList<Pointy> yValues;
        Pointx[] left;
        Pointx[] right;
        int splitPoint;
        double testSmallestDistance;
        Triple delta;

        if (P.length <= 3) {
            return bruteForce.closestPair(P);
        } else {
            // sort the points by x coordinate
            for (int i = 0; i < P.length; i++) {
                xValues[i] = new Pointx(P[i].x, P[i].y);
            }
            Arrays.sort(xValues);
            splitPoint = xValues.length / 2;
            // split the array into left and right from a separation line
            left = Arrays.copyOfRange(xValues, 0, splitPoint);
            right = Arrays.copyOfRange(xValues, splitPoint, P.length);

            Triple middleLeft = closestPair(left);
            Triple middleRight = closestPair(right);

            delta = findMin(middleLeft, middleRight);

            // delete any point greater than delta
            yValues = new ArrayList<>();
            for (Pointx i : xValues) {
                if (i.distance(P[splitPoint]) < delta.dist) {
                    yValues.add(new Pointy(i.x, i.y));
                }
            }

            // sort the rest of the points by y value
            Pointy[] newYvalues = new Pointy[yValues.size()];
            newYvalues = yValues.toArray(newYvalues);
            Arrays.sort(newYvalues);

            // scanning through the new y values to find the smallest distance
            for (int i = 0; i < newYvalues.length; i++) {
                for (int j = i + 1; j < newYvalues.length; j++) {
                    testSmallestDistance = newYvalues[i].distance(newYvalues[j]);

                    if (testSmallestDistance < delta.dist) {
                        delta.dist = testSmallestDistance;
                        delta = new Triple(newYvalues[i], newYvalues[j], delta.dist);
                    }
                }
            }
        }
        return delta;
    }

    public Triple findMin(Triple leftHalf, Triple rightHalf) {
        if (leftHalf == null) {
            return rightHalf;
        } else if (rightHalf == null) {
            return leftHalf;
        } else if (leftHalf.dist < rightHalf.dist) {
            return leftHalf;
        } else {
            return rightHalf;
        }
    }

}
