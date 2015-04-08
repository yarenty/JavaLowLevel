package com.yarenty;

import java.util.Arrays;

/**
 * You have an array of integers, and for each index you want to find the product of every integer except the integer at that index.
 * Write a function get_products_of_all_ints_except_at_index() that takes an array of integers and returns an array of the products.
 * <p/>
 * For example, given:
 * <p/>
 * [1, 7, 3, 4]
 * your function would return:
 * <p/>
 * [84, 12, 28, 21]
 * by calculating:
 * <p/>
 * [7*3*4, 1*3*4, 1*7*4, 1*7*3]
 */


public class ProductsExceptAtIndex {

    private static final int a[] = {1, 7, 3, 4};

    public static void main(final String[] args) {

        System.out.println(Arrays.toString(a));

        System.out.println("=====");
        System.out.println(Arrays.toString(get_products_of_all_ints_except_at_index(a)));
        System.out.println("=====");
        System.out.println(Arrays.toString(muchBetter(a)));

    }


    public static int[] get_products_of_all_ints_except_at_index(final int[] a) {

        final int out[] = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = 1;
        }

        for (int i = 0; i < a.length; i++) {

            for (int j = 0; j < a.length; j++) {
                if (i != j) {
                    out[j] *= a[i];
                }
            }

        }

        return out;
    }


    /**
     * We'll calculate products_of_all_ints_before_index as:
     * <p/>
     * 2, 4, 10
     * 1, 2, 8
     * <p/>
     * And we'll calculate products_of_all_ints_after_index as:
     * <p/>
     * 2, 4, 10
     * 40, 10, 1
     * If we take these arrays and multiply the integers at the same indices, we get:
     * <p/>
     * 1, 2, 8
     * 40, 10, 1
     * 40, 20, 8
     * And this gives us what we're looking for—the products of all the integers except the integer at each index.
     */
    public static int[] muchBetter(final int[] a) {

        final int out[] = new int[a.length];

        int prod = 1;

        for (int i = 0; i < a.length; i++) {

            out[i] = prod;
            prod *= a[i];
        }

        prod = 1;

        for (int i = a.length - 1; i >= 0; i--) {

            out[i] *= prod;
            prod *= a[i];
        }

        return out;
    }


}
