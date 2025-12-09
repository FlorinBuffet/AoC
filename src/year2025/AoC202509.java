package year2025;

import utility.InputParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * This class solves Advent of Code 2025, Day 4.
 *
 * @author Florin Buffet
 */
public class AoC202509 {
    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202509() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the days data as
     * @throws FileNotFoundException if the file is not found
     */
    private static List<List<Integer>> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        List<List<Integer>> data = InputParser.parseIntegerListPerLine(scanner, ",");
        scanner.close();
        return data;
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static long partOne(String path) throws FileNotFoundException {
        List<List<Integer>> data = readFile(path);
        long maxArea = 0;
        for (int i = 0; i < data.size(); i++) {
            for (List<Integer> datum : data) {
                int xDist = Math.abs(data.get(i).get(0) - datum.get(0)) + 1;
                int yDist = Math.abs(data.get(i).get(1) - datum.get(1)) + 1;
                maxArea = Math.max(maxArea, ((long) xDist) * yDist);
            }
        }
        return maxArea;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static long partTwo(String path) throws FileNotFoundException {
        List<List<Integer>> data = readFile(path);
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (List<Integer> datum : data) {
            minX = Math.min(minX, datum.get(0));
            maxX = Math.max(maxX, datum.get(0));
            minY = Math.min(minY, datum.get(1));
            maxY = Math.max(maxY, datum.get(1));
        }
        char[][] grid = new char[maxX - minX + 1][maxY - minY + 1];
        return 0;
    }
}
