package year2025;

import utility.InputParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This class solves Advent of Code 2025, Day 4.
 *
 * @author Florin Buffet
 */
public class AoC202508 {

    private static int lastXA;
    private static int lastXB;

    /**
     * Private constructor to prevent instantiation.
     */
    private AoC202508() {
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
        for (int i = 0; i < data.size(); i++) {
            List<Integer> thisItem = data.get(i);
            thisItem.add(i);
        }
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
        int runSize = data.size() >= 100 ? 1000 : 10;
        double lastDistance = -1.0;
        for (int i = 0; i < runSize; i++) {
            lastDistance = connectShortest(data, lastDistance);
        }
        Map<Integer, Integer> circuitSizes = new HashMap<>();
        for (List<Integer> item : data) {
            int circuit = item.getLast();
            circuitSizes.put(circuit, circuitSizes.getOrDefault(circuit, 0) + 1);
        }
        int maxCircuitSize = 0;
        int maxCircuitSize2 = 0;
        int maxCircuitSize3 = 0;
        for (int size : circuitSizes.values()) {
            if (size > maxCircuitSize) {
                maxCircuitSize3 = maxCircuitSize2;
                maxCircuitSize2 = maxCircuitSize;
                maxCircuitSize = size;
            } else if (size > maxCircuitSize2) {
                maxCircuitSize3 = maxCircuitSize2;
                maxCircuitSize2 = size;
            } else if (size > maxCircuitSize3) {
                maxCircuitSize3 = size;
            }
        }
        return maxCircuitSize * maxCircuitSize2 * maxCircuitSize3;
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
        double lastDistance = -1.0;
        boolean allZero;
        do {
            lastDistance = connectShortest(data, lastDistance);
            allZero = true;
            for (List<Integer> datum : data) {
                if (!datum.getLast().equals(0)) {
                    allZero = false;
                    break;
                }
            }
        } while (!allZero);
        return ((long) lastXA) * lastXB;
    }

    private static double connectShortest(List<List<Integer>> data, double lastDistance) {
        double shortestDistance = Double.POSITIVE_INFINITY;
        int itemAMin = -1;
        int itemBMin = -1;
        for (int i = 0; i < data.size(); i++) {
            List<Integer> itemA = data.get(i);
            for (int j = i + 1; j < data.size(); j++) {
                List<Integer> itemB = data.get(j);
                int dx = itemA.get(0) - itemB.get(0);
                int dy = itemA.get(1) - itemB.get(1);
                int dz = itemA.get(2) - itemB.get(2);
                double distance = Math.sqrt((double) dx * dx + (double) dy * dy + (double) dz * dz);
                if (distance < shortestDistance && distance > lastDistance) {
                    shortestDistance = distance;
                    itemAMin = i;
                    itemBMin = j;
                }
            }
        }
        List<Integer> itemA = data.get(itemAMin);
        List<Integer> itemB = data.get(itemBMin);
        int circuitA = itemA.getLast();
        int circuitB = itemB.getLast();
        lastXA = itemA.getFirst();
        lastXB = itemB.getFirst();
        int newCircuit = Math.min(circuitA, circuitB);
        for (List<Integer> item : data) {
            Integer c = item.getLast();
            if (c.equals(circuitA) || c.equals(circuitB)) {
                item.removeLast();
                item.add(newCircuit);
            }
        }
        return shortestDistance;
    }
}
