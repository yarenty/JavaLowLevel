package com.yarenty.list;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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


    public void testNullPointer() {
        try {
            withNull.sort();
            fail();
        } catch (NullPointerException expected) {
        }
    }
}
