/**
 * @author Alex Iacob ai9388@rit.edu
 * @filename BruteForce.java
 * <p>
 * File runs implementation of the closest pair algorithm via brute force
 */
public class BruteForce extends ClosestPairAlg {

    @Override
    public Triple closestPair(Point[] P) {
        double smallestDistance = P[0].distance(P[1]);
        Triple result = new Triple(P[0], P[1], smallestDistance);
        double testSmallestDistance;

        if (P.length <= 2) {
            result = new Triple(P[0], P[1], P[0].distance(P[1]));
            return result;
        } else {
            for (int i = 0; i < P.length; i++) {
                for (int j = i + 1; j < P.length; j++) {
                    testSmallestDistance = P[i].distance(P[j]);

                    if (testSmallestDistance < smallestDistance) {
                        smallestDistance = testSmallestDistance;
                        result = new Triple(P[i], P[j], testSmallestDistance);
                    }
                }
            }
        }
        return result;
    }
}
