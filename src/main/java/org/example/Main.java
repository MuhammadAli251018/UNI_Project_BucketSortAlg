package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static void main() {
        while (true) {
            System.out.print("Listening to input[-file | -inline]: ");
            Scanner scanner = new Scanner(System.in);
            String[] input = scanner.nextLine().split(" ");

            program(input);
        }
    }

    public static void program(String[] input) {
        if (Objects.equals(input[0], "-file")) {
            fileMode(input[1], input[2]);
        } else if (Objects.equals(input[0], "-inline")) {
            String[] names = Arrays.copyOfRange(input, 1, input.length);
            inlineMode(names);
        } else {
            System.out.println("Invalid input");
        }
    }

    private static void fileMode(String filePath, String outputFilePath) {
        System.out.println("Reading file: " + filePath);
        String[] names = extractNamesFromFile(filePath);
        String[] sortedNames = BucketSort.bucketSort(names);
        writeNamesToFile(sortedNames, outputFilePath);
        System.out.println("Sorted names written to: " + outputFilePath);
    }

    private static void inlineMode(String[] names) {
        String[] sortedNames = BucketSort.bucketSort(names);
        System.out.println("Sorted names: " + Arrays.toString(sortedNames));
    }

    private static String[] extractNamesFromFile(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + path, e);
        }
    }

    private static void writeNamesToFile(String[] names, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String name : names) {
                writer.write(name);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file", e);
        }
    }
}
