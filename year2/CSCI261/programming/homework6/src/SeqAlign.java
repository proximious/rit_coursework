/**
 * @author: Alex Iacob ai9388@rit.edu
 * @filename: SeqAlign.java
 */
public class SeqAlign {

    private int[][] M;
    public String X;
    public String Y;
    public int m;
    public int n;
    public int delta;
    public int alphaSemiMatch;
    public int alphaNoMatch;


    public SeqAlign(String X, String Y, int delta, int alphaSemiMatch, int alphaNoMatch) {
        this.X = X;
        this.Y = Y;
        m = X.length() + 1;
        n = Y.length() + 1;
        M = new int[m][n];
        this.delta = delta;
        this.alphaSemiMatch = alphaSemiMatch;
        this.alphaNoMatch = alphaNoMatch;
    }


    public int OPT_D() {
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                M[i][0] = i * delta;
                M[0][j] = j * delta;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int alpha = determineAlpha(X, Y, i - 1, j - 1);
                int a = alpha + M[i - 1][j - 1];
                int b = delta + M[i - 1][j];
                int c = delta + M[i][j - 1];

                M[i][j] = findMin(a, b, c);
            }
        }
        return M[m - 1][n - 1];
    }


    public int OPT_R() {
       return findOtherPaths(m - 1, n - 1);
    }


    public void printMatrix() {
        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j <= n - 1; j++) {
                System.out.print(M[i][j] + "\t");
            }
            System.out.println();
        }
    }


    public void showMatch() {
        int maxLength = m + n - 2;
        int i = m - 1;
        int j = n - 1;

        int xPos = maxLength;
        int yPos = maxLength;

        int[] xAnswer = new int[maxLength + 1];
        int[] yAnswer = new int[maxLength + 1];

        while (!(i == 0 || j == 0)) {
            int alpha = determineAlpha(X, Y, i - 1, j - 1);

            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                xAnswer[xPos--] = X.charAt(i - 1);
                yAnswer[yPos--] = Y.charAt(j - 1);
                i--;
                j--;
            } else if (M[i - 1][j - 1] + alpha == M[i][j]) {
                xAnswer[xPos--] = X.charAt(i - 1);
                yAnswer[yPos--] = Y.charAt(j - 1);
                i--;
                j--;
            } else if (M[i - 1][j] + delta == M[i][j]) {
                xAnswer[xPos--] = X.charAt(i - 1);
                yAnswer[yPos--] = '_';
                i--;
            } else if (M[i][j - 1] + delta == M[i][j]) {
                xAnswer[xPos--] = '_';
                yAnswer[yPos--] = Y.charAt(j - 1);
                j--;
            }
        }

        while (xPos > 0) {
            if (i > 0) {
                xAnswer[xPos--] = X.charAt(--i);
            } else {
                xAnswer[xPos--] = '_';
            }
        }

        while (yPos > 0) {
            if (j > 0) {
                yAnswer[yPos--] = Y.charAt(--j);
            } else {
                yAnswer[yPos--] = '_';
            }
        }

        int id = 1;
        for (i = maxLength; i >= 1; i--) {
            if ((char) yAnswer[i] == '_' && (char) xAnswer[i] == '_') {
                id = i + 1;
                break;
            }
        }

        for (i = id; i <= maxLength; i++) {
            System.out.print((char) xAnswer[i]);
        }
        System.out.println();

        for (i = id; i <= maxLength; i++) {
            System.out.print((char) yAnswer[i]);
        }
        System.out.println();
    }


    private int findMin(int a, int b, int c) {
        int smallest;
        if (a <= b && a <= c) {
            smallest = a;
        } else if (b <= c && b <= a) {
            smallest = b;
        } else {
            smallest = c;
        }
        return smallest;
    }


    private int determineAlpha(String string1, String string2, int index1, int index2) {
        int result;

        char char1 = string1.charAt(index1);
        char char2 = string2.charAt(index2);

        boolean char1vowel = false;
        boolean char2vowel = false;

        if (char1 == 'a' || char1 == 'e' || char1 == 'i' || char1 == 'o' || char1 == 'u') {
            char1vowel = true;
        }

        if (char2 == 'a' || char2 == 'e' || char2 == 'i' || char2 == 'o' || char2 == 'u') {
            char2vowel = true;
        }

        if (char1 == char2) {
            result = 0;
        } else if (char1vowel == char2vowel) {
            result = alphaSemiMatch;
        } else {
            result = alphaNoMatch;
        }

        return result;
    }


    private int findOtherPaths(int i, int j) {
        int a = 0;
        int b = 0;
        int c = 0;

        if (i == 0 && j == 0) {
            return 0;
        } else if (j == 0) {
            return delta * i;
        } else if (i == 0) {
            return delta * j;
        } else {
            int alpha = determineAlpha(X, Y, i - 1, j - 1);

            a = alpha + findOtherPaths(i - 1, j - 1);
            b = delta + findOtherPaths(i - 1, j);
            c = delta + findOtherPaths(i, j - 1);
        }

        return findMin(a, b, c);
    }
}
