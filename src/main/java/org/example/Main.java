package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        File output = new File("output");
        if (!output.exists()) {
            boolean created = output.mkdir();
            if (!created) return;
        }

        System.out.print("Listening to input[-file | -inline]: ");
        String[] input = scanner.nextLine().split(" ");
        program(input);
    }

    public static void program(String[] input) {
        if (Objects.equals(input[0], "-file")) {
            String outputPath;
            if (input.length >= 3) {
                outputPath = input[2];
            } else {
                String Uuid = UUID.randomUUID().toString();
                outputPath = "output/sorted_names_" + Uuid + ".txt";
            }
            fileMode(input[1], outputPath);
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
