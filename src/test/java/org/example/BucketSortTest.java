package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BucketSortTest {

    @Test
    void testEmptyArray() {
        String[] input = {};
        String[] expected = {};
        assertArrayEquals(expected, BucketSort.bucketSort(input));
    }

    @Test
    void testSingleElementArray() {
        String[] input = {"Apple"};
        String[] expected = {"Apple"};
        assertArrayEquals(expected, BucketSort.bucketSort(input));
    }

    @Test
    void testMultipleElementsDifferentFirstLetter() {
        String[] input = {"Banana", "Apple", "Cherry", "Date"};
        String[] expected = {"Apple", "Banana", "Cherry", "Date"};
        assertArrayEquals(expected, BucketSort.bucketSort(input));
    }

    @Test
    void testMultipleElementsSameFirstLetter() {
        String[] input = {"Banana", "Bacon", "Berry"};
        String[] expected = {"Bacon", "Banana", "Berry"};
        assertArrayEquals(expected, BucketSort.bucketSort(input));
    }

    @Test
    void testMixedCaseElements() {
        String[] input = {"apple", "Banana", "cherry", "Date"};
        String[] expected = {"apple", "Banana", "cherry", "Date"}; // Case-insensitive sort, but original case preserved
        assertArrayEquals(expected, BucketSort.bucketSort(input));
    }

    @Test
    void testWithDuplicates() {
        String[] input = {"Apple", "Banana", "Apple", "Cherry"};
        String[] expected = {"Apple", "Apple", "Banana", "Cherry"};
        assertArrayEquals(expected, BucketSort.bucketSort(input));
    }

    @Test
    void testLongerList() {
        String[] input = {
            "Zebra", "apple", "Xylophone", "banana", "Cat", "dog", "Elephant", "fox",
            "Grape", "house", "Igloo", "jacket", "Kite", "lemon", "Monkey", "nut",
            "Orange", "pear", "Queen", "rabbit", "Snake", "tree", "Umbrella", "violet",
            "Water", "yak"
        };
        String[] expected = {
            "apple", "banana", "Cat", "dog", "Elephant", "fox", "Grape", "house",
            "Igloo", "jacket", "Kite", "lemon", "Monkey", "nut", "Orange", "pear",
            "Queen", "rabbit", "Snake", "tree", "Umbrella", "violet", "Water", "Xylophone",
            "yak", "Zebra"
        };
        assertArrayEquals(expected, BucketSort.bucketSort(input));
    }
}
