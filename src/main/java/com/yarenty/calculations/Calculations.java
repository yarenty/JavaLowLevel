package com.yarenty.calculations;

/**
 * Write a function that given an input array of integers with 4 entries and a
 * desired targetSum returns the number of combinations that add up to that
 * targetSum
 * int a= calculateCombinations(new int[] {5, 5, 15, 10}, 15);
 * should return 3 namely;
 * 15
 * 5+10
 * 5+10
 * Write the function and a FULL set of unit tests.
 *
 * @author yarenty@gmail.com
 */
public class Calculations {

    // int a= calculateCombinations(new int[] {5, 5, 15, 10}, 15);
    // 15 <-single
    // 5+10 <- first 5 + 10
    // 5+10 <- second 5 + 10

    public static int calculateCombinations(int[] array, int targetSum) {
        int combinations = 0;
        int position = 1;

        for (int x : array) {
            if (x == targetSum)
                combinations++; // single

            for (int y = position; y < array.length; y++) {
                if ((x + array[y]) == targetSum)
                    combinations++; // first // second

            }
            position++;
        }

        return combinations;
    }

}
