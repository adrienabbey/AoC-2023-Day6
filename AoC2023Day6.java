/* Advent of Code, Day 6: Wait For It
 * Adrien Abbey, Jan. 2024
 * Part One Solution: 219849
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class AoC2023Day6 {
    /* Global Variables */
    public static String inputFileName = "example-input.txt";
    public static boolean testing = false;
    public static boolean partTwo = true;

    public static void main(String[] args) throws FileNotFoundException {
        // Load the input into an array list of strings:
        ArrayList<String> inputStrings = loadInputStrings();

        // Create array lists to hold the time and distance numbers:
        ArrayList<Integer> timeIntegers = new ArrayList<>();
        ArrayList<Integer> distanceIntegers = new ArrayList<>();

        // Parse the input:
        for (String line : inputStrings) {
            if (line.contains("Time:")) {
                // Parse the time integers:
                timeIntegers = parseInts(line);
            }
            if (line.contains("Distance:")) {
                // Parse the distance integers:
                distanceIntegers = parseInts(line);
            }
        }

        // Track the final score:
        int finalScore = 0;

        // For Part One:
        if (!partTwo) {
            // Verify that the time and distance lists are the same length:
            if (timeIntegers.size() != distanceIntegers.size()) {
                // Something went wrong. Complain loudly and exit:
                System.err.println("Invalid input!");
                System.err.println("Found " + timeIntegers.size() + " time integers and " + distanceIntegers.size()
                        + " distance integers.");
                System.exit(1);
            }

            // Track the number of ways to win for each pair of numbers:
            ArrayList<Integer> totalWins = new ArrayList<>();

            // Calculate the number of ways to win each race:
            for (int i = 0; i < timeIntegers.size(); i++) {
                // For each time given, calculate the distance traveled for every
                // possible value. If that number surpasses the distance given,
                // increment the number of possible wins.

                // Track the number of possible wins for this race:
                int raceWins = 0;

                // For every possible (integer) length of time:
                for (int j = 0; j <= timeIntegers.get(i); j++) {
                    // The boat's speed starts at 0 mm/s, increasing by 1 mm/s for
                    // every ms. Find how far the boat travels for the remaining
                    // time. Then compare to see if that surpasses the previous
                    // score.

                    // Calculate the boat's speed and remaining time:
                    int speed = j * 1;
                    int remainingTime = timeIntegers.get(i) - j;

                    // Calculate the distance it travels:
                    int distance = speed * remainingTime;

                    // If this distance is greater than the goal, incremement the
                    // possible win counter:
                    if (distance > distanceIntegers.get(i)) {
                        raceWins += 1;
                    }
                }

                // Record this value for later:
                totalWins.add(raceWins);
            }

            // Calculate the final score:
            for (int raceWins : totalWins) {
                if (finalScore == 0 && raceWins > 0) {
                    finalScore = raceWins;
                } else {
                    finalScore *= raceWins;
                }
            }
        } else {
            // Part Two code.

            // Concactinate time and distance integers into a single integer:
            String timeString = "";
            String distanceString = "";
            for (int time : timeIntegers) {
                timeString = timeString + "" + time;
            }
            for (int distance : distanceIntegers) {
                distanceString = distanceString + "" + distance;
            }
            int totalTime = Integer.parseInt(timeString);
            int totalDistance = Integer.parseInt(distanceString);

            // For each possible time value, calculate the distance:
            for (int i = 0; i <= totalTime; i++) {
                // Calculate the speed achieved:
                int speed = i * 1;

                // Calculate the distance traveled:
                int timeRemaining = totalTime - i;
                int distance = speed * timeRemaining;

                // Determine if this surpasses the goal distance:
                if (distance > totalDistance) {
                    finalScore += 1;
                }
            }
        }

        // Print out the results:
        System.out.println("The final score is: " + finalScore);
    }

    public static ArrayList<String> loadInputStrings() throws FileNotFoundException {
        // Loads strings from the input file into an array list.

        // Open the input file:
        File inputFile = new File(inputFileName);
        Scanner inputScanner = new Scanner(inputFile);

        // Copy the input into an array list of strings:
        ArrayList<String> inputStrings = new ArrayList<>();
        while (inputScanner.hasNextLine()) {
            inputStrings.add(inputScanner.nextLine());
        }

        // Close the scanner:
        inputScanner.close();

        // Return the input strings:
        return inputStrings;
    }

    public static ArrayList<Integer> parseInts(String inputString) {
        // Parses a string, returning an array list of integers.

        // Split the input into usable parts:
        String[] firstSplit = inputString.split(":");
        String[] integerStrings = firstSplit[1].split(" ");

        // Create an array list of integers to return:
        ArrayList<Integer> integerArrayList = new ArrayList<>();

        // Convert strings into usable integers:
        for (String intString : integerStrings) {
            if (!intString.isEmpty()) {
                integerArrayList.add(Integer.parseInt(intString));
            }
        }

        // Test code:
        if (testing) {
            System.out.println(" Input string: " + inputString);
            System.out.println(" Found integers: " + integerArrayList.toString());
        }

        // Return the integer array list:
        return integerArrayList;
    }
}