import java.util.Arrays;

/**
 * @author Alex Iacob ai9388
 * @filename DisjSets.java
 */

public class DisjSets {
    public int[] s;

    public DisjSets(int n) {
        s = new int[n];
        Arrays.fill(s, -1);
    }


    public void union(int r1, int r2) {
        s[r2] = r1;
    }


    public int find (int elt) {
        if (s[elt] < 0) {
            return elt;
        } else {
            return find(s[elt]);
        }
    }


    public int findC (int elt) {
        if (s[elt] < 0) {
            return elt;
        } else {
            s[elt] = find(s[elt]);
            return s[elt];
        }
    }


    @Override
    public String toString() {
        return Arrays.toString(s);
    }


    public int size() {
        int size = 0;
        for (Integer value : s) {
            if (value < 0) {
                size++;
            }
        }
        return size;
    }

}