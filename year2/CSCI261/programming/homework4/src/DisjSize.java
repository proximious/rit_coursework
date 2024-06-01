/**
 * @author Alex Iacob ai9388
 * @filename: DisjSize.java
 */
public class DisjSize extends DisjSets{

    public DisjSize(int n) {
        super(n);
    }

    @Override
    public void union(int r1, int r2) {
        if (s[r1] <= s[r2]){
            s[r1] += s[r2];
            s[r2] = r1;
        } else {
            s[r2] += s[r1];
            s[r1] = r2;
        }
    }
}
