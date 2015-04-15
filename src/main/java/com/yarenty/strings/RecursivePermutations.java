package com.yarenty.strings;

import java.util.ArrayList;

/**
 * Write a recursive function for generating all permutations of an input string. Return them as an array.
 * Don't worry about duplicates—assume every character is unique.
 * <p/>
 * Don't worry about time or space complexity—if we wanted efficiency we'd write an iterative version.
 * <p/>
 * Your function can have loops—it just needs to also be recursive.
 * <p/>
 * Created by yarenty on 15/04/15.
 */
public class RecursivePermutations {

    ArrayList<String> getPermutations(final String input) {

        if (input.length() <= 1) {
            final ArrayList out = new ArrayList<String>();
            out.add(input);
            return out;
        }

        final String allCharsExceptLast = input.substring(0, input.length() - 1);
        final String lastChar = input.substring(input.length() - 1);

        final ArrayList<String> intermediate = getPermutations(allCharsExceptLast);

        final int size = allCharsExceptLast.length() + 1;

        final ArrayList<String> out = new ArrayList<String>();
        String permutation;
        for (final String in : intermediate) {
            for (int position = 0; position < size; position++) {
                permutation = in.substring(0, position) + lastChar + in.substring(position);
                out.add(permutation);
            }
        }

        return out;

    }


    public static void main(final String[] args) {

        final ArrayList<String> out = new RecursivePermutations().getPermutations("cats");

        for (final String o : out) {
            System.out.println(o);
        }

    }


}
