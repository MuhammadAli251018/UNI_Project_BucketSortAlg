package org.example;

import java.util.ArrayList;
import java.util.List;

public class BucketSort {

    public static String[] bucketSort(String[] name) {
        if (name == null || name.length == 0) {
            return new String[0];
        }

        List<String>[] buckets = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (String s : name) {
            if (s != null && !s.isEmpty()) {
                char firstChar = Character.toLowerCase(s.charAt(0));
                if (firstChar >= 'a' && firstChar <= 'z') {
                    int bucketIndex = firstChar - 'a';
                    buckets[bucketIndex].add(s);
                }
            }
        }

        System.out.println("------- BEFORE SORTING BUCKETS -------");
        for (int i = 0; i < buckets.length; i ++) {
            System.out.println("Bucket " + i + ": " + buckets[i]);
        }

        String[] result = new String[name.length];
        int currentIndex = 0;
        for (List<String> bucket : buckets) {
            if (!bucket.isEmpty()) {
                String[] bucketArray = bucket.toArray(new String[0]);
                String[] sortedBucket = MergeSort.mergeSort(bucketArray);
                for (String s : sortedBucket) {
                    result[currentIndex++] = s;
                }
            }
        }
        return result;
    }

    static int compareStrings(String s1, String s2) {
        int minLength = Math.min(s1.length(), s2.length());

        for (int i = 0; i < minLength; i++) {
            char char1 = Character.toLowerCase(s1.charAt(i));
            char char2 = Character.toLowerCase(s2.charAt(i));

            if (char1 != char2) return Character.compare(char1, char2);
        }

        return Integer.compare(s1.length(), s2.length());
    }
}