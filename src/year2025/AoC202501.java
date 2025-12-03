package year2025;

import utility.InputParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * This class solves Advent of Code 2025, Day 1.
 *
 * @author Florin Buffet
 */
public class AoC202501 {

    private static final int DIAL_START_POSITION = 50;

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202501() {
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
        List<String> data = InputParser.parseStringList(scanner);
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
        List<String> data = readFile(path);
        int counter = 0;
        int dial = DIAL_START_POSITION;
        for (String line : data) {
            if (line.charAt(0) == 'L') {
                dial -= Integer.parseInt(line.substring(1));
            } else if (line.charAt(0) == 'R') {
                dial += Integer.parseInt(line.substring(1));
            }
            dial = (dial % 100 + 100) % 100;
            if (dial == 0) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partTwo(String path) throws FileNotFoundException {
        List<String> data = readFile(path);
        int counter = 0;
        int dial = DIAL_START_POSITION;
        for (String line : data) {
            char dir = line.charAt(0);
            int dist = Integer.parseInt(line.substring(1));
            if (dir == 'L') {
                if (dial > 0 && dist >= dial) {
                    counter += 1 + (dist - dial) / 100;
                } else if (dial == 0 && dist >= 100) {
                    counter += dist / 100;
                }
                dial = ((dial - dist) % 100 + 100) % 100;
            } else if (dir == 'R') {
                if (dial > 0) {
                    int firstNeeded = 100 - dial;
                    if (dist >= firstNeeded) {
                        counter += 1 + (dist - firstNeeded) / 100;
                    }
                } else if (dial == 0 && dist >= 100) {
                    counter += dist / 100;
                }
                dial = ((dial + dist) % 100 + 100) % 100;
            }
        }
        return counter;
    }
}