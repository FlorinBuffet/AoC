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
public class AoC202505 {
    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202505() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the days data as
     * @throws FileNotFoundException if the file is not found
     */
    private static Object[] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        List<String> data = InputParser.parseStringList(scanner);
        scanner.close();
        int empty = data.indexOf("");
        long[][] matrix = new long[empty][2];
        for (int i = 0; i < empty; i++) {
            String[] parts = data.removeFirst().split("-");
            matrix[i][0] = Long.parseLong(parts[0]);
            matrix[i][1] = Long.parseLong(parts[1]);
        }
        data.removeFirst();
        return new Object[]{matrix, data};
    }

    /**
     * Calculates the result for the first part of the challenge.
     *
     * @param path path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partOne(String path) throws FileNotFoundException {
        Object[] result = readFile(path);
        long[][] fresh = (long[][]) result[0];
        List<String> data = (List<String>) result[1];
        int freshCounter = 0;
        for (String s : data) {
            long thsIngredient = Long.parseLong(s);
            for (long[] thisFresh : fresh) {
                if (thsIngredient >= thisFresh[0] && thsIngredient <= thisFresh[1]) {
                    freshCounter++;
                    break;
                }
            }
        }
        return freshCounter;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static long partTwo(String path) throws FileNotFoundException {
        Object[] result = readFile(path);
        long[][] fresh = (long[][]) result[0];
        java.util.Arrays.sort(fresh, java.util.Comparator.comparingLong(a -> a[0]));

        long total = 0;
        long curStart = fresh[0][0];
        long curEnd = fresh[0][1];

        for (int i = 1; i < fresh.length; i++) {
            long start = fresh[i][0];
            long end = fresh[i][1];
            if (start <= curEnd + 1) { //overlap or adjacent
                curEnd = Math.max(curEnd, end);
            } else { // no overlap
                total += (curEnd - curStart + 1);
                curStart = start;
                curEnd = end;
            }
        }
        total += (curEnd - curStart + 1);
        return total;
    }
}
