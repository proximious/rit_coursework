/**
 * @author: Alex Iacob ai9388@rit.edu
 * @filename: MergeSort.java
 * <p>
 * File runs an implementation of merge sort on a given array
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {

    public static void merge(int[] array, int left, int middle, int right) {
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
    }


    public static void sort(int[] array, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;

            sort(array, left, middle);
            sort(array, middle + 1, right);

            merge(array, left, middle, right);
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(args[0]));
        // get the size of the array
        int n = sc.nextInt();

        // construct array and fill
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        // mergeSort the array
        sort(a, 0, a.length - 1);
        // if array length < 20, print it
        if (n < 20) {
            System.out.println(Arrays.toString(a));
            // else for longer arrays, check the ordering
        } else {
            for (int i = 0; i < a.length - 1; i++)
                if (a[i] > a[i + 1])
                    System.out.println("UNSORTED");
            System.out.println("If working, this is the only output.");
        }
    }
}