package com.yarenty.set;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * OUTPUT: <pre>
 * SET:[4, 5, 6, 7, 8, 9, 10]
 * ITER:
 * 4 5 6 7 8 9 10
 * FOREACH:
 * 4 5 6 7 8 9 10
 * </pre>
 *
 * @author yarenty
 */
public class ArraysToSet {


    final Integer[] numbers = {7, 7, 8, 9, 10, 8, 8, 9, 6, 5, 4};

    @Test
    public void testSet() {
        final Set<Integer> set = new HashSet<Integer>(Arrays.asList(numbers));
        System.out.println("SET:" + set.toString());

        System.out.println("ITER:");
        for (final Iterator<Integer> iter = set.iterator(); iter.hasNext(); ) {

            final Integer i = iter.next();
            System.out.print(i + " ");
        }

        System.out.println("\nFOREACH:");
        for (final Integer integer : set) {
            System.out.print(integer + " ");
        }

    }

}
