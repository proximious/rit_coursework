/**
 * @author Alex Iacob ai9388
 * @filename: DisjHeight.java
 */
public class DisjHeight extends DisjSets{

    public DisjHeight(int n) {
        super(n);
    }

    @Override
    public void union(int r1, int r2) {
        if (s[r1] < s[r2]){
            s[r2] = r1;
        } else if (s[r1] == s[r2]) {
            s[r2] = r1;
            s[r1]--;
        } else {
            s[r1] = r2;
        }
    }
}
