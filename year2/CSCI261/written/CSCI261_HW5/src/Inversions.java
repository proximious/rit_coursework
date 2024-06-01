/**
 * @author Alex Iacob ai9388@rit.edu
 * @filename: Inversions.java
 * <p>
 * File determines the amount of inversions necessary
 */

public class Inversions {

    public static long inversions(int[] a) {
        return sort(a, 0, a.length - 1);
    }


    public static long merge(int[] array, int left, int middle, int right) {
        long inversion_count = 0;
        int num1 = middle - left + 1;
        int num2 = right - middle;

        int[] tempLeft = new int[num1];
        int[] tempRight = new int[num2];

        for (int i = 0; i < num1; ++i)
            tempLeft[i] = array[left + i];

        for (int j = 0; j < num2; ++j)
            tempRight[j] = array[middle + 1 + j];

        int i = 0;
        int j = 0;
        int k = left;

        while (i < num1 && j < num2) {
            if (tempLeft[i] <= tempRight[j]) {
                array[k] = tempLeft[i];
                i++;
            } else {
                array[k] = tempRight[j];
                inversion_count += tempLeft.length - i;
                j++;
            }
            k++;
        }

        while (i < num1) {
            array[k] = tempLeft[i];
            i++;
            k++;
        }

        while (j < num2) {
            array[k] = tempRight[j];
            j++;
            k++;
        }

        return inversion_count;
    }


    public static long sort(int[] array, int left, int right) {
        long inversion_count = 0;
        if (left < right) {
            int middle = left + (right - left) / 2;

            inversion_count += sort(array, left, middle);
            inversion_count += sort(array, middle + 1, right);

            inversion_count += merge(array, left, middle, right);
        } else {
            return 0;
        }
        return inversion_count;
    }
}
