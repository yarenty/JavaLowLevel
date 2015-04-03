package com.yarenty.list;

import junit.framework.TestCase;

import java.util.*;

import static com.yarenty.list.Common.t;

public class MergeSortTestCase extends TestCase {

    final SinglyLinkedList<Integer> empty = new SinglyLinkedList<Integer>();
    final SinglyLinkedList<Integer> one = new SinglyLinkedList<Integer>(1);
    final SinglyLinkedList<Integer> two = new SinglyLinkedList<Integer>(1, 2);
    final SinglyLinkedList<Integer> two2 = new SinglyLinkedList<Integer>(2, 1);
    final SinglyLinkedList<Integer> three = new SinglyLinkedList<Integer>(4, 4, 4);
    final SinglyLinkedList<Integer> sorted = new SinglyLinkedList<Integer>(1, 2, 3);
    final SinglyLinkedList<Integer> someList = new SinglyLinkedList<Integer>(1000, 4, 2, 1, 3);
    final SinglyLinkedList<Integer> withNull = new SinglyLinkedList<Integer>(1000, null, 2, 1, 3);


    @SuppressWarnings("unchecked")
    final
    SinglyLinkedList<SinglyLinkedList<Integer>> allLists = new SinglyLinkedList<SinglyLinkedList<Integer>>(
            empty, one, two, two2, three, sorted, someList
    );

    public void testSort() {
        /*
        t(two.sort(), Arrays.asList(1, 2));
		t(two2.sort(), Arrays.asList(1, 2));
		t(three.sort(), Arrays.asList(4, 4, 4));
		t(sorted.sort(), Arrays.asList(1, 2, 3));
		t(someList.sort(), Arrays.asList(1, 2, 3, 4, 1000));
		*/
        for (final SinglyLinkedList<Integer> list : allLists) {
            final List<Integer> copy = new ArrayList<Integer>(list);
            Collections.sort(copy);
            list.sort();
            t(list, copy);
        }
    }

    public void testParallelSort() {
        for (final SinglyLinkedList<Integer> list : allLists) {
            final List<Integer> copy = new ArrayList<Integer>(list);
            Collections.sort(copy);
            t(list.parallelSort(), copy);
        }
    }


    public void testSublistSort() {
        final SinglyLinkedList<Integer> subway = someList.subList(1, 4);
        t(subway.size() == 3);
        subway.sort();
        t(subway, Arrays.asList(1, 2, 4));
        t(someList, Arrays.asList(1000, 1, 2, 4, 3));
        //
        final SinglyLinkedList<Integer> sub2 = someList.subList(0, 1);
        sub2.remove(0);
        try {
            subway.checkInvariants(3);
            fail();
        } catch (ConcurrentModificationException expected) {
        }
    }

    public void testSublistParallelSort() {
        final SinglyLinkedList<Integer> subway = someList.subList(1, 4);
        t(subway.size() == 3);
        t(subway.parallelSort(), Arrays.asList(1, 2, 4));
        t(someList, Arrays.asList(1000, 1, 2, 4, 3));
        //
        final SinglyLinkedList<Integer> sub2 = someList.subList(0, 1);
        sub2.remove(0);
        try {
            subway.checkInvariants(3);
            fail();
        } catch (ConcurrentModificationException expected) {
        }
    }

    public void testNullPointer() {
        try {
            withNull.sort();
            fail();
        } catch (NullPointerException expected) {
        }
    }
}
