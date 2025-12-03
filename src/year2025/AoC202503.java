package year2025;

import utility.InputParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * This class solves Advent of Code 2025, Day 3.
 *
 * @author Florin Buffet
 */
public class AoC202503 {
    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202503() {
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
        List<List<Integer>> data = InputParser.parseIntegerListPerLine(scanner, "");
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
        List<List<Integer>> data = readFile(path);
        int sum = 0;
        for (List<Integer> bank : data) {
            sum += findHighestPowerInBank(bank);
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
        List<List<Integer>> data = readFile(path);
        long sum = 0;
        for (List<Integer> bank : data) {
            sum += findHighestPowerInBankPartTwo(bank);
        }

        return sum;
    }

    /**
     * Finds the highest power that can be formed from the bank for part one.
     *
     * @param bank the list of integers representing the bank
     * @return the highest power as an integer
     */
    private static int findHighestPowerInBank(List<Integer> bank) {
        int maxPower = 0;
        for (int i = 0; i < bank.size(); i++) {
            for (int j = i + 1; j < bank.size(); j++) {
                int power = 10 * bank.get(i) + bank.get(j);
                if (power > maxPower) {
                    maxPower = power;
                }
            }
        }
        return maxPower;
    }

    /**
     * Finds the highest power that can be formed from the bank for part two. This is a greedy algorithm.
     *
     * @param bank the list of integers representing the bank
     * @return the highest power as a long integer
     */
    private static long findHighestPowerInBankPartTwo(List<Integer> bank) {
        final int battery_limit = 12;
        int n = bank.size();
        int start = 0;
        long result = 0L;
        for (int pick = 0; pick < battery_limit; pick++) {
            int last = n - (battery_limit - pick);
            int bestDigit = -1;
            int bestPos = start;
            for (int pos = start; pos <= last; pos++) {
                int d = bank.get(pos);
                if (d > bestDigit) {
                    bestDigit = d;
                    bestPos = pos;
                }
            }
            result = result * 10L + bestDigit;
            start = bestPos + 1;
        }
        return result;
    }
}
