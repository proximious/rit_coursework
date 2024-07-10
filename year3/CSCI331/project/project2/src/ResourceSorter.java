import java.util.Comparator;

/**
 * @author: Alex Iacob ai9388@rit.edu
 * @filename: ResourceSorter.java
 * <p>
 * Helper class for the animal conflict logic that sorts the array by resource value
 */
public class ResourceSorter implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        int r1 = o1.getResource();
        int r2 = o2.getResource();

        return Integer.compare(r2, r1);
    }
}
