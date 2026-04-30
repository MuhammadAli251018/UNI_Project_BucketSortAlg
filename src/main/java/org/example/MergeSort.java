package org.example;

import java.util.Arrays;


public class MergeSort {
    public static String[] mergeSort(String[] names) {
        if (names.length <= 1) return names;

        int middle = names.length / 2;
        String[] left  = Arrays.copyOfRange(names, 0, middle);
        String[] right = Arrays.copyOfRange(names, middle, names.length);

        String[] sortedLeft  = mergeSort(left);
        String[] sortedRight = mergeSort(right);

        return merge(sortedLeft, sortedRight);
    }

    private static String[] merge(String[] left, String[] right) {
        String[] result = new String[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            int compareResult = BucketSort.compareStrings(left[i], right[j]);
            if (compareResult <= 0) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }
}
