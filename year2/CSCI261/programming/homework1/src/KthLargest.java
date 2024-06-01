/*
@author: Alex Iacob ai9388
@filename: KthLargest.java
File sorts a given array of k elements and prints the array with the largest number
 */

public class KthLargest {

    public static int kthLargest(int [] a, int k) {
        int [] temp = new int[k];

        int arrayLength = a.length;
        int tempLength = temp.length;
        int tempIndex = 0;
        int index = 0;
        // initializing a minimum value as a placeholder
        int minimum = 0;

        System.arraycopy(a, 0, temp, 0, tempLength);

        int ct = 0;
        while (tempIndex < tempLength){
            if (temp[ct] < minimum){
                minimum = temp[ct];
                index = ct;
            }
            tempIndex++;
            ct++;
        }

        int i = k;
        while( i < arrayLength ){
            if (a[i] > minimum) {
                temp[index] = a[i];
                minimum = a[i];
            }

            for(int j = 0; j < tempLength; j ++){
                if (temp[j] < minimum) {
                    minimum = temp[j];
                    index = j;
                }
            }
            i++;
        }
        return minimum;
    }
}
