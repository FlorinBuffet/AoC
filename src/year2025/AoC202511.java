package year2025;

import utility.InputParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * This class solves Advent of Code 2025, Day 11.
 *
 * @author Florin Buffet
 */
public class AoC202511 {

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202511() {
    }

    /**
     * Reads the input file and returns the data for the day's challenge.
     *
     * @param path the path to the input file
     * @return the days data as
     * @throws FileNotFoundException if the file is not found
     */
    private static List<List<String>> readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        List<List<String>> data = InputParser.parseStringListPerLine(scanner, " ", ":");
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
        List<List<String>> data = readFile(path);
        return dfs("you", data, new HashSet<>(), new HashSet<>());
    }

    /**
     * Calculates the result for the second part of the challenge.
     *
     * @param path the path to the input file
     * @return the result as an integer.
     * @throws FileNotFoundException if the file is not found
     */
    public static int partTwo(String path) throws FileNotFoundException {
        List<List<String>> data = readFile(path);
        return dfs("svr", data, new HashSet<>(), new HashSet<>(List.of("dac", "fft")));
    }

    private static int dfs(String currentNode, List<List<String>> data, Set<String> visited, Set<String> mustVisit) {
        if ("out".equals(currentNode)) {
            for (String node : mustVisit) {
                if (!visited.contains(node)) {
                    return 0;
                }
            }
            return 1;
        }
        visited.add(currentNode);
        for (List<String> edge : data) {
            if (currentNode.equals(edge.getFirst())) {
                int counter = 0;
                for (int i = 1; i < edge.size(); i++) {
                    String neighbor = edge.get(i);
                    if (!visited.contains(neighbor)) {
                        counter += dfs(neighbor, data, new HashSet<>(visited), mustVisit);
                    }
                }
                return counter;
            }
        }
        return 0;
    }
}
