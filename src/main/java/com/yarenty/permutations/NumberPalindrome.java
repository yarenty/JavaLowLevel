package com.yarenty.permutations;

/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers
 * is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 * Created by yarenty on 01/04/15.
 */
public class NumberPalindrome {


    static boolean isPalindrome(final int number) {
        //final String in = String.valueOf(number);
        //final String out = new StringBuilder(in).reverse().toString();


        return number == Integer.parseInt(new StringBuilder(String.valueOf(number)).reverse().toString());
    }

    public static void main(final String[] args) {
        int biggest = 0;
        int ox = 0, oy = 0;
        for (int x = 999; x > 100; x--) {
            for (int y = 999; y > 100; y--) {
                if (isPalindrome(x * y)) {
                    System.out.println(" X * Y = ( " + x + " * " + y + " ) = " + (x * y));
                    if ((x * y) > biggest) {
                        biggest = x * y;
                        ox = x;
                        oy = y;
                    }
                    //return;
                }
            }

        }

        System.out.println(" BIGGEST PALINDROME of 3 digits number is:  X * Y = ( " + ox + " * " + oy + " ) = " + biggest);
    }

}
