package com.yarenty.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Input:  two lists of ints - sorted.
 * Argument is k - Kth specifies element which should greatest from top to down.
 * Provide algorithm for that.
 *
 * @author yarenty
 */
public class KthGreatestOfTwoArrays {

    public static void main(final String[] args) {
        final Integer l1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final Integer l2[] = {3, 7, 8, 10, 11, 12, 13};

        final int k = 6;

        int out;
        out = usingLists(l1, l2, k);
        System.out.println("LIST:  " + k + "-th greatest element from top backward is " + out);
        out = superSpeed(l1, l2, k);
        System.out.println("SuperSpeed " + k + "-th greatest element from top backward is " + out);
        out = usingSet(l1, l2, k);
        System.out.println("SET  " + k + "-th greatest element from top backward is " + out);


    }

    /**
     * Super speed - traversing through 2 arrays in same time.
     *
     * @param l1 - list 1
     * @param l2 - list 2
     * @param k  - kth element
     * @return element
     */
    private static int superSpeed(final Integer[] l1, final Integer[] l2, final int k) {
        int x1 = l1.length - 1;
        int x2 = l2.length - 1;

        for (int i = 1; i < k; i++) {
            if (l1[x1] > l2[x2]) {
                x1--;
            } else {
                x2--;
            }
        }

        return (l1[x1] > l2[x2]) ? l1[x1] : l2[x2];
    }

    /**
     * Simple solution using list  - copy them into new list, then sort and get kth.
     *
     * @param l1 - list 1
     * @param l2 - list 2
     * @param k  - kth element
     */
    public static int usingLists(final Integer[] l1, final Integer[] l2, final int k) {
        final ArrayList<Integer> a = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(l1, l1.length - k, l1.length)));
        a.addAll(Arrays.asList(Arrays.copyOfRange(l2, l2.length - k, l2.length)));

        Collections.sort(a);

        return a.get(a.size() - k);


    }


    /**
     * Try with set.
     * Not working!! as no duplicates!!
     *
     * @param l1 - list 1
     * @param l2 - list 2
     * @param k  - kth element
     */
    public static int usingSet(final Integer[] l1, final Integer[] l2, final int k) {

        final TreeSet<Integer> ts = new TreeSet<>(Arrays.asList(Arrays.copyOfRange(l1, l1.length - k, l1.length)));
        ts.addAll(Arrays.asList(Arrays.copyOfRange(l2, l2.length - k, l2.length)));

        for (int x = 1; x < k; x++) {
            ts.pollLast();
        }
        return ts.last();
    }

}
