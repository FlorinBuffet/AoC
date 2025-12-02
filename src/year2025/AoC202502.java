package year2025;

import utility.InputParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * This class solves Advent of Code 2025, Day 2.
 *
 * @author Florin Buffet
 * @version V1.0
 */
public class AoC202502 {
    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202502() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the days data as
     * @throws FileNotFoundException if the file is not found
     */
    private static List<String> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        List<String> data = InputParser.parseStringListPerLine(scanner, ",").getFirst();
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
        List<String> data = readFile(path);
        long sum = 0;
        for (String element : data) {
            int dash = element.indexOf('-');
            long start = Long.parseLong(element.substring(0, dash));
            long end = Long.parseLong(element.substring(dash + 1));
            for (long l = start; l <= end; l++) {
                if (!isIDValid(l)) {
                    sum += l;
                }
            }
        }
        return sum;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static long partTwo(String path) throws FileNotFoundException {
        List<String> data = readFile(path);
        long sum = 0;
        for (String element : data) {
            int dash = element.indexOf('-');
            long start = Long.parseLong(element.substring(0, dash));
            long end = Long.parseLong(element.substring(dash + 1));
            for (long l = start; l <= end; l++) {
                if (!isIDValidPartTwo(l)) {
                    sum += l;
                }
            }
        }
        return sum;
    }

    /**
     * This method checks if the given ID is valid.
     * As per the rule valid = not formed by repeating a sequence twice in a row.
     * @param id the ID to check
     * @return true if the ID is valid, false otherwise
     */
    private static boolean isIDValid(long id) {
        String s = String.valueOf(id);
        boolean isValid = true;
        int len = s.length();
        if (len % 2 == 0) {
            int half = len / 2;
            String halfA = s.substring(0, half);
            String halfB = s.substring(half);
            isValid = !halfA.equals(halfB);
        }
        return isValid;
    }

    /**
     * This method checks if the given ID is valid for part two.
     * As per the rule valid = not formed by repeating a sequence multiple times in a row.
     * @param id the ID to check
     * @return true if the ID is valid, false otherwise
     */
    private static boolean isIDValidPartTwo(long id) {
        String s = String.valueOf(id);
        int len = s.length();
        boolean isValid = true;
        for (int subLen = 1; subLen <= len / 2; subLen++) {
            if (len % subLen == 0) {
                String pattern = s.substring(0, subLen);
                boolean allEqual = true;
                for (int i = subLen; i < len; i += subLen) {
                    allEqual = s.substring(i, i + subLen).equals(pattern) && allEqual;
                }
                if (allEqual) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }
}