package year2025;

import utility.InputParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class solves Advent of Code 2025, Day 4.
 *
 * @author Florin Buffet
 */
public class AoC202504 {
    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202504() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the days data as
     * @throws FileNotFoundException if the file is not found
     */
    private static char[][] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        char[][] data = InputParser.parseCharSquareMatrix(scanner);
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
    public static int partOne(String path) throws FileNotFoundException {
        char[][] data = readFile(path);
        return countAccessibleRolls(data, false);
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partTwo(String path) throws FileNotFoundException {
        int removedRolls = 0;
        int removedThisRound;
        char[][] data = readFile(path);
        do {
            removedThisRound = countAccessibleRolls(data, true);
            removedRolls += removedThisRound;
        } while (removedThisRound > 0);
        return removedRolls;
    }

    private static int countAccessibleRolls(char[][] data, boolean remove) {
        int rolls = 0;
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][j] == '@') {
                    int neighbours = 0;
                    for (int[] dir : directions) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if (ni >= 0 && ni < data.length && nj >= 0 && nj < data.length && data[ni][nj] == '@') {
                            neighbours++;
                        }
                    }
                    if (neighbours < 4) {
                        rolls++;
                        if (remove) {
                            data[i][j] = '.';
                        }
                    }
                }
            }
        }
        return rolls;
    }
}
