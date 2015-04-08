/**
 *
 */
package com.yarenty.list;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for recursive reverse method.
 *
 * @author yarenty
 */
public class SinglyLinkedListReverseRecursiveTest {


    final int BIG_LIST_SIZE = 1000;


    private SinglyLinkedList<Integer> listWithBigNumberOfIntElements() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        for (int i = 0; i < BIG_LIST_SIZE; i++) {
            list.add(i);
        }
        return list;
    }


    /**
     * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
     */
    @Test
    public final void withGivenEmptyListPerformRecursiveReverse() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        try {
            list.reverseRecursive();
        } catch (Exception e) {
            fail("Exception inside recursive reverse method");
        }
        assertEquals(0, list.size());
    }

    /**
     * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
     */
    @Test
    public final void withGivenOneElementListPerformRecursiveReverse() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>(1);
        try {
            list.reverseRecursive();
        } catch (Exception e) {
            fail("Exception inside recursive reverse method");
        }
        assertEquals(1, list.size());
        assertEquals(1, (int) list.get(0));

    }

    /**
     * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
     */
    @Test
    public final void withGivenTwoElementsListPerformRecursiveReverse() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>(1, 2);
        try {
            list.reverseRecursive();
        } catch (Exception e) {
            fail("Exception inside recursive reverse method");
        }

        assertEquals(2, (int) list.get(0));
        assertEquals(1, (int) list.get(1));
    }

    /**
     * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
     */
    @Test
    public final void withGivenThreeElementsListPerformRecursiveReverse() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>(1, 2, 3);
        try {
            list.reverseRecursive();
        } catch (Exception e) {
            fail("Exception inside recursive reverse method");
        }

        assertEquals(3, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
        assertEquals(1, (int) list.get(2));
    }

    /**
     * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
     */
    @Test
    public final void withGivenBigListPerformRecursiveReverse() {
        final SinglyLinkedList<Integer> list = listWithBigNumberOfIntElements();
        list.reverseRecursive();

        assertEquals(BIG_LIST_SIZE - 1, (int) list.get(0));
        assertEquals(0, (int) list.get(BIG_LIST_SIZE - 1));
    }


    /**
     * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
     */
    @Test
    public final void withGivenStringListPerformRecursiveReverse() {
        final SinglyLinkedList<String> list = new SinglyLinkedList<String>("a",
                "b", "c", "d");
        list.reverseRecursive();

        assertEquals("d", list.get(0));
        assertEquals("c", list.get(1));
        assertEquals("b", list.get(2));
        assertEquals("a", list.get(3));
    }

}
