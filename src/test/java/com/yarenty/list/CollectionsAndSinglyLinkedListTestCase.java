package com.yarenty.list;

/**
 * Created by yarenty on 04/02/2015.
 */

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CollectionsAndSinglyLinkedListTestCase extends TestCase {


    final SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
    final List<Integer> sample = Arrays.asList(10, 11, 12);

    @Override
    public void setUp() {
        for (int i = 0; i < 10000; ++i)
            list.add(i);
    }


    public void testReverse() {
        final SinglyLinkedList<Integer> other = list.clone();
        other.reverse();
        Collections.reverse(list);
        Common.t(other, list);
    }


    void out(final Object o) {
        System.out.println(o);
    }

}
